package org.gbl.gui;

import org.gbl.gui.controller.CalculatorInput;
import org.gbl.gui.controller.CalculatorView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

class FakeCalculatorView implements CalculatorView {

    private String lastText;
    private String lastError;
    private String lastMode;
    private double lastResult = -1.0;
    private boolean showing = false;
    private final Collection<Consumer<CalculatorInput>> consumers = new ArrayList<>();

    public String lastMode() {
        return lastMode;
    }

    public String lastText() {
        return lastText;
    }

    public String lastError() {
        return lastError;
    }

    public Double lastResult() {
        return lastResult;
    }

    public boolean showing() {
        return showing;
    }

    public Collection<Consumer<CalculatorInput>> consumers() {
        return consumers;
    }

    @Override
    public void showResult(double value) {
        lastResult = value;
        lastText = String.valueOf(value);
    }

    @Override
    public void showInput(String input) {
        lastText = input;
    }

    @Override
    public void showError(Exception exception) {
        lastError = exception.toString();
    }

    @Override
    public void switchTo(String mode) {
        lastMode = mode;
    }

    @Override
    public void clear() {
        lastText = "";
    }

    @Override
    public void onInput(Consumer<CalculatorInput> consumer) {
        this.consumers.add(consumer);
    }

    @Override
    public void show() {
        showing = true;
    }
}
