package edu.kit.ukzgk.exactDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;

import org.junit.Test;

public class ExactDecimalTest {
	@Test
	public void constantNegativeInfinity () {
		ExactDecimal exactDecimal = ExactDecimal.NEGATIVE_INFINITY;
		assertFalse(exactDecimal.isFinite());
		assertTrue(exactDecimal.isInfinite());
		assertFalse(exactDecimal.isNaN());
		assertEquals("-Infinity", exactDecimal.toString());
		assertEquals(0, exactDecimal.compareTo(ExactDecimal.NEGATIVE_INFINITY));
		assertEquals(-1, exactDecimal.compareTo(ExactDecimal.NOT_A_NUMBER));
		assertEquals(-1, exactDecimal.compareTo(ExactDecimal.ONE));
		assertEquals(-1, exactDecimal.compareTo(ExactDecimal.POSITIVE_INFINITY));
		assertEquals(-1, exactDecimal.compareTo(ExactDecimal.ZERO));
	}
	
	@Test
	public void constantNotANumber () {
		ExactDecimal exactDecimal = ExactDecimal.NOT_A_NUMBER;
		assertFalse(exactDecimal.isFinite());
		assertFalse(exactDecimal.isInfinite());
		assertTrue(exactDecimal.isNaN());
		assertEquals("NaN", exactDecimal.toString());
		assertEquals(1, exactDecimal.compareTo(ExactDecimal.NEGATIVE_INFINITY));
		assertEquals(0, exactDecimal.compareTo(ExactDecimal.NOT_A_NUMBER));
		assertEquals(1, exactDecimal.compareTo(ExactDecimal.ONE));
		assertEquals(1, exactDecimal.compareTo(ExactDecimal.POSITIVE_INFINITY));
		assertEquals(1, exactDecimal.compareTo(ExactDecimal.ZERO));
	}
	
	@Test
	public void constantOne () {
		ExactDecimal exactDecimal = ExactDecimal.ONE;
		assertTrue(exactDecimal.isFinite());
		assertFalse(exactDecimal.isInfinite());
		assertFalse(exactDecimal.isNaN());
		assertEquals("1.00", exactDecimal.toString());
		assertEquals(1, exactDecimal.compareTo(ExactDecimal.NEGATIVE_INFINITY));
		assertEquals(-1, exactDecimal.compareTo(ExactDecimal.NOT_A_NUMBER));
		assertEquals(0, exactDecimal.compareTo(ExactDecimal.ONE));
		assertEquals(-1, exactDecimal.compareTo(ExactDecimal.POSITIVE_INFINITY));
		assertEquals(1, exactDecimal.compareTo(ExactDecimal.ZERO));
	}
	
	@Test
	public void constantPositiveInfinity () {
		ExactDecimal exactDecimal = ExactDecimal.POSITIVE_INFINITY;
		assertFalse(exactDecimal.isFinite());
		assertTrue(exactDecimal.isInfinite());
		assertFalse(exactDecimal.isNaN());
		assertEquals("Infinity", exactDecimal.toString());
		assertEquals(1, exactDecimal.compareTo(ExactDecimal.NEGATIVE_INFINITY));
		assertEquals(-1, exactDecimal.compareTo(ExactDecimal.NOT_A_NUMBER));
		assertEquals(1, exactDecimal.compareTo(ExactDecimal.ONE));
		assertEquals(0, exactDecimal.compareTo(ExactDecimal.POSITIVE_INFINITY));
		assertEquals(1, exactDecimal.compareTo(ExactDecimal.ZERO));
	}
	
	@Test
	public void constantZero () {
		ExactDecimal exactDecimal = ExactDecimal.ZERO;
		assertTrue(exactDecimal.isFinite());
		assertFalse(exactDecimal.isInfinite());
		assertFalse(exactDecimal.isNaN());
		assertEquals("0.00", exactDecimal.toString());
		assertEquals(1, exactDecimal.compareTo(ExactDecimal.NEGATIVE_INFINITY));
		assertEquals(-1, exactDecimal.compareTo(ExactDecimal.NOT_A_NUMBER));
		assertEquals(-1, exactDecimal.compareTo(ExactDecimal.ONE));
		assertEquals(-1, exactDecimal.compareTo(ExactDecimal.POSITIVE_INFINITY));
		assertEquals(0, exactDecimal.compareTo(ExactDecimal.ZERO));
	}
	
	@Test
	public void mathAdd00 () {
		ExactDecimal a = new ExactDecimal(-6, -1);
		ExactDecimal b = new ExactDecimal(6, -7);
		assertEquals(new ExactDecimal(36, 7), a.add(b));
	}
	
	@Test
	public void mathAdd01 () {
		ExactDecimal a = new ExactDecimal(5, 10);
		ExactDecimal b = new ExactDecimal(5, -7);
		assertEquals(new ExactDecimal(-15, 70), a.add(b));
	}
	
	@Test
	public void mathAdd02 () {
		ExactDecimal a = new ExactDecimal(2, 5);
		ExactDecimal b = new ExactDecimal(-10, 6);
		assertEquals(new ExactDecimal(-38, 30), a.add(b));
	}
	
