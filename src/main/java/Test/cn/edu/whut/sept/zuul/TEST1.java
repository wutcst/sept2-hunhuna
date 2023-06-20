package Test.cn.edu.whut.sept.zuul;

import org.junit.Test;

import java.sql.*;

public class TEST1 {

     String URL = "jdbc:mysql://localhost:3306/zuul?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=utf8";
     String USER = "root";
     String PASSWORD = "123456";

     Connection conn = null;
    @Test
    public  void test1() throws ClassNotFoundException, SQLException {


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
