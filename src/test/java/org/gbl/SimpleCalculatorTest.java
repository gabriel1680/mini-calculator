package org.gbl;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleCalculatorTest {

    private final SimpleCalculator calculator = new SimpleCalculator();

    @Test
    void sum() {
        assertThat(calculator.sum(4, 3)).isEqualTo(7);
        assertThat(calculator.sum(1.1, 1.2)).isEqualTo(2.3);
    }

    @Test
    void subtract() {
        assertThat(calculator.subtract(2, 1)).isEqualTo(1);
        assertThat(calculator.subtract(1.1, 1.0)).isEqualTo(0.1);
    }

    @Test
    void multiply() {
        assertThat(calculator.multiply(1, 2)).isEqualTo(2);
        assertThat(calculator.multiply(3, 3)).isEqualTo(9);
        assertThat(calculator.multiply(1.1, 2.0)).isEqualTo(2.2);
    }

    @Test
    void divide() {
        assertThat(calculator.divide(1, 2)).isEqualTo(0.5);
        assertThat(calculator.divide(1.5, 2.5)).isEqualTo(0.6);
    }
}