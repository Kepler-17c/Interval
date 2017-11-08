package edu.kit.ukzgk.exactDecimal;

import java.math.BigInteger;

/**
 * The {@link ExactDecimal} class is a data type that stores floating point numbers as fractions of {@link BigInteger}s.
 * Therefore values stored in it are always as exact as their input was. Instances are immutable and behave as if they
 * were native floating point numbers. There is no math library though, because this would interfere with the exactness
 * of represented values.
 * 
 * @author Florian Gilges
 * @version 201711030028
 */
public class ExactDecimal implements Comparable<ExactDecimal> {
	private enum Status {
		VALID, NOT_A_NUMBER, INFINITE;
	}
	
	/**
	 * Zero constant. Equal to: {@code new ExactDecimal(0)}
	 */
	public static final ExactDecimal	ZERO				= new ExactDecimal(0);
	/**
	 * One constant. Equal to: {@code new ExactDecimal(1)}
	 */
	public static final ExactDecimal	ONE					= new ExactDecimal(1);
	/**
	 * Two constant. Equal to: {@code new ExactDecimal(2)}
	 */
	public static final ExactDecimal	TWO					= new ExactDecimal(2);
	/**
	 * Eight constant. Equal to: {@code new ExactDecimal(8)}
	 */
	public static final ExactDecimal	EIGHT				= new ExactDecimal(8);
	/**
	 * Ten constant. Equal to: {@code new ExactDecimal(10)}
	 */
	public static final ExactDecimal	TEN					= new ExactDecimal(10);
	/**
	 * Sixteen constant. Equal to: {@code new ExactDecimal(16)}
	 */
	public static final ExactDecimal	SIXTEEN				= new ExactDecimal(16);
	/**
	 * Positive infinity constant. Equal to: {@code new ExactDecimal(1,0)}
	 */
	public static final ExactDecimal	POSITIVE_INFINITY	= new ExactDecimal(1, 0);
	/**
	 * Negative infinity constant. Equal to: {@code new ExactDecimal(-1,0)}
	 */
	public static final ExactDecimal	NEGATIVE_INFINITY	= new ExactDecimal(-1, 0);
	/**
	 * NaN (not a number) constant. Evaluates where no finite nor infinite value can be calculated (e.g. {@code 0/0} or
	 * {@code ExactDecimal#POSITIVE_INFINITY#subtract(ExactDecimal#POSITIVE_INFINITY)} )
	 */
	public static final ExactDecimal	NOT_A_NUMBER		= new ExactDecimal(0, 0);
	
	private final Status				status;
	/**
	 * + = false<br>
	 * - = true
	 */
	private final boolean				sign;
	private final BigInteger			numerator;
	private final BigInteger			denominator;
	
	/**
	 * Simple constructor for creating the fraction out of two signed integers. Their signs will work the way
	 * mathematics describes.
	 * 
	 * @param numerator
	 *            The numerator of the fraction.
	 * @param denominator
	 *            The denominator of the fraction.
	 */
	public ExactDecimal (long numerator, long denominator) {
		boolean sign = false;
		if (numerator < 0) {
			numerator = Math.abs(numerator);
			sign ^= true;
		}
		if (denominator < 0) {
			denominator = Math.abs(denominator);
			sign ^= true;
		}
		
		BigInteger n = new BigInteger(Long.toHexString(numerator), 16);
		BigInteger d = new BigInteger(Long.toHexString(denominator), 16);
		
		BigInteger[] fraction = cancelFraction(n, d);
		this.sign = sign;
		this.numerator = fraction[0];
		this.denominator = fraction[1];
		this.status = this.getStatus();
	}
	
	/**
	 * Simple constructor, creating an {@link ExactDecimal} from a {@link Long}.
	 * 
	 * @param value
	 *            The value of the new {@link ExactDecimal}
	 */
	public ExactDecimal (long value) {
		this(value < 0, new byte[] { (byte) (value >>> 56), (byte) (value >>> 48), (byte) (value >>> 40),
				(byte) (value >>> 32), (byte) (value >>> 24), (byte) (value >>> 16), (byte) (value >>> 8),
				(byte) (value) });
	}
	
