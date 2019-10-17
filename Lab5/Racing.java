package Ap_assignment.Lab5;

import java.util.*;

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

abstract class Tile {
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
    @Override
    public void shake() throws SnakeBiteException{
        throw new SnakeBiteException("\tHiss...! Iam a Snake, you go back "+ this.getPower()+ " tiles!");
    }
}

class Vulture extends Tile {
     @Override
    public void shake()throws VultureBiteException{
        throw new VultureBiteException("\tYapping...! I am a Vulture, you go back "+ this.getPower() +" tiles!");
    }
}

class Cricket extends Tile {
     @Override
    public void shake()throws CricketBiteException{
        throw new CricketBiteException("\tChirp…! I am a Cricket, you go back "+ this.getPower() +" tiles!");
    }
}

class Trampoline extends Tile {
     @Override
    public void shake()throws TrampolineException{
        throw new TrampolineException("\tPingPong! I am a Trampoline, you advance "+ this.getPower() +" tiles");
    }
}

class White extends Tile {
    @Override
    public void shake(){
        System.out.println("\tI am a White Tile!");
        
    }
}

public class Racing{
    public static Random r = new Random();
    public static int[] powers = { -1, -1, -1, -1 };
    public static int[] number_of_bites={0,0,0,0};
    public static int curr_pos = 0, roll_no = 0, value = r.nextInt(6) + 1, next_pos;
    public static String name;

    public static Tile[] setup_race_track(int[] count_of_tiles,int n){

        Tile track[]=new Tile[n];
        track[0]=new White();
        track[n-1]=new White();
        Tile t=new Snake();
        int choice;
        for(int i=1;i<n-1;i++){
            choice=r.nextInt(5)+1;
            switch (choice) {
                case 1:
                    t=new Snake();
                    if(powers[0]==-1)
                        powers[0]=r.nextInt(10)+1;
                    t.setPower(powers[0]);
                    count_of_tiles[0]++;
                    break;
                case 2:
                    t=new Cricket();
                    if (powers[1] == -1)
                        powers[1] = r.nextInt(10) + 1;
                    t.setPower(powers[1]);
                    count_of_tiles[1]++;
                    break;
                case 3:
                    t=new Vulture();
                    if (powers[2] == -1)
                        powers[2] = r.nextInt(10) + 1;
                    t.setPower(powers[2]);
                    count_of_tiles[2]++;
                    break;
                case 4:
                    t=new Trampoline();
                    if (powers[3] == -1)
                        powers[3] = r.nextInt(10) + 1;
                    t.setPower(powers[3]);
                    count_of_tiles[3]++;
                    break;
                case 5:
                    t=new White();
                    t.setPower(0);
                    count_of_tiles[4]++;
                    break;
            }
            track[i]=t;
        }
        return track;
    }

    public static void start_game(int n,Tile[] track) throws GameWinnerException,SnakeBiteException,CricketBiteException,VultureBiteException,TrampolineException {

        while (curr_pos!=n-1) {
            roll_no++;
            value = r.nextInt(6) + 1;
            if(curr_pos==0){
                while (value != 6) {
                    System.out.println("[Roll-" + roll_no + "]: Josh rolled " + value + " at Tile-1, OOPs You need 6 to start");
                    value = r.nextInt(6) + 1;
                    roll_no++;
                }
                System.out.println("[Roll-" + roll_no + "]: Josh rolled 6 at Tile-1, You are out of the cage! You get a free roll");
                value = r.nextInt(6) + 1;
                roll_no++;
            }
            next_pos=curr_pos+value;
            if(next_pos>n-1){
                next_pos=curr_pos;
            }
            System.out.println("[Roll-" + roll_no + "]: Josh rolled " + value + " at Tile-" + (curr_pos+1) + ", landed on tile "+(next_pos+1));
            if(next_pos==n-1){
                break;
            }
            if(next_pos!=curr_pos){
                curr_pos=next_pos;
                System.out.println("\tTrying to shake the Tile-"+(next_pos+1));
                track[next_pos].shake();
                System.out.println("\t" + name + " moved to Tile-" + (next_pos + 1));
            }
        }
        throw new GameWinnerException(" Wins the race in "+ roll_no+" rolls");
    }

    private static final Scanner scan=new Scanner(System.in);
    public static void main(String[] args) {
        int n=0;
        int []count_of_tiles=new int[5];
        while (true) {
            try {
                System.out.println("Enter total number of tiles on the race track (length)");
                n=scan.nextInt();
                break;
            }
            catch (Exception e) {
                System.out.println("!!!!Some error occured!!!! \n\tplease re-enter");
                n=100;
                scan.next();
            }
            finally{
                if(n<9){
                    System.out.println("Number of tiles can't be less than 10\n\tplease Re-enter");
                    continue;
                }
            }
        }

        System.out.println("Setting up the race track.....");
        Tile[] track= setup_race_track(count_of_tiles,n);
        System.out.println("\nDanger: There are "+count_of_tiles[0]+", "+count_of_tiles[1]+", "+count_of_tiles[2]+" number of Snakes,Cricket and Vultures respectively on your track!\n");
        System.out.println("Danger: Each Snake, Cricket, and Vultures can throw you back by "+ powers[0]+", "+ powers[1]+", "+ powers[2]+ " number of Tiles respectively!\n");
        System.out.println("Good News: There are "+ count_of_tiles[3] +" number of Trampolines on your track!\n");
        System.out.println("Good News: Each Trampoline can help you advance by "+ powers[3]+" number of Tiles\n");
        System.out.println("Enter the player Name");
        name=scan.next();
        System.out.println("Starting the game with " +name +" at Tile-1");
        System.out.println("Control Transferred to Computer for rolling the Dice for Josh");
        System.out.println("Hit Enter to start The Game ");
        scan.nextLine();
        while (true) {
            String s=scan.nextLine();
            if(s.length()<=0)
                break;
        }
        boolean end=true;
        System.out.println("<========================Game started=======================>");
        while(true){
            try {
                start_game(n, track);
            }
            catch (SnakeBiteException e) {
                System.out.println(e.getMessage());
                number_of_bites[0]++;
                curr_pos -= powers[0];
                
            } catch (CricketBiteException e) {
                System.out.println(e.getMessage());
                number_of_bites[1]++;
                curr_pos -= powers[1];
                
            } catch (VultureBiteException e) {
                System.out.println(e.getMessage());
                number_of_bites[2]++;
                curr_pos -= powers[2];
                
            }
            catch(TrampolineException e){
                System.out.println(e.getMessage());
                number_of_bites[3]++;
                curr_pos+=powers[3];
            }
            catch(GameWinnerException e){
                System.out.println("------------------------------------------------------------------");
                System.out.println(name +e.getMessage());
                System.out.println("Total snake Bites= "+number_of_bites[0]);
                System.out.println("Total Cricket Bites= "+number_of_bites[1]);
                System.out.println("Total Vulture Bites= "+number_of_bites[2]);
                System.out.println("Total Trampolines= "+number_of_bites[3]);
                System.out.println("------------------------------------------------------------------");
                end=false;
                break;
            }
            finally{
                if(end){
                    if (curr_pos > n - 1) {
                        curr_pos -= powers[3];
                        System.out.println("\t" + name + " remains at its position, as can't go further");
                    }
                    else if (curr_pos < 0) {
                        curr_pos = 0;
                        System.out.println("\t" + name + " moved to Tile-1 as it can't go back further");
                    } else {
                        System.out.println("\t" + name + " moved to Tile-" + (curr_pos + 1));
                    }

                }
            }
        }
    }
}