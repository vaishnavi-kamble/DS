package tokenpractical;

import java.util.Scanner;

class TokenRing {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] request = new int[n];
        int token = 0;

        // User input
        for (int i = 0; i < n; i++) {
            System.out.print("Does Process " + i + " want CS? (1/0): ");
            request[i] = sc.nextInt();
        }

        System.out.println("\n--- Token Passing ---");

        // Run token for few cycles
        for (int i = 0; i < n * 2; i++) {

            System.out.println("\nToken at Process " + token);

            if (request[token] == 1) {

                System.out.println("Process " + token + " ENTERS CS");

                // 🔹 Operation inside Critical Section
                System.out.print("Enter two numbers: ");
                int a = sc.nextInt();
                int b = sc.nextInt();

                int sum = a + b;
                System.out.println("Sum = " + sum);

                System.out.println("Process " + token + " EXITS CS");

                request[token] = 0; // reset
            }

            // Pass token
            token = (token + 1) % n;
        }

        sc.close();
    }
}