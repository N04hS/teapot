package at.fhv.sysarch.lab1.pipeline.filters;

import at.fhv.sysarch.lab1.obj.Face;
import com.hackoeur.jglm.Vec4;

public class MoveFilter implements IFilter<Face> {
    private Pipe<Face> successor = null;

    private final int deltaX;
    private final int deltaY;

    public MoveFilter(int viewWidth, int viewHeight) {
        this.deltaX = viewWidth / 3;
        this.deltaY = viewHeight / 3;
    }

    public void setSuccessor(Pipe pipe) { this.successor = pipe; }
    public void write(Face f, Container c) {
        if (f == null) successor.write(null, c);
        else {
            Face face = new Face(
                    f.getV1().add(new Vec4(deltaX, deltaY, 0, 0)),
                    f.getV2().add(new Vec4(deltaX, deltaY, 0, 0)),
                    f.getV3().add(new Vec4(deltaX, deltaY, 0, 0)),
                    f);
            successor.write(face, c);
        }
    }
}
