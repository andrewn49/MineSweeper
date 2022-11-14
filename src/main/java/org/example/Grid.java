package org.example;


import java.util.Scanner;

public class Grid {
    Tile[][] spots;
    double rand;
    int curbombs;


   //Constructor for a new Grid of Tiles. Automatically fills with a random number of bombs up to the value given
    public Grid(int size, int totalbombs){
        spots = new Tile[size][size];
        for(int row = 0; row < spots.length; row++) {
            for (int col = 0; col < spots[row].length; col++) {
                rand = Math.random() * 10;
                if(curbombs < totalbombs && rand >= 5){
                    spots[row][col] = new Tile(true);
                    curbombs++;
                    //incNeighbors(row, col);
                }
                else{
                    spots[row][col] = new Tile(false);
                }
            }
        }
        System.out.println("\n" + curbombs + " out of " + totalbombs + " bombs placed"); //Show number of bombs on the grid
    }

    public void play(){
        Scanner rowsel = new Scanner(System.in);
        System.out.println("");
        System.out.println("Enter column:");
        int col = rowsel.nextInt();
        System.out.println("Enter row: ");
        int row = rowsel.nextInt();
        if (this.spots[row][col].revealMe()) {
            gameOver();
        }
    }

    public boolean gameOver(){
        System.out.println("");
        System.out.println("BOOM! Sorry, you blew up, try again!");
        return false;
    }

    //increment Neighboring bomb count for surrounding tiles
    /*public void incNeighbors(int row, int col) {
        //if we're at the top left, we don't need to increment anything to the left or above
        if (row == 0 && col == 0) {
            spots[row + 1][col].addNeighbor();
            spots[row][col + 1].addNeighbor();
            spots[row + 1][col + 1].addNeighbor();
        } else if (row == 0 && col != 0 && col != spots[row].length) {
            
        }
    }*/

    //Display the grid to the screen
    public void showGrid(){
        for(int row = 0; row < spots.length; row++) {
            System.out.print("\n");
            for (int col = 0; col < spots[row].length; col++) {
                if(spots[row][col].getTilestate() == 1){ //spot is revealed and not a bomb
                    System.out.print("  [0]");
                }
                else if(spots[row][col].getTilestate() == 2){
                    System.out.print("  [" + spots[row][col].getNeighbors() +"]");
                }
                else if(spots[row][col].getTilestate() == 3){ //spot is revealed and IS a bomb
                    System.out.print("  [X]");
                }
                else if(spots[row][col].getTilestate() == 0){ //spot is not revealed
                    System.out.print("  [ ]");
                }

            }
        }
    }
}
