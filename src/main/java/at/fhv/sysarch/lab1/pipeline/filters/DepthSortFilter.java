package at.fhv.sysarch.lab1.pipeline.filters;

import at.fhv.sysarch.lab1.obj.Face;
import at.fhv.sysarch.lab1.pipeline.filters.base.Container;
import at.fhv.sysarch.lab1.pipeline.filters.base.IFilter;
import at.fhv.sysarch.lab1.pipeline.filters.base.Pipe;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DepthSortFilter implements IFilter<Face> {
    private Pipe<Face> successor = null;
    private Pipe<Face> forerunner = null;
    private static List<Face> allFaces = new ArrayList<>();
    private static int p = 0;

    public void setSuccessor(Pipe pipe) { successor = pipe; }
    public void setForerunner(Pipe pipe) { forerunner = pipe; }

    public void write(Face f) {
        if (f == null){
            allFaces.sort((f1, f2) -> Float.compare(((f1.getV1().getZ() + f1.getV2().getZ() +  f1.getV3().getZ()) / 3) -
                    ((f2.getV1().getZ() + f2.getV2().getZ() +  f2.getV3().getZ()) / 3),
                    0f));
            allFaces.forEach(face -> successor.write(face));
            allFaces.clear();
        } else {
            allFaces.add(f);
        }
    }

    public Face read() {
        if (p > 0) {
            p--;
            return allFaces.remove(0);
        }
        else {
            /* collect all faces */
            p=0;
            Face f = forerunner.read();
            while (f != null) {
                allFaces.add(f);
                p++;
                f = forerunner.read();
            }
            /* sort all faces */
            allFaces.sort((f1, f2) -> Float.compare(((f1.getV1().getZ() + f1.getV2().getZ() +  f1.getV3().getZ()) / 3) -
                    ((f2.getV1().getZ() + f2.getV2().getZ() +  f2.getV3().getZ()) / 3), 0f));

            /* add null face as end of frame marker */
            allFaces.add(f);
            p++;

            return read();
        }
    }
}
