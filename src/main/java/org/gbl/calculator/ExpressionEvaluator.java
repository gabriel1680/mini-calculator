package org.gbl.calculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

class ExpressionEvaluator {

    private static final List<Operator> UNARY_OPERATORS = List.of(Operator.SQUARE_ROOT);

    private final char separator;
    private final SimpleCalculator calculator;

    ExpressionEvaluator(char separator) {
        this.separator = separator;
        this.calculator = new SimpleCalculator();
    }

    double evaluate(final String expression) {
        final String[] tokens = expression.split(String.valueOf(separator));
        final Deque<Double> stack = new ArrayDeque<>();
        for (String token : tokens)
            evaluateTokenInPlace(token, stack);
        return stack.pop();
    }

    private void evaluateTokenInPlace(String token, Deque<Double> stack) {
        final var firstCharacter = token.charAt(0);
        if (Character.isDigit(firstCharacter)) {
            stack.push(Double.parseDouble(token));
            return;
        }
        final var operator = Operator.fromChar(firstCharacter);
        if (UNARY_OPERATORS.contains(operator)) {
            final double operand = stack.pop();
            stack.push(compute(operator, operand, 0));
        } else { // binary operators
            final double b = stack.pop();
            final double a = stack.pop();
            stack.push(compute(operator, a, b));
        }
    }

    private double compute(Operator operator, double a, double b) {
        return switch (operator) {
            case SUM -> calculator.sum(a, b);
            case SUBTRACTION -> calculator.subtract(a, b);
            case MULTIPLICATION -> calculator.multiply(a, b);
            case DIVISION -> calculator.divide(a, b);
            case MOD -> calculator.module(a, b);
            case SQUARE_ROOT -> calculator.squareRoot(a);
        };
    }
}
