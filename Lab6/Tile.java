package com.company;

import java.io.Serializable;

public abstract class Tile implements Serializable {
    private static final long serialVersionUID = 1L;
    private int power;

    public int getPower() {
        return this.power;
    }

    public void setPower(int power) {
        this.power = power;
    }
    public abstract void shake()throws SnakeBiteException,CricketBiteException,VultureBiteException,TrampolineException;

}

class Snake extends Tile {
    private static final long serialVersionUID = 1L;
    public void shake() throws SnakeBiteException{
        throw new SnakeBiteException("\tHiss...! Iam a Snake, you go back "+ this.getPower()+ " tiles!");
    }
}

class Vulture extends Tile {
    private static final long serialVersionUID = 1L;
    public void shake()throws VultureBiteException{
        throw new VultureBiteException("\tYapping...! I am a Vulture, you go back "+ this.getPower() +" tiles!");
    }
}

class Cricket extends Tile {
    private static final long serialVersionUID = 1L;
    public void shake()throws CricketBiteException{
        throw new CricketBiteException("\tChirp…! I am a Cricket, you go back "+ this.getPower() +" tiles!");
    }
}

class Trampoline extends Tile {
    private static final long serialVersionUID = 1L;
    public void shake()throws TrampolineException{
        throw new TrampolineException("\tPingPong! I am a Trampoline, you advance "+ this.getPower() +" tiles");
    }
}

class White extends Tile {
    private static final long serialVersionUID = 1L;
    public void shake(){
        System.out.println("\tI am a White Tile!");

    }
}
