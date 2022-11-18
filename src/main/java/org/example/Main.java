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
                System.out.println("Enter the number of columns you would like, up to 45");
                x = input.nextInt();
                System.out.println("Enter the number of rows you would like, up to 45");
                y = input.nextInt();
                if(x > 45 || y > 45){ //limit the size, otherwise game is difficult to play as console only shows a limited number of characters
                    System.out.println("Too large! Try again!");
                    setup = true;
                }
                else if (x == 0 || y == 0){ //prevent heights or widths of 0
                    System.out.println("Too small! Try again!");
                    setup = true;
                }
                else{
                    setup = false;
                }
            }
            catch (Exception e) { //catch invalid input exceptions and retry until we're given valid
                System.out.println("Invalid input. Try again");
                input.nextLine();
            }
        }

        //construct new grid of height y, width x
        Grid playarea = new Grid(x, y);
        boolean cont = true;
        playarea.showGrid();
        while(cont){
            cont = playarea.getPlaystate();
            try {
                playarea.play();

            } catch (Exception e) { //catch invalid input exceptions and retry until we're given valid
                System.out.println("Invalid input. Try again");
                playarea.getInput().nextLine();
            }
        }
    }
}