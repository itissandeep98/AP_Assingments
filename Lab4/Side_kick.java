package Ap_assignment.Lab4;

public abstract class Side_kick {
    private int xp;
	private int hp;
    private int damage_power;
	private boolean cloning_power_available; // only for minions
    private int xp_increase;
    protected Side_kick cloned[];


    public int getXp_increase() {
    	return this.xp_increase;
    }
    public void setXp_increase(int xp_increase) {
    	this.xp_increase = xp_increase;
    }
	public boolean getCloning_power_available() {
		return this.cloning_power_available;
	}
	public void setCloning_power_available(boolean cloning_power_available) {
		this.cloning_power_available = cloning_power_available;
	}
	public int getDamage_power() {
		return this.damage_power;
	}
	public void setDamage_power(int damage_power) {
		this.damage_power = damage_power;
	}
	public int getXp() {
		return this.xp;
	}
	public void setXp(int xp) {
        this.xp = xp;
        xp_increase+=xp;
        if(xp_increase>=5){
            this.hp+=1;
            xp_increase=xp_increase%5;
        }
	}
	public int getHp() {
		return this.hp;
	}
	public void setHp(int hp) {
        this.hp = hp;
        if(this.hp<0){
            this.hp=0;
        }
	}
	@Override
	public Side_kick clone(){
		Side_kick s=new minions();
		s.setCloning_power_available(this.getCloning_power_available());
		s.setDamage_power(this.getDamage_power());
		s.setHp(this.getHp());
		s.setXp(this.getXp());
		return s;
	}
	@Override
	public boolean equals(Object p){
		if(p instanceof Side_kick){
			Side_kick temp=(Side_kick) p;
			if(temp.xp==xp && temp.hp==hp && temp.damage_power==damage_power){
				return true;
			}
		}
		return false;
	}

	abstract void attack(Monster m);
    abstract void special_attack(Monster m);
    abstract void printhp();
}
