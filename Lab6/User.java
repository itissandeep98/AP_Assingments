package com.company;

import java.io.*;
import java.util.Random;

public class User implements Serializable {
    public static Random r = new Random();
    private static final long serialVersionUID = 1L;
    private String name;
    private int curr_pos;
    private int next_pos;
    private Track track;
    private int roll_count;
    private int snake_bites;
    private int cricket_bites;
    private int vulture_bites;
    private int trampoline_bites;
    private boolean checkpoint1;
    private boolean checkpoint2;
    private boolean checkpoint3;

    public User(){
        this.name="";
        checkpoint1 = true;
        checkpoint2 = true;
        checkpoint3 = true;
    }

    public User(String name) {
        this.name = name;
        roll_count=0;
        checkpoint1 = true;
        checkpoint2 = true;
        checkpoint3 = true;

    }
    @Override
    public String toString(){
        return "Player "+name+" was at "+(curr_pos+1)+" tile in a track of size "+ track.getLength_of_track() +" tiles.\n There were "+roll_count+" rolls";
    }
    public int getNext_pos() {
        return this.next_pos;
    }

    public void setNext_pos(int next_pos) {
        this.next_pos = next_pos;
    }

    public boolean getCheckpoint1() {
        return this.checkpoint1;
    }

    public void setCheckpoint1(boolean checkpoint1) {
        this.checkpoint1 = checkpoint1;
    }

    public boolean getCheckpoint2() {
        return this.checkpoint2;
    }

    public void setCheckpoint2(boolean checkpoint2) {
        this.checkpoint2 = checkpoint2;
    }

    public boolean getCheckpoint3() {
        return this.checkpoint3;
    }

    public void setCheckpoint3(boolean checkpoint3) {
        this.checkpoint3 = checkpoint3;
    }

    public int getSnake_bites() {
        return this.snake_bites;
    }

    public void incSnake_bites() {
        this.snake_bites++;
    }

    public int getCricket_bites() {
        return this.cricket_bites;
    }

    public void incCricket_bites() {
        this.cricket_bites++;
    }

    public int getVulture_bites() {
        return this.vulture_bites;
    }

    public void incVulture_bites() {
        this.vulture_bites++;
    }

    public int getTrampoline_bites() {
        return this.trampoline_bites;
    }

    public void incTrampoline_bites() {
        this.trampoline_bites++;
    }

    public int getRoll_count() {
        return this.roll_count;
    }

    public void incRoll_count() {
        this.roll_count++ ;
    }

    public Track getTrack() {
        return this.track;
    }

    public void iniTrack(int n) {
        this.track = new Track(n);
        setup_race_track(this,n);
    }


    public String getName() {
        return this.name;
    }

    public int getCurr_pos() {
        return this.curr_pos;
    }

    public void setCurr_pos(int curr_pos) {
        this.curr_pos = curr_pos;
    }
    public static void serialise(User ob)throws IOException {
        ObjectOutputStream out=null;
        try {
            out=new ObjectOutputStream(new FileOutputStream("out.txt"));
            out.writeObject(ob);
        }
        finally{
            if(out!=null){
                out.close();
            }

        }
    }

    public static User Deserialize(String name) throws IOException,ClassNotFoundException {
        ObjectInputStream in=null;
        User player=new User();
        try{
            in=new ObjectInputStream(new FileInputStream("out.txt"));
            player = (User) in.readObject();
        }
        catch(FileNotFoundException e){
            User.serialise(player);
        }
        catch (NullPointerException e){
            User.serialise(player);
        }
        finally{
            if(in!=null)
                in.close();
        }
        return player;
    }
    public static void setup_race_track(User player,int n) {
        player.getTrack().setTrack(0, new White());
        player.getTrack().setTrack(n - 1, new White());
        Tile t = new Snake();
        int choice;
        for (int i = 1; i < n - 1; i++) {
            choice = r.nextInt(5) + 1;
            switch (choice) {
                case 1:
                    t = new Snake();
                    t.setPower(player.getTrack().getSnake_power());
                    player.getTrack().incNo_of_snakes();
                    break;
                case 2:
                    t = new Cricket();
                    t.setPower(player.getTrack().getCricket_power());
                    player.getTrack().incNo_of_cricket();
                    break;
                case 3:
                    t = new Vulture();
                    t.setPower(player.getTrack().getVulture_power());
                    player.getTrack().incNo_of_vulture();
                    break;
                case 4:
                    t = new Trampoline();
                    t.setPower(player.getTrack().getTrampoline_power());
                    player.getTrack().incNo_of_trampoline();
                    break;
                case 5:
                    t = new White();
                    t.setPower(0);
                    break;
            }
            player.getTrack().setTrack(i, t);
        }
    }

}
