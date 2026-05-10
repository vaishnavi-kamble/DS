package berkleypractical;

import java.util.*;

public class BerkleyAlgorithm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();

        int[] time = new int[n];
        int[] adjustment = new int[n];

        // Assume node 0 is master
        int master = 0;

        System.out.println("\n--- Enter clock times of each node ---");
        for (int i = 0; i < n; i++) {
            System.out.print("Node " + i + " time: ");
            time[i] = sc.nextInt();
        }

        System.out.println("\nMaster node is: Node " + master);

        // Step 1: Master polls all nodes (simulated)
        int sum = 0;
        int count = 0;

        for (int i = 0; i < n; i++) {
            int diff = time[i] - time[master];

            // Ignore faulty clocks (threshold = 50 units)
            if (Math.abs(diff) <= 50) {
                sum += time[i];
                count++;
            } else {
                System.out.println("Node " + i + " ignored (faulty clock)");
            }
        }

        // Step 2: Master calculates average
        int avg = sum / count;
        System.out.println("\nCalculated average time: " + avg);

        // Step 3: Master computes adjustments
        System.out.println("\n--- Adjustments sent by Master ---");
        for (int i = 0; i < n; i++) {
            adjustment[i] = avg - time[i];
            System.out.println("Node " + i + " adjustment: " + adjustment[i]);
        }

        // Step 4: Nodes update their clocks
        System.out.println("\n--- Synchronized Clock Times ---");
        for (int i = 0; i < n; i++) {
            time[i] += adjustment[i];
            System.out.println("Node " + i + ": " + time[i]);
        }

        sc.close();
    }
}