	@Test
	public void mathAdd03 () {
		ExactDecimal a = new ExactDecimal(1, -9);
		ExactDecimal b = new ExactDecimal(0, 1);
		assertEquals(new ExactDecimal(-1, 9), a.add(b));
	}
	
	@Test
	public void mathAdd04 () {
		ExactDecimal a = new ExactDecimal(-9, 2);
		ExactDecimal b = new ExactDecimal(1, 2);
		assertEquals(new ExactDecimal(-8, 2), a.add(b));
	}
	
	@Test
	public void mathAdd05 () {
		ExactDecimal a = new ExactDecimal(4, -9);
		ExactDecimal b = new ExactDecimal(6, -5);
		assertEquals(new ExactDecimal(-74, 45), a.add(b));
	}
	
	@Test
	public void mathAdd06 () {
		ExactDecimal a = new ExactDecimal(-5, 4);
		ExactDecimal b = new ExactDecimal(1, 0);
		assertEquals(ExactDecimal.POSITIVE_INFINITY, a.add(b));
	}
	
	@Test
	public void mathAdd07 () {
		ExactDecimal a = new ExactDecimal(-7, -9);
		ExactDecimal b = new ExactDecimal(4, 1);
		assertEquals(new ExactDecimal(43, 9), a.add(b));
	}
	
	@Test
	public void mathAdd08 () {
		ExactDecimal a = ExactDecimal.POSITIVE_INFINITY;
		ExactDecimal b = new ExactDecimal(2, 3);
		assertEquals(ExactDecimal.POSITIVE_INFINITY, a.add(b));
	}
	
	@Test
	public void mathAdd09 () {
		ExactDecimal a = ExactDecimal.NEGATIVE_INFINITY;
		ExactDecimal b = new ExactDecimal(-2, 5);
		assertEquals(ExactDecimal.NEGATIVE_INFINITY, a.add(b));
	}
	
	@Test
	public void mathAdd10 () {
		ExactDecimal a = ExactDecimal.NOT_A_NUMBER;
		ExactDecimal b = new ExactDecimal(2, 5);
		assertEquals(ExactDecimal.NOT_A_NUMBER, a.add(b));
	}
	
	@Test
	public void mathAdd11 () {
		ExactDecimal a = ExactDecimal.POSITIVE_INFINITY;
		ExactDecimal b = ExactDecimal.NEGATIVE_INFINITY;
		assertEquals(ExactDecimal.NOT_A_NUMBER, a.add(b));
	}
	
	@Test
	public void mathAdd12 () {
		ExactDecimal a = new ExactDecimal(2, 3);
		ExactDecimal b = ExactDecimal.POSITIVE_INFINITY;
		assertEquals(ExactDecimal.POSITIVE_INFINITY, a.add(b));
	}
	
	@Test
	public void mathAdd13 () {
		ExactDecimal a = new ExactDecimal(-2, 5);
		ExactDecimal b = ExactDecimal.NEGATIVE_INFINITY;
		assertEquals(ExactDecimal.NEGATIVE_INFINITY, a.add(b));
	}
	
	@Test
	public void mathAdd14 () {
		ExactDecimal a = new ExactDecimal(2, 5);
		ExactDecimal b = ExactDecimal.NOT_A_NUMBER;
		assertEquals(ExactDecimal.NOT_A_NUMBER, a.add(b));
	}
	
	@Test
	public void mathAdd15 () {
		ExactDecimal a = ExactDecimal.NEGATIVE_INFINITY;
		ExactDecimal b = ExactDecimal.POSITIVE_INFINITY;
		assertEquals(ExactDecimal.NOT_A_NUMBER, a.add(b));
	}
	
	@Test
	public void mathSubtract0 () {
		ExactDecimal a = new ExactDecimal(10, 0);
		ExactDecimal b = new ExactDecimal(10, 9);
		assertEquals(ExactDecimal.POSITIVE_INFINITY, a.subtract(b));
	}
	
	@Test
	public void mathSubtract1 () {
		ExactDecimal a = new ExactDecimal(-5, 3);
		ExactDecimal b = new ExactDecimal(-10, 5);
		assertEquals(new ExactDecimal(1, 3), a.subtract(b));
	}
	
	@Test
	public void mathSubtract2 () {
		ExactDecimal a = new ExactDecimal(9, 8);
		ExactDecimal b = new ExactDecimal(-3, -8);
		assertEquals(new ExactDecimal(6, 8), a.subtract(b));
	}
	
	@Test
	public void mathSubtract3 () {
		ExactDecimal a = new ExactDecimal(-9, 5);
		ExactDecimal b = new ExactDecimal(7, 4);
		assertEquals(new ExactDecimal(-71, 20), a.subtract(b));
	}
	
	@Test
	public void mathSubtract4 () {
		ExactDecimal a = new ExactDecimal(8, -7);
		ExactDecimal b = new ExactDecimal(-10, -9);
		assertEquals(new ExactDecimal(-142, 63), a.subtract(b));
	}
	
