import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);

        System.out.print("Type a number: ");
        float number_1 = stdin.nextFloat();

        System.out.print("Type a second number: ");
        float number_2 = stdin.nextFloat();

        System.out.println("The sum is " + (number_1 + number_2));
        stdin.close();
    }
}
