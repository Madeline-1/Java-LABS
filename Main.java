import java.util.Scanner;

public class Main {

    static boolean ProstNum(int x) {
        if (x < 2) {
            return false;
        }

        for (int i = 2; i < x; i++) {
            if (x % i == 0) {
                return false;
            }
        }

        return true;
    }

    static int countNumbers(int n) {
        int count = 0;

        for (int i = 2; i <= n; i++) {
            int x = i;
            int reversed = 0;

            while (x > 0) {
                reversed = reversed * 10 + x % 10;
                x = x / 10;
            }

            if (ProstNum(i) && ProstNum(reversed)) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть n від 1 до 1000: ");
        int n = scanner.nextInt();

        if (n >= 1 && n <= 1000) {
            System.out.println("Кількість: " + countNumbers(n));
        } else {
            System.out.println("Неправильне число");
        }
    }
}