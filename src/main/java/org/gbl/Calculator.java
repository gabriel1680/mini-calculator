package org.gbl;

public class Calculator {

    private static final char SEPARATOR = ' ';

    private final ExpressionParser expressionParser;
    private final ExpressionEvaluator expressionEvaluator;

    public Calculator() {
        expressionParser = new ExpressionParser(SEPARATOR);
        expressionEvaluator = new ExpressionEvaluator(SEPARATOR);
    }

    public double calculate(final String expression) {
        return expressionEvaluator.evaluate(expressionParser.infixToPostfix(expression));
    }
}
