package org.gbl.gui;

import org.gbl.calculator.Calculator;

public class ViewController {

    private final Calculator calculator;
    private final CalculatorView view;
    private final InputState state;

    public ViewController(Calculator calculator, CalculatorView view) {
        this.calculator = calculator;
        this.view = view;
        this.state = new InputState();
    }

    public void show() {
        view.show();
    }

    public void handle(CalculatorInput input) {
        switch (input) {
            case CalculatorInput.Clear c -> clear();
            case CalculatorInput.Backspace b -> backspace();
            case CalculatorInput.Evaluate e -> evaluate();
            case CalculatorInput.Digit digit -> append(digit.value());
            case CalculatorInput.Operator operator -> append(operator.value());
        }
    }

    private void append(String value) {
        state.append(value);
        view.showInput(state.getInput());
    }

    private void backspace() {
        state.backspace();
        view.showInput(state.getInput());
    }

    private void clear() {
        state.clear();
        view.clear();
    }

    private void evaluate() {
        try {
            final var result = calculator.calculate(state.getInput());
            view.showResult(result);
            state.setResult(result);
        } catch (Exception e) {
            view.showError(e);
            state.clear();
        }
    }
}
