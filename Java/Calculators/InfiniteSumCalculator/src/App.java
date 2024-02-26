import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        double total = 0;

        System.out.println("Type any amount of numbers you want the sum of, finishing with a blank line:");

        while (true) {
            final String input = stdin.nextLine();
            if (input == "\n" || input == "")
                break;
            try {
                final double number = Double.parseDouble(input);
                total += number;
            } catch (NumberFormatException e) {
                break;
            }
        }

        System.out.println("The sum is " + total);
        stdin.close();
    }
}
