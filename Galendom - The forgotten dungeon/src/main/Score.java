package main;

import java.io.Serializable;

public class Score implements Serializable
{
	private String name;
	private int score;
	
	// Constructor
	public Score(String name, int score)
	{
		this.name = name;
		this.score = score;
	}
	
	@Override
	public String toString()
	{
		return String.format("%-3s", this.name).replace(' ', '_') + " --- --- --- " + String.format("%1$05d", this.score);
	}
	
	public int getScore()
	{
		return this.score;
	}
}