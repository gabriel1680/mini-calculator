package org.gbl.gui.presenter;

class InputState {

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

    public void invertSignal() {
        if (hasNegation()) {
            removeNegation();
        } else if (!input.isEmpty()) {
            addNegation();
        }
    }

    private boolean hasNegation() {
        return input.length() >= 3
                && input.indexOf("(-") == 0
                && input.charAt(input.length() - 1) == ')';
    }

    private void removeNegation() {
        input.delete(0, 2);
        input.deleteCharAt(input.length() - 1);
    }

    private void addNegation() {
        input.insert(0, "(-").append(")");
    }

    private boolean isDigit(String value) {
        return value.length() == 1 && Character.isDigit(value.charAt(0));
    }
}
