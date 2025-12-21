package org.gbl.gui.components;

import org.gbl.calculator.Calculator;

import java.awt.*;

public class CalculatorViewImpl implements CalculatorView {

    private final ViewController controller;
    private final Display display;

    public CalculatorViewImpl(Calculator calculator) {
        this.controller = new ViewController(calculator, this);

        final var frame = new ContainerFrame();
        display = new Display();
        frame.add(display, BorderLayout.NORTH);

        final var buttonsPanel = new ButtonsPanel(this.controller::handle);
        frame.add(buttonsPanel, BorderLayout.CENTER);

        frame.show();
    }

    @Override
    public void showText(String text) {
        display.setText(text);
    }

    @Override
    public void clearText() {
        display.setText("");
    }
}