	/**
	 * Simple constructor, creating an {@link ExactDecimal} from a {@link Integer}.
	 * 
	 * @param value
	 *            The value of the new {@link ExactDecimal}
	 */
	public ExactDecimal (int value) {
		this(value < 0,
				new byte[] { (byte) (value >>> 24), (byte) (value >>> 16), (byte) (value >>> 8), (byte) (value) });
	}
	
	private ExactDecimal (boolean sign, byte[] value) {
		this.sign = sign;
		this.numerator = new BigInteger(value).abs();
		this.denominator = BigInteger.ONE;
		this.status = this.getStatus();
	}
	
	/**
	 * Simple constructor, creating an {@link ExactDecimal} from a {@link Double}.
	 * 
	 * @param value
	 *            The value of the new {@link ExactDecimal}
	 */
	public ExactDecimal (double value) {
		final long valueBits = Double.doubleToRawLongBits(value);
		final boolean sign = (valueBits & 0x8000000000000000L) != 0;
		
		if (Double.isNaN(value)) {
			this.sign = ExactDecimal.NOT_A_NUMBER.sign;
			this.numerator = ExactDecimal.NOT_A_NUMBER.numerator;
			this.denominator = ExactDecimal.NOT_A_NUMBER.denominator;
			this.status = ExactDecimal.NOT_A_NUMBER.status;
			return;
		} else if (Double.isInfinite(value)) {
			this.sign = sign;
			this.numerator = ExactDecimal.POSITIVE_INFINITY.numerator;
			this.denominator = ExactDecimal.POSITIVE_INFINITY.denominator;
			this.status = this.getStatus();
			return;
		}
		
		// mantissa bits from double value
		final long mantissa = (valueBits & 0x000fffffffffffffL) | 0x0010000000000000L;
		// exponent value = exponent bits - exponent bias - mantissa exponent
		final int exponent = (int) ((valueBits & 0x7ff0000000000000L) >>> 52) - 0x03ff - 52;
		
		BigInteger numerator = new BigInteger(new byte[] { (byte) (mantissa >>> 48), (byte) (mantissa >>> 40),
				(byte) (mantissa >>> 32), (byte) (mantissa >>> 24), (byte) (mantissa >>> 16), (byte) (mantissa >>> 8),
				(byte) (mantissa) });
		BigInteger denominator;
		if (exponent < 0) {
			denominator = exponentToNumber(-exponent);
		} else {
			numerator = numerator.multiply(exponentToNumber(exponent));
			denominator = BigInteger.ONE;
		}
		
		this.sign = sign;
		this.numerator = numerator;
		this.denominator = denominator;
		this.status = this.getStatus();
	}
	
	/**
	 * Simple constructor, creating an {@link ExactDecimal} from a {@link Float}.
	 * 
	 * @param value
	 *            The value of the new {@link ExactDecimal}
	 */
	public ExactDecimal (float value) {
		final int valueBits = Float.floatToRawIntBits(value);
		final boolean sign = (valueBits & 0x80000000) != 0;
		
		if (Float.isNaN(value)) {
			this.sign = ExactDecimal.NOT_A_NUMBER.sign;
			this.numerator = ExactDecimal.NOT_A_NUMBER.numerator;
			this.denominator = ExactDecimal.NOT_A_NUMBER.denominator;
			this.status = ExactDecimal.NOT_A_NUMBER.status;
			return;
		} else if (Float.isInfinite(value)) {
			this.sign = sign;
			this.numerator = ExactDecimal.POSITIVE_INFINITY.numerator;
			this.denominator = ExactDecimal.POSITIVE_INFINITY.denominator;
			this.status = this.getStatus();
			return;
		}
		
		// mantissa bits from float value and putting back the cut off one (see IEEE754)
		final int mantissa = ((valueBits & 0x007fffff) | 0x00800000);
		// exponent value = exponent bits - exponent bias - mantissa exponent
		final int exponent = ((valueBits & 0x7f800000) >>> 23) - 0x7f - 23;
		
		BigInteger numerator = new BigInteger(new byte[] { 0, (byte) (mantissa >>> 16), (byte) (mantissa >>> 8),
				(byte) (mantissa) });
		BigInteger denominator;
		if (exponent < 0) {
			denominator = exponentToNumber(-exponent);
		} else {
			numerator = numerator.multiply(exponentToNumber(exponent));
			denominator = BigInteger.ONE;
		}
		
		this.sign = sign;
		this.numerator = numerator;
		this.denominator = denominator;
		this.status = this.getStatus();
	}
	
