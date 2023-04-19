package at.fhv.sysarch.lab1.pipeline.filters;

import at.fhv.sysarch.lab1.obj.Face;
import at.fhv.sysarch.lab1.pipeline.filters.base.Container;
import at.fhv.sysarch.lab1.pipeline.filters.base.IFilter;
import at.fhv.sysarch.lab1.pipeline.filters.base.Pipe;

public class ResizeFilter implements IFilter<Face> {
	private Pipe successor = null;
	private Pipe forerunner = null;
	// Container could be deleted everywhere except in filters where its needed
	// last step after pullpipeline works
	private Container container = null;

	public void setSuccessor(Pipe pipe) {
		successor = pipe;
	}
	public void setForerunner(Pipe pipe) { forerunner = pipe; }

	public ResizeFilter(Container c) { container = c; }

	public void write(Face f, Container c) {
		successor.write(process(f), c);
	}

	public Face read() {
		return process((Face) forerunner.read());
	}

	public Face process(Face f) {
		if (f == null) return null;

		return new Face(f.getV1().multiply(100), f.getV2().multiply(100), f.getV3().multiply(100), f.getN1(), f.getN2(), f.getN3());
	}
}
