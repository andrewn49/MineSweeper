package org.example;

import java.util.Scanner;

public class Grid {
    Tile[][] spots;
    double rand;
    boolean playing;
    Scanner rowsel;

   //Constructor for a new Grid of Tiles. Automatically fills with a random number of bombs, each tile has a 20% chance
    public Grid(int size, int totalbombs){
        spots = new Tile[size][size];
        rowsel = new Scanner(System.in);
        playing = true;
        for(int row = 0; row < spots.length; row++) {
            for (int col = 0; col < spots[row].length; col++) {
                rand = Math.random() * 10;
                if(rand >= 8){
                    spots[row][col] = new Tile(true);
                }
                else{
                    spots[row][col] = new Tile(false);
                }
            }
        }
        for(int row = 0; row < spots.length; row++) {
            for (int col = 0; col < spots[row].length; col++) {
                if(spots[row][col].getIsbomb()){
                    incNeighbors(row, col);
                }
            }
        }
        System.out.println("\n" + totalbombs + " bombs placed"); //Show number of bombs on the grid
    }

    public void play(){
        while(playing){
            System.out.println("");
            System.out.println("Enter column:");
            int col = rowsel.nextInt();
            System.out.println("Enter row: ");
            int row = rowsel.nextInt();
            if (this.spots[row][col].revealMe()) {
                gameOver();
            }
            showGrid();
        }
    }

    //When you hit a bomb, output fail line and set playing to false
    public void gameOver(){
        System.out.println("");
        System.out.println("BOOM! Sorry, you blew up, try again!");
        playing = false;
    }

    //increment Neighboring bomb count for surrounding tiles
    public void incNeighbors(int row, int col) {
        //if we're at the top left, we don't need to increment anything to the left or above
        if (row == 0 && col == 0) {
            spots[row+1][col].addNeighbor();
            spots[row][col+1].addNeighbor();
            spots[row+1][col+1].addNeighbor();
        }
        //top, but not far left or far right
        else if(row == 0 && col != 0 && col != spots[row].length-1) {
            spots[row][col-1].addNeighbor();
            spots[row][col+1].addNeighbor();
            spots[row+1][col-1].addNeighbor();
            spots[row+1][col].addNeighbor();
            spots[row+1][col+1].addNeighbor();
        }
        //top right
        else if(row == 0 && col == spots[row].length-1) {
            spots[row][col-1].addNeighbor();
            spots[row+1][col-1].addNeighbor();
            spots[row+1][col].addNeighbor();
        }
        //far left, but not top or bottom
        else if(col == 0 && row != 0 && row != spots.length-1){
            spots[row-1][col].addNeighbor();
            spots[row-1][col+1].addNeighbor();
            spots[row][col+1].addNeighbor();
            spots[row+1][col].addNeighbor();
            spots[row+1][col+1].addNeighbor();
        }
        //bottom left
        else if(col == 0 && row == spots.length-1){
            spots[row-1][col].addNeighbor();
            spots[row-1][col+1].addNeighbor();
            spots[row][col+1].addNeighbor();
        }
        //far right but not top or bottom
        else if(col == spots[row].length-1 && row != 0 && row != spots.length-1){
            spots[row-1][col-1].addNeighbor();
            spots[row-1][col].addNeighbor();
            spots[row][col-1].addNeighbor();
            spots[row+1][col-1].addNeighbor();
            spots[row+1][col].addNeighbor();
        }
        //bottom right
        else if(col == spots[row].length-1 && row == spots.length-1){
            spots[row-1][col-1].addNeighbor();
            spots[row-1][col].addNeighbor();
            spots[row][col-1].addNeighbor();
        }
        //bottom, but not far left or far right
        else if(row == spots.length-1 && col != 0 && col != spots[row].length){
            spots[row-1][col-1].addNeighbor();
            spots[row-1][col].addNeighbor();
            spots[row-1][col+1].addNeighbor();
            spots[row][col-1].addNeighbor();
            spots[row][col+1].addNeighbor();
        }
        //all middle tiles
        else{
            spots[row-1][col-1].addNeighbor();
            spots[row-1][col].addNeighbor();
            spots[row-1][col+1].addNeighbor();
            spots[row][col-1].addNeighbor();
            spots[row][col+1].addNeighbor();
            spots[row+1][col-1].addNeighbor();
            spots[row+1][col].addNeighbor();
            spots[row+1][col+1].addNeighbor();
        }
    }

    //Display the grid to the screen
    public void showGrid(){
        for(int row = 0; row < spots.length; row++) {
            System.out.print("\n");
            for (int col = 0; col < spots[row].length; col++) {
                if(spots[row][col].getTilestate() == 1){ //spot is revealed and not a bomb
                    System.out.print("  [0]");
                }
                else if(spots[row][col].getTilestate() == 2){ //spot is revealed and is adjacent to a bomb
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