	// creates a BigInteger instance with the value of 2^exponent
	private static BigInteger exponentToNumber (int exponent) {
		int byteSize = exponent / 8;
		int shiftInByte = exponent % 8;
		byte[] bigIntArray = new byte[byteSize + 1 + (shiftInByte == 0x80 ? 1 : 0)];
		// note: the most significant byte of a BigInteger is the arrays 0-byte
		// all bytes describe the number in 2s-complement and therefore might need a leading zero
		bigIntArray[shiftInByte == 0x80 ? 1 : 0] = (byte) (1 << shiftInByte);
		return new BigInteger(bigIntArray);
	}
	
	/**
	 * Advanced constructor, parsing a decimal floating point string in scientific notation. Values are handled as if
	 * their decimals were finite.
	 * 
	 * @param decimalString
	 *            The decimal floating point number as string.
	 */
	public ExactDecimal (String decimalString) {
		boolean sign;
		BigInteger numerator;
		BigInteger denominator;
		
		if (decimalString.matches("(([+\\-]?Infinity)|NaN)")) {
			if (decimalString.equals("NaN")) {
				sign = false;
				numerator = BigInteger.ZERO;
				denominator = BigInteger.ZERO;
			} else {
				numerator = BigInteger.ONE;
				denominator = BigInteger.ZERO;
				switch (decimalString.charAt(0)) {
					case '-':
						sign = true;
						break;
					default:
						sign = false;
						break;
				}
			}
			this.sign = sign;
			this.numerator = numerator;
			this.denominator = denominator;
			this.status = this.getStatus();
			return;
		}
		
		if (!decimalString.matches("[+\\-]?[0-9]+(\\.[0-9]+)?([eE][+\\-]?[0-9]+)?")) {
			throw new NumberFormatException();
		}
		
		sign = false;
		if (decimalString.startsWith("-")) {
			sign = true;
			decimalString = decimalString.substring(1);
		} else if (decimalString.startsWith("+")) {
			decimalString = decimalString.substring(1);
		}
		
		String[] split = decimalString.split("[eE]");
		String numberString = split[0];
		int exponent = 0;
		if (split.length == 2) {
			exponent = Integer.parseInt(split[1]);
		}
		split = numberString.split("\\.");
		String digits = split[0];
		String decimals = "";
		if (split.length == 2) {
			decimals = split[1];
		}
		
		String allDigits = digits + decimals;
		exponent -= decimals.length();
		if (exponent < 0) {
			numerator = new BigInteger(allDigits, 10);
			exponent = Math.abs(exponent);
			denominator = new BigInteger(1 + fillString("", exponent, '0'), 10);
		} else {
			allDigits += fillString("", exponent, '0');
			numerator = new BigInteger(allDigits, 10);
			denominator = BigInteger.ONE;
		}
		
		this.sign = sign;
		BigInteger[] fraction = cancelFraction(numerator, denominator);
		this.numerator = fraction[0];
		this.denominator = fraction[1];
		this.status = this.getStatus();
	}
	
	private ExactDecimal (boolean sign, BigInteger numerator, BigInteger denominator) {
		BigInteger[] fraction = cancelFraction(numerator, denominator);
		this.sign = sign;
		this.numerator = fraction[0];
		this.denominator = fraction[1];
		this.status = this.getStatus();
	}
	
