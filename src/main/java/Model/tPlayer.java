package Model;

import cn.edu.whut.sept.zuul.Game;
import cn.edu.whut.sept.zuul.Item;
import cn.edu.whut.sept.zuul.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;

public class tPlayer {


    private int player_id;
    private String player_name;
    private String player_password;


    public int getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public String getPlayer_password() {
        return player_password;
    }

    public void setPlayer_password(String player_password) {
        this.player_password = player_password;
    }

}
