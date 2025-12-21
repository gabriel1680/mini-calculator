package org.gbl.gui.view;

import org.gbl.gui.controller.CalculatorInput;
import org.gbl.gui.controller.CalculatorView;

import java.awt.*;
import java.util.function.Consumer;

public class CalculatorViewImpl implements CalculatorView {

    private final ContainerFrame frame;
    private Consumer<CalculatorInput> inputListener;
    private final Display display;

    public CalculatorViewImpl() {
        frame = new ContainerFrame();
        display = new Display();
        frame.add(display, BorderLayout.NORTH);

        final var buttonsPanel = new ButtonsPanel(this::handleInput);
        frame.add(buttonsPanel, BorderLayout.CENTER);
    }

    private void handleInput(CalculatorInput input) {
        if (inputListener != null) {
            inputListener.accept(input);
        }
    }

    @Override
    public void clear() {
        display.setText("");
    }


    @Override
    public void onInput(Consumer<CalculatorInput> listener) {
        this.inputListener = listener;
    }

    @Override
    public void show() {
        frame.show();
    }

    @Override
    public void showResult(double result) {
        display.setText(String.valueOf(result));
    }

    @Override
    public void showInput(String input) {
        display.setText(input);
    }

    @Override
    public void showError(Exception exception) {
        display.setText("Error: %s".formatted(exception.getMessage()));
    }
}
