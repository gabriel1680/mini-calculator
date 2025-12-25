package org.gbl.gui.presenter;

import org.gbl.calculator.Calculator;
import org.gbl.gui.presenter.CalculatorInput.Mode;

/**
 * The {@code ViewController} class acts as the Presenter in the Model-View-Presenter (MVP)
 * architecture for the calculator application.
 *
 * <p>Its main responsibilities are:
 * <ul>
 *     <li>Receive user inputs from the {@link org.gbl.gui.presenter.CalculatorView View}.</li>
 *     <li>Executes the internal {@link org.gbl.calculator.Calculator} module based on input.</li>
 *     <li>Maintain the input state via {@link InputState}.</li>
 *     <li>Update the {@link org.gbl.gui.presenter.CalculatorView View} to reflect changes or
 *     results.</li>
 * </ul>
 *
 * <p>The {@code ViewController} decouples the view from the business logic, so that the
 * calculator can process input and compute results without the view knowing the computation
 * details.
 *
 * <p>Usage example:
 * <pre>{@code
 * Calculator calculator = new Calculator();
 * CalculatorView view = new DummyCalculatorViewImplementation();
 * ViewController controller = new ViewController(calculator, view);
 * view.show();
 * }</pre>
 *
 * <p>All input handling methods (digits, operators, evaluation, clear, backspace, invert, mode)
 * are processed through {@link #handle(CalculatorInput)}.
 *
 * @author Gabriel P. Lopes
 * @see org.gbl.gui.presenter.CalculatorView
 * @see org.gbl.gui.presenter.CalculatorInput
 * @see org.gbl.calculator.Calculator
 * @see InputState
 */
public class CalculatorPresenter {

    private final Calculator calculator;
    private final CalculatorView view;
    private final InputState state;

    public CalculatorPresenter(Calculator calculator, CalculatorView view) {
        this.calculator = calculator;
        this.view = view;
        this.state = new InputState();
        view.onInput(this::handle);
    }

    public void showView() {
        view.show();
    }

    public void handle(CalculatorInput input) {
        switch (input) {
            case CalculatorInput.Clear c -> clear();
            case CalculatorInput.Backspace b -> backspace();
            case CalculatorInput.Evaluate e -> evaluate();
            case CalculatorInput.InvertSignal i -> invertSignal();
            case CalculatorInput.Mode mode -> changeMode(mode);
            case CalculatorInput.Digit digit -> append(digit.value());
            case CalculatorInput.Operator operator -> append(operator.value());
        }
    }

    private void changeMode(Mode mode) {
        state.clear();
        view.clear();
        view.switchTo(mode.value());
    }

    private void invertSignal() {
        state.invertSignal();
        view.showInput(state.getInput());
    }

    private void append(String value) {
        state.append(value);
        view.showInput(state.getInput());
    }

    private void backspace() {
        state.backspace();
        view.showInput(state.getInput());
    }

    private void clear() {
        state.clear();
        view.clear();
    }

    private void evaluate() {
        try {
            final var result = calculator.calculate(state.getInput());
            view.showResult(result);
            state.setResult(result);
        } catch (Exception e) {
            System.err.println(e);
            view.showError(e);
            state.clear();
        }
    }
}
