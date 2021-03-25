package com.company;



public class MarupekeTile {
    boolean isEditable;
    Tile tile;

    MarupekeTile(boolean isEditable,Tile tile){
        this.isEditable=isEditable;
        this.tile=tile;

    }
    MarupekeTile(){}
    void setisEditable(boolean value){
        isEditable=value;
    }

}
