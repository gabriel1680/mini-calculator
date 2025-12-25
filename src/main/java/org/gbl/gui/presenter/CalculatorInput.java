package org.gbl.gui.presenter;

import org.gbl.gui.presenter.CalculatorInput.Backspace;
import org.gbl.gui.presenter.CalculatorInput.Clear;
import org.gbl.gui.presenter.CalculatorInput.Digit;
import org.gbl.gui.presenter.CalculatorInput.Evaluate;
import org.gbl.gui.presenter.CalculatorInput.InvertSignal;
import org.gbl.gui.presenter.CalculatorInput.Mode;
import org.gbl.gui.presenter.CalculatorInput.Operator;

public sealed interface CalculatorInput
        permits Backspace, Clear, Digit, Evaluate, InvertSignal, Mode, Operator {

    record Digit(String value) implements CalculatorInput {}

    record Operator(String value) implements CalculatorInput {}

    record Evaluate() implements CalculatorInput {}

    record Clear() implements CalculatorInput {}

    record InvertSignal() implements CalculatorInput {}

    record Backspace() implements CalculatorInput {}

    record Mode(String value) implements CalculatorInput {}
}