	private Status getStatus () {
		if (this.numerator.equals(BigInteger.ZERO) && this.denominator.equals(BigInteger.ZERO)) {
			return Status.NOT_A_NUMBER;
		} else if (this.denominator.equals(BigInteger.ZERO)) {
			return Status.INFINITE;
		}
		return Status.VALID;
	}
	
	/**
	 * Adds another {@link ExactDecimal} to {@code this} and returns a new instance of that value.
	 * 
	 * @param a
	 *            The value to add.
	 * @return A new instance, holding the result.
	 */
	public ExactDecimal add (ExactDecimal a) {
		if (this.status == Status.NOT_A_NUMBER || a.status == Status.NOT_A_NUMBER) {
			return NOT_A_NUMBER;
		}
		if (this.sign == a.sign) {
			if (this.status == Status.INFINITE || a.status == Status.INFINITE) {
				return this.sign ? NEGATIVE_INFINITY : POSITIVE_INFINITY;
			}
			BigInteger numeratorThis = this.numerator.multiply(a.denominator);
			BigInteger numeratorA = a.numerator.multiply(this.denominator);
			BigInteger denominator = this.denominator.multiply(a.denominator);
			return new ExactDecimal(this.sign, numeratorThis.add(numeratorA), denominator);
		} else if (this.sign) {
			return a.subtract(this.abs());
		} else {
			return this.subtract(a.abs());
		}
	}
	
	/**
	 * Subtracts another {@link ExactDecimal} from {@code this} and returns a new instance of that value.
	 * 
	 * @param a
	 *            The value to subtract.
	 * @return A new instance, holding the result.
	 */
	public ExactDecimal subtract (ExactDecimal a) {
		if (this.status == Status.NOT_A_NUMBER || a.status == Status.NOT_A_NUMBER) {
			return NOT_A_NUMBER;
		}
		
		if (this.sign == a.sign) {
			if (this.status == Status.INFINITE && a.status == Status.INFINITE) {
				return NOT_A_NUMBER;
			} else if (this.status == Status.INFINITE) {
				return this.sign ? NEGATIVE_INFINITY : POSITIVE_INFINITY;
			} else if (a.status == Status.INFINITE) {
				return !this.sign ? NEGATIVE_INFINITY : POSITIVE_INFINITY;
			}
			BigInteger numeratorThis = this.numerator.multiply(a.denominator);
			BigInteger numeratorA = a.numerator.multiply(this.denominator);
			BigInteger denominator = this.denominator.multiply(a.denominator);
			
			BigInteger numerator;
			boolean sign;
			if (numeratorThis.compareTo(numeratorA) >= 0) {
				numerator = numeratorThis.subtract(numeratorA);
				sign = this.sign;
			} else {
				numerator = numeratorA.subtract(numeratorThis);
				sign = !this.sign;
			}
			return new ExactDecimal(sign, numerator, denominator);
		} else if (this.sign) {
			ExactDecimal absThis = this.abs();
			ExactDecimal result = absThis.add(a);
			return result.negate();
		} else {
			return this.add(a.abs());
		}
	}
	
	/**
	 * Multiplies another {@link ExactDecimal} to {@code this} and returns a new instance of that value.
	 * 
	 * @param a
	 *            The value to multiply with.
	 * @return A new instance, holding the result.
	 */
	public ExactDecimal multiply (ExactDecimal a) {
		boolean sign = this.sign ^ a.sign;
		BigInteger numerator = this.numerator.multiply(a.numerator);
		BigInteger denominator = this.denominator.multiply(a.denominator);
		return new ExactDecimal(sign, numerator, denominator);
	}
	
	/**
	 * Divides {@code this} by another {@link ExactDecimal} and returns a new instance of that value.
	 * 
	 * @param a
	 *            The value to divide by.
	 * @return A new instance, holding the result.
	 */
	public ExactDecimal divide (ExactDecimal a) {
		boolean sign = this.sign ^ a.sign;
		BigInteger numerator = this.numerator.multiply(a.denominator);
		BigInteger denominator = this.denominator.multiply(a.numerator);
		return new ExactDecimal(sign, numerator, denominator);
	}
	
