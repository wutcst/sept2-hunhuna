package Server;

import java.sql.*;
import java.sql.Connection;

public class JDBC {

     static String URL = "jdbc:mysql://localhost:3306/zuul?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=utf8";
     static String USER = "root";
     static String PASSWORD = "123456";

     static Connection conn = null;

     public static void main(String[] args) throws ClassNotFoundException, SQLException{


          Class.forName("com.mysql.cj.jdbc.Driver");

          conn = DriverManager.getConnection(URL, USER, PASSWORD);

          Statement stmt = conn.createStatement();

          ResultSet rest = stmt.executeQuery("select * from players");

          while(rest.next()){
               int player_id = rest.getInt("player_id");
               String player_name = rest.getString("player_name");
               String player_password = rest.getString("player_password");

               System.out.println(player_id + "--" + player_name + "--" + player_password);
          }
          rest.close();
          stmt.close();
          conn.close();
     }

}
