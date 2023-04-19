package at.fhv.sysarch.lab1.pipeline.filters.base;

import at.fhv.sysarch.lab1.obj.Face;
import at.fhv.sysarch.lab1.obj.Model;

import java.util.ArrayList;
import java.util.List;

public class Source implements IFilter<Face> {
	private Pipe successor = null;
	private List<Face> allFaces = new ArrayList<>();
	private int p;

	public void setSuccessor(Pipe pipe) {
		this.successor = pipe;
	}
	public void setForerunner(Pipe pipe) {
		// NOT IMPLEMENTED
	}

	public void setModel(Model model) {
		allFaces.clear();
		allFaces.addAll(model.getFaces());
		p = 0;
	}

	public void write(Model model, Container c) {
		// TODO: call next filter
		model.getFaces().forEach(f -> this.successor.write(f, c));
		this.successor.write(null, c); /*separate each render call*/
	}

	public void write(Face f, Container c) {
		// NOT IMPLEMENTED
	}

	public Face read() {
		if (p >= allFaces.size()) {
			p = 0;
			return null; /* separate each render call */
		}

		return allFaces.get(p++);
	}

	public Face process(Face f) {
		// NOT IMPLEMENTED
		return null;
	}
}
