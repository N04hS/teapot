package at.fhv.sysarch.lab1.pipeline.filters;

public class Pipe<T> {
	public IFilter<T> outgoing = null;

	public void write(T input) {
		this.outgoing.write(input);
	}

	public void setOutgoing(IFilter<T> filter) {
		this.outgoing = filter;
	}
}
