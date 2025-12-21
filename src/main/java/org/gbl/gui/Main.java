package org.gbl.gui;

import org.gbl.calculator.Calculator;
import org.gbl.gui.components.CalculatorViewImpl;

public class Main {
    public static void main(String[] args) {
        final var calculator = new Calculator();
        new CalculatorViewImpl(calculator);
    }
}
