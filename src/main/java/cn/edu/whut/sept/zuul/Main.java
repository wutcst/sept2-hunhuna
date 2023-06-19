package cn.edu.whut.sept.zuul;

import Dao.player_dataDAO;
import Dao.player_itemsDAO;
import Dao.player_pathDAO;
import Model.tPlayer;
import Dao.playerDAO;
import Model.tPlayer_data;
import Model.tPlayer_items;
import Model.tPlayer_path;
import Server.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.Phaser;

public class Main {

    public static void main(String[] args) throws SQLException {

//        Parser parser1 = new Parser();
        Game game1 = new Game();




        System.out.println("Welcome to the World of Zuul!");
        System.out.println("Do you have player account? (y/n)");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        if(s.equals("y")){
            if(game1.Log())
                game1.play();
        } else if (s.equals("n")) {
            if(game1.Register()){
                game1.play();
            }


        }else{
            System.out.println("please log on!");
        }

//        Command command1 = parser1.getCommand();
//        game1.processCommand(command1);
//        game1.play();








//        tPlayer_data tpd = new tPlayer_data();
//        player_dataDAO pdD = new player_dataDAO();
//
//        tPlayer_items tpi = new tPlayer_items();
//        player_itemsDAO piD = new player_itemsDAO();
//
//        tPlayer_path tpp = new tPlayer_path();
//        player_pathDAO ppD = new player_pathDAO();





//        game.printWelcome();



//        System.out.println("succeed in connection");










    }
}
