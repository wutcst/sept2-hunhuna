package cn.edu.whut.sept.zuul;

import Dao.player_dataDAO;
import Dao.player_itemsDAO;
import Model.tPlayer;
import Model.tPlayer_data;
import Model.tPlayer_items;

public class MagicCookieCommand implements Action{
    @Override
    public void doAction(Command command, Player player) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to take...
            System.out.println("Eat what?");
            return;
        }
        Item Magic_Cookie;
        String str = "Magic_cookie";
        Magic_Cookie = player.getItem(str);

        if(command.getSecondWord().equals(str)){
            player.maxWeight += 5;
            player.currentStamina += 5;




            System.out.println("you have eat Magic_cookie,your maxWeight will plus 5");
            player.tokenitems.remove(Magic_Cookie);
            player.currentWeight -= 3;

            tPlayer_data pl_data = new tPlayer_data();
            pl_data.setPlayer_name(player.getPname());
            pl_data.setPlayer_stamina(player.currentStamina);
            pl_data.setPlayer_weight(player.currentWeight);
            pl_data.setMaxstamina(player.maxStamina);
            pl_data.setMaxweight(player.maxWeight);

            player_dataDAO pdD = new player_dataDAO();
            pdD.update(pl_data);

            tPlayer_items tpi = new tPlayer_items();

            tpi.setPlayer_name(player.getPname());
            tpi.setToken_items(player.tokenitems.size());

            player_itemsDAO piD = new player_itemsDAO();
            piD.update(tpi);

            System.out.println("now: " + player.currentWeight + "/" + player.maxWeight);
        }
    }
}
