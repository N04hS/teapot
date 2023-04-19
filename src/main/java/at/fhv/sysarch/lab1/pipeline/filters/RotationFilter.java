package at.fhv.sysarch.lab1.pipeline.filters;

import at.fhv.sysarch.lab1.obj.Face;
import at.fhv.sysarch.lab1.pipeline.filters.base.Container;
import at.fhv.sysarch.lab1.pipeline.filters.base.IFilter;
import at.fhv.sysarch.lab1.pipeline.filters.base.Pipe;
import com.hackoeur.jglm.Mat4;

public class RotationFilter implements IFilter<Face> {
	private Pipe<Face> successor = null;
	private Pipe<Face> forerunner = null;
	private Mat4 rotMat = null;

	public void setSuccessor(Pipe pipe) { this.successor = pipe; }
	public void setForerunner(Pipe pipe) { this.forerunner = pipe; }

	public RotationFilter(Mat4 rotationMatrix) { this.rotMat = rotationMatrix; }

	public void write(Face f) {
		successor.write(process(f));
	}

	public Face read() {
		return process(forerunner.read());
	}

	public Face process(Face f) {
		Mat4 rM = rotMat;
		if (f == null) return null;
		else
			return new Face(rM.multiply(f.getV1()), rM.multiply(f.getV2()), rM.multiply(f.getV3()),
					rM.multiply(f.getN1()), rM.multiply(f.getN2()), rM.multiply(f.getN3()));
	}
}
