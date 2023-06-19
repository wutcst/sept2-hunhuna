package cn.edu.whut.sept.zuul;
//import com.alibaba.fastjson.JSON;

import Dao.player_dataDAO;
import Dao.player_itemsDAO;
import Model.tPlayer;
import Model.tPlayer_data;
import Model.tPlayer_items;

import java.util.ArrayList;

public class TakeCommand implements Action{


    @Override
    public void doAction(Command command, Player player) {

        if(player.isResting.get()){
            System.out.println("you are resting,please wait 5s... ");
        }else{

            if (!command.hasSecondWord()) {
                // if there is no second word, we don't know what to take...
                System.out.println("Take what?");
                return;
            }

            String itemName = command.getSecondWord();
            Item item_take;
            item_take = player.getCurrentRoom().getItem(itemName);

//        ArrayList<Item> items = player.getCurrentRoom().getItems();


            if(player.currentWeight + item_take.getIweight() < player.maxWeight){
                player.tokenitems.add(item_take);

                tPlayer_items tpi = new tPlayer_items();

                tpi.setPlayer_name(player.getPname());
                tpi.setToken_items(player.tokenitems.size());

                player_itemsDAO piD = new player_itemsDAO();
                piD.update(tpi);

                player.currentWeight += item_take.getIweight();

                tPlayer_data pl_data = new tPlayer_data();
                pl_data.setPlayer_name(player.getPname());
                pl_data.setPlayer_stamina(player.currentStamina);
                pl_data.setPlayer_weight(player.currentWeight);
                pl_data.setMaxstamina(player.maxStamina);
                pl_data.setMaxweight(player.maxWeight);

                player_dataDAO pdD = new player_dataDAO();
                pdD.update(pl_data);

                player.getCurrentRoom().removeItem(item_take);
                System.out.println("you picked up the item:" + item_take.getIname() + "(" + item_take.getIweight() + ")");
                System.out.println("now: " + player.currentWeight + " / " + player.maxWeight);
            } else if (player.currentWeight + item_take.getIweight() >= player.maxWeight) {
                System.out.println("You've reached your maxweight and can't pick up new item.");
            } else{
                System.out.println("Do not exist this item in this room!");
                return;
            }
//        player.getCurrentRoom().getItem(itemName).getIname();
            player.printProgressBar("Capacity", player.currentWeight, player.maxWeight);
            player.printProgressBar("Stamina", player.currentStamina, player.maxStamina);
            player.getCurrentRoom().showRoomItems();
        }
        }

}
