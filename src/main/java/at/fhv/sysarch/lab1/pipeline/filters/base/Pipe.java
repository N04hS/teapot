package at.fhv.sysarch.lab1.pipeline.filters.base;

public class Pipe<T> {
	public IFilter<T> outgoing = null;
	public IFilter<T> incoming = null;

	public void write(T input) {
		outgoing.write(input);
	}
	public T read() { return incoming.read(); }

	public void setOutgoing(IFilter<T> filter) { outgoing = filter;	}
	public void setIncoming(IFilter<T> filter) { incoming = filter; }
}
