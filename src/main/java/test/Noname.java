package test;

import java.util.Scanner;

public class Noname {
    private void compute(int x1, int x2, double n1, double n2, int counter) {
        if (n2 < x2 && x2 < n1) {
            System.out.println(counter);
        } else {
            counter += 1;
            compute(x2, x1 + x2, n1, n2, counter);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Noname noname = new Noname();
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int number = scanner.nextInt();
            noname.compute(1, 1, Math.pow(10, number) - 1, Math.pow(10, number - 1), 2);
        }
    }
}
