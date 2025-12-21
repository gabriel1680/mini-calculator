package org.gbl.gui;

class FakeCalculatorView implements CalculatorView {

    private String lastText;
    private String lastError;
    private Double lastResult;

    public String lastText() {
        return lastText;
    }

    public String lastError() {
        return lastError;
    }

    public Double lastResult() {
        return lastResult;
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
    public void clear() {
        lastText = "";
    }

    @Override
    public void onInput(java.util.function.Consumer<CalculatorInput> listener) {
        // not needed for controller tests
    }

    @Override
    public void show() {
        // not needed for controller tests
    }
}