	/**
	 * Returns the absolute value of this number.
	 * 
	 * @return The absolute value.
	 */
	public ExactDecimal abs () {
		return new ExactDecimal(false, this.numerator, this.denominator);
	}
	
	/**
	 * Returns the negative absolute value of this number.
	 * 
	 * @return The negative value.
	 */
	public ExactDecimal negate () {
		return new ExactDecimal(true, this.numerator, this.denominator);
	}
	
	/**
	 * Returns the value's sign.
	 * <table border="1" bordercolor="#757575">
	 * <tr>
	 * <th>Value</th>
	 * <th>Returns</th>
	 * </tr>
	 * <tr>
	 * <th><i>Positive number</i></th>
	 * <th>1</th>
	 * </tr>
	 * <tr>
	 * <th><i>Zero</i></th>
	 * <th>0</th>
	 * </tr>
	 * <tr>
	 * <th><i>Negative number</i></th>
	 * <th>-1</th>
	 * </tr>
	 * <tr>
	 * <th><i>NaN (has no sign)</i></th>
	 * <th>0</th>
	 * </tr>
	 * </table>
	 * 
	 * @return The sign.
	 */
	public int signum () {
		if (this.status == Status.NOT_A_NUMBER || this.numerator.equals(BigInteger.ZERO)) {
			return 0;
		}
		return this.sign ? -1 : 1;
	}
	
	/**
	 * Returns the smaller value. Note that {@code NaN} is bigger than any value, including {@code Infinity}.
	 * 
	 * @param a
	 *            The first value.
	 * @param b
	 *            The second value.
	 * @return The smaller one of the two values.
	 */
	public static ExactDecimal min (ExactDecimal a, ExactDecimal b) {
		return a.compareTo(b) < 0 ? a : b;
	}
	
	/**
	 * Returns the bigger value. Note that {@code NaN} is bigger than any value, including {@code Infinity}.
	 * 
	 * @param a
	 *            The first value.
	 * @param b
	 *            The second value.
	 * @return The bigger one of the two values.
	 */
	public static ExactDecimal max (ExactDecimal a, ExactDecimal b) {
		return a.compareTo(b) > 0 ? a : b;
	}
	
	private static BigInteger[] cancelFraction (BigInteger numerator, BigInteger denominator) {
		if (numerator.compareTo(BigInteger.ONE) == 0 || denominator.compareTo(BigInteger.ONE) == 0) {
			return new BigInteger[] { numerator, denominator };
		}
		
		if (numerator.equals(BigInteger.ZERO) && denominator.equals(BigInteger.ZERO)) {
			return new BigInteger[] { BigInteger.ZERO, BigInteger.ZERO };
		}
		if (numerator.equals(BigInteger.ZERO)) {
			return new BigInteger[] { BigInteger.ZERO, BigInteger.ONE };
		}
		if (denominator.equals(BigInteger.ZERO)) {
			return new BigInteger[] { BigInteger.ONE, BigInteger.ZERO };
		}
		
		BigInteger a = numerator;
		BigInteger b = denominator;
		BigInteger gcd = a.gcd(b);
		a = a.divide(gcd);
		b = b.divide(gcd);
		return new BigInteger[] { a, b };
	}
	
	/**
	 * Returns whether {@code this} is NaN (not a number).
	 * 
	 * @return {@code true}, if {@code this} is NaN, {@code false} otherwise.
	 */
	public boolean isNaN () {
		return this.status == Status.NOT_A_NUMBER;
	}
	
	/**
	 * Returns whether {@code this} is a valid number (neither infinite, nor NaN).
	 * 
	 * @return {@code true}, if {@code this} is a valid number, {@code false} otherwise.
	 */
	public boolean isFinite () {
		return this.status == Status.VALID;
	}
	
