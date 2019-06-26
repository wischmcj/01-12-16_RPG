import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Warrior extends RPGCharacter {

	// ============================================================= Properties
	
	// =========================================================== Constructors
	
	public Warrior (String name) {
		super(name, RPGType.WARRIOR);
		this.randomlyGenerateAbilities();
	}
	
	// ================================================================ Methods

	@Override public void run() { super.run(); }
	@Override public void walk() { super.walk(); }
	@Override public void stop() { super.stop(); }
	@Override public void save(PrintWriter pw) { super.save(pw); }
	@Override public void load(Scanner fin) { super.load(fin); }
	
	@Override
	public void randomlyGenerateAbilities() {
		super.randomlyGenerateAbilities();
		Random rand = new Random();
		this.setLvl(1);
		this.setExp(0);
		this.setStr(rand.nextInt(RPGType.WARRIOR.getMaxStr()) + 1);
		this.setDex(rand.nextInt(RPGType.WARRIOR.getMaxDex()) + 1);
		this.setCha(rand.nextInt(RPGType.WARRIOR.getMaxCha()) + 1);
		this.setWins(0);
		this.setCharacterSpd(rand.nextInt(RPGType.WARRIOR.getMaxSpd()) + 1);
		this.setLife(rand.nextInt(RPGType.WARRIOR.getMaxLife()) + 1);
	}
	
	@Override
	public void move(ArrayList<RPGCharacter> enemys) {
		super.move(enemys);
	}
	
	@Override 
	public void fight(RPGCharacter enemy) {	
		if (this.getRange(enemy) < 80) {
			int damage = (this.getStr());
			enemy.setLife(enemy.getLife() - damage);
		}
	}
	
	
	// ==================================================== Getters and Setters

	@Override 
	public int getLvl() { 
		return super.getLvl();	
	}
	
	@Override 
	public void setLvl(int lvl) { 
		if (lvl < 0) lvl = 0;
		if (lvl > RPGType.WARRIOR.getMaxLvl()) lvl = RPGType.WARRIOR.getMaxLvl();
		super.setLvl(lvl);
	}
	
	@Override 
	public int getStr() { 
		return super.getStr();	
		}
	
	@Override 
	public void setStr(int str){		
		if (str < 0) str = 0;
		if (str > RPGType.WARRIOR.getMaxStr()) str = RPGType.WARRIOR.getMaxStr();
		super.setStr(str);
	}
	
	@Override 
	public int getDex() { 
		return super.getDex();	
	}
	
	@Override 
	public void setDex(int dex) 	{ 
		if (dex < 0) dex = 0;
		if (dex > RPGType.WARRIOR.getMaxDex()) dex = RPGType.WARRIOR.getMaxDex();
		super.setDex(dex);
	}
	
	@Override 
	public int getCha() { 
		return super.getCha();	
	}
	
	@Override 
	public void setCha(int cha)	{ 
		if (cha < 0) cha = 0;
		if (cha > RPGType.WARRIOR.getMaxCha()) cha = RPGType.WARRIOR.getMaxCha();
		super.setCha(cha);
	}
	
	@Override 
	public int getLife() { 
		return super.getLife();	
	}
	
	@Override 
	public void setLife(int life) { 
		if (life < 0) life = 0;
		if (life > RPGType.WARRIOR.getMaxLife()) life = RPGType.WARRIOR.getMaxLife();
		super.setLife(life);
	}
	
	@Override 
	public int getExp() { 
		return super.getExp();	
	}
	
	@Override 
	public void setExp(int cha)	{ 
		if (cha < 0) cha = 0;
		if (cha > RPGType.WARRIOR.getMaxCha()) cha = RPGType.WARRIOR.getMaxCha();
		super.setExp(cha);
	}

	@Override
	public double[] target( ArrayList<RPGCharacter> players){
		double dist;
		double max=-1;
		double[] coord = new double[2];
		RPGCharacter target = null;
		for (RPGCharacter enemy : players) {
			dist = this.getRange(enemy);
			if(dist > max) target = enemy;
		}
		coord[0] = target.getXPos();
		coord[1] = target.getYPos();
		return coord;
		
	}
}
