package org.gbl.gui.components;

import org.gbl.calculator.Calculator;

import java.awt.*;
import java.awt.event.ActionEvent;

public class CalculatorWindow {

    private final Display display;
    private final Calculator calculator;
    private final StringBuilder input;

    public CalculatorWindow(Calculator calculator) {
        this.calculator = calculator;
        input = new StringBuilder();
        final var frame = new ContainerFrame();
        display = new Display();
        frame.add(display, BorderLayout.NORTH);
        final var buttonsPanel = new ButtonsPanel(this::buttonPressed);
        frame.add(buttonsPanel, BorderLayout.CENTER);
        frame.show();
    }

    private void buttonPressed(ActionEvent e) {
        final Button source = (Button) e.getSource();
        final String label = source.getLabel();
        switch (label) {
            case "=" -> handleEvaluation();
            case "CE" -> handleClear();
            case "←" -> handleBackspace();
            default -> handleAppendLabel(label);
        }
    }

    private void handleAppendLabel(String label) {
        input.append(label);
        display.setText(input.toString());
    }

    private void handleBackspace() {
        if (!input.isEmpty()) {
            input.deleteCharAt(input.length() - 1);
            display.setText(input.toString());
        }
    }

    private void handleClear() {
        input.setLength(0);
        display.setText("");
    }

    private void handleEvaluation() {
        try {
            final double result = calculator.calculate(input.toString());
            display.setText(String.valueOf(result));
            input.setLength(0);
        } catch (Exception ex) {
            display.setText("Error: %s".formatted(ex.getMessage()));
            input.setLength(0);
        }
    }
}
