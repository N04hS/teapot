package at.fhv.sysarch.lab1.pipeline.filters;

import at.fhv.sysarch.lab1.obj.Face;
import com.hackoeur.jglm.Mat4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DepthSortFilter implements IFilter<Face> {
    private Pipe successor = null;
    private static List<Face> allFaces = new ArrayList<>();
    public void setSuccessor(Pipe pipe) { this.successor = pipe; }

    public void write(Face f, Container c) {
        if (f == null){
            allFaces.sort(new Comparator<Face>() {
                @Override
                public int compare(Face f1, Face f2) {
                    return Float.compare(((f1.getV1().getZ() + f1.getV2().getZ() +  f1.getV3().getZ()) / 3) -
                            ((f2.getV1().getZ() + f2.getV2().getZ() +  f2.getV3().getZ()) / 3),
                            0f);
                }
            });
            allFaces.forEach(face -> successor.write(face, c));
            allFaces.clear();
        } else {
            allFaces.add(f);
        }
    }
}
