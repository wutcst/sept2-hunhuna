package cn.edu.whut.sept.zuul;

public class QuitCommand implements Action
{
    public QuitCommand(){

    }

//    public boolean execute(Game game, Player player)
//    {
//        if(hasSecondWord()) {
//            System.out.println("Quit what?");
//            return false;
//        }
//        else {
//            return true;  // signal that we want to quit
//        }
//    }

    @Override
    public void doAction(Command command, Player player) {
        if (!command.hasSecondWord()) {
            Command.setWantToQuit(true); // signal that we want to quit
        } else {
            System.out.println("Quit what?");
        }
    }

}
