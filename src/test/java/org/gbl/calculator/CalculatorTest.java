package org.gbl.calculator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    void withoutParenthesis() {
        assertThat(calculator.calculate("1+2")).isEqualTo(3);
        assertThat(calculator.calculate("5-1")).isEqualTo(4);
        assertThat(calculator.calculate("2-4")).isEqualTo(-2);
        assertThat(calculator.calculate("2*4")).isEqualTo(8);
        assertThat(calculator.calculate("2/4")).isEqualTo(0.5);
    }

    @Test
    void withSpaces() {
        assertThat(calculator.calculate("4 + 2")).isEqualTo(6);
        assertThat(calculator.calculate("4 / 2")).isEqualTo(2);
        assertThat(calculator.calculate("4 / 2")).isEqualTo(2);
    }

    @Test
    void withParenthesis() {
        assertThat(calculator.calculate("(4 + 2)")).isEqualTo(6);
    }

    @Test
    void multipleOperations() {
        assertThat(calculator.calculate("(4 + 2) * 4")).isEqualTo(24);
        assertThat(calculator.calculate("4 + 2 * 4")).isEqualTo(12);
        assertThat(calculator.calculate("2 + 3 * 4")).isEqualTo(14);
        assertThat(calculator.calculate("(2 + 3) * 4")).isEqualTo(20);
    }

    @Test
    void multipleDigits() {
        assertThat(calculator.calculate("10 + 5")).isEqualTo(15);
        assertThat(calculator.calculate("10 + 25")).isEqualTo(35);
        assertThat(calculator.calculate("158 + 155")).isEqualTo(313);
    }

    @Test
    void operatorsPrecedence() {
        assertThat(calculator.calculate("12 + 155 * 2 - 10")).isEqualTo(312);
        assertThat(calculator.calculate("12 + 200 / 2 * 10")).isEqualTo(1012);
    }
}