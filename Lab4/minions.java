package Ap_assignment.Lab4;
public class minions extends Side_kick implements Cloneable{
	minions(){
		this.setCloning_power_available(false);
		this.setDamage_power(1);
        this.setHp(100);
        this.setXp_increase(0);
	}
	void attack(Monster m){
		if(this.getCloning_power_available()){
			for(int i=0;i<3;i++){
				cloned[i].attack(m);
			}
		}
        m.reduce_Hp(this.getDamage_power());
        System.out.println("Sidekick attacked and inflicted "+this.getDamage_power()+" damage to the monster");
        System.out.println("Sidekick HP: "+this.getHp());

	}


	void special_attack(Monster m){
		cloned=new minions[3];
		for(int i=0;i<3;i++){
			try{
				cloned[i]=super.clone();
			}
			catch(Exception e){
				cloned[i]=this.clone();
			}
		}
		this.setCloning_power_available(true);
    }
    void printhp(){
        System.out.println("Sidekick HP: "+this.getHp());
        if(this.getCloning_power_available()){
            for(int i=0;i<3;i++){
                cloned[i].printhp();
            }
        }
    }
}
