package cn.edu.whut.sept.zuul;

import Dao.player_dataDAO;
import Model.tPlayer;
import Model.tPlayer_data;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class RestCommand implements Action{
    @Override
    public void doAction(Command command, Player player) {
//        AtomicBoolean isResting = new AtomicBoolean(false);
        System.out.println("starting rest....");
        player.isResting.set(true);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                System.out.println("end of rest");
                player.currentStamina += 3;

                tPlayer_data pl_data = new tPlayer_data();
                pl_data.setPlayer_name(player.getPname());
                pl_data.setPlayer_stamina(player.currentStamina);
                pl_data.setPlayer_weight(player.currentWeight);
                pl_data.setMaxstamina(player.maxStamina);
                pl_data.setMaxweight(player.maxWeight);

                player_dataDAO pdD = new player_dataDAO();
                pdD.update(pl_data);

                player.isResting.set(false);
                timer.cancel();
            }
        }, 10000);


    }
}