	@Test
	public void mathSubtract5 () {
		ExactDecimal a = new ExactDecimal(-7, 6);
		ExactDecimal b = new ExactDecimal(-6, 10);
		assertEquals(new ExactDecimal(-34, 60), a.subtract(b));
	}
	
	@Test
	public void mathSubtract6 () {
		ExactDecimal a = new ExactDecimal(8, -9);
		ExactDecimal b = new ExactDecimal(7, -9);
		assertEquals(new ExactDecimal(-1, 9), a.subtract(b));
	}
	
	@Test
	public void mathSubtract7 () {
		ExactDecimal a = new ExactDecimal(10, -1);
		ExactDecimal b = new ExactDecimal(-7, 9);
		assertEquals(new ExactDecimal(-83, 9), a.subtract(b));
	}
	
	@Test
	public void mathSubtract08 () {
		ExactDecimal a = ExactDecimal.POSITIVE_INFINITY;
		ExactDecimal b = new ExactDecimal(2, 3);
		assertEquals(ExactDecimal.POSITIVE_INFINITY, a.subtract(b));
	}
	
	@Test
	public void mathSubtract09 () {
		ExactDecimal a = ExactDecimal.NEGATIVE_INFINITY;
		ExactDecimal b = new ExactDecimal(-2, 5);
		assertEquals(ExactDecimal.NEGATIVE_INFINITY, a.subtract(b));
	}
	
	@Test
	public void mathSubtract10 () {
		ExactDecimal a = ExactDecimal.NOT_A_NUMBER;
		ExactDecimal b = new ExactDecimal(2, 5);
		assertEquals(ExactDecimal.NOT_A_NUMBER, a.subtract(b));
	}
	
	@Test
	public void mathSubtract11 () {
		ExactDecimal a = ExactDecimal.POSITIVE_INFINITY;
		ExactDecimal b = ExactDecimal.NEGATIVE_INFINITY;
		assertEquals(ExactDecimal.POSITIVE_INFINITY, a.subtract(b));
	}
	
	@Test
	public void mathSubtract12 () {
		ExactDecimal a = new ExactDecimal(2, 3);
		ExactDecimal b = ExactDecimal.POSITIVE_INFINITY;
		assertEquals(ExactDecimal.NEGATIVE_INFINITY, a.subtract(b));
	}
	
	@Test
	public void mathSubtract13 () {
		ExactDecimal a = new ExactDecimal(-2, 5);
		ExactDecimal b = ExactDecimal.NEGATIVE_INFINITY;
		assertEquals(ExactDecimal.POSITIVE_INFINITY, a.subtract(b));
	}
	
	@Test
	public void mathSubtract14 () {
		ExactDecimal a = new ExactDecimal(2, 5);
		ExactDecimal b = ExactDecimal.NOT_A_NUMBER;
		assertEquals(ExactDecimal.NOT_A_NUMBER, a.subtract(b));
	}
	
	@Test
	public void mathSubtract15 () {
		ExactDecimal a = ExactDecimal.NEGATIVE_INFINITY;
		ExactDecimal b = ExactDecimal.POSITIVE_INFINITY;
		assertEquals(ExactDecimal.NEGATIVE_INFINITY, a.subtract(b));
	}
	
	@Test
	public void mathMultiply0 () {
		ExactDecimal a = new ExactDecimal(-6, 6);
		ExactDecimal b = new ExactDecimal(-6, -7);
		assertEquals(new ExactDecimal(-6, 7), a.multiply(b));
	}
	
	@Test
	public void mathMultiply1 () {
		ExactDecimal a = new ExactDecimal(6, -2);
		ExactDecimal b = new ExactDecimal(7, -1);
		assertEquals(new ExactDecimal(21, 1), a.multiply(b));
	}
	
	@Test
	public void mathMultiply2 () {
		ExactDecimal a = new ExactDecimal(5, 9);
		ExactDecimal b = new ExactDecimal(3, 4);
		assertEquals(new ExactDecimal(5, 12), a.multiply(b));
	}
	
	@Test
	public void mathMultiply3 () {
		ExactDecimal a = new ExactDecimal(-3, 8);
		ExactDecimal b = new ExactDecimal(-8, 8);
		assertEquals(new ExactDecimal(3, 8), a.multiply(b));
	}
	
	@Test
	public void mathMultiply4 () {
		ExactDecimal a = new ExactDecimal(6, -6);
		ExactDecimal b = new ExactDecimal(2, 6);
		assertEquals(new ExactDecimal(-2, 6), a.multiply(b));
	}
	
	@Test
	public void mathMultiply5 () {
		ExactDecimal a = new ExactDecimal(5, 2);
		ExactDecimal b = new ExactDecimal(-1, 9);
		assertEquals(new ExactDecimal(-5, 18), a.multiply(b));
	}
	
	@Test
	public void mathMultiply6 () {
		ExactDecimal a = new ExactDecimal(3, -8);
		ExactDecimal b = new ExactDecimal(5, -6);
		assertEquals(new ExactDecimal(5, 16), a.multiply(b));
	}
	
