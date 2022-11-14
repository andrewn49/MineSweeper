package org.example;

public class Tile {
    private boolean isbomb;
    private int tilestate; // 0 = unrevealed, 1 = no bomb, 2 = adjacent bomb, 3 = bomb
    private int neighbors;

    public Tile(boolean bomb) {
        isbomb = bomb;
        tilestate = 0;
    }

    public boolean revealMe() {
        if (isbomb) {
            tilestate = 3;
            return true;
        }
        else{
            tilestate = 1;
            return false;
        }
    }

    public int getTilestate(){
        return tilestate;
    }

    public void addNeighbor(){
        neighbors++;
    }

    public int getNeighbors(){
        return neighbors;
    }
}