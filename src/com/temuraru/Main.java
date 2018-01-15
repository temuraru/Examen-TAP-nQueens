package com.temuraru;

import java.util.Scanner;
import java.util.Random;
import com.temuraru.*;

public class Main {

    public static void main(String[] args) {
        System.out.println();
        System.out.print("Please input the number of queens: ");
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();


        Random rand = new Random();
        int randomX = rand.nextInt(size - 1);
        int randomY = rand.nextInt(size - 1);

        Board board = new Board(size);
        board.addQueen(randomX,randomY);
        board.display();
        System.out.println("Finding solution...");
        board.solve();

        System.out.println("Finished...");
    }
}
