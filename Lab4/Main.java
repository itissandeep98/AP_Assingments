package Lab;
import java.util.*;
public class Main{
    static void Game_start(Hero h){
        int[] level_HP_Hero={Integer.MAX_VALUE,100,150,200,250};
        int[] level_HP_Monster={Integer.MAX_VALUE,100,100,200,250};
        int[] next_levels={0,0,0};

        int n=0,level=1,option,curr=0;
        String in;
        System.out.println("Welcome "+h.getName());
        while(true){
            Monster[][] level_map={null,{new Goblin(),new Goblin(),new Goblin()},
                                        {new Zombie(),new Zombie(),new Zombie()},
                                        {new Fiends(),new Fiends(),new Fiends()},
                                        {new Lionfang()}};
            next_levels[2]=level*3;
            next_levels[1]=next_levels[2]-1;
            next_levels[0]=next_levels[2]-2;
            if(level<4){
                System.out.println("You are at location "+curr+" Choose path: ");
                System.out.println("1) Go to Location "+ next_levels[0]);
                System.out.println("2) Go to Location "+ next_levels[1]);
                System.out.println("3) Go to Location "+ next_levels[2]);
            }
            else if(level==4){
                System.out.println("Final level, Fight with the BOSS");
            }
            if(level>1){
                System.out.println("4) Go back");
            }
            System.out.println("Enter -1 to exit");
            n=scan.nextInt()-1;

            if(n<0){
                System.out.println("Exiting");
                return;
            }
            if(n==3){
                level-=1;
                continue;
            }
            curr=next_levels[n];
            System.out.println("Moving to Location "+curr);
            if(n<4){
                h.setHP(level_HP_Hero[level]);
            }
            System.out.println("Fight started, you are fighting a level "+level+" Monster");
            if(h.getCurr_sidekick()!=null){
                System.out.println("Type yes if you wish to use a sidekick, else Type no");
                in=scan.next();
                if(in.equals("yes")){
                    if(h.getCurr_sidekick().getClass()==(new minions()).getClass()){
                        System.out.println("You have a sidekick Minion with you. Attack of sidekick is "+h.getCurr_sidekick().getDamage_power());
                        if(h.getCurr_sidekick().getCloning_power_available()){
                            System.out.println("Press c to use cloning ability,else press f to move to the fight");
                            in=scan.next();
                            if(in.equals("c")){
                                h.getCurr_sidekick().special_attack(level_map[level][n]);
                                System.out.println("cloning done");
                            }
                        }
                    }
                }
            }
            while(true){
                System.out.println("Choose move");
                System.out.println("1) Attack");
                System.out.println("2) Defense");
                if(h.getNumber_of_moves()>3){
                    System.out.println("3) Special Attack");
                }
                option=scan.nextInt();
                switch (option) {
                    case 1:
                        System.out.println("You choose to attack");
                        h.attack(level_map[level][n],level_HP_Hero[level],level_HP_Monster[level]);
                        break;
                    case 2:
                        System.out.println("You choose to defend");
                        h.defense(level_map[level][n],level_HP_Hero[level],level_HP_Monster[level]);
                        break;
                    case 3:
                        System.out.println("Special power activated");
                        System.out.println("Performing special attack");
                        h.use_special_power(level_map[level][n],level_HP_Hero[level],level_HP_Monster[level]);
                        break;
                    default:
                        System.out.println("You did Nothing by choosing wrong option");
                        break;
                }
                System.out.println("Monster attack!");
                level_map[level][n].attack(h,level_HP_Hero[level],level_HP_Monster[level]);
                if(h.getHP()<=0){
                    System.out.println("You Lost The game \n Exiting!!!");
                    return;
                }
                else if(h.getXP()>=level*20){
                    level++;
                    System.out.println("REQUIRED XP completed \n levelling up");
                    System.out.println("Level UP: level:"+level);
                    System.out.println(20+" XP awarded");
                    h.setXP(h.getXP()+20);
                    break;
                }
                else if(level_map[level][n].getHP()<=0){
                    System.out.println("Monster Killed");
                    h.setXP(h.getXP()+20);
                    level++;
                    System.out.println(20+"XP awarded");
                    System.out.println("Level UP: level: "+level);
                    System.out.println("Fight won Proceed to next Location");
                    break;
                }
            }
            if(h.getCurr_sidekick().getCloning_power_available()){
                h.getCurr_sidekick().setCloning_power_available(false);
            }
            System.out.println("If you would like to buy a sidekick, type yes. Else type no to upgrade level");
            in=scan.next();
            if(in.equals("yes") || in.equals("YES")){
                System.out.println("Your current XP is "+h.getXP());
                System.out.println("For minion press 1 ");
                System.out.println("For knight press 2");
                option=scan.nextInt();
                System.out.print("XP to spend: ");
                n=scan.nextInt();
                if(n>h.getXP()||(option==1 && n<5) || (option==2 && n<8)){
                    System.out.println("\nNot enough XP available");
                    return;
                }
                Side_kick s;
                if(option==1){
                    s=new minions();
                }
                else if(option==2){
                    s=new knight();
                }
                else{
                    System.out.println("Wrong option");
                    return;
                }
                h.Buy_sidekick(n,s);
            }
        }
    }

    private static final Scanner scan=new Scanner(System.in);
    public static void main(String[] args) {
        int n;
        String name;
        HashMap<String,Hero> map=new HashMap<>();
        Hero h;

        while(true){
            System.out.println("Welcome to ArchLegends");
            System.out.println("Choose your option");
            System.out.println("1) New User");
            System.out.println("2) Existing User");
            System.out.println("3) Exit");
            n=scan.nextInt();
            switch (n) {
                case 1:
                    System.out.println("Enter Username");
                    name=scan.next();
                    System.out.println("choose a Hero");
                    System.out.println("1) Warrior");
                    System.out.println("2) Thief");
                    System.out.println("3) Mage");
                    System.out.println("4) Healer");
                    n=scan.nextInt();
                    if(n==1){
                        h=new Warrior(name);
                    }
                    else if(n==2){
                        h=new Thief(name);
                    }
                    else if(n==3){
                        h=new Mage(name);
                    }
                    else{
                        h=new Healer(name);
                    }
                    map.put(name, h);
                    System.out.println("user creation done. username: "+h.getName()+". Hero type: "+h.getClass()+" Login to play the game. Exiting");
                    break;
                case 2:
                    System.out.println("Enter Username");
                    name=scan.next();
                    if(map.containsKey(name)){
                        System.out.println("User found... logging in");
                        Game_start(map.get(name));

                    }
                    else{
                        System.out.println("User Not found");
                    }
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong option");
                    break;
            }
        }
    }
}