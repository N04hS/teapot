package at.fhv.sysarch.lab1.pipeline.filters;

import at.fhv.sysarch.lab1.obj.Face;
import at.fhv.sysarch.lab1.pipeline.filters.base.Container;
import at.fhv.sysarch.lab1.pipeline.filters.base.IFilter;
import at.fhv.sysarch.lab1.pipeline.filters.base.Pipe;

public class AngleTransformFilter implements IFilter<Face> {
	private Pipe<Face> successor = null;
	private Pipe<Face> forerunner = null;

	public void setSuccessor(Pipe pipe) { this.successor = pipe; }
	public void setForerunner(Pipe pipe) { this.forerunner = pipe; }

	public void write(Face f, Container c) {
		if (f == null) successor.write(null, c);
		else {
			Face face = new Face(f.getV1().getNegated(), f.getV2().getNegated(), f.getV3().getNegated(), f);
			successor.write(face, c);
		}
	}

	public Face read() {
		// NOT IMPLEMENTED
		return null;
	}

	public Face process(Face f) {
		// NOT IMPLEMENTED
		return null;
	}
}
