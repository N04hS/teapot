package at.fhv.sysarch.lab1.pipeline.filters;

import at.fhv.sysarch.lab1.obj.Face;
import com.hackoeur.jglm.Mat4;

public class RotationFilter implements IFilter<Face> {
	private Pipe successor = null;

	public void setSuccessor(Pipe pipe) { this.successor = pipe; }

	public void write(Face f, Container c) {
		Mat4 rM = c.rotMat;
		Face newFace = new Face(rM.multiply(f.getV1()), rM.multiply(f.getV2()), rM.multiply(f.getV3()), f);
		successor.write(newFace, c);
	}
}
