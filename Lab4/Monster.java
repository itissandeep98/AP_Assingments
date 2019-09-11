package Lab;

import java.util.*;
public class Monster{
    private int HP;
    private double curr_attack_power;

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
        this.setHP(100);
    }

}
class Zombie extends Monster{
    Zombie(){
        this.setHP(100);
    }

}
class Fiends extends Monster{
    Fiends(){
        this.setHP(200);
    }
}
class Lionfang extends Monster{
    Lionfang(){
        this.setHP(250);

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
            System.out.println("Your HP: "+h.getHP()+"/"+total_hero_hp+" Monsters HP: "+this.getHP()+"/"+total_monster_hp);

        }

    }
}