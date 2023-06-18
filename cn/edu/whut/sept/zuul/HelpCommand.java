package cn.edu.whut.sept.zuul;

public class HelpCommand implements Action
{
    CommandWords commandWords = new CommandWords();

    public HelpCommand()
    {

    }


//    public boolean execute(Game game, Player player)
//    {
//        System.out.println("You are lost. You are alone. You wander");
//        System.out.println("around at the university.");
//        System.out.println();
//        System.out.println("Your command words are:");
//        commandWords.showAll();
//        return false;
//    }


    public void doAction(Command command, Player player) {
        System.out.println("You are lost. ");
        System.out.println("You are alone. ");
        System.out.println("You wander around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        commandWords.showAll();
    }
}
