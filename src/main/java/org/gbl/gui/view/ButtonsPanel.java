package org.gbl.gui.view;

import org.gbl.gui.controller.CalculatorInput;
import org.gbl.gui.view.mode.BasicMode;
import org.gbl.gui.view.mode.CalculatorMode;
import org.gbl.gui.view.mode.Mode;

import java.awt.*;
import java.util.function.Consumer;

final class ButtonsPanel implements Presentable {

    private final Panel root;
    private final Panel gridPanel;
    private final Consumer<CalculatorInput> consumer;

    ButtonsPanel(Consumer<CalculatorInput> buttonPressedConsumer) {
        this.consumer = buttonPressedConsumer;
        this.root = new Panel(new BorderLayout());
        this.gridPanel = new Panel();
        root.add(createModeSelector(consumer), BorderLayout.NORTH);
        root.add(gridPanel, BorderLayout.CENTER);
        setMode(new BasicMode()); // set default
    }

    @Override
    public Component getComponent() {
        return root;
    }

    public void switchTo(String mode) {
        setMode(CalculatorMode.of(mode).getMode());
    }

    private void setMode(Mode mode) {
        gridPanel.removeAll();
        gridPanel.setLayout(mode.layout());
        for (String label : mode.labels()) {
            gridPanel.add(createButton(consumer, label));
        }
        gridPanel.revalidate();
        gridPanel.repaint();
    }

    private Panel createModeSelector(Consumer<CalculatorInput> consumer) {
        final var choice = new Choice();
        for (var mode : CalculatorMode.values()) {
            choice.add(mode.name());
        }
        choice.addItemListener(
                e -> consumer.accept(new CalculatorInput.Mode(choice.getSelectedItem())));
        final var wrapper = new Panel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        // prevent stretching
        wrapper.add(choice);
        return wrapper;
    }

    private static Button createButton(Consumer<CalculatorInput> consumer, String label) {
        Button button = new Button(label);
        button.addActionListener(e -> {
            final var input = mapLabel(label);
            consumer.accept(input);
        });
        return button;
    }

    private static CalculatorInput mapLabel(String label) {
        return switch (label) {
            case "+/-" -> new CalculatorInput.InvertSignal();
            case "CE" -> new CalculatorInput.Clear();
            case "←" -> new CalculatorInput.Backspace();
            case "=" -> new CalculatorInput.Evaluate();
            case "+", "-", "*", "/", "(", ")", "%" -> new CalculatorInput.Operator(label);
            default -> new CalculatorInput.Digit(label);
        };
    }
}
