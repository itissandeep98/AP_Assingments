package Ap_assignment.Lab4;

public class Thief extends Hero{
    Thief(String name){
        super(name);
        this.setAttackDamage(6);
        this.setDefense_safe(4);
    }
    public void attack(Monster m,int total_hero_hp,int total_monster_hp){

        int temp=6;
        this.setNumber_of_moves(this.getNumber_of_moves()+1);
        if(this.getIs_special_power_active()){
            temp=(int)(m.getHP()*0.3);
            temp=Math.min(temp, m.getHP());
            m.reduce_Hp(temp);
            this.setHP(this.getHP()+temp);
            this.setIs_special_power_active(false);
            System.out.println("You have stolen "+temp+" HP from the monster");
            System.out.println("special power deactivated");
        }
        // else{
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
    }
    public void defense(Monster m,int total_hero_hp,int total_monster_hp){
        this.setNumber_of_moves(this.getNumber_of_moves()+1);
        this.setDefense_applicable(true);
        System.out.println("Monster attack reduced by 4");
        if(this.getIs_special_power_active()){
            int temp=(int)(m.getHP()*0.3);
            temp=Math.min(temp, m.getHP());
            m.reduce_Hp(temp);
            this.setHP(this.getHP()+temp);
            this.setIs_special_power_active(false);
            System.out.println("You stole "+temp+" HP from monster");
            System.out.println("special power deactivated");
        }
        System.out.println("Your Hp: "+this.getHP()+"/"+total_hero_hp+" Monsters HP: "+m.getHP()+"/"+total_monster_hp);
    }

}
