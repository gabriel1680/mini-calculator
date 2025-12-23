package org.gbl.calculator;

import java.util.Stack;

class ExpressionParser {

    private static final char DECIMAL_DELIMITER = '.';

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
            if (Character.isDigit(character) || character == DECIMAL_DELIMITER) {
                final var result = parseNumber(expression, i);
                i = result.index() - 1; // step back one position
                postfix.append(result.token()).append(separator);
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

    private ParseNumberResult parseNumber(String expression, int startIndex) {
        final var number = new StringBuilder();
        var hasDecimalPoint = false;
        int i = startIndex;
        while (i < expression.length()) {
            final char c = expression.charAt(i);
            if (Character.isDigit(c)) {
                number.append(c);
            } else if (c == DECIMAL_DELIMITER && !hasDecimalPoint) {
                hasDecimalPoint = true;
                number.append(c);
            } else if (c == DECIMAL_DELIMITER) {
                throw new IllegalArgumentException("Invalid decimal number");
            } else {
                break;
            }
            i++;
        }
        return new ParseNumberResult(number.toString(), i);
    }

    private record ParseNumberResult(String token, int index) {}

    private static boolean havePrecedenceOver(char operation1, char operation2) {
        return precedenceFor(operation1) >= precedenceFor(operation2);
    }

    private static int precedenceFor(char operation) {
        return switch (operation) {
            case '√' -> 3;
            case '*', '/' -> 2;
            case '+', '-' -> 1;
            default -> 0;
        };
    }
}