	@Test
	public void mathMultiply7 () {
		ExactDecimal a = new ExactDecimal(3, 8);
		ExactDecimal b = new ExactDecimal(-8, -5);
		assertEquals(new ExactDecimal(3, 5), a.multiply(b));
	}
	
	@Test
	public void mathDivide0 () {
		ExactDecimal a = new ExactDecimal(4, -7);
		ExactDecimal b = new ExactDecimal(6, 0);
		assertEquals(ExactDecimal.ZERO, a.divide(b));
	}
	
	@Test
	public void mathDivide1 () {
		ExactDecimal a = new ExactDecimal(-2, 1);
		ExactDecimal b = new ExactDecimal(-5, 9);
		assertEquals(new ExactDecimal(18, 5), a.divide(b));
	}
	
	@Test
	public void mathDivide2 () {
		ExactDecimal a = new ExactDecimal(-10, -10);
		ExactDecimal b = new ExactDecimal(6, 7);
		assertEquals(new ExactDecimal(7, 6), a.divide(b));
	}
	
	@Test
	public void mathDivide3 () {
		ExactDecimal a = new ExactDecimal(4, 8);
		ExactDecimal b = new ExactDecimal(6, -9);
		assertEquals(new ExactDecimal(-36, 48), a.divide(b));
	}
	
	@Test
	public void mathDivide4 () {
		ExactDecimal a = new ExactDecimal(8, 6);
		ExactDecimal b = new ExactDecimal(5, -2);
		assertEquals(new ExactDecimal(-8, 15), a.divide(b));
	}
	
	@Test
	public void mathDivide5 () {
		ExactDecimal a = new ExactDecimal(-4, 3);
		ExactDecimal b = new ExactDecimal(8, 5);
		assertEquals(new ExactDecimal(-5, 6), a.divide(b));
	}
	
	@Test
	public void mathDivide6 () {
		ExactDecimal a = new ExactDecimal(7, 6);
		ExactDecimal b = new ExactDecimal(10, -4);
		assertEquals(new ExactDecimal(-7, 15), a.divide(b));
	}
	
	@Test
	public void mathDivide7 () {
		ExactDecimal a = new ExactDecimal(-9, -4);
		ExactDecimal b = new ExactDecimal(1, -4);
		assertEquals(new ExactDecimal(-9, 1), a.divide(b));
	}
	
	@Test
	public void mathMin0 () {
		ExactDecimal a = ExactDecimal.POSITIVE_INFINITY;
		ExactDecimal b = ExactDecimal.NEGATIVE_INFINITY;
		assertEquals(ExactDecimal.NEGATIVE_INFINITY, ExactDecimal.min(a, b));
	}
	
	@Test
	public void mathMin1 () {
		ExactDecimal a = ExactDecimal.POSITIVE_INFINITY;
		ExactDecimal b = ExactDecimal.NOT_A_NUMBER;
		assertEquals(ExactDecimal.POSITIVE_INFINITY, ExactDecimal.min(a, b));
	}
	
	@Test
	public void mathMin2 () {
		ExactDecimal a = ExactDecimal.POSITIVE_INFINITY;
		ExactDecimal b = ExactDecimal.ONE;
		assertEquals(ExactDecimal.ONE, ExactDecimal.min(a, b));
	}
	
	@Test
	public void mathMin3 () {
		ExactDecimal a = ExactDecimal.NEGATIVE_INFINITY;
		ExactDecimal b = ExactDecimal.POSITIVE_INFINITY;
		assertEquals(ExactDecimal.NEGATIVE_INFINITY, ExactDecimal.min(a, b));
	}
	
	@Test
	public void mathMin4 () {
		ExactDecimal a = ExactDecimal.NEGATIVE_INFINITY;
		ExactDecimal b = ExactDecimal.NOT_A_NUMBER;
		assertEquals(ExactDecimal.NEGATIVE_INFINITY, ExactDecimal.min(a, b));
	}
	
	@Test
	public void mathMin5 () {
		ExactDecimal a = ExactDecimal.NEGATIVE_INFINITY;
		ExactDecimal b = ExactDecimal.ZERO;
		assertEquals(ExactDecimal.NEGATIVE_INFINITY, ExactDecimal.min(a, b));
	}
	
	@Test
	public void mathMin6 () {
		ExactDecimal a = ExactDecimal.NOT_A_NUMBER;
		ExactDecimal b = ExactDecimal.POSITIVE_INFINITY;
		assertEquals(ExactDecimal.POSITIVE_INFINITY, ExactDecimal.min(a, b));
	}
	
	@Test
	public void mathMin7 () {
		ExactDecimal a = ExactDecimal.NOT_A_NUMBER;
		ExactDecimal b = ExactDecimal.NEGATIVE_INFINITY;
		assertEquals(ExactDecimal.NEGATIVE_INFINITY, ExactDecimal.min(a, b));
	}
	
	@Test
	public void mathMin8 () {
		ExactDecimal a = ExactDecimal.NOT_A_NUMBER;
		ExactDecimal b = ExactDecimal.ZERO;
		assertEquals(ExactDecimal.ZERO, ExactDecimal.min(a, b));
	}
	
