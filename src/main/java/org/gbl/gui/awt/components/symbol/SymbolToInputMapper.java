package org.gbl.gui.awt.components.symbol;

import org.gbl.gui.presenter.CalculatorInput;

public final class SymbolToInputMapper {

    private SymbolToInputMapper() {
    }

    public static CalculatorInput map(Symbol symbol) {
        return switch (symbol.type()) {
            case DIGIT -> new CalculatorInput.Digit(symbol.text());
            case OPERATOR -> new CalculatorInput.Operator(symbol.text());
            case ACTION -> mapAction(symbol);
        };
    }

    private static CalculatorInput mapAction(Symbol symbol) {
        return switch (symbol) {
            case EQUALS -> new CalculatorInput.Evaluate();
            case CLEAR -> new CalculatorInput.Clear();
            case BACKSPACE -> new CalculatorInput.Backspace();
            case INVERT -> new CalculatorInput.InvertSignal();
            default -> throw new IllegalStateException("Unknown action: " + symbol);
        };
    }
}
