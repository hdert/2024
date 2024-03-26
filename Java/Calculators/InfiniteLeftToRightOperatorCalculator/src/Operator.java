
enum Operator {
    addition('+'), subtraction('-'), division('/'), multiplication('*'), exponentiation(
            '^'), modulus('%');

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
}