	@Test
	public void mathMin9 () {
		ExactDecimal a = ExactDecimal.ONE;
		ExactDecimal b = ExactDecimal.POSITIVE_INFINITY;
		assertEquals(ExactDecimal.ONE, ExactDecimal.min(a, b));
	}
	
	@Test
	public void mathMin10 () {
		ExactDecimal a = ExactDecimal.ONE;
		ExactDecimal b = ExactDecimal.NEGATIVE_INFINITY;
		assertEquals(ExactDecimal.NEGATIVE_INFINITY, ExactDecimal.min(a, b));
	}
	
	@Test
	public void mathMin11 () {
		ExactDecimal a = ExactDecimal.ONE;
		ExactDecimal b = ExactDecimal.NOT_A_NUMBER;
		assertEquals(ExactDecimal.ONE, ExactDecimal.min(a, b));
	}
	
	@Test
	public void mathMin12 () {
		ExactDecimal a = ExactDecimal.ONE;
		ExactDecimal b = ExactDecimal.ZERO;
		assertEquals(ExactDecimal.ZERO, ExactDecimal.min(a, b));
	}
	
	@Test
	public void mathMax0 () {
		ExactDecimal a = ExactDecimal.POSITIVE_INFINITY;
		ExactDecimal b = ExactDecimal.NEGATIVE_INFINITY;
		assertEquals(ExactDecimal.POSITIVE_INFINITY, ExactDecimal.max(a, b));
	}
	
	@Test
	public void mathMax1 () {
		ExactDecimal a = ExactDecimal.POSITIVE_INFINITY;
		ExactDecimal b = ExactDecimal.NOT_A_NUMBER;
		assertEquals(ExactDecimal.NOT_A_NUMBER, ExactDecimal.max(a, b));
	}
	
	@Test
	public void mathMax2 () {
		ExactDecimal a = ExactDecimal.POSITIVE_INFINITY;
		ExactDecimal b = ExactDecimal.ONE;
		assertEquals(ExactDecimal.POSITIVE_INFINITY, ExactDecimal.max(a, b));
	}
	
	@Test
	public void mathMax3 () {
		ExactDecimal a = ExactDecimal.NEGATIVE_INFINITY;
		ExactDecimal b = ExactDecimal.POSITIVE_INFINITY;
		assertEquals(ExactDecimal.POSITIVE_INFINITY, ExactDecimal.max(a, b));
	}
	
	@Test
	public void mathMax4 () {
		ExactDecimal a = ExactDecimal.NEGATIVE_INFINITY;
		ExactDecimal b = ExactDecimal.NOT_A_NUMBER;
		assertEquals(ExactDecimal.NOT_A_NUMBER, ExactDecimal.max(a, b));
	}
	
	@Test
	public void mathMax5 () {
		ExactDecimal a = ExactDecimal.NEGATIVE_INFINITY;
		ExactDecimal b = ExactDecimal.ZERO;
		assertEquals(ExactDecimal.ZERO, ExactDecimal.max(a, b));
	}
	
	@Test
	public void mathMax6 () {
		ExactDecimal a = ExactDecimal.NOT_A_NUMBER;
		ExactDecimal b = ExactDecimal.POSITIVE_INFINITY;
		assertEquals(ExactDecimal.NOT_A_NUMBER, ExactDecimal.max(a, b));
	}
	
	@Test
	public void mathMax7 () {
		ExactDecimal a = ExactDecimal.NOT_A_NUMBER;
		ExactDecimal b = ExactDecimal.NEGATIVE_INFINITY;
		assertEquals(ExactDecimal.NOT_A_NUMBER, ExactDecimal.max(a, b));
	}
	
	@Test
	public void mathMax8 () {
		ExactDecimal a = ExactDecimal.NOT_A_NUMBER;
		ExactDecimal b = ExactDecimal.ZERO;
		assertEquals(ExactDecimal.NOT_A_NUMBER, ExactDecimal.max(a, b));
	}
	
	@Test
	public void mathMax9 () {
		ExactDecimal a = ExactDecimal.ONE;
		ExactDecimal b = ExactDecimal.POSITIVE_INFINITY;
		assertEquals(ExactDecimal.POSITIVE_INFINITY, ExactDecimal.max(a, b));
	}
	
	@Test
	public void mathMax10 () {
		ExactDecimal a = ExactDecimal.ONE;
		ExactDecimal b = ExactDecimal.NEGATIVE_INFINITY;
		assertEquals(ExactDecimal.ONE, ExactDecimal.max(a, b));
	}
	
	@Test
	public void mathMax11 () {
		ExactDecimal a = ExactDecimal.ONE;
		ExactDecimal b = ExactDecimal.NOT_A_NUMBER;
		assertEquals(ExactDecimal.NOT_A_NUMBER, ExactDecimal.max(a, b));
	}
	
	@Test
	public void mathMax12 () {
		ExactDecimal a = ExactDecimal.ONE;
		ExactDecimal b = ExactDecimal.ZERO;
		assertEquals(ExactDecimal.ONE, ExactDecimal.max(a, b));
	}
	
