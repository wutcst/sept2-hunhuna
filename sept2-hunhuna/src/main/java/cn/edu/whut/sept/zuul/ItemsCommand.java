package cn.edu.whut.sept.zuul;

public class ItemsCommand implements Action{
    @Override
    public void doAction(Command command, Player player) {

        String str = "you have token these items: ";
        for(Item e : player.tokenitems)
            str += e.getIname();
        System.out.println(str);
    }


//    @Override
//    public boolean execute(Game game, Player player) {
//        Room currentRoom = game.getCurrentRoom();
//        System.out.println(currentRoom.getLongDescription() + "/n");
//        player.showTokenitems();
//        return false;
//    }
}
