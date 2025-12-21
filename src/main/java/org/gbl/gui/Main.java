package org.gbl.gui;

import org.gbl.calculator.Calculator;
import org.gbl.gui.controller.ViewController;
import org.gbl.gui.view.CalculatorViewImpl;

public class Main {
    public static void main(String[] args) {
        final var calculator = new Calculator();
        final var view = new CalculatorViewImpl();
        final var controller = new ViewController(calculator, view);
        view.onInput(controller::handle);
        controller.show();
    }
}
