package edu.kit.ukzgk.interval.number;

/*-
 * Symbols for description:
 * Latex      Unicode    HTML
 * \in        U+2208     &#8712;
 * \mathbb{K} U+1D542    &#120130;
 */
/**
 * For any objects, that can be used in calculative manner, this interface provides all functions available on an
 * algebraic field. In addition some functions are added to give access to basic properties.<br>
 * <br>
 * Operations defined on fields are:<br>
 * <table>
 * <tr>
 * <th>Operation</th>
 * <th>Property</th>
 * </tr>
 * <tr>
 * <td>a + b = c</td>
 * <td>Adding two elements yield to another element in the field</td>
 * </tr>
 * <tr>
 * <td>a * b = c</td>
 * <td>Multiplying two elements</td>
 * </tr>
 * <tr>
 * <td rowspan="2">For a field &#120130;<br>
 * a,b,c &#8712; &#120130;</td>
 * </tr>
 * </table>
 *
 * @param <T>
 */
public interface Number<T> extends Comparable<T> {
	public T add (T number);
	
	public T subtract (T number);
	
	public T multiply (T number);
	
	public T divide (T number);
	
	public T abs ();
	
	public T negative ();
	
	public int signum ();
	
	public T negate (T number);
	
	public T reciprocate (T number);
}
