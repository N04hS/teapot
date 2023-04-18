package at.fhv.sysarch.lab1.pipeline.filters;

import at.fhv.sysarch.lab1.obj.Face;

public class AngleTransformFilter implements IFilter<Face> {
	private Pipe<Face> successor = null;

	public void setSuccessor(Pipe pipe) { this.successor = pipe; }

	public void write(Face f, Container c) {
		if (f == null) successor.write(null, c);
		else {
			Face face = new Face(f.getV1().getNegated(), f.getV2().getNegated(), f.getV3().getNegated(), f);
			successor.write(face, c);
		}
	}
}
