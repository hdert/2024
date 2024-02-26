import java.util.Scanner;

public class App {
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
        final char operator = get_operator(stdin);

        System.out.println("Type any amount of numbers you want the result of, finishing with a blank line:");
        double total = get_double(stdin, "");
        while (true) {
            double number;
            try {
                number = get_double(stdin, "");
            } catch (ArithmeticException e) {
                break;
            }
            try {
                total = evaluate(total, number, operator);
            } catch (ArithmeticException e) {
                System.out.println("Cannot divide by zero");
                return;
            }
        }

        System.out.println("The result is " + total);
        stdin.close();
    }

    static double get_double(Scanner stdin, String message) {
        while (true) {
            System.out.print(message);
            final String input = stdin.nextLine();
            if (input.length() == 0) {
                throw new ArithmeticException();
            }
            try {
                final double number = Double.parseDouble(input);
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number");
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
