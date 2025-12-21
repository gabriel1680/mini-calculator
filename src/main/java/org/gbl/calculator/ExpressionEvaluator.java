package org.gbl.calculator;

import java.util.Stack;

class ExpressionEvaluator {

    private final char separator;
    private final SimpleCalculator calculator;

    ExpressionEvaluator(char separator) {
        this.separator = separator;
        this.calculator = new SimpleCalculator();
    }

    double evaluate(final String postfixExpression) {
        final String[] tokens = postfixExpression.split(String.valueOf(separator));
        final var stack = new Stack<Double>();
        for (String token : tokens) {
            if (token.isEmpty()) {
                continue;
            }
            final var operator = token.charAt(0);
            if (Character.isDigit(operator)) {
                stack.push(Double.parseDouble(token));
                continue;
            }
            final double b = stack.pop();
            final double a = stack.pop();
            stack.push(compute(operator, a, b));
        }
        return stack.pop();
    }

    private double compute(char operator, double a, double b) {
        return switch (operator) {
            case '+' -> calculator.sum(a, b);
            case '-' -> calculator.subtract(a, b);
            case '*' -> calculator.multiply(a, b);
            case '/' -> calculator.divide(a, b);
            default -> throw new IllegalArgumentException("Unknown operator: " + operator);
        };
    }
}
