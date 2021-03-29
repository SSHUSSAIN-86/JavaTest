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

<<<<<<< HEAD
class ReasonHorizontal extends Reason{
    ReasonHorizontal(String reason){
        this.reason=reason;
        System.out.println(reason);
    }
    void toString(MarupekeGrid marupekeGrid, int size){
        System.out.println(reason);
    }
}

class ReasonVertical extends Reason{
    ReasonVertical(String reason){
        this.reason=reason;
        System.out.println(reason);
    }
    void toString(MarupekeGrid marupekeGrid, int size){
        System.out.println(reason);
    }
}

=======
>>>>>>> 686fdba4f52820534f718f9a9d5bda2043cea6a9
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