	@Test
	public void toString00 () {
		ExactDecimal a = new ExactDecimal(1, 3);
		String s = a.toStringAdvanced(16);
		assertEquals("0.3333333333333333", s);
	}
	
	@Test
	public void toString01 () {
		ExactDecimal a = new ExactDecimal(3, 9);
		String s = a.toStringAdvanced(16);
		assertEquals("0.3333333333333333", s);
	}
	
	@Test
	public void toString02 () {
		ExactDecimal a = new ExactDecimal(7, -4);
		String s = a.toStringAdvanced(16);
		assertEquals("-1.7500000000000000", s);
	}
	
	@Test
	public void toString03 () {
		ExactDecimal a = new ExactDecimal(-2, 0);
		String s = a.toStringAdvanced(16);
		assertEquals("-Infinity", s);
	}
	
	@Test
	public void toString04 () {
		ExactDecimal a = new ExactDecimal(0, -2);
		String s = a.toStringAdvanced(16);
		assertEquals("0.0000000000000000", s);
	}
	
	@Test
	public void toString05 () {
		ExactDecimal a = new ExactDecimal(4, 5);
		String s = a.toStringAdvanced(16);
		assertEquals("0.8000000000000000", s);
	}
	
	@Test
	public void toString06 () {
		ExactDecimal a = new ExactDecimal(9, 4);
		String s = a.toStringAdvanced(16);
		assertEquals("2.2500000000000000", s);
	}
	
	@Test
	public void toString07 () {
		ExactDecimal a = new ExactDecimal(3, 1);
		String s = a.toStringAdvanced(16);
		assertEquals("3.0000000000000000", s);
	}
	
	@Test
	public void toString08 () {
		ExactDecimal a = new ExactDecimal(-1, 2);
		String s = a.toStringAdvanced(16);
		assertEquals("-0.5000000000000000", s);
	}
	
	@Test
	public void toString09 () {
		ExactDecimal a = new ExactDecimal(-10, 0);
		String s = a.toStringAdvanced(16);
		assertEquals("-Infinity", s);
	}
	
	@Test
	public void toString10 () {
		ExactDecimal a = new ExactDecimal(-8, 7);
		String s = a.toStringAdvanced(16);
		assertEquals("-1.1428571428571428", s);
	}
	
	@Test
	public void toString11 () {
		ExactDecimal a = new ExactDecimal(-8, 1);
		String s = a.toStringAdvanced(16);
		assertEquals("-8.0000000000000000", s);
	}
	
	@Test
	public void toString12 () {
		ExactDecimal a = new ExactDecimal(8, 1);
		String s = a.toStringAdvanced(16);
		assertEquals("8.0000000000000000", s);
	}
	
	@Test
	public void toString13 () {
		ExactDecimal a = new ExactDecimal(6, -4);
		String s = a.toStringAdvanced(16);
		assertEquals("-1.5000000000000000", s);
	}
	
	@Test
	public void toString14 () {
		ExactDecimal a = new ExactDecimal(-10, 10);
		String s = a.toStringAdvanced(-5);
		assertEquals("-1", s);
	}
	
	@Test
	public void toString15 () {
		ExactDecimal a = new ExactDecimal(-4, -7);
		String s = a.toStringAdvanced(-10);
		assertEquals("0", s);
	}
	
	@Test
	public void constructors00 () {
		String s = "";
		boolean b = false;
		try {
			ExactDecimal.stringToExactDecimal(s);
		} catch (NumberFormatException e) {
			b = true;
		}
		assertTrue(b);
	}
	
	@Test
	public void constructors01 () {
		String s = "Infinity";
		ExactDecimal a = ExactDecimal.stringToExactDecimal(s);
		assertEquals(s, a.toStringAdvanced(8));
	}
	
	@Test
	public void constructors02 () {
		String s = "+Infinity";
		ExactDecimal a = ExactDecimal.stringToExactDecimal(s);
		assertEquals("Infinity", a.toStringAdvanced(8));
	}
	
	@Test
	public void constructors03 () {
		String s = "-Infinity";
		ExactDecimal a = ExactDecimal.stringToExactDecimal(s);
		assertEquals(s, a.toStringAdvanced(8));
	}
	
	@Test
	public void constructors04 () {
		String s = "NaN";
		ExactDecimal a = ExactDecimal.stringToExactDecimal(s);
		assertEquals(s, a.toStringAdvanced(8));
	}
	
	@Test
	public void constructors05 () {
		String s = "3.14159";
		ExactDecimal a = ExactDecimal.stringToExactDecimal(s);
		assertEquals(s + "000", a.toStringAdvanced(8));
	}
	
	@Test
	public void constructors06 () {
		String s = "+314.159";
		ExactDecimal a = ExactDecimal.stringToExactDecimal(s);
		assertEquals("314.15900000", a.toStringAdvanced(8));
	}
	
	@Test
	public void constructors07 () {
		String s = "-31415.9";
		ExactDecimal a = ExactDecimal.stringToExactDecimal(s);
		assertEquals(s + "0000000", a.toStringAdvanced(8));
	}
	
