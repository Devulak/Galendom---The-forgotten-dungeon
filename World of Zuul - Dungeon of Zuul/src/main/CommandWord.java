package main;

public enum CommandWord
{
    GO("go"), QUIT("quit"), HELP("help"), MAP("map"), EQUIP("equip"), UNKNOWN("?");
    
    private String commandString;
    
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    public String toString()
    {
        return commandString;
    }
}
