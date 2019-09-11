import java.util.*;

abstract class Hero{
    protected String name;
    protected int HP;
    protected int XP;
    protected int defense_safe;
    protected boolean defense_applicable;
    protected int number_of_moves;
    protected int AttackDamage;
    protected boolean is_special_power_active;

    public int getAttackDamage() {
    	return this.AttackDamage;
    }
    public void setAttackDamage(int AttackDamage) {
    	this.AttackDamage = AttackDamage;
    }
    public int getDefense_safe() {
        return this.defense_safe;
    }
    public void setDefense_safe(int defense_safe) {
        this.defense_safe = defense_safe;
    }
    public int getNumber_of_moves() {
    	return this.number_of_moves;
    }
    public void setNumber_of_moves(int number_of_moves) {
    	this.number_of_moves = number_of_moves;
    }
    public boolean getIs_special_power_active() {
    	return this.is_special_power_active;
    }
    public void setIs_special_power_active(boolean is_special_power_active) {
    	this.is_special_power_active = is_special_power_active;
    }
    public String getName() {
        return this.name;
    }
    public int getHP() {
    	return this.HP;
    }
    public void reduce_Hp(int HP){
        this.HP-=HP;
        if(this.defense_applicable){
            this.defense_applicable=false;
            this.HP+=this.defense_safe;
        }
        if(this.HP<0){
            this.HP=0;
        }
    }
    public void setHP(int HP) {
        this.HP = HP;
        if(this.HP<0){
            this.HP=0;
        }
    }
    public int getXP() {
    	return this.XP;
    }
    public void setXP(int XP) {
    	this.XP = XP;
    }
    abstract void attack(Monster m,int total_hero_hp,int total_monster_hp);
    abstract void defense(Monster m,int total_hero_hp,int total_monster_hp);
    public void use_special_power(Monster m,int total_hero_hp,int total_monster_hp){
        this.number_of_moves=0;
        this.is_special_power_active=true;
        this.attack(m,total_hero_hp,total_monster_hp);
    }
}
class Warrior extends Hero{
    Warrior(String name){
        super();
        this.name=name;
        this.AttackDamage=10;
        this.XP=0;
        this.defense_safe=3;
        this.is_special_power_active=false;
        this.defense_applicable=false;
    }
    public void attack(Monster m,int total_hero_hp,int total_monster_hp){
        int temp=10;
        this.number_of_moves+=1;
        if(this.number_of_moves<=3 && this.is_special_power_active){
            this.XP+=5;
            this.HP+=5;
            System.out.println("Your Hp and XP is increased by 5");
        }
        else if(this.number_of_moves>3){
            temp=Math.min(temp, m.getHP());
            m.reduce_Hp(temp);
            System.out.println("You attacked and inflicted "+temp+" damage to the monster");
        }
        System.out.println("Your Hp: "+this.HP+"/"+total_hero_hp+" Monsters HP: "+m.getHP()+"/"+total_monster_hp);
        if(this.number_of_moves>3 && this.is_special_power_active){
            System.out.println("special power deactivated");
            this.is_special_power_active=false;
        }
    }
    public void defense(Monster m,int total_hero_hp,int total_monster_hp){
        this.defense_applicable=true;
        System.out.println("Monster attack reduced by 3");
        this.number_of_moves+=1;
        if(this.number_of_moves<=3 && this.is_special_power_active){
            this.XP+=5;
            this.HP+=5;
            System.out.println("Your Hp and XP is increased by 5");
        }
        System.out.println("Your Hp: "+this.HP+"/"+total_hero_hp+" Monsters HP: "+m.getHP()+"/"+total_monster_hp);
        if(this.number_of_moves>3 && this.is_special_power_active){
            this.is_special_power_active=false;
            System.out.println("Special power deactivated");
        }
    }

}
class Mage extends Hero{
    Mage(String name){
        super();
        this.name=name;
        this.AttackDamage=5;
        this.XP=0;
        this.defense_safe=5;
        this.is_special_power_active=false;
        this.defense_applicable=false;

    }
    public void attack(Monster m,int total_hero_hp,int total_monster_hp){
        int temp=5;
        this.number_of_moves+=1;
        if(this.number_of_moves<=3 && this.is_special_power_active){
            temp=(int)(m.getHP()*0.05);
            temp=Math.min(temp, m.getHP());
            m.reduce_Hp(temp);
            System.out.println("You attacked and inflicted "+temp+" damage to the monster");
        }
        else if(this.number_of_moves>3){
            temp=Math.min(temp, m.getHP());
            m.reduce_Hp(temp);
            System.out.println("You attacked and inflicted "+temp+" damage to the monster");
        }
        System.out.println("Your Hp: "+this.HP+"/"+total_hero_hp+" Monsters HP: "+m.getHP()+"/"+total_monster_hp);
        if(this.number_of_moves>3 && this.is_special_power_active){
            this.is_special_power_active=false;
            System.out.println("special power deactivated");
        }
    }
    public void defense(Monster m,int total_hero_hp,int total_monster_hp){
        this.defense_applicable=true;
        System.out.println("Monster attack reduced by 5");
        this.number_of_moves+=1;
        if(this.number_of_moves<3 && this.is_special_power_active){
            int temp=(int)(m.getHP()*0.05);
            temp=Math.min(temp, m.getHP());
            m.reduce_Hp(temp);
            System.out.println("You caused "+temp+" damage to the monster");
        }
        System.out.println("Your Hp: "+this.HP+"/"+total_hero_hp+" Monsters HP: "+m.getHP()+"/"+total_monster_hp);
        if(this.number_of_moves>3 && this.is_special_power_active){
            this.is_special_power_active=false;
            System.out.println("Special power deactivated");
        }
    }

}
class Thief extends Hero{
    Thief(String name){
        super();
        this.name=name;
        this.AttackDamage=6;
        this.XP=0;
        this.defense_safe=4;
        this.is_special_power_active=false;
        this.defense_applicable=false;
    }
    public void attack(Monster m,int total_hero_hp,int total_monster_hp){
        int temp=6;
        this.number_of_moves+=1;
        if(this.is_special_power_active){
            temp=(int)(m.getHP()*0.3);
            temp=Math.min(temp, m.getHP());
            m.reduce_Hp(temp);
            this.HP+=temp;
            this.is_special_power_active=false;
            System.out.println("You have stolen "+temp+" HP from the monster");
            System.out.println("special power deactivated");
        }
        else{
            temp=Math.min(temp, m.getHP());
            m.reduce_Hp(temp);
            System.out.println("You attacked and inflicted "+temp+" damage to the monster");
        }
        System.out.println("Your Hp: "+this.HP+"/"+total_hero_hp+" Monsters HP: "+m.getHP()+"/"+total_monster_hp);
    }
    public void defense(Monster m,int total_hero_hp,int total_monster_hp){
        this.number_of_moves+=1;
        this.defense_applicable=true;
        System.out.println("Monster attack reduced by 4");
        if(this.is_special_power_active){
            int temp=(int)(m.getHP()*0.3);
            temp=Math.min(temp, m.getHP());
            m.reduce_Hp(temp);
            this.HP+=temp;
            this.is_special_power_active=false;
            System.out.println("You stole "+temp+" HP from monster");
            System.out.println("special power deactivated");
        }
        System.out.println("Your Hp: "+this.HP+"/"+total_hero_hp+" Monsters HP: "+m.getHP()+"/"+total_monster_hp);
    }

}
class Healer extends Hero{
    Healer(String name){
        super();
        this.name=name;
        this.AttackDamage=4;
        this.XP=0;
        this.defense_safe=8;
        this.is_special_power_active=false;
        this.defense_applicable=false;
    }
    public void attack(Monster m,int total_hero_hp,int total_monster_hp){
        int temp=4;
        this.number_of_moves+=1;
        if(this.number_of_moves<=3 && this.is_special_power_active){
            temp=(int)(this.HP*0.05);
            this.HP=temp;
            System.out.println("your Hp increased by 5% ");
        }
        else if(this.number_of_moves>3){
            temp=Math.min(temp, m.getHP());
            m.reduce_Hp(temp);
            System.out.println("You attacked and inflicted "+temp+" damage to the monster");
        }
        System.out.println("Your Hp: "+this.HP+"/"+total_hero_hp+" Monsters HP: "+m.getHP()+"/"+total_monster_hp);
        if(this.number_of_moves<=3 && this.is_special_power_active){
            this.is_special_power_active=false;
            System.out.println("Special power deactivated");
        }

    }
    public void defense(Monster m,int total_hero_hp,int total_monster_hp){
        this.defense_applicable=true;
        this.number_of_moves+=1;
        System.out.println("Monster attack reduced by 8");
        if(this.number_of_moves<=3 && this.is_special_power_active){
            int temp=(int)(this.HP*0.05);
            this.HP=temp;
            System.out.println("your Hp increased by 5% ");
        }
        System.out.println("Your Hp: "+this.HP+"/"+total_hero_hp+" Monsters HP: "+m.getHP()+"/"+total_monster_hp);
        if(this.number_of_moves<=3 && this.is_special_power_active){
            this.is_special_power_active=false;
            System.out.println("Special power deactivated");
        }
    }


}

