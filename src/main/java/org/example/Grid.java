package org.example;

import java.util.Scanner;

public class Grid {
    private Tile[][] spots;
    private double rand;
    private boolean playing;
    private Scanner input;
    private int revealedtiles;
    private int totaltiles;
    private int bombs;

   //Constructor for a new Grid of Tiles. Automatically fills with a random number of bombs, each tile has a 20% chance
    public Grid(int sizex, int sizey){
        spots = new Tile[sizey][sizex];
        input = new Scanner(System.in);
        playing = true;
        revealedtiles = 0;
        totaltiles = sizex * sizey;
        bombs = 0;
        //Go through and create tiles in each spot in the grid with a 20% chance of that tile being a bomb
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
        //Go through grid again and increment the neighbor value for all tiles adjacent to bombs
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
            System.out.println("Flag (f) or clear (c)?"); //Prompt for action
            char flag = input.next().charAt(0);
            System.out.println("Enter column:"); //Prompt for column
            int col = input.nextInt();
            System.out.println("Enter row: "); //Prompt for row
            int row = input.nextInt();
            revealGroup(flag, row, col); //Reveal tiles
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
        //show all bomb locations after failure
        for(int row = 0; row < spots.length; row++){
            for(int col = 0; col < spots[row].length; col++){
                if(spots[row][col].getIsbomb()){
                    spots[row][col].revealMe();
                }
            }
        }
    }

    //Called when all safe tiles are revealed
    public void winGame(){
        System.out.println("");
        System.out.println("You win! Congratulations!");
        System.out.println(bombs + " bombs | " + revealedtiles + " revealed tiles | " + totaltiles + " total tiles");
        playing = false;
    }

    //Increment Neighboring bomb count for surrounding tiles
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
        System.out.print("  "); //Displaying column numbers and aligning them
        for(int i = 0; i <= spots[0].length - 1; i++){
            if(i< 11){
                System.out.print("    " + i);
            }
            else{
                System.out.print("   " + i);
            }
        }
        for(int row = 0; row < spots.length; row++) {
            System.out.print("\n");
            for (int col = 0; col < spots[row].length; col++) {
                //Displaying row numbers and aligning them
                if(col == 0 && row < 10){
                    System.out.print(row+ "  ");
                }else if(col == 0){
                    System.out.print(row+ " ");
                }
                //Displaying tiles
                if(spots[row][col].getTilestate() == 1){ //Spot is revealed and not a bomb
                    System.out.print("     ");
                }
                else if(spots[row][col].getTilestate() == 2){ //Spot is revealed and is adjacent to a bomb
                    System.out.print("  [" + spots[row][col].getNeighbors() +"]");
                }
                else if(spots[row][col].getTilestate() == 3){ //Spot is revealed and IS a bomb
                    System.out.print("   X ");
                }
                else if(spots[row][col].getTilestate() == 0){ //Spot is not revealed
                    System.out.print("   ■ ");
                }
                else if(spots[row][col].getTilestate() == 4){ //Spot is flagged
                    System.out.print("   □ ");
                }

            }
        }
    }

    public void revealGroup(char flag, int row, int col){
        if(flag == 'c'){ //If action is 'clear' move on to clearing tiles
            revealedtiles++;
            if(spots[row][col].revealMe()){ //Hit a bomb
                gameOver();
            }
            else if(!spots[row][col].revealMe() && spots[row][col].getTilestate() == 1){ //Didn't hit a bomb and there are no adjacent bombs to current tile
                //If we're at the top left, we don't need to reveal anything to the left or above
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
                //Top, but not far left or far right
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
                //Top right
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
                //Far left, but not top or bottom
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
                //Bottom left
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
                //Far right but not top or bottom
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
                //Bottom right
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
                //Bottom, but not far left or far right
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
                //All middle tiles
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
        else if (flag == 'f'){//If action is 'flag' move on to flagging tile
            spots[row][col].flagMe();
        }
        else{ //If action is neither 'clear' nor 'flag'
            System.out.println("Invalid input. Try again");
        }

    }

    public boolean getPlaystate(){
        return playing;
    }

    public Scanner getInput(){
        return input;
    }
}
