package cn.edu.whut.sept.zuul;

import Dao.player_dataDAO;
import Dao.player_pathDAO;
import Model.tPlayer;
import Model.tPlayer_data;
import Model.tPlayer_path;

import java.sql.SQLException;

public class GoCommand implements Action
{
    public GoCommand(){

    }


    @Override
    public void doAction(Command command, Player player) {
        if(player.isResting.get()){
            System.out.println("you are resting,please wait 5s... ");
        }else{

            if(!command.hasSecondWord()) {
                System.out.println("Go where?");
                return;
            }

            String direction = command.getSecondWord();

            try{

                Room currentRoom = player.getCurrentRoom();



                Room nextRoom = player.getCurrentRoom().getExit(direction);

                if (nextRoom == null) {
                    System.out.println("There is no door!");
                }
                else {
                    player.moveHistory.add(nextRoom);
                    player.path++;

                    tPlayer_path tpth = new tPlayer_path();
//                    设置player_path姓名和路径
                    tpth.setPlayer_name(player.getPname());
                    tpth.setRoom_path(player.path);

                    player_pathDAO pthD = new player_pathDAO();
                    if(pthD.add(tpth)){
                        System.out.println("succeed");
                    }else{
                        System.out.println("no");
                    }


                    player.setLastRoom(currentRoom);

                    player.setCurrentRoom(nextRoom);

                    player.currentStamina -= 5;

                    tPlayer_data pl_data = new tPlayer_data();
                    pl_data.setPlayer_name(player.getPname());
                    pl_data.setPlayer_stamina(player.currentStamina);
                    pl_data.setPlayer_weight(player.currentWeight);
                    pl_data.setMaxstamina(player.maxStamina);
                    pl_data.setMaxweight(player.maxWeight);


                    player_dataDAO pdD = new player_dataDAO();
                    pdD.update(pl_data);

                    player.printProgressBar("Capacity", player.currentWeight, player.maxWeight);
                    player.printProgressBar("Stamina", player.currentStamina, player.maxStamina);
                    System.out.println(nextRoom.getLongDescription());
//                System.out.println(player.moveHistory);
                }

            } catch (NullPointerException e){
                System.out.println("there is no exit " + direction);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }





    }
}
