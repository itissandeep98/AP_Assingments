package Ap_assignment.Lab4;

public class Warrior extends Hero{
    Warrior(String name){
        super(name);
        this.setAttackDamage(10);
        this.setDefense_safe(3);
    }
    public void attack(Monster m,int total_hero_hp,int total_monster_hp){
        int temp=10;
        this.setNumber_of_moves(this.getNumber_of_moves()+1);
        if(this.getNumber_of_moves()<=3 && this.getIs_special_power_active()){
            this.setXP(this.getXP()+5);
            this.setHP(this.getHP()+5);
            System.out.println("Your Hp and XP is increased by 5");
        }
        // else if(this.getNumber_of_moves()>3 || !this.getIs_special_power_active() ){
            temp=Math.min(temp, m.getHP());
            m.reduce_Hp(temp);
            System.out.println("You attacked and inflicted "+temp+" damage to the monster");
        // }
        Side_kick s=this.getbestsidekick();
        if(s!=null){
            s.attack(m);
            this.setCurr_sidekick(s);
        }
        System.out.println("Your Hp: "+this.getHP()+"/"+total_hero_hp+" Monsters HP: "+m.getHP()+"/"+total_monster_hp);
        if(this.getNumber_of_moves()>3 && this.getIs_special_power_active()){
            System.out.println("special power deactivated");
            this.setIs_special_power_active(false);
        }
    }
    public void defense(Monster m,int total_hero_hp,int total_monster_hp){
        Side_kick s=this.getbestsidekick();
        this.setCurr_sidekick(s);
        this.setDefense_applicable(true);
        System.out.println("Monster attack reduced by 3");
        this.setNumber_of_moves(this.getNumber_of_moves()+1);
        if(this.getNumber_of_moves()<=3 && this.getIs_special_power_active()){
            this.setXP(this.getXP()+5);
            this.setHP(this.getHP()+5);
            System.out.println("Your Hp and XP is increased by 5");
        }
        if(s!=null){
            s.printhp();
        }
        System.out.println("Your Hp: "+this.getHP()+"/"+total_hero_hp+" Monsters HP: "+m.getHP()+"/"+total_monster_hp);
        if(this.getNumber_of_moves()>3 && this.getIs_special_power_active()){
            this.setIs_special_power_active(false);
            System.out.println("Special power deactivated");
        }
    }

}
