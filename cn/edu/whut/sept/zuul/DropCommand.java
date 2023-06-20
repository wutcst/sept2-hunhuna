package cn.edu.whut.sept.zuul;

public class DropCommand implements Action{

    public DropCommand(){

    }
    @Override
    public void doAction(Command command, Player player) {
        Item item_drop = null;

        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to take...
            System.out.println("Drop what?");
            return;
        }

        String itemName = command.getSecondWord();
        String str = "all";
        item_drop = player.getItem(itemName);

        if (itemName.equals(str)) {
            player.dropAllItems();
        }

        if(!player.tokenitems.isEmpty()){
            if(player.doItemExist(itemName)){
                for(int i = 0; i < player.tokenitems.size(); i++){
                    if(player.tokenitems.get(i).equals(item_drop)){
                    player.dropItem(item_drop);
//                        player.tokenitems.remove(i);
//                        player.currentWeight -= item_drop.getIweight();
//                        player.getCurrentRoom().addItem(item_drop);
//                        System.out.println("you dropped the item:" + item_drop.getIname());
                    }
                }
            }

        }else {
            System.out.println("Player does not have this item!");
            return;
        }

        player.printProgressBar("Capacity", player.currentWeight, player.maxWeight);
//        player.printProgressBar("Stamina", player.currentWeight, player.maxWeight);
        player.printProgressBar("Stamina", player.currentStamina, player.maxStamina);
        player.getCurrentRoom().showRoomItems();
    }
}
