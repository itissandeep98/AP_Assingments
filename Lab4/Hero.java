package Lab;

import java.util.*;
public abstract class Hero{
    private String name;
    private int HP;
    private int XP;
    private int defense_safe;
    private boolean defense_applicable;
    private int number_of_moves;
    private int AttackDamage;
	private boolean is_special_power_active;
	private List<Side_kick> sidekicks;
	private Side_kick curr_sidekick;
    Hero(String name){
        this.name=name;
        this.setXP(0);
        this.sidekicks=new ArrayList<Side_kick>();
        this.setDefense_applicable(false);
        this.setIs_special_power_active(false);
    }
    public boolean getDefense_applicable() {
        return this.defense_applicable;
    }
    public void setDefense_applicable(boolean defense_applicable) {
        this.defense_applicable = defense_applicable;
    }
    public Side_kick getCurr_sidekick() {
        if(this.curr_sidekick==null){
            this.curr_sidekick=this.getbestsidekick();
        }
		return this.curr_sidekick;
	}
	public void setCurr_sidekick(Side_kick curr_sidekick) {
		this.curr_sidekick = curr_sidekick;
	}
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
        if(this.curr_sidekick==null){
            this.curr_sidekick=this.getbestsidekick();
        }
        int damage=-1*HP;
        if(this.defense_applicable){
            this.defense_applicable=false;
            damage+=this.defense_safe;
        }
        if(curr_sidekick!=null && curr_sidekick.getClass()==(new knight()).getClass()){
            damage+=5;
        }
        if(damage<0){
            this.HP+=damage;
            if(curr_sidekick!=null){
                this.curr_sidekick.setHp(this.curr_sidekick.getHp()+3*damage/2);
                if(this.curr_sidekick.getCloning_power_available()){
                    for(int i=0;i<3;i++){
                        this.curr_sidekick.cloned[i].setHp(this.curr_sidekick.getHp()+3*damage/2);
                    }
                }
                this.curr_sidekick.printhp();
            }
        }
        if(this.HP<0){
            this.HP=0;
        }
        if(curr_sidekick!=null && this.curr_sidekick.getHp()<=0){
            this.sidekicks.remove(this.curr_sidekick);
            this.curr_sidekick=null;
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
    public void Buy_sidekick(int xp_spent,Side_kick s){
		this.XP-=xp_spent;
		this.sidekicks.add(s);
		if(s.getClass()==(new minions()).getClass()){
			s.setDamage_power(1+(xp_spent-5)/2);
		}
		else{
			s.setDamage_power(2+(xp_spent-8)/2);
        }
        System.out.println("Side kick bought successfully");
    }
    public Side_kick getbestsidekick(){
        Side_kick s=null;
        Collections.sort(sidekicks,new sortbyxp());
        if(sidekicks.size()>0){
            s=sidekicks.get(0);
        }
        return s;
    }
}