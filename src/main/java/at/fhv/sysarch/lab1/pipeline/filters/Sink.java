package at.fhv.sysarch.lab1.pipeline.filters;

import at.fhv.sysarch.lab1.obj.Face;
import javafx.scene.canvas.GraphicsContext;

public class Sink implements IFilter<Face> {
	private GraphicsContext context;

	public Sink(GraphicsContext context) {
		this.context = context;
	}

	public void setSuccessor(Pipe pipe) {
		// NOT IMPLEMENTED
	}

	public void write(Face f) {
		context.strokeLine(f.getV1().getX(), f.getV1().getY(), f.getV2().getX(), f.getV2().getY());
		context.strokeLine(f.getV1().getX(), f.getV1().getY(), f.getV3().getX(), f.getV3().getY());
		context.strokeLine(f.getV2().getX(), f.getV2().getY(), f.getV3().getX(), f.getV3().getY());

	}
}
