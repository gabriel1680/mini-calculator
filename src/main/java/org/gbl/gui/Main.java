package org.gbl.gui;

import org.gbl.calculator.Calculator;
import org.gbl.gui.presenter.CalculatorPresenter;
import org.gbl.gui.awt.AWTCalculatorView;

public class Main {
    public static void main(String[] args) {
        final var calculator = new Calculator();
        final var view = new AWTCalculatorView();
        final var presenter = new CalculatorPresenter(calculator, view);
        presenter.showView();
    }
}
