package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Grid playarea = new Grid(3);
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