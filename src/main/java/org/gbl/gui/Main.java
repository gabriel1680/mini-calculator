package org.gbl.gui;

import org.gbl.calculator.Calculator;
import org.gbl.gui.controller.ViewController;
import org.gbl.gui.view.AWTCalculatorView;

public class Main {
    public static void main(String[] args) {
        final var calculator = new Calculator();
        final var view = new AWTCalculatorView();
        final var controller = new ViewController(calculator, view);
        view.onInput(controller::handle);
        controller.show();
    }
}
