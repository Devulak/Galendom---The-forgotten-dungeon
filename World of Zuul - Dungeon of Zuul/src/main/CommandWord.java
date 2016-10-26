package main;

public enum CommandWord
{
    GO("go"), QUIT("quit"), HELP("help"), MAP("map"), DROP("drop"), UNKNOWN("?");
    
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
