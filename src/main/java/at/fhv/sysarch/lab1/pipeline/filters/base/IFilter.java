package at.fhv.sysarch.lab1.pipeline.filters.base;

public interface IFilter<T> {
	void setSuccessor(Pipe pipe);
	void setForerunner(Pipe pipe);

	void write(T input, Container c);
	T read();
	T process(T input);
}
