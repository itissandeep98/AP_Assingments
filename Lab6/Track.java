package com.company;

import java.io.Serializable;
import java.util.Random;

public class Track implements Serializable {
    private static final long serialVersionUID = 1L;
    public static Random r = new Random();
    private int length_of_track;
    private int no_of_snakes;
    private int no_of_cricket;
    private int no_of_vulture;
    private int no_of_trampoline;
    private Tile track[];
    private int snake_power;
    private int cricket_power;
    private int vulture_power;
    private int trampoline_power;

    public Track(int length_of_track){
        this.length_of_track=length_of_track;
        track=new Tile[length_of_track];
        snake_power=r.nextInt(10)+1;
        cricket_power=r.nextInt(10)+1;
        vulture_power=r.nextInt(10)+1;
        trampoline_power= r.nextInt(10) + 1;
    }

    public Track(int length_of_track, int no_of_snakes, int no_of_cricket, int no_of_vulture, int no_of_trampoline) {
        this.length_of_track = length_of_track;
        this.no_of_snakes = no_of_snakes;
        this.no_of_cricket = no_of_cricket;
        this.no_of_vulture = no_of_vulture;
        this.no_of_trampoline = no_of_trampoline;
        track = new Tile[length_of_track];

    }
    @Override
    public String toString(){
        return "length "+ length_of_track+" snakes "+no_of_snakes+" cricket "+no_of_cricket+" vulture "+no_of_vulture+" trampoline "+no_of_trampoline;
    }

    public int getSnake_power() {
        return this.snake_power;
    }

    public int getCricket_power() {
        return this.cricket_power;
    }

    public int getVulture_power() {
        return this.vulture_power;
    }

    public int getTrampoline_power() {
        return this.trampoline_power;
    }

    public Tile getTrack(int i) {
        return this.track[i];
    }

    public Tile[] getTrack() {
        return this.track;
    }

    public void setTrack(int i, Tile obj) {
        this.track[i] = obj;
    }

    public int getLength_of_track() {
        return this.length_of_track;
    }

    public int getNo_of_snakes() {
        return this.no_of_snakes;
    }

    public void incNo_of_snakes() {
        this.no_of_snakes++;
    }

    public int getNo_of_cricket() {
        return this.no_of_cricket;
    }

    public void incNo_of_cricket() {
        this.no_of_cricket++;
    }

    public int getNo_of_vulture() {
        return this.no_of_vulture;
    }

    public void incNo_of_vulture() {
        this.no_of_vulture++;
    }

    public int getNo_of_trampoline() {
        return this.no_of_trampoline;
    }

    public void incNo_of_trampoline() {
        this.no_of_trampoline++;
    }

}
