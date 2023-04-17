package at.fhv.sysarch.lab1.pipeline.filters;

import at.fhv.sysarch.lab1.obj.Face;

public class ResizeFilter implements IFilter<Face> {
	private Pipe successor = null;

	public void setSuccessor(Pipe pipe) {
		this.successor = pipe;
	}

	public void write(Face f, Container c) {
		if (f == null)
			this.successor.write(null, c);
		else
			this.successor.write(new Face(f.getV1().multiply(100), f.getV2().multiply(100), f.getV3().multiply(100),
					f.getN1(), f.getN2(), f.getN3()), c);

	}
}
