package at.fhv.sysarch.lab1.pipeline.filters;

import at.fhv.sysarch.lab1.obj.Face;
import com.hackoeur.jglm.Mat4;

public class ViewTransformFilter implements IFilter<Face> {
	private Pipe<Face> successor = null;
	private final Mat4 vp;

	public ViewTransformFilter(Mat4 viewProj){
		vp = viewProj;
	}
	public void setSuccessor(Pipe pipe) {
		this.successor = pipe;
	}

	public void write(Face f, Container c) {
		if (f == null) successor.write(null, c);
		else{
			Face face = new Face(vp.multiply(f.getV1()), vp.multiply(f.getV2()), vp.multiply(f.getV3()), f);
			successor.write(face, c);

		}
	}
}
