package at.fhv.sysarch.lab1.pipeline.filters;

import at.fhv.sysarch.lab1.obj.Face;
import at.fhv.sysarch.lab1.pipeline.filters.base.Container;
import at.fhv.sysarch.lab1.pipeline.filters.base.IFilter;
import at.fhv.sysarch.lab1.pipeline.filters.base.Pipe;
import com.hackoeur.jglm.Vec3;

public class BackfaceCullingFilter implements IFilter<Face> {
    private Pipe successor = null;
    private Container container = null;
    private final Vec3 camera;

    public BackfaceCullingFilter(Vec3 camera){
        this.camera = camera.getUnitVector();
    }

    public void setSuccessor(Pipe pipe) {
        this.successor = pipe;
    }
    public void setForerunner(Pipe pipe) {
        // NOT IMPLEMENTED
    }

    public void write(Face f, Container c) {
        if (f == null) successor.write(null, c);
        else {
            float  culling = camera.dot(f.getV1().toVec3().getUnitVector());
            if (culling > 0f)
                successor.write(f, c);
        }
    }

    public Face read() {
       // NOT IMPLEMENTED
        return null;
    }

    public Face process(Face f) {
        // NOTE IMPLEMENTED
        return null;
    }
}
