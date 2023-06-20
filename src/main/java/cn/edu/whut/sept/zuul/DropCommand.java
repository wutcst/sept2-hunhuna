package cn.edu.whut.sept.zuul;

import Dao.player_dataDAO;
import Dao.player_itemsDAO;
import Model.tPlayer;
import Model.tPlayer_data;
import Model.tPlayer_items;

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

            tPlayer_items tpi = new tPlayer_items();

            tpi.setPlayer_name(player.getPname());
            tpi.setToken_items(player.tokenitems.size());

            player_itemsDAO piD = new player_itemsDAO();
            piD.update(tpi);


            tPlayer_data pl_data = new tPlayer_data();
            pl_data.setPlayer_name(player.getPname());
            pl_data.setPlayer_stamina(player.currentStamina);
            pl_data.setPlayer_weight(player.currentWeight);
            pl_data.setMaxstamina(player.maxStamina);
            pl_data.setMaxweight(player.maxWeight);


            player_dataDAO pdD = new player_dataDAO();
            pdD.update(pl_data);
        }

        if(!player.tokenitems.isEmpty()){
            if(player.doItemExist(itemName)){
                for(int i = 0; i < player.tokenitems.size(); i++){
                    if(player.tokenitems.get(i).equals(item_drop)){
                    player.dropItem(item_drop);

                        tPlayer_items tpi = new tPlayer_items();

                        tpi.setPlayer_name(player.getPname());
                        tpi.setToken_items(player.tokenitems.size());

                        player_itemsDAO piD = new player_itemsDAO();
                        piD.update(tpi);


                        tPlayer_data pl_data = new tPlayer_data();
                        pl_data.setPlayer_name(player.getPname());
                        pl_data.setPlayer_stamina(player.currentStamina);
                        pl_data.setPlayer_weight(player.currentWeight);
                        pl_data.setMaxstamina(player.maxStamina);
                        pl_data.setMaxweight(player.maxWeight);


                        player_dataDAO pdD = new player_dataDAO();
                        pdD.update(pl_data);

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
