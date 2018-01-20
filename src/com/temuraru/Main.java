package com.temuraru;

import java.util.Scanner;

import static java.lang.Integer.MAX_VALUE;

/**
 * Run:
 * - "C:\Program Files\Java\jdk1.8.0_151\bin\javac" [src/com/temuraru/Main.java
 * - "C:\Program Files\Java\jdk1.8.0_151\bin\java" ]
 * Results (with displaying failed boards)
 *
 * Board size:			    64 * 64
 * Finished after:			2593 retries
 * Time Elapsed:			19 seconds
 *
 * Board size:				128 * 128
 * Finished after:			10904 retries
 * Time Elapsed:			341 seconds
 *
 *
 * Results (without displaying failed boards)
 *
 * Board size:				128 * 128
 * Finished after:			2609 retries
 * Time Elapsed:			1 seconds

 * Board size:				28 * 28
 * Finished after:			118 retries
 * Time Elapsed:			0 seconds
 *
 * Board size:				256 * 256
 * Finished after:			11879 retries
 * Time Elapsed:			23 seconds
 *
 *
 * Board size:				500 * 500
 * Finished after:			40109 retries
 * Time Elapsed:			365 seconds
 *
 * Board size:				1200 * 1200
 * Finished after:			70191 retries
 * Time Elapsed:			6169 seconds
 */
public class Main {
    public static void main(String[] args) {
        System.out.println();
        System.out.print("Please input the number of queens: ");
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();

        long startTime = System.nanoTime();

        int retries = 0;
        while (retries++ < MAX_VALUE) {
            Board board = new Board(size);
            System.out.println("Finding a solution... Retry #" + retries);
            boolean solution = board.solve();
            if (solution) {
                board.display();
                break;
            } else {
                board.displayFailure(false);
            }
        }

        int timeElapsed = (int)((System.nanoTime() - startTime) / 1_000_000_000L);

        System.out.println("Board size:\t\t\t\t" + size + " * " + size);
        System.out.println("Finished after:\t\t\t" + retries + " retries");
        System.out.println("Time Elapsed:\t\t\t" + timeElapsed + " seconds");

    }
}
