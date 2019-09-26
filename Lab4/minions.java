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
	@Override
	public minions clone(){
		minions s=null;
		try{
			s=(minions) super.clone();
		}
		catch(CloneNotSupportedException e){
			s=null;
		}
		s.setCloning_power_available(this.getCloning_power_available());
		s.setDamage_power(this.getDamage_power());
		s.setHp(this.getHp());
		s.setXp(this.getXp());
		return s;
	}

	void special_attack(Monster m){
		cloned=new minions[3];
		for(int i=0;i<3;i++){
			cloned[i]=this.clone();
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
