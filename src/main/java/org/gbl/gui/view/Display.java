package org.gbl.gui.view;

import java.awt.*;

class Display implements Presentable {

    private final Panel panel;
    private final TextField textField;

    Display() {
        this.textField = createTextField();
        this.panel = createPanel(textField);
    }

    private static Panel createPanel(TextField textField) {
        Panel panel = new Panel(new BorderLayout());
        panel.add(textField, BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(320, 60)); // height = 60 pixels
        return panel;
    }

    private static TextField createTextField() {
        final var textfield = new TextField();
        textfield.setEditable(false);
        textfield.setBackground(Color.LIGHT_GRAY);
        textfield.setFont(new Font("Arial", Font.BOLD, 24));
        textfield.setEditable(false);
        textfield.setBackground(Color.LIGHT_GRAY);
        return textfield;
    }

    @Override
    public Component getComponent() {
        return panel;
    }

    public void setText(String text) {
        textField.setText(text);
    }
}
