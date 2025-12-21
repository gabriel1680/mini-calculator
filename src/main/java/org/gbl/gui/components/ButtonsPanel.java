package org.gbl.gui.components;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

class ButtonsPanel implements Presentable {
    private final Panel panel;

    ButtonsPanel(Consumer<ActionEvent> buttonPressedConsumer) {
        this.panel = createPanel(buttonPressedConsumer);
    }

    private static Panel createPanel(Consumer<ActionEvent> consumer) {
        Panel buttonsPanel = new Panel(new GridLayout(5, 4, 5, 5));
        String[] buttonsIds = {
                "C", "←", "(", ")",
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"};
        for (String id : buttonsIds) {
            Button button = new Button(id);
            button.addActionListener(consumer::accept);
            buttonsPanel.add(button);
        }
        return buttonsPanel;
    }

    @Override
    public Component getComponent() {
        return panel;
    }
}
