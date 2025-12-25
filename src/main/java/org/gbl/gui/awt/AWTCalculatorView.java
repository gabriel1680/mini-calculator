package org.gbl.gui.awt;

import org.gbl.gui.awt.components.ButtonsPanel;
import org.gbl.gui.awt.components.ContainerFrame;
import org.gbl.gui.awt.components.Display;
import org.gbl.gui.presenter.CalculatorInput;
import org.gbl.gui.presenter.CalculatorView;

import java.awt.*;
import java.util.function.Consumer;

public class AWTCalculatorView implements CalculatorView {

    private final ContainerFrame frame;
    private final ButtonsPanel buttonsPanel;
    private Consumer<CalculatorInput> inputListener;
    private final Display display;

    public AWTCalculatorView() {
        frame = new ContainerFrame();
        display = new Display();
        frame.add(display, BorderLayout.NORTH);

        buttonsPanel = new ButtonsPanel(this::handleInput);
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

    @Override
    public void switchTo(String mode) {
        buttonsPanel.switchTo(mode);
        frame.pack();
    }

    public Component[] getComponents() {
        return frame.getComponents();
    }
}
