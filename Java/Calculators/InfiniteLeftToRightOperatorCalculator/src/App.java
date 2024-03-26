import java.util.Scanner;
// import org.apache.commons.lang.mutable.MutableInt;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);

        while (true) {
            final String input = get_input(stdin);
            double result = 0;
            try {
                result = evaluate_string(input);
            } catch (DivisionByZero e) {
                System.out.println("You cannot divide by 0!");
                continue;
            }
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
            throw new EmptyInput();
        }
        for (char character : result.toCharArray()) {
            switch (character) {
                case ' ' -> {
                }
                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> isOperator = false;
                default -> {
                    if (isOperator)
                        throw new SequentialOperators();
                    try {
                        Operator.fromChar(character);
                    } catch (ArithmeticException e) {
                        throw new InvalidOperator();
                    }
                    isOperator = true;
                }
            }
        }
        if (isOperator)
            throw new EndsWithOperator();
        return result;

    }

    static double evaluate_string(String input) throws DivisionByZero {
        int i[] = {0};
        double result = get_double(input, i);
        while (i[0] < input.length()) {
            final char operator = get_operator(input, i);
            try {
                result = evaluate(result, get_double(input, i), operator);
            } catch (ArithmeticException e) {
                throw new DivisionByZero(e);
            }
        }
        return result;
    }

    static double get_double(String input, int[] i) {
        double number = 0;
        while (i[0] < input.length()) {
            switch (input.charAt(i[0])) {
                case ' ' -> {
                }
                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                    number *= 10;
                    number += input.charAt(i[0]) - '0';
                }
                default -> {
                    return number;
                }
            }
            i[0] += 1;
        }
        return number;
    }

    static char get_operator(String input, int[] i) {
        while (i[0] < input.length()) {
            switch (input.charAt(i[0])) {
                case ' ' -> {
                }
                default -> {
                    return input.charAt(i[0]++);
                }
            }
            i[0] += 1;
        }
        return input.charAt(i[0]);
    }

    static double evaluate(double number_1, double number_2, char operator)
            throws ArithmeticException {
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
