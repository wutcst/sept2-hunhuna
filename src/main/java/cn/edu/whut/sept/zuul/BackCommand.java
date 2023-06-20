package cn.edu.whut.sept.zuul;

import Dao.player_dataDAO;
import Dao.player_pathDAO;
import Model.tPlayer;
import Model.tPlayer_data;
import Model.tPlayer_path;

import java.sql.SQLException;

public class BackCommand implements Action{

    public BackCommand(){

    }

    /**
     * 重写
     * @param
     * @return false
     */
//    @Override
//    public boolean execute(Game game, Player player) {
////        Room saveRoom = game.getCurrentRoom();
////        game.setCurrentRoom(game.getCurrentRoom());
////        game.setCurrentRoom(saveRoom);
//
//        if(player.moveHistory.size() > 1){
//            player.moveHistory.pop();
//            Room lastRoom = player.moveHistory.peek();
//            game.setCurrentRoom(lastRoom);
//            System.out.println("you are " + game.getCurrentRoom().getLongDescription());
//        }else{
//            System.out.println("you are in the beginning......");
//        }
//
//        return false;
//    }

    @Override
    public void doAction(Command command, Player player) {




        if(player.isResting.get()){
            System.out.println("you are resting,please wait 5s... ");
        }else {
            if(!player.moveHistory.empty()){
                Room saveRoom = player.getCurrentRoom();
                player.moveHistory.pop();
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

                if(!player.moveHistory.empty()){
                    Room currentRoom = player.moveHistory.peek();
                    player.setCurrentRoom(currentRoom);
                    player.currentStamina -= 5;

                    tPlayer_data pl_data = new tPlayer_data();
                    pl_data.setPlayer_name(player.getPname());
                    pl_data.setPlayer_stamina(player.currentStamina);
                    pl_data.setPlayer_weight(player.currentWeight);
                    pl_data.setMaxstamina(player.maxStamina);
                    pl_data.setMaxweight(player.maxWeight);


                    player_dataDAO pdD = new player_dataDAO();
                    pdD.update(pl_data);

//                System.out.println(player.moveHistory);
                    System.out.println(player.getCurrentRoom().getLongDescription());
                }else{
                    System.out.println("you are in the beginning......");
                }
                player.printProgressBar("Capacity", player.currentWeight, player.maxWeight);
                player.printProgressBar("Stamina", player.currentStamina, player.maxStamina);
//            Room currentRoom = player.moveHistory.peek();
//            player.setCurrentRoom(currentRoom);
//            System.out.println(player.moveHistory);
//            System.out.println("you are " + player.getCurrentRoom().getLongDescription());
            }else{
                System.out.println("you are in the beginning......");
            }
        }


//        if(!player.moveHistory.empty()){
//            player.moveHistory.pop();
//            if(!player.moveHistory.empty()){
//                Room currentRoom = player.moveHistory.peek();
//                player.setCurrentRoom(currentRoom);
//                System.out.println(player.moveHistory);
//                System.out.println("you are " + player.getCurrentRoom().getLongDescription());
//            }else{
//                System.out.println("you are in the beginning......");
//            }
//        }else{
//            System.out.println("you are in the beginning......");
//        }
    }
}
