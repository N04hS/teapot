package at.fhv.sysarch.lab1.pipeline.filters;

import at.fhv.sysarch.lab1.obj.Face;
import at.fhv.sysarch.lab1.pipeline.filters.base.Container;
import at.fhv.sysarch.lab1.pipeline.filters.base.IFilter;
import at.fhv.sysarch.lab1.pipeline.filters.base.Pipe;
import com.hackoeur.jglm.Mat4;

public class ViewTransformFilter implements IFilter<Face> {
	private Pipe<Face> successor = null;
	private Pipe<Face> forerunner = null;
	private final Mat4 vp;

	public ViewTransformFilter(Mat4 viewProj){
		vp = viewProj;
	}
	public void setSuccessor(Pipe pipe) {
		this.successor = pipe;
	}
	public void setForerunner(Pipe pipe) { this.forerunner = pipe; }

	public void write(Face f) {
		successor.write(process(f));
	}

	public Face read() {
		return process(forerunner.read());
	}

	public Face process(Face f) {
		if (f == null)
			return null;
		else
			return new Face(vp.multiply(f.getV1()), vp.multiply(f.getV2()), vp.multiply(f.getV3()), f);
	}
}
