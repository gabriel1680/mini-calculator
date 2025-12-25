package org.gbl.gui.presenter;

import org.gbl.calculator.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorPresenterTest {

    private FakeCalculatorView view;
    private CalculatorPresenter presenter;

    @BeforeEach
    void setup() {
        view = new FakeCalculatorView();
        presenter = new CalculatorPresenter(new Calculator(), view);
    }

    @Test
    void appendingDigitsUpdatesDisplay() {
        presenter.handle(new CalculatorInput.Digit("1"));
        presenter.handle(new CalculatorInput.Operator("+"));
        presenter.handle(new CalculatorInput.Digit("2"));

        assertThat(view.lastText()).isEqualTo("1+2");
    }

    @Test
    void evaluationShowsResult() {
        presenter.handle(new CalculatorInput.Digit("2"));
        presenter.handle(new CalculatorInput.Operator("*"));
        presenter.handle(new CalculatorInput.Digit("3"));
        presenter.handle(new CalculatorInput.Evaluate());
        assertThat(view.lastResult()).isEqualTo(6.0);
    }

    @Test
    void resultCanBeUsedInNextOperation() {
        // eval 1
        presenter.handle(new CalculatorInput.Digit("2"));
        presenter.handle(new CalculatorInput.Operator("+"));
        presenter.handle(new CalculatorInput.Digit("3"));
        presenter.handle(new CalculatorInput.Evaluate());
        // eval 2
        presenter.handle(new CalculatorInput.Operator("*"));
        presenter.handle(new CalculatorInput.Digit("4"));
        presenter.handle(new CalculatorInput.Evaluate());
        assertThat(view.lastResult()).isEqualTo(20);
    }

    @Test
    void digitAfterEvaluationStartsNewExpression() {
        presenter.handle(new CalculatorInput.Digit("9"));
        presenter.handle(new CalculatorInput.Evaluate());

        presenter.handle(new CalculatorInput.Digit("3"));
        assertThat(view.lastText()).isEqualTo("3");
    }

    @Test
    void clearResetsInput() {
        presenter.handle(new CalculatorInput.Digit("8"));
        presenter.handle(new CalculatorInput.Clear());
        assertThat(view.lastText()).isEqualTo("");
    }

    @Test
    void invalidExpressionShowsError() {
        presenter.handle(new CalculatorInput.Operator("&"));
        presenter.handle(new CalculatorInput.Evaluate());
        assertThat(view.lastError()).isNotNull();
    }

    @Test
    void backspace() {
        presenter.handle(new CalculatorInput.Operator("1"));
        presenter.handle(new CalculatorInput.Operator("2"));
        presenter.handle(new CalculatorInput.Backspace());
        assertThat(view.lastText()).isEqualTo("1");
    }

    @Test
    void doubleBackspace() {
        presenter.handle(new CalculatorInput.Operator("1"));
        presenter.handle(new CalculatorInput.Operator("2"));
        presenter.handle(new CalculatorInput.Backspace());
        presenter.handle(new CalculatorInput.Backspace());
        assertThat(view.lastText()).isEqualTo("");
    }

    @Test
    void doubleBackspaceAfterIsEmpty() {
        presenter.handle(new CalculatorInput.Operator("1"));
        presenter.handle(new CalculatorInput.Backspace());
        presenter.handle(new CalculatorInput.Backspace());
        assertThat(view.lastText()).isEqualTo("");
    }

    @Test
    void show() {
        assertThat(view.showing()).isFalse();
        presenter.showView();
        assertThat(view.showing()).isTrue();
    }

    @Test
    void registerInputListenersOnConstruct() {
        assertThat(view.consumers()).size().isEqualTo(1);
    }

    @Test
    void changeMode() {
        var mode = "BASIC";
        view.switchTo(mode);
        assertThat(view.lastMode()).isEqualTo(mode);

        mode = "SCIENTIFIC";
        view.switchTo(mode);
        assertThat(view.lastMode()).isEqualTo(mode);
    }
}
