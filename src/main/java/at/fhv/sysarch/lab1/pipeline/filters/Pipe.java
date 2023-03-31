package at.fhv.sysarch.lab1.pipeline.filters;

public class Pipe<T> {
	public IFilter<T> outgoing = null;

	public void write(T input, Container c) {
		this.outgoing.write(input, c);
	}

	public void setOutgoing(IFilter<T> filter) {
		this.outgoing = filter;
	}
}
