package cn.edu.whut.sept.zuul;

import Dao.player_dataDAO;
import Dao.player_pathDAO;
import Model.tPlayer;
import Model.tPlayer_data;
import Model.tPlayer_path;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class TransRoom implements Action{

//    private ArrayList<Room> troomArrayList;


    @Override
    public void doAction(Command command, Player player) {

        if(command.getCommandWord().equals("trans")){
            int randomIndex = player.random.nextInt(player.transRoom.size());
            Room transportRoom = player.transRoom.get(randomIndex);
            player.setCurrentRoom(transportRoom);
            player.path++;

            tPlayer_path tpth = new tPlayer_path();
//                    设置player_path姓名和路径
            tpth.setPlayer_name(player.getPname());
            tpth.setRoom_path(player.path);

            player_pathDAO pthD = new player_pathDAO();
            try {
                pthD.add(tpth);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            player.currentStamina -= 5;

            tPlayer_data pl_data = new tPlayer_data();
            pl_data.setPlayer_name(player.getPname());
            pl_data.setPlayer_stamina(player.currentStamina);
            pl_data.setPlayer_weight(player.currentWeight);
            pl_data.setMaxstamina(player.maxStamina);
            pl_data.setMaxweight(player.maxWeight);


            player_dataDAO pdD = new player_dataDAO();
            pdD.update(pl_data);

            System.out.println("you have transferred to " + player.getCurrentRoom().getShortDescription());
            player.moveHistory.add(player.getCurrentRoom());
            System.out.println(player.getCurrentRoom().getExitString());
            player.printProgressBar("Capacity", player.currentWeight, player.maxWeight);
            player.printProgressBar("Stamina", player.currentStamina, player.maxStamina);

        }else{
            System.out.println("I don't know what you mean...");
        }

    }
}
