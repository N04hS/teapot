package at.fhv.sysarch.lab1.pipeline.filters;

public interface IFilter<T> {
	void setSuccessor(Pipe pipe);

	void write(T input, Container c);
}
