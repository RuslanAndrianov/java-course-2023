package edu.hw2.Task1;

public sealed interface Expr {

    double evaluate();

    public record Constant(double number) implements Expr {

        public Constant(Expr expr) {
            this(expr.evaluate());
        }

        @Override
        public double evaluate() {
            return number;
        }
    }

    public record Negate(double number) implements Expr {

        public Negate(Expr expr) {
            this(expr.evaluate());
        }

        @Override
        public double evaluate() {
            return -number;
        }
    }

    public record Addition(double number1, double number2) implements Expr {

        public Addition(Expr expr1, Expr expr2) {
            this(expr1.evaluate(), expr2.evaluate());
        }

        @Override
        public double evaluate() {
            return number1 + number2;
        }
    }

    public record Multiplication(double number1, double number2) implements Expr {
        public Multiplication(Expr expr1, Expr expr2) {
            this(expr1.evaluate(), expr2.evaluate());
        }

        @Override
        public double evaluate() {
            return number1 * number2;
        }
    }

    public record Exponent(double base, double power) implements Expr {
        public Exponent(Expr expr, double power) {
            this(expr.evaluate(), power);
        }

        @Override
        public double evaluate() {
            return Math.pow(base, power);
        }
    }
}
