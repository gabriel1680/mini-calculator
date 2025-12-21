package org.gbl.gui.components;

import org.gbl.gui.components.CalculatorInput.Backspace;
import org.gbl.gui.components.CalculatorInput.Clear;
import org.gbl.gui.components.CalculatorInput.Digit;
import org.gbl.gui.components.CalculatorInput.Evaluate;
import org.gbl.gui.components.CalculatorInput.Operator;

public sealed interface CalculatorInput
        permits Digit, Operator, Evaluate, Clear, Backspace {

    record Digit(String value) implements CalculatorInput {}

    record Operator(String value) implements CalculatorInput {}

    record Evaluate() implements CalculatorInput {}

    record Clear() implements CalculatorInput {}

    record Backspace() implements CalculatorInput {}
}
