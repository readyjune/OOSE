package edu.curtin.matheval;

public class DivOperator extends BinaryOperator {
    public DivOperator(ExprNode n1, ExprNode n2) {
        super(n1, n2, '/');
    }

    @Override
    protected double evalOperator(double v1, double v2) {
        return v1 / v2;
    }
}
