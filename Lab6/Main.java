package com.company;

import java.util.*;
import java.io.*;

@SuppressWarnings("serial")
class GameSaveException extends Exception {
    public GameSaveException(String m) {
        super(m);
    }
}
@SuppressWarnings("serial")
class SnakeBiteException extends Exception {
    public SnakeBiteException(String m) {
        super(m);
    }
}

@SuppressWarnings("serial")
class CricketBiteException extends Exception {
    public CricketBiteException(String m) {
        super(m);
    }
}

@SuppressWarnings("serial")
class GameWinnerException extends Exception {
    public GameWinnerException(String m) {
        super(m);
    }
}

@SuppressWarnings("serial")
class VultureBiteException extends Exception {
    public VultureBiteException(String m) {
        super(m);
    }
}

@SuppressWarnings("serial")
class TrampolineException extends Exception {
    public TrampolineException(String m) {
        super(m);
    }
}

public class Main{
    public static Random r = new Random();
    public static int value = r.nextInt(6) + 1;
    public  static String in="n";

    public static boolean isIntermediate(User player,int pos,int n){

        if(pos>=n/4 -1 && player.getCheckpoint1()){
            player.setCheckpoint1(false);
            return true;
        }
        else if(pos>=n/2 -1 && player.getCheckpoint2()) {
            player.setCheckpoint2(false);
            return true;
        }
        else if(pos>=3*n/4 && player.getCheckpoint3()){
            player.setCheckpoint3(false);
            return true;
        }
        return false;
    }

    public static int getRoll(){
        return r.nextInt(6) + 1;
    }

    public static void start_game(User player ,int n,Track track) throws GameSaveException,GameWinnerException,SnakeBiteException,CricketBiteException,VultureBiteException,TrampolineException {

        while (player.getCurr_pos()<=n-1) {
            if(player.getRoll_count()==0)
                value = 1;
            if(player.getCurr_pos()==0){
                while (value != 6) {
                    value=getRoll();
                    player.incRoll_count();
                    if(value==6){
                        System.out.println("[Roll-" + player.getRoll_count() + "]: Josh rolled 6 at Tile-1, You are out of the cage! You get a free roll");
                        break;
                    }
                    System.out.println("[Roll-" + player.getRoll_count() + "]: Josh rolled " + value+ " at Tile-1, OOPs You need 6 to start");

                }
                value=getRoll();
                player.incRoll_count();
            }
            player.setNext_pos(player.getCurr_pos()+value);
            if(player.getNext_pos()>n-1){
                player.setNext_pos(player.getCurr_pos());
            }
            System.out.println("[Roll-" + player.getRoll_count() + "]: Josh rolled " + value + " at Tile-" + (player.getCurr_pos()+1) + ", landed on tile "+(player.getNext_pos()+1));
            value=getRoll();
            player.incRoll_count();
            if(player.getNext_pos()==n-1){
                player.setCurr_pos(player.getNext_pos());
                break;
            }
            if(isIntermediate(player,player.getNext_pos(),n)){
                System.out.println("------You have crossed one of the Intermediate points------");
                throw new GameSaveException("Game Saved");
            }
            if(player.getNext_pos()!= player.getCurr_pos()){
                player.setCurr_pos(player.getNext_pos());
                System.out.println("\tTrying to shake the Tile-"+(player.getCurr_pos()+1));
                track.getTrack(player.getCurr_pos()).shake();
                System.out.println("\t" + player.getName() + " moved to Tile-" + (player.getNext_pos() + 1));
            }
        }
        throw new GameWinnerException(" Wins the race in "+ player.getRoll_count()+" rolls");
    }

