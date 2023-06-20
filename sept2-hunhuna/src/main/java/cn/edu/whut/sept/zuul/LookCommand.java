package cn.edu.whut.sept.zuul;

import java.util.ArrayList;

public class LookCommand implements Action{

//    @Override
//    public boolean execute(Game game, Player player) {
//
//        Room currentRoom = game.getCurrentRoom();
//        System.out.println("you are " + game.getCurrentRoom().getLongDescription());
//
//        ArrayList<Item> items = currentRoom.getItems();
//        if(items.isEmpty()){
//            System.out.println("this room has no item");
//        }else{
//            System.out.println("Items:");
//            for(Item item : items){
//                System.out.println(" - " + item.getIname() + "(weight:" + item.getIweight() + ")" + item.getIdescription());
//            }
//        }
//        return false;
//    }

    @Override
    public void doAction(Command command, Player player) {
        Room currentRoom = player.getCurrentRoom();
        System.out.println("you are " + player.getCurrentRoom().getLongDescription());

        ArrayList<Item> items = currentRoom.getItems();
        if(items.isEmpty()){
            System.out.println("this room has no item");
        }else{
            System.out.println("Items:");
            for(Item item : items){
                System.out.println(" - " + item.getIname() + "(weight:" + item.getIweight() + ")" + item.getIdescription());
            }
        }
    }
}
