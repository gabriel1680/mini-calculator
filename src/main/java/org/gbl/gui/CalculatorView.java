package org.gbl.gui;

import java.util.function.Consumer;

public interface CalculatorView {

    void clear();

    void onInput(Consumer<CalculatorInput> listener);

    void show();

    void showResult(double result);

    void showInput(String input);

    void showError(Exception exception);
}
