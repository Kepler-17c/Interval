package edu.kit.ukzgk.exactDecimal;

public class Interval {
	private final ExactDecimal	upperBound;
	private final ExactDecimal	lowerBound;
	
	public Interval (ExactDecimal exactDecimal) {
		this.upperBound = exactDecimal;
		this.lowerBound = exactDecimal;
	}
	
	private Interval (ExactDecimal boundA, ExactDecimal boundB) {
		this.upperBound = ExactDecimal.max(boundA, boundB);
		this.lowerBound = ExactDecimal.min(boundA, boundB);
	}
	
	public Interval (byte value) {
		this(new ExactDecimal(value));
	}
	
	public Interval (short value) {
		this(new ExactDecimal(value));
	}
	
	public Interval (int value) {
		this(new ExactDecimal(value));
	}
	
	public Interval (long value) {
		this(new ExactDecimal(value));
	}
	
	public Interval (float value) {
		this(new ExactDecimal(value));
	}
	
	public Interval (double value) {
		this(new ExactDecimal(value));
	}
	
	public Interval add (Interval interval) {
		return new Interval(this.lowerBound.add(interval.lowerBound), this.upperBound.add(interval.upperBound));
	}
	
	public Interval subtract (Interval interval) {
		return new Interval(this.lowerBound.subtract(interval.lowerBound),
				this.upperBound.subtract(interval.upperBound));
	}
	
	public Interval multiply (Interval interval) {
		return new Interval(this.lowerBound.multiply(interval.lowerBound),
				this.upperBound.multiply(interval.upperBound));
	}
	
	public Interval divide (Interval interval) {
		return new Interval(this.lowerBound.divide(interval.lowerBound), this.upperBound.divide(interval.upperBound));
	}
	
	public Interval abs () {
		if (this.upperBound.compareTo(ExactDecimal.ZERO) > 0 && this.lowerBound.compareTo(ExactDecimal.ZERO) > 0) {
			return this;
		}
		if (this.upperBound.compareTo(ExactDecimal.ZERO) < 0 && this.lowerBound.compareTo(ExactDecimal.ZERO) < 0) {
			return new Interval(this.upperBound.abs(), this.lowerBound.abs());
		}
		if (this.lowerBound.abs().compareTo(this.upperBound.abs()) > 0) {
			return new Interval(this.upperBound.abs().multiply(new ExactDecimal(-1, 1)), this.lowerBound.abs());
		}
		return this;
	}
	
	/*-
	 * Functions recommended by IEEE754-2008 "IEEE Standard for Floating-Point Arithmetic" (document 4610935), Table 9.1:
	 * exp(x) = e^x          > [-inf, +inf]
	 * expm1(x) = e^x - 1    > -"-
	 * exp2(x) = 2^x         > -"-
	 * exp2m1(x) = 2^x - 1   > -"-
	 * exp10(x) = 10^x       > -"-
	 * exp10m1(x) = 10^x - 1 > -"-
	 * 
	 * log(x) = log_e(x)          > [0, +inf]
	 * log2(x) = log_2(x)         > -"-
	 * log10(x) = log_10(x)       > -"-
	 * logp1(x) = log_e(x+1)      > [-1, +inf]
	 * log2p1(x) = log_2(x+1)     > -"-
	 * log10p1(x) = log_10(x+1)   > -"-
	 * 
	 * hypot(x,y) = sqrt(x^2 + y^2)   > [-inf, +inf] X [-inf, +inf]
	 * 
	 * rSqrt(x) = 1/sqrt(x)   > [0, +inf]
	 * 
	 * compound(x,n) = (1+x)^n   > [-1, +inf] X Z
	 * 
	 * rootn(x,n) = x^(1/n)   > [-inf, +inf] X Z
	 * 
	 * pow(x,y) = x^y   > [-inf, +inf] X [-inf, +inf]
	 * 
	 * sin(x) = sin(x)                > [-inf, +inf]
	 * cos(x) = cos(x)                > -"-
	 * tan(x) = tan(x)                > -"-
	 * sinPi(x) = sin(Pi*x)           > -"-
	 * cosPi(x) = cos(Pi*x)           > -"-
	 * atanPi(x) = atan(x)/Pi         > -"-
	 * atan2Pi(y,x) = atan2(y,x)/Pi   > [-inf, +inf] X [-inf, +inf]
	 * asin(x) = asin(x)              > [-1, +1]
	 * acos(x) = acos(x)              > -"-
	 * atan(x) = atan(x)              > [-inf, +inf]
	 *                / y>=0, x>0 : atan(abs(y/x))
	 *               |  y>=0, x<0 : Pi - atan(abs(y/x))
	 * atan2(y,x) = <   y // TODO
	 * sinh(x) = sinh(x)              > [-inf, +inf]
	 * cosh(x) = cosh(x)              > -"-
	 * tanh(x) = tanh(x)              > -"-
	 * asinh(x) = asinh(x)            > -"-
	 * acosh(x) = acosh(x)            > [+1, +inf]
	 * atanh(x) = atanh(x)            > [-1, +1]
	 */
}
