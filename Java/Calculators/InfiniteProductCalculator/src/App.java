import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        double total = 1;
        System.out.println("Type any amount of numbers you want the product of, finishing with a blank line:");
        while (true) {
            final String input = stdin.nextLine();
            if (input == "\n" || input == "")
                break;
            try {
                final double number = Double.parseDouble(input);
                total *= number;
            } catch (NumberFormatException e) {
                break;
            }
        }

        System.out.print("The product is " + total);
        stdin.close();
    }
}