	@Test
	public void constructors08 () {
		String s = "1E5";
		ExactDecimal a = ExactDecimal.stringToExactDecimal(s);
		assertEquals("100000.00000000", a.toStringAdvanced(8));
	}
	
	@Test
	public void constructors09 () {
		String s = "314159265358979323E-17";
		ExactDecimal a = ExactDecimal.stringToExactDecimal(s);
		assertEquals("3.14159265", a.toStringAdvanced(8));
	}
	
	@Test
	public void constructors10 () {
		String s = "271828.1828E-5";
		ExactDecimal a = ExactDecimal.stringToExactDecimal(s);
		assertEquals("2.71828182", a.toStringAdvanced(8));
	}
	
	@Test
	public void constructors11 () {
		String s = "734982";
		ExactDecimal a = ExactDecimal.stringToExactDecimal(s);
		assertEquals(s + ".00000000", a.toStringAdvanced(8));
	}
	
	@Test
	public void constructors12 () {
		String s = "0.00000000";
		ExactDecimal a = ExactDecimal.stringToExactDecimal(s);
		assertEquals(s, a.toStringAdvanced(8));
	}
	
	@Test
	public void constructors13 () {
		String s = "0.00000000";
		ExactDecimal a = ExactDecimal.stringToExactDecimal(s);
		assertEquals(s, a.toStringAdvanced(8));
	}
	
	@Test
	public void constructors14 () {
		String s = "0.00000000";
		ExactDecimal a = ExactDecimal.stringToExactDecimal(s);
		assertEquals(s, a.toStringAdvanced(8));
	}
	
	@Test
	public void constructors15 () {
		String s = "0.00000000";
		ExactDecimal a = ExactDecimal.stringToExactDecimal(s);
		assertEquals(s, a.toStringAdvanced(8));
	}
	
	@Test
	public void constructorByte0 () {
		byte b = 25;
		ExactDecimal ed = new ExactDecimal(b);
		assertEquals("25.00", ed.toString());
	}
	
	@Test
	public void constructorByte1 () {
		byte b = -23;
		ExactDecimal ed = new ExactDecimal(b);
		assertEquals("-23.00", ed.toString());
	}
	
	@Test
	public void constructorShort0 () {
		short s = 23789;
		ExactDecimal ed = new ExactDecimal(s);
		assertEquals("23789.00", ed.toString());
	}
	
	@Test
	public void constructorShort1 () {
		short s = -785;
		ExactDecimal ed = new ExactDecimal(s);
		assertEquals("-785.00", ed.toString());
	}
	
	@Test
	public void constructorInteger0 () {
		int i = 278045;
		ExactDecimal ed = new ExactDecimal(i);
		assertEquals("278045.00", ed.toString());
	}
	
	@Test
	public void constructorInteger1 () {
		int i = -3847;
		ExactDecimal ed = new ExactDecimal(i);
		assertEquals("-3847.00", ed.toString());
	}
	
	@Test
	public void constructorLong0 () {
		long l = 278043498557756757L;
		ExactDecimal ed = new ExactDecimal(l);
		assertEquals("278043498557756757.00", ed.toString());
	}
	
	@Test
	public void constructorLong1 () {
		long l = -37698528934732L;
		ExactDecimal ed = new ExactDecimal(l);
		assertEquals("-37698528934732.00", ed.toString());
	}
	
	@Test
	public void constructorFloat0 () {
		float f = Float.NaN;
		ExactDecimal ed = new ExactDecimal(f);
		assertEquals("NaN", ed.toString());
	}
	
	@Test
	public void constructorFloat1 () {
		float f = Float.NEGATIVE_INFINITY;
		ExactDecimal ed = new ExactDecimal(f);
		assertEquals("-Infinity", ed.toString());
	}
	
	@Test
	public void constructorFloat2 () {
		float f = Float.POSITIVE_INFINITY;
		ExactDecimal ed = new ExactDecimal(f);
		assertEquals("Infinity", ed.toString());
	}
	
	@Test
	public void constructorFloat3 () {
		float f = 0.3435f;
		ExactDecimal ed = new ExactDecimal(f);
		assertEquals("0.34", ed.toString());
	}
	
	@Test
	public void constructorFloat4 () {
		float f = -1E+3f;
		ExactDecimal ed = new ExactDecimal(f);
		assertEquals("-1000.00", ed.toString());
	}
	
	@Test
	public void constructorFloat5 () {
		float f = 2f;
		BigInteger bi = BigInteger.ONE.add(BigInteger.ONE);
		for (int i = 0; i < 6; i++) {
			f *= f;
			bi = bi.multiply(bi);
		}
		ExactDecimal ed = new ExactDecimal(f);
		assertEquals(bi + ".00", ed.toString());
	}
	
	@Test
	public void constructorDouble0 () {
		double d = Double.NaN;
		ExactDecimal ed = new ExactDecimal(d);
		assertEquals("NaN", ed.toString());
	}
	
