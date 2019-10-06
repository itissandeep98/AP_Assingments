package com.company;

import org.junit.Test;

import java.io.*;
import static org.junit.Assert.assertEquals;
import java.util.Random;

public class testing {
    public static Random r = new Random();
    public static int n= r.nextInt(10000)+1;
    public static int pos=r.nextInt(n-20)+10;

    @Test(expected = SnakeBiteException.class)
    public void test_snake_bite() throws SnakeBiteException,CricketBiteException,TrampolineException,VultureBiteException,GameWinnerException, IOException,GameSaveException {
        User player=new User("test");
        player.iniTrack(n);
        player.getTrack().setTrack(pos,new Snake());
        player.setCurr_pos(pos-1);
        player.setNext_pos(pos);
        player.setCheckpoint1(false);
        player.setCheckpoint2(false);
        player.setCheckpoint3(false);
        Main.start_game(player,n,player.getTrack());
    }
    @Test(expected = CricketBiteException.class)
    public void test_cricket_bite() throws SnakeBiteException,CricketBiteException,TrampolineException,VultureBiteException,GameWinnerException, IOException,GameSaveException {
        User player=new User("test");
        player.iniTrack(n);
        player.getTrack().setTrack(pos,new Cricket());
        player.setCurr_pos(pos-1);
        player.setNext_pos(pos);
        player.setCheckpoint1(false);
        player.setCheckpoint2(false);
        player.setCheckpoint3(false);
        Main.start_game(player,n,player.getTrack());
    }
    @Test(expected = VultureBiteException.class)
    public void test_vulture_bite() throws SnakeBiteException,CricketBiteException,TrampolineException,VultureBiteException,GameWinnerException, IOException,GameSaveException {
        User player=new User("test");
        player.iniTrack(n);
        player.getTrack().setTrack(pos,new Vulture());
        player.setCurr_pos(pos-1);
        player.setNext_pos(pos);
        player.setCheckpoint1(false);
        player.setCheckpoint2(false);
        player.setCheckpoint3(false);
        Main.start_game(player,n,player.getTrack());
    }
    @Test(expected = TrampolineException.class)
    public void test_Trampoline_bite() throws SnakeBiteException,CricketBiteException,TrampolineException,VultureBiteException,GameWinnerException, IOException,GameSaveException {
        User player=new User("test");
        player.iniTrack(n);
        player.getTrack().setTrack(pos,new Trampoline());
        player.setCurr_pos(pos-1);
        player.setNext_pos(pos);
        player.setCheckpoint1(false);
        player.setCheckpoint2(false);
        player.setCheckpoint3(false);
        Main.start_game(player,n,player.getTrack());
    }
    @Test(expected = GameWinnerException.class)
    public void test_game_winner() throws SnakeBiteException,CricketBiteException,TrampolineException,VultureBiteException,GameWinnerException, IOException,GameSaveException {
        User player=new User("test");
        player.iniTrack(n);
        player.setCurr_pos(n-1);
        player.setNext_pos(n);
        Main.start_game(player,n,player.getTrack());
    }
    @Test(expected = GameSaveException.class)
    public void test_game_save() throws SnakeBiteException,CricketBiteException,TrampolineException,VultureBiteException,GameWinnerException, IOException,GameSaveException {
        pos=r.nextInt(n/4)+n/4;
        User player=new User("test");
        player.iniTrack(n);
        player.setCurr_pos(pos-1);
        player.setNext_pos(pos);
        Main.start_game(player,n,player.getTrack());
    }
    @Test
    public void test_game_save1() throws ClassNotFoundException, IOException,GameSaveException {
        pos=r.nextInt(n/4)+n/4;
        User player=new User("test");
        player.iniTrack(n);
        User.serialise(player);
        assertEquals(player.getName(),User.Deserialize("test").getName());

    }
}
