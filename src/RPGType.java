public enum RPGType {

	//		ClassType	lvl	exp	str	dex	cha	spd	lif	
	ARCHER	("Archer",	20,	99,	15,	25,	15,	10,	75),
	WARRIOR	("Warrior",	20,	99,	25,	15,	15,	8,	100);
	
	// ============================================================= Properties
	
	private String type;
	private int maxLvl, maxExp, maxStr, maxDex, maxCha, maxSpd, maxLife;
	
	// =========================================================== Constructors
	
	private RPGType(String type, int maxLvl, int maxExp, int maxStr,
			int maxDex, int maxCha, int maxSpd, int maxLife) {
		this.type = type;
		this.maxLvl = maxLvl;
		this.maxExp = maxExp;
		this.maxStr = maxStr;
		this.maxDex = maxDex;
		this.maxCha = maxCha;
		this.maxSpd = maxSpd;
		this.maxLife = maxLife;
	}
	
	// ================================================================ Getters

	public String getType()	{ return this.type; 	}
	public int getMaxLvl()	{ return this.maxLvl; 	}
	public int getMaxExp()	{ return this.maxExp; 	}
	public int getMaxStr()	{ return this.maxStr; 	}
	public int getMaxDex()	{ return this.maxDex; 	}
	public int getMaxCha()	{ return this.maxCha; 	}
	public int getMaxSpd()	{ return this.maxSpd; 	}
	public int getMaxLife()	{ return this.maxLife; 	}
	
}