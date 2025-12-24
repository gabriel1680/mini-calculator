package org.gbl.cli;

import org.gbl.calculator.Calculator;

public class Main {

    public static void main(String[] args) {
        final var calculator = new Calculator();
        System.out.println(calculator.calculate(args[0]));
    }
}