package at.fhv.sysarch.lab1.pipeline.filters;

import at.fhv.sysarch.lab1.obj.Face;
import at.fhv.sysarch.lab1.pipeline.filters.base.Container;
import at.fhv.sysarch.lab1.pipeline.filters.base.IFilter;
import at.fhv.sysarch.lab1.pipeline.filters.base.Pipe;
import com.hackoeur.jglm.Vec3;

public class BackfaceCullingFilter implements IFilter<Face> {
    private Pipe<Face> successor = null;
    private Pipe<Face> forerunner = null;
    private final Vec3 camera;

    public BackfaceCullingFilter(Vec3 camera){
        this.camera = camera.getUnitVector();
    }

    public void setSuccessor(Pipe pipe) {
        successor = pipe;
    }
    public void setForerunner(Pipe pipe) {
        forerunner = pipe;
    }

    public void write(Face f) {
        Face face = process(f);
        if (face == null)
            successor.write(null);
        else if (face.getV1() != null)
            successor.write(f);
    }

    public Face read() {
        Face in = forerunner.read();

        Face processed = process(in);

        if (processed == null)
            return null;

        while (processed.getV1() == null) {
            processed = process(forerunner.read());
            if (processed == null)
                return null;
        }

        return processed;
    }

    public Face process(Face f) {
        if (f == null)
            return null;
        else {
            float culling = camera.dot(f.getV1().toVec3().getUnitVector());
            if (culling > 0f)
                return f;
            else
                return new Face(null, null, null, null, null, null);
        }
    }
}
