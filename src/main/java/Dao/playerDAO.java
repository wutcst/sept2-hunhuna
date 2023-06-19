package Dao;

import Model.tPlayer;
import Server.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class playerDAO {
//    新增
    public boolean add(tPlayer player) throws SQLException {
        Connection conn =DBConnection.getConn();

        String checkQuery = "SELECT * FROM players WHERE player_name = ?";
        PreparedStatement check = conn.prepareStatement(checkQuery);
        check.setString(1, player.getPlayer_name());
        ResultSet checkResult = check.executeQuery();
        boolean player_nameExists = checkResult.next();
        checkResult.close();
        check.close();

        if(player_nameExists){
            return false;
        }
        String sql = "insert into players values(null,?,?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, player.getPlayer_name());
            ps.setString(2, player.getPlayer_password());

            boolean resule = ps.executeUpdate() > 0;

            DBConnection.close(null, ps, conn);
//            关闭数据库
            return resule;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  false;
    }


    public boolean update(tPlayer player){
        Connection conn =DBConnection.getConn();
        String sql = "update players set player_name=?,player_password=? where player_id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, player.getPlayer_name());
            ps.setString(2, player.getPlayer_password());
            ps.setInt(3, player.getPlayer_id());

            boolean resule = ps.executeUpdate() > 0;

            DBConnection.close(null, ps, conn);
//            关闭数据库
            return resule;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  false;
    }

    public boolean delete(int player_id){
        Connection conn =DBConnection.getConn();
        String sql = "delete from players where player_id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, player_id);

            boolean resule = ps.executeUpdate() > 0;

            DBConnection.close(null, ps, conn);
//            关闭数据库
            return resule;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  false;
    }

    //查询单条
    public tPlayer query(String player_name) {
        Connection conn = DBConnection.getConn();
        //2、预编译sql执行
        String sql = "select * from players where player_name=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, player_name);

            //执行操作更改
            ResultSet rs = ps.executeQuery();
            //创建一个学生对象返回
            tPlayer p = new tPlayer();
            while(rs.next()) {
                p.setPlayer_id(rs.getInt(1));
                p.setPlayer_name(rs.getString(2));
                p.setPlayer_password(rs.getString(3));
            }
            //关闭操作数据库
            DBConnection.close(rs, ps, conn);
            return p;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public List<tPlayer> getListAll(){
        Connection conn = DBConnection.getConn();
        //2、预编译sql执行
        String sql = "select * from players";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            //执行操作更改
            ResultSet rs = ps.executeQuery();
            //创建一个学生对象返回
           List<tPlayer> list = new ArrayList<tPlayer>();
            while(rs.next()) {
                tPlayer p = new tPlayer();
                p.setPlayer_id(rs.getInt(1));
                p.setPlayer_name(rs.getString(2));
//                p.setPlayer_password(rs.getString(3));
                list.add(p);
            }
            //关闭操作数据库
            DBConnection.close(rs, ps, conn);
            return list;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public boolean login(String username, String password) throws SQLException {
        Connection conn = DBConnection.getConn();
        String query = "SELECT * FROM players WHERE player_name = ? AND player_password = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        boolean success = resultSet.next();
        resultSet.close();
        statement.close();
        return success;
    }

//    模糊查询所有
//public Vector<Vector<String>> getAll(String keyword) {
//    Connection conn = DBConnection.getConn();
//    //2、预编译sql执行
//    String sql = "select * from players";
//    if(keyword != null) {
//        sql += " where name like '%"+keyword+"%'";
//    }
//    try {
//        PreparedStatement ps = conn.prepareStatement(sql);
//        //执行查询结果集
//        ResultSet rs = ps.executeQuery();
//        //创建一个对象返回
//        Vector<Vector<String>> list = new Vector<Vector<String>>();
//        while(rs.next()) {
//            Vector<String> s = new Vector<String>();
//            s.add(rs.getString(1));
//            s.add(rs.getString(2));
//            s.add(rs.getString(3));
//            s.add(rs.getString(4));
//            s.add(rs.getString(5));
//            s.add(rs.getString(6));
//            s.add(rs.getString(7));
//            //把当前对象存储到list集合中
//            list.add(s);
//        }
//        //关闭操作数据库
//        DBConnection.close(rs, ps, conn);
//        return list;
//    } catch (SQLException e) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//    }
//    return null;
//}

//    public static void main(String[] args){
////        tPlayer player1 = new tPlayer();
////
////        player1.setPlayer_name("qaz");
////        player1.setPlayer_password("123");
//
//        playerDAO p = new playerDAO();
////增加
////        if(p.add(player1)){
////            System.out.println("new create succeed");
////        }else{
////            System.out.println("new create failed");
////        }
//
//        tPlayer player2 = new tPlayer();
////修改
////        player2.setPlayer_name("qaz");
////        player2.setPlayer_password("111");
////        player2.setPlayer_id(2);
////        if(p.update(player2)){
////            System.out.println("update succeed");
////        }else{
////            System.out.println("update failed");
////        }
////删除
////        if(p.delete(2)){
////            System.out.println("delete succeed");
////        }else{
////            System.out.println("delete failed");
////        }
//        player2 = p.query("zzh");
//        System.out.println(player2.getPlayer_id() + " "+ player2.getPlayer_name() + " " + player2.getPlayer_password());
////        List<tPlayer> list = p.getListAll();
////        for(tPlayer pl : list){
////            System.out.println(pl.getPlayer_id() + "->" + pl.getPlayer_name());
////
////        }
//    }
}
