package at.fhv.sysarch.lab1.pipeline.filters.base;

public interface IFilter<T> {
	void setSuccessor(Pipe pipe);
	void setPredecessor(Pipe pipe);

	void write(T input);
	T read();
}
