package com.company;

import java.util.Arrays;
import java.util.Scanner;

class MarupekeGrid{
    MarupekeTile[][] marupekeTiles;

    MarupekeGrid(MarupekeTile[][] marupekeTiles){
        this.marupekeTiles=marupekeTiles;
    }

    // intilizeAllMaupekeTiles intitilzes Grid to all Blank
    void intilizeAllMaupekeTiles(MarupekeTile[][] marupekeTiles,int size){
        Tile tile=Tile.BLANK;
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                marupekeTiles[i][j] = new MarupekeTile(true,tile);
            }
        }

    }

    // printGrid prints out the Marupeke Grid
    void printGrid(MarupekeTile[][] marupekeTiles,int size){
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

        Tile tile=Tile.BLANK;
        MarupekeTile marupekeTiles= new MarupekeTile();

        MarupekeTile[][] marupekeTileArray=new MarupekeTile[size][size];

        MarupekeGrid marupekeGrid = new MarupekeGrid(marupekeTileArray);
        marupekeGrid.intilizeAllMaupekeTiles(marupekeTileArray,size);

        Tile[][] tiles=new Tile[size][size];

        tiles[1][1]=Tile.X;


        marupekeTiles.tile=Tile.O;

        marupekeGrid.printGrid(marupekeTileArray,size);

    }

}

