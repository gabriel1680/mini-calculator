package org.gbl.gui.view.mode;

import java.awt.*;

public final class ScientificMode implements Mode {
    @Override
    public String[] labels() {
        return new String[]{
                "sin", "cos", "tan", "√",
                "(", ")", "^", "%",
                "←", "+/-", "%", "/",
                "7", "8", "9", "*",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "CE", "0", ".", "=",
        };
    }

    @Override
    public GridLayout layout() {
        return new GridLayout(7, 4, 5, 5);
    }
}
