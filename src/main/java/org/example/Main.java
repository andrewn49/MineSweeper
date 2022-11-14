package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean playing = true;
        Grid playarea = new Grid(6, 15);
        playarea.showGrid();

        try {
            while (playing) {
               playarea.play();
               playarea.showGrid();
            }
        } catch (Exception e) {
            System.out.println("invalid input, try again");
        }
    }
}