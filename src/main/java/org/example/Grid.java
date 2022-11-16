package org.example;

import java.util.Scanner;

public class Grid {
    Tile[][] spots;
    double rand;
    boolean playing;
    Scanner rowsel;
    int revealedtiles;
    int totaltiles;
    int bombs;

   //Constructor for a new Grid of Tiles. Automatically fills with a random number of bombs, each tile has a 20% chance
    public Grid(int size){
        spots = new Tile[size][size];
        rowsel = new Scanner(System.in);
        playing = true;
        revealedtiles = 0;
        totaltiles = size * size;
        bombs = 0;
        //go through and create tiles in each spot in the grid with a 20% chance of that tile being a bomb
        for(int row = 0; row < spots.length; row++) {
            for (int col = 0; col < spots[row].length; col++) {
                rand = Math.random() * 10;
                if(rand >= 8){
                    spots[row][col] = new Tile(true);
                    bombs++;
                }
                else{
                    spots[row][col] = new Tile(false);
                }
            }
        }
        //go through grid again and increment the neighbor value for all tiles adjacent to bombs
        System.out.println(bombs + " bombs placed");
        for(int row = 0; row < spots.length; row++) {
            for (int col = 0; col < spots[row].length; col++) {
                if(spots[row][col].getIsbomb()){
                    incNeighbors(row, col);
                }
            }
        }
    }

    public void play(){
        while(playing){
            System.out.println("");
            System.out.println("Flag (f) or clear (c)?");
            char flag = rowsel.next().charAt(0);
            System.out.println("Enter column:");
            int col = rowsel.nextInt();
            System.out.println("Enter row: ");
            int row = rowsel.nextInt();
            revealGroup(flag, row, col);
            if (revealedtiles == totaltiles - bombs){
                winGame();
            }
            showGrid();
        }
    }

    //When you hit a bomb, output fail line and set playing to false
    public void gameOver(){
        System.out.println("");
        System.out.println("BOOM! Sorry, you blew up, try again!");
        playing = false;
        for(int row = 0; row < spots.length; row++){
            for(int col = 0; col < spots[row].length; col++){
                if(spots[row][col].getIsbomb()){
                    spots[row][col].revealMe();
                }
            }
        }
    }

