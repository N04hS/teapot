package at.fhv.sysarch.lab1.pipeline.filters;

import at.fhv.sysarch.lab1.obj.Face;
import at.fhv.sysarch.lab1.pipeline.filters.base.Container;
import at.fhv.sysarch.lab1.pipeline.filters.base.IFilter;
import at.fhv.sysarch.lab1.pipeline.filters.base.Pipe;
import com.hackoeur.jglm.Mat4;

public class RotationFilter implements IFilter<Face> {
	private Pipe successor = null;
	private Pipe forerunner = null;
	private Container container = null;

	public void setSuccessor(Pipe pipe) { this.successor = pipe; }
	public void setForerunner(Pipe pipe) { this.forerunner = pipe; }

	public RotationFilter(Container c) { this.container = c; }

	public void write(Face f, Container c) {
		successor.write(process(f), c);
	}

	public Face read() {
		return process((Face) forerunner.read());
	}

	public Face process(Face f) {
		Mat4 rM = container.rotMat;
		if (f == null) return null;
		else
			return new Face(rM.multiply(f.getV1()), rM.multiply(f.getV2()), rM.multiply(f.getV3()),
					rM.multiply(f.getN1()), rM.multiply(f.getN2()), rM.multiply(f.getN3()));
	}
}
