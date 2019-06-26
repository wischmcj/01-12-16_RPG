// ROBERT DOUGHT, COLLIN WISCHMEYER

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public abstract class RPGCharacter {

	// ============================================================= Properties
	
	public static final int MAX_SIZE = 700;
	
	private String name;
	private RPGType rpgType;
	private double xPos, yPos;
	private int currentSpeed, lvl, exp, str, dex, cha, wins, characterSpd, life;
	
	// =========================================================== Constructors
	
	public RPGCharacter(String name, RPGType rpgType, double xPos, double yPos) {
		super();
		setName(name);
		setRPGType(rpgType);
		setXPos(xPos);
		setYPos(yPos);
	}
	
	public RPGCharacter(String name, RPGType rpgType) {
		this(name, rpgType, 0, 0);
		this.randomlyGenerateAbilities();
	}


	// ================================================================ Methods
	
	public double getRange(RPGCharacter enemy) {
		return Math.sqrt((this.getXPos() - enemy.getXPos()) * 
				(this.getXPos() - enemy.getXPos()) + 
				(this.getYPos() - enemy.getYPos()) *
				(this.getYPos() - enemy.getYPos()));
	}
	
	public void draw() {
		System.out.println("Error");
	}
	
	public abstract double[] target(ArrayList<RPGCharacter> enemy);
		
	public void move(ArrayList<RPGCharacter> enemys) {
		double[] target = this.target(enemys);
		double maxX = this.getXPos() - target[0];
		double maxY = this.getYPos() - target[1];
		double xMove;
		double yMove;
	
		Random rand = new Random();
		double speed =this.getCurrentSpeed(); 
		
		if(maxX<speed) {
			xMove = speed * rand.nextDouble() * 
				(rand.nextBoolean()?1:-1);
		}
		else {
			xMove = maxY * rand.nextDouble() * 
				(rand.nextBoolean()?1:-1);
		}
		
		if(maxY<speed) {
			yMove = speed * rand.nextDouble() * 
				(rand.nextBoolean()?1:-1);
		}
		else {
			yMove = maxX * rand.nextDouble() * 
					(rand.nextBoolean()?1:-1);
		}
		
		xPos = this.getXPos() + xMove;
		yPos = this.getYPos() + yMove;
		
		if (xPos < 0) 				{xPos = 0;				}
		if (yPos < 0)				{yPos = 0;				}
		if (xPos > MAX_SIZE - 30) 	{xPos = MAX_SIZE - 30;	}
		if (yPos > MAX_SIZE - 100) 	{yPos = MAX_SIZE - 100;	}
		
		this.setXPos(xPos);
		this.setYPos(yPos);
	}
	
	public void run() {
		this.setCurrentSpeed(this.getCharacterSpd());
	}
	public void walk() {
		this.setCurrentSpeed(1);
	}
	public void stop() {
		this.setCurrentSpeed(0);
	}
	
	public void fight(RPGCharacter enemy) {
	}
	
	public void save(PrintWriter pw) {
		// the double rpgtype is not an accident. It is necessary for how I use this method in RPGRunner.
		pw.println(this.getRPGType()); 
		pw.println(this.getRPGType());
		pw.println(this.getName());
		pw.println(this.getXPos());
		pw.println(this.getYPos());
		pw.println(this.getCurrentSpeed());
		pw.println(this.getLvl());
		pw.println(this.getExp());
		pw.println(this.getStr());
		pw.println(this.getDex());
		pw.println(this.getCha());
		pw.println(this.getWins());
		pw.println(this.getCharacterSpd());
		pw.println(this.getLife());
	}
	
	public void load(Scanner fin) {
		this.setRPGType(RPGType.valueOf(fin.nextLine()));
		this.setName(fin.nextLine());
		this.setXPos(Double.parseDouble(fin.nextLine()));
		this.setYPos(Double.parseDouble(fin.nextLine()));
		this.setCurrentSpeed(Integer.parseInt(fin.nextLine()));
		this.setLvl(Integer.parseInt(fin.nextLine()));
		this.setExp(Integer.parseInt(fin.nextLine()));
		this.setStr(Integer.parseInt(fin.nextLine()));
		this.setDex(Integer.parseInt(fin.nextLine()));
		this.setCha(Integer.parseInt(fin.nextLine()));
		this.setWins(Integer.parseInt(fin.nextLine()));
		this.setCharacterSpd(Integer.parseInt(fin.nextLine()));
		this.setLife(Integer.parseInt(fin.nextLine()));	
	}
	
	public void randomlyGenerateAbilities() {
		Random rand = new Random();
		setXPos(MAX_SIZE * rand.nextDouble());
		setYPos(MAX_SIZE * rand.nextDouble());
	}
	
	
	// ==================================================== Getters and Setters
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RPGType getRPGType() {
		return rpgType;
	}
	public void setRPGType(RPGType rpgType) {
		this.rpgType = rpgType;
	}
	public double getXPos() {
		return xPos;
	}
	public void setXPos(double xPos) {
		this.xPos = xPos;
	}
	public double getYPos() {
		return yPos;
	}
	public void setYPos(double yPos) {
		this.yPos = yPos;
	}
	public int getCurrentSpeed() {
		return currentSpeed;
	}
	public void setCurrentSpeed(int currentSpeed) {
		this.currentSpeed = currentSpeed;
	}
	public int getLvl() {
		return lvl;
	}
	public void setLvl(int level) {
		this.lvl = level;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getStr() {
		return str;
	}
	public void setStr(int str) {
		this.str = str;
	}
	public int getDex() {
		return dex;
	}
	public void setDex(int dex) {
		this.dex = dex;
	}
	public int getCha() {
		return cha;
	}
	public void setCha(int cha) {
		this.cha = cha;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int getCharacterSpd() {
		return characterSpd;
	}
	public void setCharacterSpd(int characterSpd) {
		this.characterSpd = characterSpd;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	
}
