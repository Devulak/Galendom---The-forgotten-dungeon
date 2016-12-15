package main;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Scoreboard
{
	private ArrayList<Score> scoreboard;
	
	public Scoreboard() throws IOException
	{
		this.scoreboard = new ArrayList<>();
		try {
			load();
		} catch (IOException | ClassNotFoundException ex) {
			Logger.getLogger(Scoreboard.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	private void sort()
	{
		Collections.sort(this.scoreboard, Comparator.comparingInt(Score::getScore));
		Collections.reverse(this.scoreboard);
	}
	
	private void save() throws IOException
	{
		try (FileOutputStream fos = new FileOutputStream("src/scoreboard.scor"))
		{  
			ObjectOutputStream oos = new ObjectOutputStream(fos);  

			oos.writeObject(this.scoreboard);
			
			oos.close();
			fos.close();
		}
		System.out.println("Serialization succesful");
	}
	
	private void load() throws IOException, ClassNotFoundException
	{
		try (FileInputStream fis = new FileInputStream("src/scoreboard.scor"))
		{
			ObjectInputStream oos = new ObjectInputStream(fis);
			
			scoreboard = (ArrayList) oos.readObject();
			
			oos.close();
			fis.close();
		}
		System.out.println("Deserialization succesful");
	}
	
	public void add(String name, int points)
	{
		this.scoreboard.add(new Score(name, points));
		
		this.sort();
		
		try {
			save();
		} catch (IOException ex) {
			Logger.getLogger(Scoreboard.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public ArrayList<Score> getScoreboard()
	{
		return this.scoreboard;
	}
}