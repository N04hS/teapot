package at.fhv.sysarch.lab1.pipeline.filters.base;

import at.fhv.sysarch.lab1.obj.Face;
import at.fhv.sysarch.lab1.pipeline.PipelineData;
import at.fhv.sysarch.lab1.rendering.RenderingMode;
import com.hackoeur.jglm.Vec3;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Sink implements IFilter<Face> {
	private Pipe<Face> forerunner = null;
	private final GraphicsContext gc;
	private final RenderingMode rm;
	private final Vec3 light;
	private final Color base;

	public Sink(PipelineData pd) {
		this.gc = pd.getGraphicsContext();
		this.rm = pd.getRenderingMode();
		this.base = pd.getModelColor();
		light = pd.getLightPos();
	}

	public void setSuccessor(Pipe pipe) {
		// NOT IMPLEMENTED
	}

	public void setForerunner(Pipe pipe) {
		this.forerunner = pipe;
	}

	public void write(Face f) {
		/* Flat shading */
		Vec3 lightVec = light.subtract(f.getV1().toVec3()).getUnitVector();
		double ratio = Math.max(0.0, lightVec.dot(f.getN1().toVec3().getUnitVector()));
		Color shaded = Color.color(base.getRed()*ratio, base.getGreen()*ratio, base.getBlue()*ratio);
		gc.setFill(shaded);

		switch (rm){
			case POINT -> {
				gc.strokeLine(f.getV1().getX(), f.getV1().getY(), f.getV1().getX(), f.getV1().getY());
				gc.strokeLine(f.getV2().getX(), f.getV2().getY(), f.getV2().getX(), f.getV2().getY());
				gc.strokeLine(f.getV3().getX(), f.getV3().getY(), f.getV3().getX(), f.getV3().getY());
			}
			case FILLED -> {
				double[] x = {f.getV1().getX(), f.getV2().getX(), f.getV3().getX()};
				double[] y = {f.getV1().getY(), f.getV2().getY(), f.getV3().getY()};
				gc.fillPolygon(x, y, 3);
			}
			case WIREFRAME -> {
				gc.strokeLine(f.getV1().getX(), f.getV1().getY(), f.getV2().getX(), f.getV2().getY());
				gc.strokeLine(f.getV1().getX(), f.getV1().getY(), f.getV3().getX(), f.getV3().getY());
				gc.strokeLine(f.getV2().getX(), f.getV2().getY(), f.getV3().getX(), f.getV3().getY());
			}
		}
	}

	public Face process(Face f) {
		// NOT IMPLEMENTED
		return null;
	}

	public Face read() {
		Face f = forerunner.read();
		while (f != null) {
			write(f);

			f = forerunner.read();
		}

		return null;
	}
}
