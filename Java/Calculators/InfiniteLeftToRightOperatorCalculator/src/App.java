import java.util.Scanner;
// import org.apache.commons.lang.mutable.MutableInt;

public class App {
    class InvalidOperator extends Exception {
    };

    class DivisionByZero extends Exception {
    };

    class EmptyInput extends Exception {
    };

    class SequentialOperators extends Exception {
    };

    class EndsWithOperator extends Exception {
    };

    enum Operator {
        addition('+'),
        subtraction('-'),
        division('/'),
        multiplication('*'),
        exponentiation('^'),
        modulus('%');

        public final char operator;

        Operator(char operator) {
            this.operator = operator;
        }

        public static Operator fromChar(char operator) throws ArithmeticException {
            Operator op = switch (operator) {
                case '+' -> addition;
                case '-' -> subtraction;
                case '/' -> division;
                case '*' -> multiplication;
                case '^' -> exponentiation;
                case '%' -> modulus;
                default -> {
                    throw new ArithmeticException();
                }
            };
            return op;
        }
    };

    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);

        while (true) {
            final String input = get_input(stdin);

            final double result = evaluate_string(input);

            System.out.println("The result is " + result);
        }

        // stdin.close();
    }

    static String get_input(Scanner stdin) {
        while (true) {
            System.out.print("Enter your equation: ");
            final String input = stdin.nextLine();
            try {
                final String result = validate_input(input);
                return result;
            } catch (EmptyInput e) {
                System.out.println("You cannot have an empty input");
            } catch (SequentialOperators e) {
                System.out.println("You cannot enter sequential operators");
            } catch (InvalidOperator e) {
                System.out.println("You have entered an invalid operator");
            } catch (EndsWithOperator e) {
                System.out.println("You cannot finish with an operator");
            }
        }
    }

    static String validate_input(String input)
            throws EmptyInput, SequentialOperators, InvalidOperator, EndsWithOperator {
        boolean isOperator = true;
        String result = input.trim();
        if (result.isEmpty()) {
            throw new App().new EmptyInput();
        }
        for (char character : result.toCharArray()) {
            switch (character) {
                case ' ' -> {
                }
                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> isOperator = false;
                default -> {
                    if (isOperator)
                        throw new App().new SequentialOperators();
                    try {
                        Operator.fromChar(character);
                    } catch (ArithmeticException e) {
                        throw new App().new InvalidOperator();
                    }
                    isOperator = true;
                }
            }
        }
        if (isOperator)
            throw new App().new EndsWithOperator();
        return result;

    }

    static double evaluate_string(String input) throws DivisionByZero {
        int i[] = { 0 };
        double result = get_double(input, i);

        return 3;
    }

    static double get_double(String input, int[] i) {
        double number = 0;
        while (i[0] < input.length()) {
            switch (input.charAt(i[0])) {
                ' ' -> {
                }
                '0', '1', '2','3','4','5','6','7','8', '9' -> {
                    number *= 10;
                    number += input.charAt(i[0]) - '0';
                }
                default -> {break;}
            }
        }
    }

    static char get_operator(Scanner stdin) {
        while (true) {
            System.out.print("Enter an operator: ");
            final String input = stdin.nextLine();
            if (input.length() == 1) {
                try {
                    Operator.fromChar(input.charAt(0));
                } catch (ArithmeticException e) {
                    System.out.println("That wasn't a valid operator");
                    continue;
                }
                return input.charAt(0);
            } else {
                System.out.println("That wasn't a valid operator");
            }
        }
    }

    static double evaluate(double number_1, double number_2, char operator) throws ArithmeticException {
        final double result = switch (Operator.fromChar(operator)) {
            case addition -> number_1 + number_2;
            case subtraction -> number_1 - number_2;
            case division -> {
                if (number_2 != 0)
                    yield number_1 / number_2;
                else
                    throw new ArithmeticException();
            }
            case multiplication -> number_1 * number_2;
            case exponentiation -> java.lang.Math.pow(number_1, number_2);
            case modulus -> {
                if (number_2 > 0)
                    yield number_1 % number_2;
                else
                    throw new ArithmeticException();
            }
            default -> throw new ArithmeticException();
        };
        return result;
    }
}