    public void winGame(){
        System.out.println("");
        System.out.println("You win! Congratulations!");
        System.out.println(bombs + " bombs | " + revealedtiles + " revealed tiles | " + totaltiles + " total tiles");
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
                    System.out.print("  [ ]");
                }
                else if(spots[row][col].getTilestate() == 2){ //spot is revealed and is adjacent to a bomb
                    System.out.print("  [" + spots[row][col].getNeighbors() +"]");
                }
                else if(spots[row][col].getTilestate() == 3){ //spot is revealed and IS a bomb
                    System.out.print("  [X]");
                }
                else if(spots[row][col].getTilestate() == 0){ //spot is not revealed
                    System.out.print("   ■ ");
                }
                else if(spots[row][col].getTilestate() == 4){
                    System.out.print("   ⚑ ");
                }

            }
        }
    }

    public void revealGroup(char flag, int row, int col){
        if(flag == 'c'){
            if(spots[row][col].revealMe()){
                gameOver();
            }
            else if(!spots[row][col].revealMe() && spots[row][col].getTilestate() == 1){
                //if we're at the top left, we don't need to increment anything to the left or above
                if (row == 0 && col == 0) {
                    if(spots[row+1][col].getTilestate() == 0){
                        revealGroup('c',row+1, col);
                    }
                    if(spots[row][col+1].getTilestate() == 0){
                        revealGroup('c', row, col+1);
                    }
                    if(spots[row+1][col+1].getTilestate() == 0){
                        revealGroup('c', row+1, col+1);
                    }
                }
                //top, but not far left or far right
                else if(row == 0 && col != 0 && col != spots[row].length-1) {
                    if(spots[row][col-1].getTilestate() == 0){
                        revealGroup('c', row, col-1);
                    }
                    if(spots[row][col+1].getTilestate() == 0){
                        revealGroup('c', row,col+1);
                    }
                    if(spots[row+1][col-1].getTilestate() == 0){
                        revealGroup('c', row+1,col-1);
                    }
                    if(spots[row+1][col].getTilestate() == 0){
                        revealGroup('c', row+1, col);
                    }
                    if(spots[row+1][col+1].getTilestate() == 0){
                        revealGroup('c', row+1,col+1);
                    }
                }
                //top right
                else if(row == 0 && col == spots[row].length-1) {
                    if(spots[row][col-1].getTilestate() == 0){
                        revealGroup('c', row, col-1);
                    }
                    if(spots[row+1][col-1].getTilestate() == 0){
                        revealGroup('c', row+1, col-1);
                    }
                    if(spots[row+1][col+1].getTilestate() ==0){
                        revealGroup('c', row+1, col+1);
                    }

                }
                //far left, but not top or bottom
                else if(col == 0 && row != 0 && row != spots.length-1){
                    if(spots[row-1][col].getTilestate() == 0){
                        revealGroup('c', row-1, col);
                    }
                    if(spots[row-1][col+1].getTilestate() == 0){
                        revealGroup('c', row-1, col+1);
                    }
                    if(spots[row][col+1].getTilestate() == 0){
                        revealGroup('c', row, col+1);
                    }
                    if(spots[row+1][col].getTilestate() == 0){
                        revealGroup('c', row+1, col);
                    }
                    if(spots[row+1][col+1].getTilestate() == 0){
                        revealGroup('c', row+1, col+1);
                    }
                }
                //bottom left
                else if(col == 0 && row == spots.length-1){
                    if(spots[row-1][col].getTilestate() == 0){
                        revealGroup('c', row-1, col);
                    }
                    if(spots[row-1][col+1].getTilestate() == 0){
                        revealGroup('c', row-1, col+1);
                    }
                    if(spots[row][col+1].getTilestate() == 0){
                        revealGroup('c', row, col+1);
                    }
                }
                //far right but not top or bottom
                else if(col == spots[row].length-1 && row != 0 && row != spots.length-1){
                    if(spots[row-1][col-1].getTilestate() == 0){
                        revealGroup('c', row-1, col-1);
                    }
                    if(spots[row-1][col].getTilestate() == 0){
                        revealGroup('c', row-1, col);
                    }
                    if(spots[row][col-1].getTilestate() == 0){
                        revealGroup('c', row, col-1);
                    }
                    if(spots[row+1][col-1].getTilestate() == 0){
                        revealGroup('c', row+1, col-1);
                    }
                    if(spots[row+1][col].getTilestate() == 0){
                        revealGroup('c', row+1, col);
                    }
                }
                //bottom right
                else if(col == spots[row].length-1 && row == spots.length-1){
                    if(spots[row-1][col-1].getTilestate() == 0){
                        revealGroup('c', row-1, col-1);
                    }
                    if(spots[row-1][col].getTilestate() == 0){
                        revealGroup('c', row-1, col);
                    }
                    if(spots[row][col-1].getTilestate() == 0){
                        revealGroup('c', row, col-1);
                    }
                }
                //bottom, but not far left or far right
                else if(row == spots.length-1 && col != 0 && col != spots[row].length){
                    if(spots[row-1][col-1].getTilestate() == 0){
                        revealGroup('c', row-1, col-1);
                    }
                    if(spots[row-1][col].getTilestate() == 0){
                        revealGroup('c', row-1, col);
                    }
                    if(spots[row-1][col+1].getTilestate() == 0){
                        revealGroup('c', row-1, col+1);
                    }
                    if(spots[row][col-1].getTilestate() == 0){
                        revealGroup('c', row, col-1);
                    }
                    if(spots[row][col+1].getTilestate() == 0){
                        revealGroup('c', row, col+1);
                    }
                }
                //all middle tiles
                else{
                    if(spots[row-1][col-1].getTilestate() == 0){
                        revealGroup('c', row-1, col-1);
                    }
                    if(spots[row-1][col].getTilestate() == 0){
                        revealGroup('c', row-1, col);
                    }
                    if(spots[row-1][col+1].getTilestate() == 0){
                        revealGroup('c', row-1, col+1);
                    }
                    if(spots[row][col-1].getTilestate() == 0){
                        revealGroup('c', row, col-1);
                    }
                    if(spots[row][col+1].getTilestate() == 0){
                        revealGroup('c', row, col+1);
                    }
                    if(spots[row+1][col-1].getTilestate() == 0){
                        revealGroup('c', row+1, col-1);
                    }
                    if(spots[row+1][col].getTilestate() == 0){
                        revealGroup('c', row+1, col);
                    }
                    if(spots[row+1][col+1].getTilestate() == 0){
                        revealGroup('c', row+1, col+1);
                    }
                }
            }
        }
        else if (flag == 'f'){
            spots[row][col].flagMe();
        }

    }
}
