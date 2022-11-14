package org.example;

public class Tile {
    private boolean isbomb;
    private int isrevealed;
    private int neighbors;

    public Tile(boolean bomb) {
        isbomb = bomb;
        isrevealed = 0;
    }

    public boolean revealMe() {
        if (isbomb == true) {
            isrevealed = 2;
            return true;
        }
        else{
            isrevealed = 1;
            return false;
        }
    }

    public int revealState(){
        return isrevealed;
    }
}