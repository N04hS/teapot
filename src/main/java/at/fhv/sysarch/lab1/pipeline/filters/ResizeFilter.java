package at.fhv.sysarch.lab1.pipeline.filters;

import at.fhv.sysarch.lab1.obj.Face;
import at.fhv.sysarch.lab1.pipeline.filters.base.IFilter;
import at.fhv.sysarch.lab1.pipeline.filters.base.Pipe;

public class ResizeFilter implements IFilter<Face> {
	private Pipe<Face> successor = null;
	private Pipe<Face> predecessor = null;

	public void setSuccessor(Pipe pipe) {
		successor = pipe;
	}
	public void setPredecessor(Pipe pipe) { predecessor = pipe; }

	public void write(Face f) {
		successor.write(process(f));
	}

	public Face read() {
		return process(predecessor.read());
	}

	private Face process(Face f) {
		if (f == null)
			return null;
		else
			return new Face(f.getV1().multiply(100), f.getV2().multiply(100), f.getV3().multiply(100), f.getN1(), f.getN2(), f.getN3());
	}
}
