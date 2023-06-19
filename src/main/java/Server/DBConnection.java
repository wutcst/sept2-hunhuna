package Server;

import java.sql.*;

public class DBConnection {
      static String URL = "jdbc:mysql://localhost:3306/zuul?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=utf8";
      static String USER = "root";
      static String PASSWORD = "123456";
//加载JDBC
     static {
          try {
               Class.forName("com.mysql.cj.jdbc.Driver");
          } catch (ClassNotFoundException e) {
               e.printStackTrace();
          }
     }

//连接MySQL的连接对象
     public static Connection getConn(){
          try {
              return DriverManager.getConnection(URL, USER, PASSWORD);
          } catch (SQLException ex) {
               ex.printStackTrace();
          }
          return null;
     }

//     关闭连接
     public static void close(ResultSet rs, PreparedStatement ps, Connection conn){
          try {
               if(rs != null){
                    rs.close();
               }
               if(ps != null){
                    ps.close();
               }
               if(conn != null){
                    conn.close();
               }
          } catch (SQLException ex) {
               ex.printStackTrace();
          }
     }

//     public static void main(String[] args){
//          System.out.println(getConn());
//     }
}
