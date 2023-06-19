package Dao;


import Model.tPlayer;
import Model.tPlayer_data;
import Server.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class player_dataDAO {

//    public void loadPlayer(tPlayer player) {
//        try {
//            Connection conn = DBConnection.getConn();
//            // 查询玩家信息
//            String selectQuery = "SELECT * FROM player_data WHERE player_name = ?";
//            PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
//            selectStatement.setString(1,player.getPlayer_name());
//            ResultSet resultSet = selectStatement.executeQuery();
//
//            if (resultSet.next()) {
//                // 从数据库中读取玩家信息
//                int player_weight = resultSet.getInt("player_weight");
//                this.currentRoom = new Room(currentRoomName);
//                this.currentWeight = resultSet.getDouble("current_weight");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }





    public boolean add(tPlayer_data player) throws SQLException {
        Connection conn = DBConnection.getConn();

        String checkQuery = "SELECT * FROM player_data WHERE player_name = ?";
        PreparedStatement check = conn.prepareStatement(checkQuery);
        check.setString(1, player.getPlayer_name());
        ResultSet checkResult = check.executeQuery();
        boolean player_nameExists = checkResult.next();
        checkResult.close();
        check.close();

        if(player_nameExists){

            String sql = "update player_data set player_weight=?,player_stamina=?,maxweight=?,maxstamina=? where player_name=?";

            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, player.getPlayer_weight());
                ps.setInt(2, player.getPlayer_stamina());
                ps.setInt(3, player.getMaxweight());
                ps.setInt(4, player.getMaxstamina());
                ps.setString(5, player.getPlayer_name());

                boolean resule = ps.executeUpdate() > 0;

                DBConnection.close(null, ps, conn);
//            关闭数据库
                return resule;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        String sql = "insert into player_data values(?,?,?,?,?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, player.getPlayer_name());
            ps.setInt(2, player.getPlayer_weight());
            ps.setInt(3, player.getPlayer_stamina());
            ps.setInt(4, player.getMaxweight());
            ps.setInt(5, player.getMaxstamina());

//            boolean resule = ps.executeUpdate() > 0;

            DBConnection.close(null, ps, conn);
//            关闭数据库
//            return resule;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  false;
    }


    public boolean update(tPlayer_data player){
        Connection conn =DBConnection.getConn();
        String sql = "update player_data set player_weight=?,player_stamina=?,maxweight=?,maxstamina=? where player_name=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, player.getPlayer_weight());
            ps.setInt(2, player.getPlayer_stamina());
            ps.setInt(3, player.getMaxweight());
            ps.setInt(4, player.getMaxstamina());
            ps.setString(5, player.getPlayer_name());

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
    public tPlayer_data query(String player_name) {
        Connection conn = DBConnection.getConn();
        //2、预编译sql执行
        String sql = "select * from player_data where player_name=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, player_name);

            //执行操作更改
            ResultSet rs = ps.executeQuery();
            //创建一个学生对象返回
            tPlayer_data p = new tPlayer_data();
            while(rs.next()) {
                p.setPlayer_name(rs.getString(1));
                p.setPlayer_weight(rs.getInt(2));
                p.setPlayer_stamina(rs.getInt(3));
                p.setMaxweight(rs.getInt(4));
                p.setPlayer_stamina(rs.getInt(5));
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

//    public List<tPlayer> getListAll(){
//        Connection conn = DBConnection.getConn();
//        //2、预编译sql执行
//        String sql = "select * from players";
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//
//            //执行操作更改
//            ResultSet rs = ps.executeQuery();
//            //创建一个学生对象返回
//            List<tPlayer> list = new ArrayList<tPlayer>();
//            while(rs.next()) {
//                tPlayer p = new tPlayer();
//                p.setPlayer_id(rs.getInt(1));
//                p.setPlayer_name(rs.getString(2));
////                p.setPlayer_password(rs.getString(3));
//                list.add(p);
//            }
//            //关闭操作数据库
//            DBConnection.close(rs, ps, conn);
//            return list;
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return null;
//    }


}
