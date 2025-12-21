package org.gbl;

import java.math.BigDecimal;
import java.math.MathContext;

public class SimpleCalculator {

    private static final MathContext PRECISION = MathContext.DECIMAL64;

    public double sum(double a, double b) {
        return toBigDecimal(a).add(toBigDecimal(b), PRECISION).doubleValue();
    }

    public double multiply(double a, double b) {
        return toBigDecimal(a).multiply(toBigDecimal(b), PRECISION).doubleValue();
    }

    public double divide(double a, double b) {
        return toBigDecimal(a).divide(toBigDecimal(b), PRECISION).doubleValue();
    }

    public double subtract(double a, double b) {
        return toBigDecimal(a).subtract(toBigDecimal(b), PRECISION).doubleValue();
    }

    private static BigDecimal toBigDecimal(double a) {
        return BigDecimal.valueOf(a);
    }
}