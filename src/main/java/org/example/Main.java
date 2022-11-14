package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner rowsel = new Scanner(System.in);
        Scanner colsel = new Scanner(System.in);
        Grid playarea = new Grid(6, 25);

        System.out.println("");
        System.out.println("Enter row: ");
        int row = rowsel.nextInt();
        int col = colsel.nextInt();

        playarea.tilePick(row, col);
    }
}