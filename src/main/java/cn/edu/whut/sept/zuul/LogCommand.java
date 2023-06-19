package cn.edu.whut.sept.zuul;

import Dao.playerDAO;
import Model.tPlayer;

import java.sql.SQLException;
import java.util.Scanner;

public class LogCommand implements Action{
    @Override
    public void doAction(Command command, Player player) {
        tPlayer pl = new tPlayer();
        playerDAO pd = new playerDAO();

        Scanner scanner = new Scanner(System.in);

        System.out.println("please input YOUR PLAYERNAME:");
        String inputline1 = scanner.nextLine();
        pl.setPlayer_name(inputline1);

        System.out.println("please input YOUR PASSWORD:");
        String inputline2 = scanner.nextLine();
        pl.setPlayer_password(inputline2);
        boolean loginSuccess = false;
        try {
            loginSuccess = pd.login(pl.getPlayer_name(), pl.getPlayer_password());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(loginSuccess){
            System.out.println("Welcome into Word-Of-Zuul " + pl.getPlayer_name());


        }else{
            System.out.println("this player_name doesn't exists,please register first! ");

        }
    }

}
