package org.example;


public class Grid {
    Node[][] spots;
    double rand;
    int curbombs;


    public Grid(int size, int totalbombs){
        spots = new Node[size][size];
        System.out.println(rand);
        for(int row = 0; row < spots.length; row++) {
            System.out.println("\n");
            for (int col = 0; col < spots[row].length; col++) {
                rand = Math.random() * 10;
                if(curbombs < totalbombs && rand >= 5){
                    spots[row][col] = new Node(true);
                    curbombs++;
                }
                else{
                    spots[row][col] = new Node(false);
                }
                System.out.print(spots[row][col].bombCheck());
            }
        }
        System.out.println("\n" + curbombs + " out of " + totalbombs + " bombs placed");
    }

    public boolean tilePick(int row, int col){
        spots[row][col].reveal();
    }

}
