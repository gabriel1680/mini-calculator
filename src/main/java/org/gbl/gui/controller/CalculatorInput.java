package org.gbl.gui.controller;

import org.gbl.gui.controller.CalculatorInput.Backspace;
import org.gbl.gui.controller.CalculatorInput.Clear;
import org.gbl.gui.controller.CalculatorInput.Digit;
import org.gbl.gui.controller.CalculatorInput.Evaluate;
import org.gbl.gui.controller.CalculatorInput.Operator;

public sealed interface CalculatorInput
        permits Digit, Operator, Evaluate, Clear, Backspace {

    record Digit(String value) implements CalculatorInput {}

    record Operator(String value) implements CalculatorInput {}

    record Evaluate() implements CalculatorInput {}

    record Clear() implements CalculatorInput {}

    record Backspace() implements CalculatorInput {}
}
