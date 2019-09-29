package Ap_assignment.Lab6;

import java.util.*;

import jdk.internal.org.jline.utils.InputStreamReader;

import java.io.*;

class User implements Serializable {
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

    public User(){
        this.name="";
    }

    public User(String name) {
        this.name = name;
        roll_count=0;

    }
    @Override
    public String toString(){
        return "Name: "+name+" at Pos: "+curr_pos+" track "+track;
    }
    public int getNext_pos() {
        return this.next_pos;
    }

    public void setNext_pos(int next_pos) {
        this.next_pos = next_pos;
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

    public void setRoll_count(int roll_count) {
        this.roll_count = roll_count;
    }
    
    public void incRoll_count() {
        this.roll_count ++;
    }

    public Track getTrack() {
        return this.track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurr_pos() {
        return this.curr_pos;
    }

    public void setCurr_pos(int curr_pos) {
        this.curr_pos = curr_pos;
    }
    public static void seralize(User ob)throws IOException{
        ObjectOutputStream out=null;
        try {
            out=new ObjectOutputStream(new FileOutputStream("out.txt"));
            out.writeObject(ob);

        }
        finally{
            out.close();
        }
    }

    public static User Deserialize() throws IOException,ClassNotFoundException {
        ObjectInputStream in=null;
        User player=new User();
        try{
            in=new ObjectInputStream(new FileInputStream("out.txt"));
            player = (User) in.readObject();
        }
        catch(FileNotFoundException e){
            User.seralize(player);
        }
        finally{
            if(in!=null)
                in.close();
        }
        return player;
    }

}

class Track implements Serializable {
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

    public void setTrack(Tile track[]) {
        this.track = track;
    }

    public void setTrack(int i, Tile obj) {
        this.track[i] = obj;
    }

    public int getLength_of_track() {
        return this.length_of_track;
    }

    public void setLength_of_track(int length_of_track) {
        this.length_of_track = length_of_track;
    }

    public int getNo_of_snakes() {
        return this.no_of_snakes;
    }

    public void setNo_of_snakes(int no_of_snakes) {
        this.no_of_snakes = no_of_snakes;
    }
    
    public void incNo_of_snakes() {
        this.no_of_snakes++;
    }

    public int getNo_of_cricket() {
        return this.no_of_cricket;
    }

    public void setNo_of_cricket(int no_of_cricket) {
        this.no_of_cricket = no_of_cricket;
    }
    
    public void incNo_of_cricket() {
        this.no_of_cricket++;
    }

    public int getNo_of_vulture() {
        return this.no_of_vulture;
    }

    public void setNo_of_vulture(int no_of_vulture) {
        this.no_of_vulture = no_of_vulture;
    }
    
    public void incNo_of_vulture() {
        this.no_of_vulture++;
    }

    public int getNo_of_trampoline() {
        return this.no_of_trampoline;
    }

    public void setNo_of_trampoline(int no_of_trampoline) {
        this.no_of_trampoline = no_of_trampoline;
    }
    
    public void incNo_of_trampoline() {
        this.no_of_trampoline++;
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

class Tile implements Serializable{
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

public class Racing{
    public static Random r = new Random();
    public static int value = r.nextInt(6) + 1;
    public static User player;

    public static boolean isIntermediate(int pos,int n){
        if(pos==n/2 -1 || pos==3*n/4 -1 || pos==n/4-1){
            return true;
        }
        return false;
    }

    public static int getRoll(){
        return r.nextInt(6) + 1;
    }

    public static void setup_race_track(int n){
        player.getTrack().setTrack(0, new White());
        player.getTrack().setTrack(n-1, new White());
        Tile t=new Snake();
        int choice;
        for(int i=1;i<n-1;i++){
            choice=r.nextInt(5)+1;
            switch (choice) {
                case 1:
                    t=new Snake();
                    t.setPower(player.getTrack().getSnake_power());
                    player.getTrack().incNo_of_snakes();
                    break;
                case 2:
                    t=new Cricket();
                    t.setPower(player.getTrack().getCricket_power());
                    player.getTrack().incNo_of_cricket();
                    break;
                case 3:
                    t=new Vulture();
                    t.setPower(player.getTrack().getVulture_power());
                    player.getTrack().incNo_of_vulture();
                    break;
                case 4:
                    t=new Trampoline();
                    t.setPower(player.getTrack().getTrampoline_power());
                    player.getTrack().incNo_of_trampoline();
                    break;
                case 5:
                    t=new White();
                    t.setPower(0);
                    break;
            }
            player.getTrack().setTrack(i, t);
        }
    }

    public static void start_game(int n,Track track) throws IOException,GameWinnerException,SnakeBiteException,CricketBiteException,VultureBiteException,TrampolineException {

        while (player.getCurr_pos()!=n-1) {
            value = getRoll();
            player.incRoll_count();
            if(player.getCurr_pos()==0){
                while (value != 6) {
                    System.out.println("[Roll-" + player.getRoll_count() + "]: Josh rolled " + player.getRoll_count() + " at Tile-1, OOPs You need 6 to start");
                    value=getRoll();
                    player.incRoll_count();
                }
                System.out.println("[Roll-" + player.getRoll_count() + "]: Josh rolled 6 at Tile-1, You are out of the cage! You get a free roll");
                value=getRoll();
                player.incRoll_count();
            }
            player.setNext_pos(player.getCurr_pos()+value);
            if(player.getNext_pos()>n-1){
                player.setNext_pos(player.getCurr_pos());
            }
            System.out.println("[Roll-" + player.getRoll_count() + "]: Josh rolled " + value + " at Tile-" + (player.getCurr_pos()+1) + ", landed on tile "+(player.getNext_pos()+1));
            if(isIntermediate(player.getNext_pos(),n)){
                System.out.println("------You have reached to one of the Intermediate points------");
                System.out.println("DO YOU WANT TO SAVE THIS GAME? (PRESS Y/N)");
                String in=scan.next();
                if(in.equals("Y")|| in.equals("y")){
                    System.out.println("Saving your Game....");
                    User.seralize(player);
                    System.out.println(player);
                    System.exit(0);
                }
            }
            if(player.getNext_pos()==n-1){
                break;
            }
            if(player.getNext_pos()!= player.getCurr_pos()){
                player.setCurr_pos(player.getRoll_count());
                System.out.println("\tTrying to shake the Tile-"+(player.getCurr_pos()+1));
                if(track.getTrack(player.getCurr_pos()).getClass()==(new Snake()).getClass()){
                    player.incSnake_bites();
                    throw new SnakeBiteException("\tHiss...! Iam a Snake, you go back "+ track.getTrack(player.getCurr_pos()).getPower()+ " tiles!");
                }
                else if(track.getTrack(player.getCurr_pos()).getClass()==(new Cricket()).getClass()){
                    player.incCricket_bites();
                    throw new CricketBiteException("\tChirp...! I am a Cricket, you go back "+ track.getTrack(player.getCurr_pos()).getPower() +" tiles!");
                }
                else if(track.getTrack(player.getCurr_pos()).getClass()==(new Vulture()).getClass()){
                    player.incVulture_bites();
                    throw new VultureBiteException("\tYapping...! I am a Vulture, you go back "+ track.getTrack(player.getCurr_pos()).getPower() +" tiles!");
                }
                else if(track.getTrack(player.getCurr_pos()).getClass()==(new Trampoline()).getClass()){
                    player.incTrampoline_bites();
                    throw new TrampolineException("\tPingPong..! I am a Trampoline, you advance "+ track.getTrack(player.getCurr_pos()).getPower() +" tiles");
                }
                else{
                    System.out.println("\tI am a White Tile!");
                    System.out.println("\t"+player.getName()+" moved to Tile-"+(player.getNext_pos()+1));
                }

            }
        }
        throw new GameWinnerException(" Wins the race in "+ player.getRoll_count()+" rolls");
    }

    public static final Scanner scan=new Scanner(System.in);
    // public static final BufferedReader bufscan=new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException,ClassNotFoundException{
        int n=0;
        System.out.println("Enter the player Name");
        String name = scan.next();
        player=User.Deserialize();
        if(name.equals(player.getName())){
            System.out.println("Player name already exists");
            System.out.println("DO YOU WANT TO CONTINUE WHERE YOU LEFT OFF? (PRESS Y/N)");
            String op=scan.next();
            if(op.equals("Y")|| op.equals("y")){
                System.out.println(player);
                n=player.getTrack().getLength_of_track();
            }
            else{
                System.out.println("!!!!!!!!!!Exiting!!!!");
                System.exit(0);
            }

        }
        else{
            player=new User(name);
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
            player.setTrack(new Track(n));
            setup_race_track(n);
            System.out.println("\nDanger: There are "+ player.getTrack().getNo_of_snakes()+", "+ player.getTrack().getNo_of_cricket()+", "+ player.getTrack().getNo_of_vulture()+" number of Snakes,Cricket and Vultures respectively on your track!\n");
            System.out.println("Danger: Each Snake, Cricket, and Vultures can throw you back by "+ player.getTrack().getSnake_power()+", "+ player.getTrack().getCricket_power()+", "+ player.getTrack().getVulture_power()+ " number of Tiles respectively!\n");
            System.out.println("Good News: There are "+ player.getTrack().getNo_of_trampoline() +" number of Trampolines on your track!\n");
            System.out.println("Good News: Each Trampoline can help you advance by "+ player.getTrack().getTrampoline_power()+" number of Tiles\n");
            
            System.out.println("\n-----------------------------------------------------------------------------------------------------\n");
            System.out.println("Starting the game with " +player.getName()+" at Tile-1");
        }

        System.out.println("Control Transferred to Computer for rolling the Dice for"+player.getName());
        System.out.println("Hit Enter to start The Game ");
        scan.nextLine();
        while (true) {
            String s=scan.nextLine();
            if(s.length()<=0)
                break;
        }
        System.out.println("<========================Game started=======================>");
        while(true){
            try {
                start_game(n, player.getTrack());
                break;
            }
            catch (SnakeBiteException e) {
                System.out.println(e.getMessage());
                player.setCurr_pos(player.getCurr_pos() - player.getTrack().getSnake_power());
                if (player.getCurr_pos() < 0) {
                    player.setCurr_pos(0);
                    System.out.println("\t" + player.getName() + " moved to Tile-1 as it can't go back further");
                } else {
                    System.out.println("\t" + player.getName() + " moved to Tile-" + (player.getCurr_pos() + 1));
                }
            } catch (CricketBiteException e) {
                System.out.println(e.getMessage());
                player.setCurr_pos(player.getCurr_pos()- player.getTrack().getCricket_power());
                if (player.getCurr_pos() < 0) {
                    player.setCurr_pos(0);
                    System.out.println("\t" + player.getName() + " moved to Tile-1 as it can't go back further");
                } else {
                    System.out.println("\t" + player.getName() + " moved to Tile-" + (player.getCurr_pos() + 1));
                }
            } catch (VultureBiteException e) {
                System.out.println(e.getMessage());
                player.setCurr_pos(player.getCurr_pos() - player.getTrack().getVulture_power());
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
                if(player.getCurr_pos()>n-1){
                    player.setCurr_pos(player.getCurr_pos()- player.getTrack().getTrampoline_power());
                    System.out.println("\t"+player.getName()+" remains at its position, as can't go further");
                }
                 else {
                    System.out.println("\t" + player.getName() + " moved to Tile-" + (player.getCurr_pos() + 1));
                }
            }
            catch(GameWinnerException e){
                System.out.println("------------------------------------------------------------------");
                System.out.println(player.getName() +e.getMessage());
                System.out.println("Total snake Bites= "+player.getSnake_bites());
                System.out.println("Total Cricket Bites= "+player.getCricket_bites());
                System.out.println("Total Vulture Bites= "+player.getVulture_bites());
                System.out.println("Total Trampolines= "+player.getTrampoline_bites());
                System.out.println("------------------------------------------------------------------");
                break;
            }
        }
    }
}