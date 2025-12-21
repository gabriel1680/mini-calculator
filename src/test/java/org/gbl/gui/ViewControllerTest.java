package org.gbl.gui;

import org.gbl.calculator.Calculator;
import org.gbl.gui.controller.CalculatorInput;
import org.gbl.gui.controller.ViewController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ViewControllerTest {

    private FakeCalculatorView view;
    private ViewController controller;

    @BeforeEach
    void setup() {
        view = new FakeCalculatorView();
        controller = new ViewController(new Calculator(), view);
    }

    @Test
    void appendingDigitsUpdatesDisplay() {
        controller.handle(new CalculatorInput.Digit("1"));
        controller.handle(new CalculatorInput.Operator("+"));
        controller.handle(new CalculatorInput.Digit("2"));

        assertThat(view.lastText()).isEqualTo("1+2");
    }

    @Test
    void evaluationShowsResult() {
        controller.handle(new CalculatorInput.Digit("2"));
        controller.handle(new CalculatorInput.Operator("*"));
        controller.handle(new CalculatorInput.Digit("3"));
        controller.handle(new CalculatorInput.Evaluate());
        assertThat(view.lastResult()).isEqualTo(6.0);
    }

    @Test
    void resultCanBeUsedInNextOperation() {
        // eval 1
        controller.handle(new CalculatorInput.Digit("2"));
        controller.handle(new CalculatorInput.Operator("+"));
        controller.handle(new CalculatorInput.Digit("3"));
        controller.handle(new CalculatorInput.Evaluate());
        // eval 2
        controller.handle(new CalculatorInput.Operator("*"));
        controller.handle(new CalculatorInput.Digit("4"));
        controller.handle(new CalculatorInput.Evaluate());
        assertThat(view.lastResult()).isEqualTo(20);
    }

    @Test
    void digitAfterEvaluationStartsNewExpression() {
        controller.handle(new CalculatorInput.Digit("9"));
        controller.handle(new CalculatorInput.Evaluate());

        controller.handle(new CalculatorInput.Digit("3"));
        assertThat(view.lastText()).isEqualTo("3");
    }

    @Test
    void clearResetsInput() {
        controller.handle(new CalculatorInput.Digit("8"));
        controller.handle(new CalculatorInput.Clear());
        assertThat(view.lastText()).isEqualTo("");
    }

    @Test
    void invalidExpressionShowsError() {
        controller.handle(new CalculatorInput.Operator("&"));
        controller.handle(new CalculatorInput.Evaluate());
        assertThat(view.lastError()).isNotNull();
    }
}
