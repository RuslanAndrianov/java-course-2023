package edu.hw2;

import edu.hw2.Task1.Expr;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask1 {

    @Test
    @DisplayName("Проверка выражения из условия задачи")
    void testExpr1() {
        var two = new Expr.Constant(2);
        var four = new Expr.Constant(4);
        var negOne = new Expr.Negate(new Expr.Constant(1));
        var sumTwoFour = new Expr.Addition(two, four);
        var mult = new Expr.Multiplication(sumTwoFour, negOne);
        var exp = new Expr.Exponent(mult, 2);
        var res = new Expr.Addition(exp, new Expr.Constant(1));

        assertEquals(37.0, res.evaluate());
    }

    @Test
    @DisplayName("Собственное выражение")
    void testExpr2() {
        var one = new Expr.Constant(1);
        var six = new Expr.Constant(6);
        var negOne = new Expr.Negate(one);
        var sixPlusNegOne = new Expr.Addition(six, negOne);
        var mult = new Expr.Multiplication(sixPlusNegOne, six);
        var res = new Expr.Exponent(mult, 3);

        assertEquals(27000.0, res.evaluate());
    }
}
