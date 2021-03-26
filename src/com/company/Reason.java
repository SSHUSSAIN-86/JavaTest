package com.company;

abstract public class Reason {
    String reason;
    abstract void toString(MarupekeGrid marupekeGrid, int size);
}

class ReasonDiagonal extends Reason{
    ReasonDiagonal(String reason){
        this.reason=reason;
        System.out.println(reason);
    }
    void toString(MarupekeGrid marupekeGrid, int size){
        System.out.println(reason);
    }
}

class ReasonX extends Reason{
    ReasonX(String reason){
        this.reason = reason;
        System.out.println(reason);
    }
    void toString(MarupekeGrid marupekeGrid, int size){
        System.out.println(reason);
    }
}

class ReasonO extends Reason{
    ReasonO(String reason){
        this.reason = reason;
        System.out.println(reason);
    }
    void toString(MarupekeGrid marupekeGrid, int size){
        System.out.println(reason);
    }
}

