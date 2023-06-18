package cn.edu.whut.sept.zuul;

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
            player.currentStamina -= 5;
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
