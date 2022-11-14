package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner rowsel = new Scanner(System.in);
        boolean playing = true;
        Grid playarea = new Grid(6, 15);
        playarea.showGrid();


        while(playing){
            System.out.println("");
            System.out.println("Enter column:");
            int col = rowsel.nextInt();
            System.out.println("Enter row: ");
            int row = rowsel.nextInt();
            if(playarea.spots[row][col].revealMe()){
                playing = false;
            }
            playarea.showGrid();
        }
        System.out.println("");
        System.out.println("BOOM! Sorry, you blew up, try again!");
    }
}