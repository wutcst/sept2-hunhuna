package Dao;

import Model.tPlayer;
import Model.tPlayer_items;
import Model.tPlayer_path;
import Server.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class player_itemsDAO {
    public boolean add(tPlayer_items player) throws SQLException {
        Connection conn = DBConnection.getConn();

        String checkQuery = "SELECT * FROM player_items WHERE player_name = ?";
        PreparedStatement check = conn.prepareStatement(checkQuery);
        check.setString(1, player.getPlayer_name());
        ResultSet checkResult = check.executeQuery();
        boolean player_nameExists = checkResult.next();
        checkResult.close();
        check.close();

        if(player_nameExists){

        }

        String sql = "insert into player_data values(?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, player.getPlayer_name());
            ps.setInt(2, player.getToken_items());

            boolean resule = ps.executeUpdate() > 0;

            DBConnection.close(null, ps, conn);
//            关闭数据库
            return resule;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  false;
    }


    public boolean update(tPlayer_items player){
        Connection conn =DBConnection.getConn();
        String sql = "update player_items set token_items=? where player_name=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, player.getToken_items());
            ps.setString(2, player.getPlayer_name());

            boolean resule = ps.executeUpdate() > 0;

            DBConnection.close(null, ps, conn);
//            关闭数据库
            return resule;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  false;
    }

    public boolean delete(String player_name){
        Connection conn =DBConnection.getConn();
        String sql = "delete from player_items where player_name=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, player_name);

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
    public tPlayer_path query(String player_name) {
        Connection conn = DBConnection.getConn();
        //2、预编译sql执行
        String sql = "select * from player_items where player_name=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, player_name);

            //执行操作更改
            ResultSet rs = ps.executeQuery();
            //创建一个学生对象返回
            tPlayer_path p = new tPlayer_path();
            while(rs.next()) {
                p.setPlayer_name(rs.getString(1));
                p.setRoom_path(rs.getInt(2));
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

    public List<tPlayer_path> getListAll(){
        Connection conn = DBConnection.getConn();
        //2、预编译sql执行
        String sql = "select * from player_items";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            //执行操作更改
            ResultSet rs = ps.executeQuery();
            //创建一个学生对象返回
            List<tPlayer_path> list = new ArrayList<tPlayer_path>();
            while(rs.next()) {
                tPlayer_path p = new tPlayer_path();

                p.setPlayer_name(rs.getString(1));
                p.setRoom_path(rs.getInt(2));

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
}
