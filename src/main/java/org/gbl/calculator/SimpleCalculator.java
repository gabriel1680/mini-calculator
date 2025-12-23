package org.gbl.calculator;

import java.math.BigDecimal;
import java.math.MathContext;

class SimpleCalculator {

    private static final MathContext PRECISION = MathContext.DECIMAL64;

    double sum(double a, double b) {
        return toBigDecimal(a).add(toBigDecimal(b), PRECISION).doubleValue();
    }

    double multiply(double a, double b) {
        return toBigDecimal(a).multiply(toBigDecimal(b), PRECISION).doubleValue();
    }

    double divide(double a, double b) {
        return toBigDecimal(a).divide(toBigDecimal(b), PRECISION).doubleValue();
    }

    double subtract(double a, double b) {
        return toBigDecimal(a).subtract(toBigDecimal(b), PRECISION).doubleValue();
    }

    double module(double a, double b) {
        return toBigDecimal(a).remainder(toBigDecimal(b), PRECISION).doubleValue();
    }

    double squareRoot(double value) {
        return toBigDecimal(value).sqrt(PRECISION).doubleValue();
    }

    private static BigDecimal toBigDecimal(double a) {
        return BigDecimal.valueOf(a);
    }
}