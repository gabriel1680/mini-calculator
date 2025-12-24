package org.gbl.gui.integration;

import org.junit.jupiter.api.Test;

import static org.gbl.gui.integration.AWTCalculatorDSL.BASIC_BUTTONS;
import static org.gbl.gui.integration.AWTCalculatorDSL.SCIENTIFIC_BUTTONS;
import static org.gbl.gui.integration.AWTCalculatorDSL.calculator;

public class IntegrationTest {

    @Test
    void integration() throws Exception {
        calculator()
                .type("2", "+", "2").equals().shouldDisplay("4.0")
                .type("4", "*", "4").equals().shouldDisplay("16.0")
                .type("4", "*", "4").clear().shouldDisplay("")
                .type("1", ".", "0", "+", "0", ".", "1").equals().shouldDisplay("1.1")
                .type("1").invert().shouldDisplay("(-1)").invert().shouldDisplay("1").clear()
                .type("4", "/", "4").equals().shouldDisplay("1.0")
                .scientificMode()
                .clear().type("√", "4").equals().shouldDisplay("2.0");
    }

    @Test
    void functions() throws Exception {
        calculator()
                .type("1")
                .invert()
                .shouldDisplay("(-1)")
                .invert()
                .shouldDisplay("1");
        calculator()
                .type("1")
                .clear()
                .shouldDisplay("");
        calculator()
                .type("1", "2")
                .backspace()
                .shouldDisplay("1");
    }

    @Test
    void switchModes() throws Exception {
        calculator()
                .shouldHaveButtons(BASIC_BUTTONS)
                .scientificMode()
                .shouldHaveButtons(SCIENTIFIC_BUTTONS)
                .basicMode()
                .shouldHaveButtons(BASIC_BUTTONS);
    }
}
