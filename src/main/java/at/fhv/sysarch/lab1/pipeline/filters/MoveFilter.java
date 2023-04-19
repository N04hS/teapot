package at.fhv.sysarch.lab1.pipeline.filters;

import at.fhv.sysarch.lab1.obj.Face;
import at.fhv.sysarch.lab1.pipeline.filters.base.Container;
import at.fhv.sysarch.lab1.pipeline.filters.base.IFilter;
import at.fhv.sysarch.lab1.pipeline.filters.base.Pipe;
import com.hackoeur.jglm.Vec4;

public class MoveFilter implements IFilter<Face> {
    private Pipe<Face> successor = null;
    private Pipe<Face> forerunner = null;

    private final int deltaX;
    private final int deltaY;

    public MoveFilter(int viewWidth, int viewHeight) {
        this.deltaX = (int) (viewWidth / 1.9);
        this.deltaY = (int) (viewHeight / 1.25);
    }

    public void setSuccessor(Pipe pipe) { this.successor = pipe; }
    public void setForerunner(Pipe pipe) { this.forerunner = pipe; }

    public void write(Face f) {
        successor.write(process(f));
    }

    public Face read() {
        return process(forerunner.read());
    }

    public Face process(Face f) {
        if (f == null) return null;
        else
            return new Face(
                    f.getV1().add(new Vec4(deltaX, deltaY, 0, 0)),
                    f.getV2().add(new Vec4(deltaX, deltaY, 0, 0)),
                    f.getV3().add(new Vec4(deltaX, deltaY, 0, 0)),
                    f);
    }
}
