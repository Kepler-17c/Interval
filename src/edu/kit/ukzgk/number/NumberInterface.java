package edu.kit.ukzgk.number;

public interface NumberInterface<T> {
	public T add (T number);
	
	public T subtract (T number);
	
	public T multiply (T number);
	
	public T divide (T number);
	
	public T abs ();
	
	public int signum ();
}
