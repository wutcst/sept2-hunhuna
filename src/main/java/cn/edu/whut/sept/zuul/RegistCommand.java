package cn.edu.whut.sept.zuul;

import Dao.playerDAO;
import Model.tPlayer;

import java.sql.SQLException;
import java.util.Scanner;

public class RegistCommand implements Action{
    @Override
    public void doAction(Command command,Player player) {
        tPlayer pl = new tPlayer();
        playerDAO pd = new playerDAO();

        Scanner scanner = new Scanner(System.in);


        System.out.println("you are redistering a account....");
        System.out.println("please input YOUR PLAYERNAME:");
        String inputline1 = scanner.nextLine();
//            tPlayer pl = new tPlayer();
//            playerDAO pd = new playerDAO();
        pl.setPlayer_name(inputline1);

        System.out.println("please input YOUR PASSWORD:");
        String inputline2 = scanner.nextLine();
        pl.setPlayer_password(inputline2);


        boolean registSuccess = false;
        try {
            registSuccess = pd.add(pl);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(registSuccess){
            System.out.println("successfully registered.");
            System.out.println("Welcome into Word-Of-Zuul " + pl.getPlayer_name());
//            Game game = new Game();

        }else {
            System.out.println("the player_name already exists,please re-enter.");
        }

    }

}
