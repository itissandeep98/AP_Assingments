package com.company;

import java.io.Serializable;

public class Tile implements Serializable {
    private static final long serialVersionUID = 1L;
    private int power;

    public int getPower() {
        return this.power;
    }

    public void setPower(int power) {
        this.power = power;
    }

}

class Snake extends Tile {
    private static final long serialVersionUID = 1L;
}

class Vulture extends Tile {
    private static final long serialVersionUID = 1L;
}

class Cricket extends Tile {
    private static final long serialVersionUID = 1L;
}

class Trampoline extends Tile {
    private static final long serialVersionUID = 1L;
}

class White extends Tile {
    private static final long serialVersionUID = 1L;
}
