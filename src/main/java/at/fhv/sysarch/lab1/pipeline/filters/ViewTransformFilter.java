package at.fhv.sysarch.lab1.pipeline.filters;

import at.fhv.sysarch.lab1.obj.Face;
import com.hackoeur.jglm.Mat4;

public class ViewTransformFilter implements IFilter<Face> {
	private Pipe successor = null;

	public void setSuccessor(Pipe pipe) {
		this.successor = pipe;
	}

	public void write(Face f, Container c) {
		Mat4 vTM = c.viewMat;
		Face newFace = new Face(vTM.multiply(f.getV1()), vTM.multiply(f.getV2()), vTM.multiply(f.getV3()), f);
		successor.write(newFace, c);
	}
}