class Monster{
    protected int HP;
    protected double curr_attack_power;

    public int getHP() {
        return this.HP;
    }
    public void setHP(int HP) {
        this.HP = HP;
    }
    public double getCurr_attack_power() {
    	return this.curr_attack_power;
    }
    public void setCurr_attack_power(double curr_attack_power) {
    	this.curr_attack_power = curr_attack_power;
    }
    public void reduce_Hp(int n){
        this.HP-=n;
        if(this.HP<=0){
            this.HP=0;
        }
    }
    public void attack(Hero h,int total_hero_hp,int total_monster_hp){
        Random ran=new Random();
        double val=ran.nextGaussian();
        while(val<0){
            val=ran.nextGaussian();
        }
        int temp=(int)((val+1)*(0.125)*this.HP);
        h.reduce_Hp(temp);
        System.out.println("The monster attacked and inflicted "+temp+" damage to you");
        System.out.println("Your HP: "+h.getHP()+"/"+total_hero_hp+" Monsters HP: "+this.HP+"/"+total_monster_hp);

    }
}
class Goblin extends Monster{
    Goblin(){
        this.HP=100;
    }

}
class Zombie extends Monster{
    Zombie(){
        this.HP=100;
    }

}
class Fiends extends Monster{
    Fiends(){
        this.HP=200;
    }
}
class Lionfang extends Monster{
    Lionfang(){
        this.HP=250;
    }

