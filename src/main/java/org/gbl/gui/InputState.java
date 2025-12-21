package org.gbl.gui;

public class InputState {

    private final StringBuilder input = new StringBuilder();
    private boolean showingResult = false;

    public void append(String value) {
        if (showingResult && isDigit(value)) {
            input.setLength(0); // start new expression
        }
        showingResult = false;
        input.append(value);
    }

    public void clear() {
        input.setLength(0);
        showingResult = false;
    }

    public void backspace() {
        if (!input.isEmpty()) {
            input.deleteCharAt(input.length() - 1);
        }
        showingResult = false;
    }

    public void setResult(double value) {
        input.setLength(0);
        input.append(value);
        showingResult = true;
    }

    public String getInput() {
        return input.toString();
    }

    public boolean isShowingResult() {
        return showingResult;
    }

    private boolean isDigit(String value) {
        return value.length() == 1 && Character.isDigit(value.charAt(0));
    }
}
