package com.company;

import java.lang.annotation.ElementType;

public enum Tile{
    BLANK('\0'),
    SOLID('#'),
    X('x'),
    O('o');

    public final char c;

    Tile(char c){
        this.c=c;
    }

}