package main;

public enum CommandWord
{
    GO("go"), QUIT("quit"), HELP("help"), MAP("map"), UNKNOWN("?"), LOOK("look"), STATUS("status"), INVENTORY("inventory"), ATTACK("attack"), USE("use"), TAKE("take");
    
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
