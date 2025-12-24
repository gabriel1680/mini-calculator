package org.gbl.gui.integration;

import org.gbl.calculator.Calculator;
import org.gbl.gui.awt.AWTCalculatorView;
import org.gbl.gui.controller.ViewController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class AWTCalculatorDSL {

    public static final String[] BASIC_BUTTONS = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "CE", "←", "+/-", "%"
    };

    public static final String[] SCIENTIFIC_BUTTONS = {
            "sin", "cos", "tan", "√", "(", ")"
    };

    private static AWTCalculatorDSL instance;

    private final AWTCalculatorView view;

    private AWTCalculatorDSL() throws Exception {
        this.view = createViewOnEDT();
        Calculator calculator = new Calculator();
        ViewController controller = new ViewController(calculator, view);
        view.onInput(controller::handle);
    }

    public static AWTCalculatorDSL calculator() throws Exception {
        if (instance == null) {
            instance = new AWTCalculatorDSL();
        }
        return instance;
    }

    /**
     * Type a sequence of buttons
     */
    public AWTCalculatorDSL type(String... keys) {
        for (String key : keys) {
            click(key);
        }
        return this;
    }

    public AWTCalculatorDSL equals() {
        click("=");
        return this;
    }

    public AWTCalculatorDSL clear() {
        click("CE");
        return this;
    }

    public AWTCalculatorDSL backspace() {
        click("←");
        return this;
    }

    public AWTCalculatorDSL invert() {
        click("+/-");
        return this;
    }

    public AWTCalculatorDSL basicMode() {
        view.switchTo("BASIC");
        return this;
    }

    public AWTCalculatorDSL scientificMode() {
        view.switchTo("SCIENTIFIC");
        return this;
    }

    /**
     * Assert the display shows expected value
     */
    public AWTCalculatorDSL shouldDisplay(String expected) {
        TextField display = ComponentFinder.findAll(view.getComponents(), TextField.class)
                .stream()
                .findFirst()
                .orElseThrow(() -> new AssertionError("Display TextField not found"));
        assertThat(display.getText()).isEqualTo(expected);
        return this;
    }

    /**
     * Assert the available buttons
     */
    public AWTCalculatorDSL shouldHaveButtons(String... expectedLabels) {
        List<String> labels = ComponentFinder.findAll(view.getComponents(), Button.class)
                .stream()
                .map(Button::getLabel)
                .collect(Collectors.toList());
        assertThat(labels).contains(expectedLabels);
        return this;
    }

    private void click(String key) {
        Button button = ComponentFinder.findAll(view.getComponents(), Button.class)
                .stream()
                .filter(b -> b.getLabel().equals(key))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Button '" + key + "' not found"));

        for (var listener : button.getActionListeners()) {
            final var event = new ActionEvent(button, ActionEvent.ACTION_PERFORMED, key);
            listener.actionPerformed(event);
        }
    }

    /**
     * Create AWTCalculatorView on the Event Dispatch Thread
     */
    private static AWTCalculatorView createViewOnEDT() throws Exception {
        AtomicReference<AWTCalculatorView> ref = new AtomicReference<>();
        EventQueue.invokeAndWait(() -> ref.set(new AWTCalculatorView()));
        return ref.get();
    }
}