	@Test
	public void constructorDouble1 () {
		double d = Double.POSITIVE_INFINITY;
		ExactDecimal ed = new ExactDecimal(d);
		assertEquals("Infinity", ed.toString());
	}
	
	@Test
	public void constructorDouble2 () {
		double d = Double.NEGATIVE_INFINITY;
		ExactDecimal ed = new ExactDecimal(d);
		assertEquals("-Infinity", ed.toString());
	}
	
	@Test
	public void constructorDouble3 () {
		double d = Math.PI;
		ExactDecimal ed = new ExactDecimal(d);
		assertEquals("3.14", ed.toString());
	}
	
	@Test
	public void constructorDouble4 () {
		double d = -3.62E-1;
		ExactDecimal ed = new ExactDecimal(d);
		assertEquals("-0.36", ed.toString());
	}
	
	@Test
	public void constructorDouble5 () {
		double d = 2;
		BigInteger bi = BigInteger.ONE.add(BigInteger.ONE);
		for (int i = 0; i < 8; i++) {
			d *= d;
			bi = bi.multiply(bi);
		}
		ExactDecimal ed = new ExactDecimal(d);
		assertEquals(bi + ".00", ed.toString());
	}
	
	@Test
	public void compareTo0 () {
		ExactDecimal a = ExactDecimal.ZERO;
		ExactDecimal b = ExactDecimal.ZERO.multiply(new ExactDecimal(-1, 1));
		assertEquals(0, a.compareTo(b));
	}
	
	@Test
	public void compareTo1 () {
		ExactDecimal a = ExactDecimal.ONE;
		ExactDecimal b = new ExactDecimal(-1, 1);
		assertEquals(1, a.compareTo(b));
	}
	
	@Test
	public void compareTo2 () {
		ExactDecimal a = ExactDecimal.NOT_A_NUMBER;
		ExactDecimal b = ExactDecimal.NOT_A_NUMBER;
		assertEquals(0, a.compareTo(b));
	}
	
	@Test
	public void compareTo3 () {
		ExactDecimal a = ExactDecimal.POSITIVE_INFINITY;
		ExactDecimal b = ExactDecimal.NOT_A_NUMBER;
		assertEquals(-1, a.compareTo(b));
	}
	
	@Test
	public void compareTo4 () {
		ExactDecimal a = ExactDecimal.POSITIVE_INFINITY;
		ExactDecimal b = ExactDecimal.POSITIVE_INFINITY;
		assertEquals(0, a.compareTo(b));
	}
	
	@Test
	public void compareTo5 () {
		ExactDecimal a = ExactDecimal.NEGATIVE_INFINITY;
		ExactDecimal b = ExactDecimal.NEGATIVE_INFINITY;
		assertEquals(0, a.compareTo(b));
	}
	
	@Test (expected = NullPointerException.class)
	public void compareTo6 () {
		ExactDecimal a = ExactDecimal.NEGATIVE_INFINITY;
		ExactDecimal b = null;
		a.compareTo(b);
	}
	
	@Test
	public void compareTo7 () {
		ExactDecimal a = new ExactDecimal(-1, 1);
		ExactDecimal b = new ExactDecimal(1, 2);
		assertEquals(-1, a.compareTo(b));
	}
	
	@Test
	public void equals0 () {
		ExactDecimal a = ExactDecimal.ZERO;
		ExactDecimal b = ExactDecimal.ZERO.multiply(new ExactDecimal(-1, 1));
		assertTrue(a.equals(b));
	}
	
	@Test
	public void equals1 () {
		ExactDecimal a = ExactDecimal.POSITIVE_INFINITY;
		ExactDecimal b = ExactDecimal.NEGATIVE_INFINITY;
		assertFalse(a.equals(b));
	}
	
	@Test
	public void equals2 () {
		ExactDecimal a = ExactDecimal.NOT_A_NUMBER;
		ExactDecimal b = ExactDecimal.NOT_A_NUMBER;
		assertTrue(a.equals(b));
	}
	
	@Test
	public void equals3 () {
		ExactDecimal a = ExactDecimal.POSITIVE_INFINITY;
		ExactDecimal b = ExactDecimal.NOT_A_NUMBER;
		assertFalse(a.equals(b));
	}
	
	@Test
	public void equals4 () {
		ExactDecimal a = ExactDecimal.POSITIVE_INFINITY;
		ExactDecimal b = ExactDecimal.POSITIVE_INFINITY;
		assertTrue(a.equals(b));
	}
	
	@Test
	public void equals5 () {
		ExactDecimal a = ExactDecimal.NEGATIVE_INFINITY;
		ExactDecimal b = ExactDecimal.NEGATIVE_INFINITY;
		assertTrue(a.equals(b));
	}
	
	@Test
	public void equals6 () {
		ExactDecimal a = ExactDecimal.NEGATIVE_INFINITY;
		ExactDecimal b = null;
		assertFalse(a.equals(b));
	}
	
	@Test
	public void equals7 () {
		ExactDecimal a = ExactDecimal.ONE;
		assertFalse(a.equals(new Object()));
	}
}
