package org.example;

public class Node {
    private boolean isbomb;
    Node topleft;
    Node top;
    Node topright;
    Node right;
    Node bottomright;
    Node bottom;
    Node bottomleft;
    Node left;

    public Node(boolean bomb){
        topleft = null;
        top = null;
        topright = null;
        right = null;
        bottomright = null;
        bottom = null;
        bottomleft = null;
        left = null;
        isbomb = bomb;
    }

    public void reveal() {

    }

    public void setBomb() {

    }

    public int bombCheck() {
        return 4;
    }

}
