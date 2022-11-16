package org.example;

public class Tile {
    private boolean isbomb;
    private int tilestate; // 0 = unrevealed, 1 = no bomb, 2 = adjacent bomb, 3 = bomb, 4 = flagged
    private int neighbors;

    public Tile(boolean bomb) {
        isbomb = bomb;
        tilestate = 0;
    }

    public boolean getIsbomb(){
        return isbomb;
    }

    public void flagMe(){
        tilestate = 4;
    }
    public boolean revealMe() {
        if(tilestate != 4){
            if (isbomb) {
                tilestate = 3;
                return true;
            }else if (neighbors > 0){
                tilestate = 2;
                return false;
            }
            else{
                tilestate = 1;
                return false;
            }
        }
        else{
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