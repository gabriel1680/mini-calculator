package org.gbl.gui.view.mode;

import java.awt.*;

public sealed interface Mode permits BasicMode, ScientificMode {
    String[] labels();

    GridLayout layout();
}
