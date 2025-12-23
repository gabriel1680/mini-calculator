package org.gbl.calculator;

import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    void naturalNumbers() {
        assertThat(calculator.calculate("1+2")).isEqualTo(3);
        assertThat(calculator.calculate("5-1")).isEqualTo(4);
        assertThat(calculator.calculate("2-4")).isEqualTo(-2);
        assertThat(calculator.calculate("2*4")).isEqualTo(8);
        assertThat(calculator.calculate("2/4")).isEqualTo(0.5);
        assertThat(calculator.calculate("5%2")).isEqualTo(1);
    }

    @Test
    void decimalNumbers() {
        assertThat(calculator.calculate("1.1+1.2")).isEqualTo(2.3);
        assertThat(calculator.calculate("1.1*10")).isEqualTo(11);
        assertThat(calculator.calculate("1.2-0.3")).isEqualTo(0.9);
        assertThat(calculator.calculate("1.1-1.2")).isEqualTo(-0.1);
        assertThat(calculator.calculate("2.0*5")).isEqualTo(10);
        assertThat(calculator.calculate("2.2/5.5")).isEqualTo(0.4);
        assertThat(calculator.calculate("√4")).isEqualTo(2);
    }

    @Test
    void invalidDecimalNumber() {
        assertThatThrownBy(() -> calculator.calculate("1.1.2+1.2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid decimal number");
    }

    @Test
    void withSpaces() {
        assertThat(calculator.calculate("4 + 2")).isEqualTo(6);
        assertThat(calculator.calculate("4 / 2")).isEqualTo(2);
        assertThat(calculator.calculate("4 * 2")).isEqualTo(8);
    }

    @Test
    void withParenthesis() {
        assertThat(calculator.calculate("(4 + 2)")).isEqualTo(6);
        assertThat(calculator.calculate("(4 + 2) + (5 - 8)")).isEqualTo(3);
        assertThat(calculator.calculate("(4 + 2) + (5 - 8) - (1 - 1)")).isEqualTo(3);
        assertThat(calculator.calculate("((4 + 2) - 3) + 4")).isEqualTo(7);
    }

    @Test
    void invalidParenthesis() {
        assertThatThrownBy(() -> calculator.calculate("4 + 2)"))
                .isInstanceOf(EmptyStackException.class);
    }

    @Test
    void invalidOperation() {
        assertThatThrownBy(() -> calculator.calculate("4 $ 2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Unknown operator: '$'");
    }

    @Test
    void multipleDigits() {
        assertThat(calculator.calculate("10 + 5")).isEqualTo(15);
        assertThat(calculator.calculate("10 - 25")).isEqualTo(-15);
        assertThat(calculator.calculate("158 + 155")).isEqualTo(313);
        assertThat(calculator.calculate("122 * 122")).isEqualTo(14884);
        assertThat(calculator.calculate("100 / 1000")).isEqualTo(0.1);
    }

    @Test
    void operatorsPrecedence() {
        assertThat(calculator.calculate("12 + 155 * 2 - 10")).isEqualTo(312);
        assertThat(calculator.calculate("12 + 200 / 2 * 10")).isEqualTo(1012);
        assertThat(calculator.calculate("2 * √16 + 4")).isEqualTo(12);
    }
}