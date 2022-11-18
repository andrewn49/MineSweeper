package org.example;

public class Tile {
    private boolean isbomb;
    private int tilestate; // 0 = unrevealed, 1 = no bomb, 2 = adjacent bomb, 3 = bomb, 4 = flagged
    private int neighbors;

    public Tile(boolean bomb) {
        isbomb = bomb;
        tilestate = 0; //All tiles start unrevealed
    }

    public boolean getIsbomb(){
        return isbomb;
    }

    public void flagMe(){
        if(tilestate == 0){ //If tile is unrevealed, flag it
            tilestate = 4;
        }
        else if (tilestate == 4){ //If tile is flagged, unflag it
            tilestate = 0;
        }
    }

    public boolean revealMe() {
        if(tilestate != 4){ //If tile is not flagged
            if (isbomb) {
                tilestate = 3; //Tile displays as a bomb
                return true; //Returning true ends the game
            }else if (neighbors > 0){
                tilestate = 2; //Tile displays number of adjacent bombs
                return false;
            }
            else{
                tilestate = 1; //Tile displays that it doesn't have adjacent bombs
                return false;
            }
        }
        else{ //If tile is flagged, do nothing and continue play
            return false;
        }
    }

    public int getTilestate(){
        return tilestate;
    }

    public void addNeighbor(){
        neighbors++; //Incrementing number of adjacent bombs
    }

    public int getNeighbors(){
        return neighbors;
    }
}