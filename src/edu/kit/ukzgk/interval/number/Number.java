package edu.kit.ukzgk.interval.number;

/*-
 * Symbols for description:
 * Latex      Unicode    HTML      Description
 * \in        U+2208     &isin;    is element of set
 * \mapsto    U+21A6     &#8614;   right arrow from vertical bar
 * \mathbb{R} U+211D     &#8477;   double stroked upper case r
 * \mathbb{S} U+1D54A    &#120138; double stroked upper case s
 * \mathbb{Z} U+2124     &#8484;   double stroked upper case z
 */
/**
 * For any objects, that can be used in calculative manner, this interface provides all functions available on an <a
 * href="https://en.wikipedia.org/wiki/Field_(mathematics)">algebraic field</a>. In addition some functions are added to
 * give access to basic properties.<br>
 * <br>
 * Here is, in short, the definition of a field, from bottom to top:<br>
 * A composition is a transformation, that assigns one element of a set to two other elements of the same set:<br>
 * + : &#8484; &times; &#8484 &rarr; &#8484;, (x, y) &#8614; x + y<br>
 * A group is a pair of a non-empty set &#120138; and a composition &#9913; on &#120138; with the following properties:<br>
 * <table>
 * <tr>
 * <td>G1</td>
 * <td>Associativity</td>
 * <td>&forall; a,b,c &isin; &#120138; : (a &#9913; b) &#9913; c = a &#9913; (b &#9913; c)</td>
 * </tr>
 * <tr>
 * <td>G2</td>
 * <td>Neutral element</td>
 * <td>&exist; e &isin; &#120138; &forall; a &isin; &#120138; : e &#9913; a = a = a &#9913; e</td>
 * </tr>
 * <tr>
 * <td>G3</td>
 * <td>Inverse element</td>
 * <td>&forall; a &isin; &#120138; &exist; a<sup>-1</sup> &isin; &#120138; : a<sup>-1</sup> &#9913; a = e = a &#9913;
 * a<sup>-1</sup></td>
 * </tr>
 * <tr>
 * <td colspan="3">Groups with this last property additionally are called abelian groups:</td>
 * </tr>
 * <tr>
 * <td>G4</td>
 * <td>Commutativity</td>
 * <td>&forall; a,b &isin; &#120138; : a &#9913; b = b &#9913; a</td>
 * </tr>
 * </table>
 * A ring is a set &#8477; with two compositions + and &#9913; and the following properties:<br>
 * <table>
 * <tr>
 * <td>R1</td>
 * <td>Abelian</td>
 * <td>(&#8477;, +) is an abelian group</td>
 * </tr>
 * <tr>
 * <td>R2</td>
 * <td>Associativity</td>
 * <td>&#9913; is associative</td>
 * </tr>
 * <tr>
 * <td>R3</td>
 * <td>Distributive</td>
 * <td>
 * <table>
 * <tr>
 * <td rowspan="2">&forall; a,b,c &isin; &#8477; : &nbsp;</td>
 * <td>a &#9913; ( b + c ) = a &#9913; b + a &#9913; c</td>
 * </tr>
 * <tr>
 * <td>( b + c ) &#9913; a = b &#9913; a + c &#9913; a</td>
 * </tr>
 * </table>
 * </td>
 * </tr>
 * </table>
 * A field is a ring (&#120138;, +, &#9913;) with the property that (&#120138;\{0}, &#9913;) is an abelian group.
 *
 * @param <T>
 *            The set type for the field.
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