    @Override
    public void attack(Hero h,int total_hero_hp,int total_monster_hp){
        Random ran=new Random();
        int val=ran.nextInt(100);
        if(val>10){
            super.attack(h,total_hero_hp,total_monster_hp);
        }
        else{
            int temp=h.getHP()/2;
            h.reduce_Hp(temp);
            System.out.println("The monster attacked and inflicted "+temp+" damage to you");
            System.out.println("Your HP: "+h.getHP()+"/"+total_hero_hp+" Monsters HP: "+this.HP+"/"+total_monster_hp);

        }

    }
}

public class Assignment3{
    static void Game_start(Hero h){

        int[] level_HP_Hero={Integer.MAX_VALUE,100,150,200,250};
        int[] level_HP_Monster={Integer.MAX_VALUE,100,100,200,250};
        int[] next_levels={0,0,0};

        int n=0,level=1,option,curr=0;
        System.out.println("Welcome "+h.getName());
        while(true){
            Monster[][] level_map={null,{new Goblin(),new Goblin(),new Goblin(),},
                            {new Zombie(),new Zombie(),new Zombie(),},
                            {new Fiends(),new Fiends(),new Fiends(),},
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
                level-=1;
                continue;
            }
            System.out.println("Enter -1 to exit");
            n=scan.nextInt()-1;
            curr=next_levels[n];
            if(n<0 || n>2){
                System.out.println("Exiting");
                return;
            }
            System.out.println("Moving to Location "+curr);
            if(n<4){
                h.setHP(level_HP_Hero[level]);
            }
            System.out.println("Fight started, you are fighting a level "+level+" Monster");
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
                    h.setXP(h.getHP()+20);
                    break;
                }
                else if(level_map[level][n].getHP()<=0){
                    System.out.println("Monster Killed");
                    h.setXP(h.getHP()+20);
                    level++;
                    System.out.println(20+"XP awarded");
                    System.out.println("Level UP: level: "+level);
                    System.out.println("Fight won Proceed to next Location");
                    break;
                }
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