	/**
	 * Returns whether {@code this} is value is infinite (positive or negative infinity).
	 * 
	 * @return {@code true}, if {@code this} is infinite, {@code false} otherwise.
	 */
	public boolean isInfinite () {
		return this.status == Status.INFINITE;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString () {
		return this.toStringAdvanced(2);
	}
	
	/**
	 * Creates a string representation of the number in decimal format with decimal point '.' and implicit sign. The
	 * number of decimals is free to choose in the argument. It will cut of decimals after that, without rounding. Zero
	 * or less will create an integer string without decimals.
	 * 
	 * @param decimals
	 *            The number of decimal places to show.
	 * @return The number as decimal string.
	 */
	public String toStringAdvanced (int decimals) {
		switch (this.status) {
			case INFINITE:
				return this.sign ? "-Infinity" : "Infinity";
			case NOT_A_NUMBER:
				return "NaN";
			default:
				break; // do the normal procedure (as for 'VALID')
		}
		
		if (decimals < 0) {
			decimals = 0;
		}
		
		String result = this.sign && !this.numerator.equals(BigInteger.ZERO) ? "-" : "";
		BigInteger[] div = this.numerator.divideAndRemainder(this.denominator);
		String decimalDigits;
		if (decimals == 0) {
			decimalDigits = "";
		} else {
			BigInteger shift = BigInteger.ONE;
			int mask = Integer.highestOneBit(decimals);
			while (mask != 0) {
				shift = shift.multiply(shift);
				if ((mask & decimals) != 0) {
					shift = shift.multiply(BigInteger.TEN);
				}
				mask >>>= 1;
			}
			decimalDigits = ".";
			BigInteger shiftedRemainder = div[1].multiply(shift);
			String tmp = shiftedRemainder.divide(this.denominator).toString();
			decimalDigits += fillString("", decimals - tmp.length(), '0') + tmp;
		}
		result += div[0] + decimalDigits;
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo (ExactDecimal a) {
		if (a == null) {
			throw new NullPointerException("Cannot compare to null");
		}
		
		// similar to Double, NaN is greater than any value (including infinity)
		if (this.isNaN() && a.isNaN()) {
			return 0;
		} else if (this.isNaN()) {
			return 1;
		} else if (a.isNaN()) {
			return -1;
		}
		
		// all combinations with +/- infinity
		if (this.isInfinite() && a.isInfinite()) {
			if (this.sign == a.sign) {
				return 0;
			} else if (this.sign) {
				// - compareTo +
				return -1;
			} else {
				// + compareTo -
				return 1;
			}
		} else if (this.isInfinite()) {
			return this.sign ? -1 : 1;
		} else if (a.isInfinite()) {
			return a.sign ? 1 : -1;
		}
		
		// both 0
		if (this.numerator.equals(BigInteger.ZERO) && a.numerator.equals(BigInteger.ZERO)) {
			return 0;
		}
		
		// check for alternating signs
		if (this.sign && !a.sign) {
			// - compareTo +
			return -1;
		} else if (!this.sign && a.sign) {
			// + compareTo -
			return 1;
		}
		
		// resolve fraction
		BigInteger numeratorThis = this.numerator.multiply(a.denominator);
		BigInteger numeratorA = a.numerator.multiply(this.denominator);
		return numeratorThis.compareTo(numeratorA);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals (Object o) {
		if (o == null) {
			return false;
		}
		if (o instanceof ExactDecimal) {
			ExactDecimal other = (ExactDecimal) o;
			return this.compareTo(other) == 0;
		}
		return false;
	}
	
	private static String fillString (String string, int length, char fillCharacter) {
		String filler = Character.toString(fillCharacter);
		int diff = length - string.length();
		if (diff <= 0) {
			return string;
		}
		String spaces = "";
		
		int mask = Integer.highestOneBit(diff);
		while (mask != 0) {
			spaces += spaces;
			if ((mask & diff) != 0) {
				spaces += filler;
			}
			mask >>>= 1;
		}
		
		return string + spaces;
	}
}
