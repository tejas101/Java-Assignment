/* Calculator.java
 * Version: 1
 *
 * Revisions:
 *
 * Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 * Lipisha Chaudhary MS-CS 2018 RIT lc2919@rit.edu
 */
/**
 * ----------Program Description-----------
 * This program performs basic operation of calculator using
 * Lambda expressions.
 *@author      Tejas Raval
 *@author      Lipisha Chaudhary
 */
interface OperationFunctionalInterface{
    int operation(int a, int b);
}

interface CalculatorInterface{
    int performOperation(OperationFunctionalInterface ofi, int num1, int num2);
    static void helpMessage(int exitCode)    {
        System.out.println("Usage: <num> <operation> <num>");
        System.exit(exitCode);
    }
}

public class Calculator implements CalculatorInterface {
    @Override
    public int performOperation(OperationFunctionalInterface ofi, int num1, int num2) {
        return ofi.operation(num1, num2);
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        if(args.length == 3) {
            try {
                int num_1 =Integer.parseInt(args[0]);
                int num_2 =Integer.parseInt(args[2]);
                if(!args[1].equals("+") && !args[1].equals("-") &&
                        !args[1].equals("*") && !args[1].equals("/")) {
                    CalculatorInterface.helpMessage(3);
                }
                else {
                    if(args[1].equals("+")) {
                        System.out.println("Addition :\n\t "+ num_1+ " + " +num_2+ " = "
                                + calculator.performOperation(((num1, num2) -> num1 + num2), num_1, num_2));
                    }
                    else{
                        if(args[1].equals("-")) {
                            System.out.println("Subtraction :\n\t "+ num_1+ " - " +num_2+ " = "
                                    + calculator.performOperation(((num1, num2) -> num1 - num2), num_1, num_2));
                        }
                        else{
                            if(args[1].equals("*")) {
                                System.out.println("Multiplication :\n\t "+ num_1+ " * " +num_2+ " = "
                                        + calculator.performOperation(((num1, num2) -> num1 * num2), num_1, num_2));
                            }
                            else {
                                if(args[1].equals("/")) {
                                    try {
                                        System.out.println("Division :\n\t " + num_1 + " / " + num_2 + " = "
                                                + calculator.performOperation(((num1, num2) -> num1 / num2), num_1, num_2));
                                    }catch(ArithmeticException a){
                                        System.out.println("Divide by 0 Error!");

                                    }
                                }
                            }
                        }

                    }
                }
            }catch(NumberFormatException ex) {
                CalculatorInterface.helpMessage(2);
            }
        }
        else {
            CalculatorInterface.helpMessage(1);
        }
    }

}
