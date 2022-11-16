package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean setup = true;
        Scanner input = new Scanner(System.in);
        int x = 0;
        int y = 0;
        //Start setup process, continue until we have valid input
        while(setup){
            try{
                System.out.println("Enter the number of columns you would like.");
                x = input.nextInt();
                System.out.println("Enter the number of rows you would like.");
                y = input.nextInt();
                setup = false;
            }
            catch (Exception e) {
                System.out.println("invalid input, try again");
                input.nextLine();
            }

        }

        //construct new grid of height y, width x
        Grid playarea = new Grid(x, y);
        boolean cont = true;
        playarea.showGrid();

        while(cont){
            cont = playarea.playing;
            try {
                playarea.play();

            } catch (Exception e) {
                System.out.println("invalid input, try again");
                playarea.rowsel.nextLine();
            }
        }
    }
}