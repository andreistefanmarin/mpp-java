package eu.ase.io.lambda;

import java.util.Optional;

public class MainLambda {
    public static void main(String[] args) {
        MathOperation addition = (a,b) -> a + b;
        MathOperation addition2 = (int a, int b) -> a + b;
        System.out.println(addition.operation(10,5));

        MathOperation substraction = (a,b) -> a - b;
        System.out.println(substraction.operation(10,5));
        MathOperation multiplication = (a,b) -> a * b;

        MathOpClass tester = new MathOpClass();
        System.out.println("10 + 5 = " + tester.operate(10,5,addition));
        System.out.println("10 - 5 = " + tester.operate(10,5,substraction));
        System.out.println("10 * 5 = " + tester.operate(10,5,multiplication));

        MathOperation additionVerbose = (a,b) -> {
            int result = a + b;
            return result;
        };

        System.out.println(additionVerbose.operation(10,5));
    }
}
