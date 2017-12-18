package edu.kit.ukzgk.interval.number;

/*-
 * Symbols for description:
 * Latex      Unicode    HTML
 * \in        U+2208     &#8712;
 * \mathbb{K} U+1D542    &#120130;
 */
/**
 * For any objects, that can be used in calculative manner, this interface provides all functions available on an <a
 * href="https://en.wikipedia.org/wiki/Field_(mathematics)">algebraic field</a>. In addition some functions are added to
 * give access to basic properties.<br>
 * <br>
 * Here is, in short, the definition of a field, from bottom to top:<br>
 * A composition is a transformation, that assigns one element of a set to two other elements of the same set.<br>
 * A group is a pair of a non-empty set S and a composition &#9913; on S with the following properties:<br>
 * <table>
 * <tr>
 * <td>G1</td>
 * <td>Associativity</td>
 * <td>&forall; a,b,c &isin; S : (a &#9913; b) &#9913; c = a &#9913; (b &#9913; c)</td>
 * </tr>
 * <tr>
 * <td>G2</td>
 * <td>Neutral element</td>
 * <td>&exist; e &isin; S &forall; a &isin; S : e &#9913; a = a = a &#9913; e</td>
 * </tr>
 * <tr>
 * <td>G3</td>
 * <td>Inverse element</td>
 * <td>&forall; a &isin; S &exist; a<sup>-1</sup> &isin; S : a<sup>-1</sup> &#9913; a = e = a &#9913; a<sup>-1</sup></td>
 * </tr>
 * <tr>
 * <td colspan="3">Groups with this last property additionally are called abelian groups:</td>
 * </tr>
 * <tr>
 * <td>G4</td>
 * <td>Commutativity</td>
 * <td>&forall; a,b &isin; S : a &#9913; b = b &#9913; a</td>
 * </tr>
 * </table>
 * TODO: Group -> Ring -> Field
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
	
	public int intSign ();
	
	public T signum ();
	
	public T negate (T number);
	
	public T reciprocate (T number);
}
