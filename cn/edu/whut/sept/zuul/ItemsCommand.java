package cn.edu.whut.sept.zuul;

public class ItemsCommand implements Action{
    @Override
    public void doAction(Command command, Player player) {

        String str = "you have token these items: ";
        System.out.println(str);
        for(Item e : player.tokenitems)
            System.out.println(e.getIname() + "(" + e.getIweight() + ")");

        System.out.println("now:" + player.currentWeight + "/" + player.maxWeight);
        player.printProgressBar("Capacity", player.currentWeight, player.maxWeight);
        player.printProgressBar("Stamina", player.currentStamina, player.maxStamina);


    }



//    @Override
//    public boolean execute(Game game, Player player) {
//        Room currentRoom = game.getCurrentRoom();
//        System.out.println(currentRoom.getLongDescription() + "/n");
//        player.showTokenitems();
//        return false;
//    }


}
