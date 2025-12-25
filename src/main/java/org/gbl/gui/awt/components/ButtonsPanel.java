package org.gbl.gui.awt.components;

import org.gbl.gui.awt.components.mode.BasicMode;
import org.gbl.gui.awt.components.mode.CalculatorMode;
import org.gbl.gui.awt.components.mode.Mode;
import org.gbl.gui.awt.components.symbol.Symbol;
import org.gbl.gui.awt.components.symbol.SymbolToInputMapper;
import org.gbl.gui.presenter.CalculatorInput;

import java.awt.*;
import java.util.function.Consumer;

public final class ButtonsPanel implements Presentable {

    private final Panel root;
    private final Panel gridPanel;
    private final Consumer<CalculatorInput> consumer;

    public ButtonsPanel(Consumer<CalculatorInput> buttonPressedConsumer) {
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
        mode.symbols().forEach(symbol -> gridPanel.add(createButton(symbol)));
        gridPanel.revalidate();
        gridPanel.repaint();
        root.invalidate();
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

    private Button createButton(Symbol symbol) {
        Button button = new Button(symbol.text());
        button.addActionListener(e -> consumer.accept(SymbolToInputMapper.map(symbol)));
        return button;
    }
}
