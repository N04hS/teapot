package at.fhv.sysarch.lab1.pipeline.filters;

import at.fhv.sysarch.lab1.obj.Face;
import at.fhv.sysarch.lab1.obj.Model;
import at.fhv.sysarch.lab1.pipeline.filters.base.IFilter;
import at.fhv.sysarch.lab1.pipeline.filters.base.Pipe;

import java.util.ArrayList;
import java.util.List;

public class Source implements IFilter<Face> {
	private Pipe<Face> successor = null;
	private final List<Face> allFaces = new ArrayList<>();
	private int p;

	public void setSuccessor(Pipe pipe) {
		this.successor = pipe;
	}
	public void setPredecessor(Pipe pipe) {
		// NOT IMPLEMENTED
	}

	public void setModel(Model model) {
		allFaces.clear();
		allFaces.addAll(model.getFaces());
		p = 0;
	}

	public void write(Model model) {
		// TODO: call next filter
		model.getFaces().forEach(f -> successor.write(f));
		successor.write(null); /*separate each render call*/
	}

	public void write(Face f) {
		// NOT IMPLEMENTED
	}

	public Face read() {
		if (p >= allFaces.size()) {
			p = 0;
			return null; /* separate each render call */
		}

		return allFaces.get(p++);
	}
}
