package org.gbl.calculator;

/**
 * A simple calculator that evaluates mathematical expressions written
 * in <b>infix notation</b> and returns the result as a {@code double}.
 * <p>
 * The calculator delegates parsing and evaluation to {@link ExpressionParser}
 * and {@link ExpressionEvaluator}. Expressions are expected to use spaces
 * as token separators.
 *
 * <h2>Supported usage</h2>
 * <pre>{@code
 * Calculator calculator = new Calculator();
 *
 * double result1 = calculator.calculate("1 + 2");
 * // result1 == 3.0
 *
 * double result2 = calculator.calculate("10 + 2 * 3");
 * // result2 == 16.0
 *
 * double result3 = calculator.calculate("( 10 + 2 ) * 3");
 * // result3 == 36.0
 * }</pre>
 *
 * <p>
 * Expressions must be well-formed and space-separated. For example,
 * {@code "(1+2"} is invalid, while {@code "(1+2)"} is valid.
 * Both {@code "1+2"} and {@code "1 + 2"} are valid.
 *
 * @author Gabriel P. Lopes
 */
public class Calculator {

    private static final char SEPARATOR = ' ';

    private final ExpressionParser parser;
    private final ExpressionEvaluator evaluator;

    /**
     * Creates a new {@code Calculator} instance.
     *
     * @see Calculator
     */
    public Calculator() {
        parser = new ExpressionParser(SEPARATOR);
        evaluator = new ExpressionEvaluator(SEPARATOR);
    }

    /**
     * Evaluates a mathematical expression written in <b>infix notation</b>.
     * <p>
     * This method only supports expressions expressed in infix form
     * (e.g. {@code "1 + 2"}, {@code "( 3 + 4 ) * 5"}). Prefix or postfix
     * (Reverse Polish) notations are not supported.
     *
     * @param expression the mathematical expression in infix notation
     * @return the result of evaluating the expression
     * @throws IllegalArgumentException if the expression is invalid or malformed
     */
    public double calculate(final String expression) {
        return evaluator.evaluate(parser.parse(expression));
    }
}