    public static final BufferedReader bufscan=new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException,ClassNotFoundException{
        int n=0;
        User player;
        String op="n";
        System.out.println("Enter the player Name");
        in=bufscan.readLine();

        player=User.Deserialize(in);
        if(in.equals(player.getName())) {
            System.out.println("Player name already exists");
            System.out.println("DO YOU WANT TO CONTINUE WHERE YOU LEFT OFF? (PRESS Y/N)");
            op = bufscan.readLine();
            if (op.equals("Y") || op.equals("y")) {
                n = player.getTrack().getLength_of_track();
                System.out.println("----------------------------------------------");
                System.out.println("\t!!!GAME RESTORED!!!");
                System.out.println(player);
                System.out.println("----------------------------------------------");
            } else {
                System.out.println("Starting the game freshly with the same Name");
            }

        }
        if(op.equals("n")||op.equals("N")){
            player=new User(in);
            while (true) {
                try {
                    System.out.println("Enter total number of tiles on the race track (length)");
                    n=Integer.parseInt(bufscan.readLine());
                    break;
                }
                catch (Exception e) {
                    System.out.println("You entered value in a Wrong format \n\tplease re-enter");
                    n=100;
                }
                finally{
                    if(n<9){
                        System.out.println("Number of tiles can't be less than 10\n\tplease Re-enter");
                        continue;
                    }
                }
            }

            System.out.println("Setting up the race track.....");
            player.iniTrack(n);
            System.out.println("\nDanger: There are "+ player.getTrack().getNo_of_snakes()+", "+ player.getTrack().getNo_of_cricket()+", "+ player.getTrack().getNo_of_vulture()+" number of Snakes,Cricket and Vultures respectively on your track!\n");
            System.out.println("Danger: Each Snake, Cricket, and Vultures can throw you back by "+ player.getTrack().getSnake_power()+", "+ player.getTrack().getCricket_power()+", "+ player.getTrack().getVulture_power()+ " number of Tiles respectively!\n");
            System.out.println("Good News: There are "+ player.getTrack().getNo_of_trampoline() +" number of Trampolines on your track!\n");
            System.out.println("Good News: Each Trampoline can help you advance by "+ player.getTrack().getTrampoline_power()+" number of Tiles\n");

            System.out.println("\n-----------------------------------------------------------------------------------------------------\n");
            System.out.println("Starting the game with " +player.getName()+" at Tile-1");
        }

        System.out.println("Control Transferred to Computer for rolling the Dice for "+player.getName());
        System.out.println("Hit Enter to start The Game ");
        while (true) {
            String s=bufscan.readLine();
            if(s.length()<=0)
                break;
        }
        System.out.println("<========================Game started=======================>");
        while(true){
            try {
                start_game(player,n, player.getTrack());
                User.serialise(player);
                break;
            }
            catch (SnakeBiteException e) {
                System.out.println(e.getMessage());
                player.setCurr_pos(player.getCurr_pos() - player.getTrack().getSnake_power());
                player.incSnake_bites();
                if (player.getCurr_pos() < 0) {
                    player.setCurr_pos(0);
                    System.out.println("\t" + player.getName() + " moved to Tile-1 as it can't go back further");
                } else {
                    System.out.println("\t" + player.getName() + " moved to Tile-" + (player.getCurr_pos() + 1));
                }
            } catch (CricketBiteException e) {
                System.out.println(e.getMessage());
                player.setCurr_pos(player.getCurr_pos()- player.getTrack().getCricket_power());
                player.incCricket_bites();
                if (player.getCurr_pos() < 0) {
                    player.setCurr_pos(0);
                    System.out.println("\t" + player.getName() + " moved to Tile-1 as it can't go back further");
                } else {
                    System.out.println("\t" + player.getName() + " moved to Tile-" + (player.getCurr_pos() + 1));
                }
            } catch (VultureBiteException e) {
                System.out.println(e.getMessage());
                player.setCurr_pos(player.getCurr_pos() - player.getTrack().getVulture_power());
                player.incVulture_bites();
                if (player.getCurr_pos() < 0) {
                    player.setCurr_pos(0);
                    System.out.println("\t" + player.getName() + " moved to Tile-1 as it can't go back further");
                } else {
                    System.out.println("\t" + player.getName() + " moved to Tile-" + (player.getCurr_pos() + 1));
                }
            }
            catch(TrampolineException e){
                System.out.println(e.getMessage());
                player.setCurr_pos(player.getCurr_pos() + player.getTrack().getTrampoline_power());
                player.incTrampoline_bites();
                if(player.getCurr_pos()>n-1){
                    player.setCurr_pos(player.getCurr_pos()- player.getTrack().getTrampoline_power());
                    System.out.println("\t"+player.getName()+" remains at its position, as can't go further");
                }
                else {
                    System.out.println("\t" + player.getName() + " moved to Tile-" + (player.getCurr_pos() + 1));
                }
            }
            catch(GameWinnerException e){
                System.out.println("<========================Game Ended=========================>");
                System.out.println("------------------------------------------------------------------");
                System.out.println(player.getName() +e.getMessage());
                System.out.println("Total snake Bites= "+player.getSnake_bites());
                System.out.println("Total Cricket Bites= "+player.getCricket_bites());
                System.out.println("Total Vulture Bites= "+player.getVulture_bites());
                System.out.println("Total Trampolines= "+player.getTrampoline_bites());
                System.out.println("------------------------------------------------------------------");
                User.serialise(player);
                break;
            }
            catch(GameSaveException e){

                System.out.println("DO YOU WANT TO SAVE THIS GAME? (PRESS Y/N)");
                in=bufscan.readLine();
                if(in.equals("Y")|| in.equals("y")){
                    System.out.println("Saving your Game....");
                    User.serialise(player);
                    System.exit(0);
                }
                else{
                    continue;
                }
            }

        }
    }
}