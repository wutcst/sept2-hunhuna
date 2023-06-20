package cn.edu.whut.sept.zuul;

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
                player.isResting.set(false);
                timer.cancel();
            }
        }, 10000);


    }
}
