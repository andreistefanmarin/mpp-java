package eu.ase.io.lambda;

public class MathOpClass {
    public int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a,b);
    }
}
