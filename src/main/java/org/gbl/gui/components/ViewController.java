package org.gbl.gui.components;

import org.gbl.calculator.Calculator;

public class ViewController {

    private final Calculator calculator;
    private final CalculatorViewImpl view;
    private final StringBuilder input;

    public ViewController(Calculator calculator, CalculatorViewImpl view) {
        this.calculator = calculator;
        this.view = view;
        this.input = new StringBuilder();
    }

    public void handle(CalculatorInput input) {
        switch (input) {
            case CalculatorInput.Clear c -> clear();
            case CalculatorInput.Backspace b -> backspace();
            case CalculatorInput.Evaluate e -> evaluate();
            case CalculatorInput.Digit digit -> appendLabel(digit.value());
            case CalculatorInput.Operator operator -> appendLabel(operator.value());
        }
    }

    private void appendLabel(String label) {
        input.append(label);
        view.showText(input.toString());
    }

    private void backspace() {
        if (!input.isEmpty()) {
            input.deleteCharAt(input.length() - 1);
            view.showText(input.toString());
        }
    }

    private void clear() {
        clearInputBuffer();
        view.clearText();
    }

    private void evaluate() {
        try {
            double result = calculator.calculate(input.toString());
            view.showText(String.valueOf(result));
            clearInputBuffer();
        } catch (Exception e) {
            view.showText("Error: " + e.getMessage());
            clearInputBuffer();
        }
    }

    private void clearInputBuffer() {
        input.setLength(0);
    }
}
