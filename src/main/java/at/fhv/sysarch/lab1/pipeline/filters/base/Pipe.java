package at.fhv.sysarch.lab1.pipeline.filters.base;

public class Pipe<T> {
	public IFilter<T> outgoing = null;
	public IFilter<T> incoming = null;

	public void write(T input, Container c) {
		this.outgoing.write(input, c);
	}
	public T read() { return this.incoming.read(); }

	public void setOutgoing(IFilter<T> filter) {
		this.outgoing = filter;
	}
	public void setIncoming(IFilter<T> filter) { this.incoming = filter; }
}
