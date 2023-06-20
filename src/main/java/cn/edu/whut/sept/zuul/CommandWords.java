package cn.edu.whut.sept.zuul;

import java.util.HashMap;
import java.util.Iterator;

public class CommandWords
{
//    private static String[] validCommands = {
//            "go", "quit", "help", "look", "back"
//    };

    private HashMap<String, Action> commands;

    public void constructCommands(){
        commands = new HashMap<String, Action>();

        Action executeHelp = new HelpCommand();
        commands.put("help", executeHelp);

        Action executeQuit = new QuitCommand();
        commands.put("quit", executeQuit);

        Action executeBack = new BackCommand();
        commands.put("back", executeBack);

        Action executeGo = new GoCommand();
        commands.put("go", executeGo);

        Action executeLook = new LookCommand();
        commands.put("look", executeLook);

        Action executeTake = new TakeCommand();
        commands.put("take", executeTake);

        Action executeItems = new ItemsCommand();
        commands.put("items", executeItems);

        Action executeDrop = new DropCommand();
        commands.put("drop", executeDrop);

        Action executeEat = new MagicCookieCommand();
        commands.put("eat", executeEat);

        Action executeTrans = new TransRoom();
        commands.put("trans", executeTrans);

        Action executeRest = new RestCommand();
        commands.put("rest", executeRest);

        Action executeLog = new LogCommand();
        commands.put("y", executeLog);

        Action executeReg = new RegistCommand();
        commands.put("n", executeReg);
    }

    public CommandWords()
    {


//        commands = new HashMap<String, Command>();
//        commands.put("go", new GoCommand());
//        commands.put("help", new HelpCommand(this));
//        commands.put("quit", new QuitCommand());
//        commands.put("back", new BackCommand());
//        commands.put("take", );
    }

//    public Command get(String word)
//    {
//        return (Command)commands.get(word);
//    }

    public boolean idCommand(String aString){
        if(commands.containsKey(aString)){
            return true;
        }
        return false;
    }

    public Action getCommand(String command){
        return commands.get(command);
    }

    public void showAll()
    {
//        for(Iterator i = commands.keySet().iterator(); i.hasNext(); ) {
//            System.out.print(i.next() + "  ");
//        }
//        System.out.println();
        System.out.println("go, help, quit, take, drop, back, eat, trans, items, rest. ");
    }


}
