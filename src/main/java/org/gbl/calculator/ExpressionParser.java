package org.gbl.calculator;

import java.util.Stack;

class ExpressionParser {

    private final char separator;

    ExpressionParser(char separator) {
        this.separator = separator;
    }

    String infixToPostfix(final String expression) {
        final var postfix = new StringBuilder();
        final var operators = new Stack<Character>();
        for (int i = 0; i < expression.length(); i++) {
            final char character = expression.charAt(i);
            if (character == separator) {
                continue;
            }
            if (Character.isDigit(character)) {
                final var number = new StringBuilder();
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    number.append(expression.charAt(i));
                    i++;
                }
                i--; // step back one position
                postfix.append(number).append(separator);
            } else if (character == '(') {
                operators.push(character);
            } else if (character == ')') {
                while (!operators.isEmpty() && operators.peek() != '(')
                    postfix.append(operators.pop()).append(separator);
                operators.pop(); // discard the '(' operator and never adds the ')'
            } else {
                while (!operators.isEmpty() && havePrecedenceOver(operators.peek(), character))
                    postfix.append(operators.pop()).append(separator);
                operators.push(character);
            }
        }
        while (!operators.isEmpty()) {
            postfix.append(operators.pop()).append(separator);
        }
        return postfix.toString();
    }

    private static boolean havePrecedenceOver(char operation1, char operation2) {
        return precedenceFor(operation1) >= precedenceFor(operation2);
    }

    private static int precedenceFor(char operation) {
        return switch (operation) {
            case '*', '/' -> 2;
            case '+', '-' -> 1;
            default -> 0;
        };
    }
}
