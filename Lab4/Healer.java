package Ap_assignment.Lab4;
public class Healer extends Hero{
    Healer(String name){
        super(name);
        this.setAttackDamage(4);
        this.setDefense_safe(8);
    }
    public void attack(Monster m,int total_hero_hp,int total_monster_hp){

        int temp=4;
        this.setNumber_of_moves(this.getNumber_of_moves()+1);
        if(this.getNumber_of_moves()<=3 && this.getIs_special_power_active()){
            temp=(int)(this.getHP()*0.05);
            this.setHP(this.getHP()+temp);
            System.out.println("your Hp increased by 5% ");
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
            this.setIs_special_power_active(false);
            System.out.println("Special power deactivated");
        }

    }

    public void defense(Monster m, int total_hero_hp, int total_monster_hp) {
        this.setDefense_applicable(true);
        this.setNumber_of_moves(this.getNumber_of_moves()+1);
        System.out.println("Monster attack reduced by 8");
        if(this.getNumber_of_moves()<=3 && this.getIs_special_power_active()){
            int temp=(int)(this.getHP()*0.05);
            this.setHP(this.getHP()+temp);
            System.out.println("your Hp increased by 5% ");
        }
        System.out.println("Your Hp: "+this.getHP()+"/"+total_hero_hp+" Monsters HP: "+m.getHP()+"/"+total_monster_hp);
        this.setIs_special_power_active(false);
        System.out.println("Special power deactivated");
    }
}
