package main.creature;

public interface Levelable 
{

	static final int EXP_BASE = 4; // The amount of experience you need atleast per level
	static final int EXP_REQUIRED_RATIO = 2; // The amount of experience you need further more per level
	static final int EXPERIENCE_GAIN_RATIO = 2; // The amount of experience you get per level of the monster

	static final int HEALTH_BASE_AMOUNT = 8; // The base amount of health you have
	static final int HEALTH_GAIN_AMOUNT = 4; // The amount of health you gain each level

	int getLevel();
}
