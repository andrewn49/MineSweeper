package org.example;


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
                }
                else{
                    spots[row][col] = new Tile(false);
                }
            }
        }
        System.out.println("\n" + curbombs + " out of " + totalbombs + " bombs placed"); //Show number of bombs on the grid
    }

    //Display the grid to the screen
    public void showGrid(){
        for(int row = 0; row < spots.length; row++) {
            System.out.print("\n");
            for (int col = 0; col < spots[row].length; col++) {
                if(spots[row][col].revealState() == 1){ //spot is revealed and not a bomb
                    System.out.print("  [0]");
                }
                else if(spots[row][col].revealState() == 2){ //spot is revealed and IS a bomb
                    System.out.print("  [X]");
                }
                else if(spots[row][col].revealState() == 0){ //spot is not revealed
                    System.out.print("  [ ]");
                }

            }
        }
    }
}
