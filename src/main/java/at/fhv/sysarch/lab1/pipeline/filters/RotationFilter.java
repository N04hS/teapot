package at.fhv.sysarch.lab1.pipeline.filters;

import at.fhv.sysarch.lab1.obj.Face;
import com.hackoeur.jglm.Mat4;

public class RotationFilter implements IFilter<Face> {
	private Pipe successor = null;

	public void setSuccessor(Pipe pipe) { this.successor = pipe; }

	public void write(Face f, Container c) {
		Mat4 rM = c.rotMat;
		if (f == null)
			successor.write(null, c);
		else
			successor.write(new Face(rM.multiply(f.getV1()), rM.multiply(f.getV2()), rM.multiply(f.getV3()),
					rM.multiply(f.getN1()), rM.multiply(f.getN2()), rM.multiply(f.getN3())), c);
	}
}
