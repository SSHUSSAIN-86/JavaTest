package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


class MarupekeGrid{
    int size;
    MarupekeTile[][] marupekeTiles;
    boolean isLegalGrid;
    List<Reason> illegalitiesInGrid;

    MarupekeGrid(MarupekeTile[][] marupekeTiles,int size){
        this.marupekeTiles=marupekeTiles;
        this.size=size;

        //Setting up isLegalGrid value
        if(size>10 || size<3) {
            isLegalGrid = false;
            illegalitiesInGrid= new ArrayList<Reason>();
        } else{
            isLegalGrid = true;
            intilizeAllMaupekeTiles(marupekeTiles,size);
            printTiles(marupekeTiles,size);

        }

    }

    // intilizeAllMaupekeTiles intitilzes Grid to all Blank
    void intilizeAllMaupekeTiles(MarupekeTile[][] marupekeTiles,int size){
        Tile tile=Tile.BLANK;
        isLegalGrid = false;
        illegalitiesInGrid= new ArrayList<Reason>();
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                marupekeTiles[i][j] = new MarupekeTile(true,tile);
            }
        }

    }

    void checkIllegalitiesInGrid(boolean isLegalGrid, MarupekeGrid marupekeGrid){
        if(isLegalGrid==false) {
            if (marupekeGrid.size > 10) {

                ReasonDiagonal reasonDiagonal = new ReasonDiagonal("Grid size cannot be greater then 10");
                illegalitiesInGrid.add(reasonDiagonal);

            } else if(marupekeGrid.size < 3){
                ReasonDiagonal reasonDiagonal = new ReasonDiagonal("Grid size cannot be less then 3");
                illegalitiesInGrid.add(reasonDiagonal);
<<<<<<< HEAD
            } else {
                // Scanning for Horizontal violation [x,y] [x,y+1] and [x,y+2]
                for(int i = 0; i < size; i++) {
                    for (int j = 0; j < size-2; j++) {
                        if(marupekeGrid.marupekeTiles[i][j].tile == marupekeGrid.marupekeTiles[i][j+1].tile &&
                                marupekeGrid.marupekeTiles[i][j].tile == marupekeGrid.marupekeTiles[i][j+2].tile){
                            ReasonHorizontal reasonHorizontal = new ReasonHorizontal("Grid violation at grid coordinates " + i +"," + j +" "+ i +"," + (j+1) + " and "
                            + i +"," + (j+2));
                            illegalitiesInGrid.add(reasonHorizontal);
                        }
                    }
                }

                // Scanning for Horizontal violation [x,y] [x,y-1] and [x,y-2]
                for(int i = 0; i < size; i++) {
                    for (int j = size-1; j > 1; j--) {
                        if(marupekeGrid.marupekeTiles[i][j].tile == marupekeGrid.marupekeTiles[i][j-1].tile &&
                                marupekeGrid.marupekeTiles[i][j].tile == marupekeGrid.marupekeTiles[i][j-2].tile){
                            ReasonHorizontal reasonHorizontal = new ReasonHorizontal("Grid violation at grid coordinates " + i +"," + j +" "+ i +"," + (j-1) + " and "
                                    + i +"," + (j-2));
                            illegalitiesInGrid.add(reasonHorizontal);
                        }
                    }
                }

                // Scanning for Vertical violation [x,y] [x+1,y] and [x+2,y]
                for(int i = 0; i < size-2; i++) {
                    for (int j = 0; j < size; j++) {
                        if(marupekeGrid.marupekeTiles[i][j].tile == marupekeGrid.marupekeTiles[i+1][j].tile &&
                                marupekeGrid.marupekeTiles[i][j].tile == marupekeGrid.marupekeTiles[i+2][j].tile){
                            ReasonVertical reasonVertical = new ReasonVertical("Grid violation at grid coordinates " + i +"," + j +" "+ (i+1) +"," + j + " and "
                                    + (i+2) +"," + j);
                            illegalitiesInGrid.add(reasonVertical);
                        }
                    }
                }

                // Scanning for Vertical violation [x,y] [x-1,y] and [x-2,y]
                for(int i = size-1; i > 1; i--) {
                    for (int j = 0; j < size; j++) {
                        if(marupekeGrid.marupekeTiles[i][j].tile == marupekeGrid.marupekeTiles[i-1][j].tile &&
                                marupekeGrid.marupekeTiles[i][j].tile == marupekeGrid.marupekeTiles[i-2][j].tile){
                            ReasonVertical reasonVertical = new ReasonVertical("Grid violation at grid coordinates " + i +"," + j +" "+ (i-1) +"," + j + " and "
                                    + (i-2) +"," + j);
                            illegalitiesInGrid.add(reasonVertical);
                        }
                    }
                }

                // Scanning for Diagonal violation [x,y] [x+1,y-1] and [x+2,y-2]
                for(int i = 0; i < size-2; i++) {
                    for (int j = size-1; j > 1; j--) {
                        if(marupekeGrid.marupekeTiles[i][j].tile == marupekeGrid.marupekeTiles[i+1][j-1].tile &&
                                marupekeGrid.marupekeTiles[i][j].tile == marupekeGrid.marupekeTiles[i+2][j-2].tile){
                            ReasonDiagonal reasonDiagonal = new ReasonDiagonal("Grid violation at grid coordinates " + i +"," + j +" "+ (i+1) +"," + (j-1) + " and "
                                    + (i+2) +"," + (j-2));
                            illegalitiesInGrid.add(reasonDiagonal);
                        }
                    }
                }

                // Scanning for Diagonal violation [x,y] [x-1,y+1] and [x-2,y+2]
                for(int i = size-1; i > 1; i--) {
                    for (int j = 0; j < size-2; j++) {
                        if(marupekeGrid.marupekeTiles[i][j].tile == marupekeGrid.marupekeTiles[i-1][j+1].tile &&
                                marupekeGrid.marupekeTiles[i][j].tile == marupekeGrid.marupekeTiles[i-2][j+2].tile){
                            ReasonDiagonal reasonDiagonal = new ReasonDiagonal("Grid violation at grid coordinates " + i +"," + j +" "+ (i-1) +"," + (j+1) + " and "
                                    + (i-2) +"," + (j+2));
                            illegalitiesInGrid.add(reasonDiagonal);
                        }
                    }
                }

                // Scanning for Diagonal violation [x,y] [x-1,y-1] and [x-2,y-2]
                for(int i = size-1; i > 1; i--) {
                    for (int j = size-1; j > 1; j--) {
                        if(marupekeGrid.marupekeTiles[i][j].tile == marupekeGrid.marupekeTiles[i-1][j-1].tile &&
                                marupekeGrid.marupekeTiles[i][j].tile == marupekeGrid.marupekeTiles[i-2][j-2].tile){
                            ReasonDiagonal reasonDiagonal = new ReasonDiagonal("Grid violation at grid coordinates " + i +"," + j +" "+ (i-1) +"," + (j-1) + " and "
                                    + (i-2) +"," + (j-2));
                            illegalitiesInGrid.add(reasonDiagonal);
                        }
                    }
                }

                // Scanning for Diagonal violation [x,y] [x+1,y+1] and [x+2,y+2]
                for(int i = 0; i < size-2; i++) {
                    for (int j = 0; j < size-2; j++) {
                        if(marupekeGrid.marupekeTiles[i][j].tile == marupekeGrid.marupekeTiles[i+1][j+1].tile &&
                                marupekeGrid.marupekeTiles[i][j].tile == marupekeGrid.marupekeTiles[i+2][j+2].tile){
                            ReasonDiagonal reasonDiagonal = new ReasonDiagonal("Grid violation at grid coordinates " + i +"," + j +" "+ (i+1) +"," + (j+1) + " and "
                                    + (i+2) +"," + (j+2));
                            illegalitiesInGrid.add(reasonDiagonal);
                        }
                    }
                }

            }

=======

            }
>>>>>>> 686fdba4f52820534f718f9a9d5bda2043cea6a9
        }
    }

    //printTiles will print put out only if Grid value is legal
    void printTiles(MarupekeTile[][] marupekeTiles,int size){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                System.out.println(marupekeTiles[i][j].tile);
            }
            System.out.println("\n\r");
        }

    }

}

public class Main{
    public static void main(String[] args) {
        int size;

        /*  This reads the size of grid
         *  provided by player
         */
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter size of grid: ");

        // This method reads the number provided
        size = scan.nextInt();

        // Closing Scanner after the use
        scan.close();

        //Initializing Marupeke Tile Array
        MarupekeTile[][] marupekeTileArray=new MarupekeTile[size][size];

        //Initializing Marupeke Grid
        MarupekeGrid marupekeGrid = new MarupekeGrid(marupekeTileArray,size);
<<<<<<< HEAD

        marupekeGrid.intilizeAllMaupekeTiles(marupekeTileArray,size);
=======
>>>>>>> 686fdba4f52820534f718f9a9d5bda2043cea6a9

        //Checking for Maurpeke Grid legality conditions
        marupekeGrid.checkIllegalitiesInGrid(marupekeGrid.isLegalGrid,marupekeGrid);

        System.out.println(marupekeGrid.illegalitiesInGrid);
        System.out.println(marupekeGrid.isLegalGrid);


    }

}

