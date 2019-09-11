package Ap_assignment.Lab4;

public class knight extends Side_kick{
	knight(){
		this.setDamage_power(2);
        this.setHp(100);
        this.setXp_increase(0);
	}
	void attack(Monster m){
		m.reduce_Hp(this.getDamage_power());

	}
	void special_attack(Monster m){

    }
    void printhp(){
        System.out.println("Sidekick HP: "+this.getHp());
    }
}
