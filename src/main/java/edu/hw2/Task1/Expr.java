package edu.hw2.Task1;

import org.jetbrains.annotations.NotNull;

public sealed interface Expr {

    double evaluate();

     record Constant(double number) implements Expr {

        @Override
        public double evaluate() {
            return number;
        }
    }

    record Negate(double number) implements Expr {

        public Negate(@NotNull Expr expr) {
            this(expr.evaluate());
        }

        @Override
        public double evaluate() {
            return -number;
        }
    }

     record Addition(double number1, double number2) implements Expr {

        public Addition(@NotNull Expr expr1, @NotNull Expr expr2) {
            this(expr1.evaluate(), expr2.evaluate());
        }

        @Override
        public double evaluate() {
            return number1 + number2;
        }
    }

     record Multiplication(double number1, double number2) implements Expr {
        public Multiplication(@NotNull Expr expr1, @NotNull Expr expr2) {
            this(expr1.evaluate(), expr2.evaluate());
        }

        @Override
        public double evaluate() {
            return number1 * number2;
        }
    }

     record Exponent(double base, double power) implements Expr {
        public Exponent(@NotNull Expr expr, double power) {
            this(expr.evaluate(), power);
        }

        @Override
        public double evaluate() {
            return Math.pow(base, power);
        }
    }
}
