package org.gbl.gui.view.mode;

public enum CalculatorMode {
    BASIC(new BasicMode()),
    SCIENTIFIC(new ScientificMode());

    private final Mode mode;

    CalculatorMode(Mode mode) {
        this.mode = mode;
    }

    public String stringValue() {
        return name();
    }

    public Mode getMode() {
        return mode;
    }

    public static CalculatorMode of(String name) {
        for (CalculatorMode value : values()) {
            if (value.name().equalsIgnoreCase(name)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Unknown mode: " + name);
    }
}
