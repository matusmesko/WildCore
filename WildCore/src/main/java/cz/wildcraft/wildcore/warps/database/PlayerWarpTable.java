package cz.wildcraft.wildcore.warps.database;

import cz.wildcraft.wildcore.WildCore;
import cz.wildcraft.wildcore.warps.model.PlayerWarpModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerWarpTable {
    private Connection connection;

    public Connection getConnection() {
        String ip = WildCore.getPlugin().getConfig().getString("ip-adress");
        String user = WildCore.getPlugin().getConfig().getString("user");
        String password = WildCore.getPlugin().getConfig().getString("password");
        String database = WildCore.getPlugin().getConfig().getString("database");

        String url = "jdbc:mysql://" + ip +"/" + database;

        try {
            Connection connection = DriverManager.getConnection(url, user,password);
            this.connection = connection;
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return connection;
    }

    public void initializeTable() throws SQLException {
        Statement statement = getConnection().createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS player_warps(warp_name varchar(255) primary key, description varchar(255),owner varchar(255), visited int ,icon varchar(255), world varchar(255),x double , y double, z double, time_created varchar(255), yaw float, pitch float )";
        statement.execute(sql);
        statement.close();
    }


    public void createWarp(PlayerWarpModel playerWarpModel) {

        try {
            PreparedStatement statement = getConnection()
                    .prepareStatement("INSERT INTO player_warps (warp_name,description,owner,visited,icon,world,x,y,z,time_created,yaw,pitch) VALUES  (?,?,?,?,?,?,?,?,?,?,?,?)");
            statement.setString(1, playerWarpModel.getWarp_name());
            statement.setString(2, playerWarpModel.getDescription());
            statement.setString(3, playerWarpModel.getOwner());
            statement.setInt(4, playerWarpModel.getVisited());
            statement.setString(5, playerWarpModel.getIcon());
            statement.setString(6, playerWarpModel.getWorld());
            statement.setDouble(7, playerWarpModel.getX());
            statement.setDouble(8, playerWarpModel.getY());
            statement.setDouble(9, playerWarpModel.getZ());
            statement.setString(10, playerWarpModel.getTime_created());
            statement.setFloat(11, playerWarpModel.getYaw());
            statement.setFloat(12, playerWarpModel.getPitch());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PlayerWarpModel findPlayerWarpByName(String name) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM player_warps WHERE warp_name = ?");
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();
        PlayerWarpModel warp;
        if(resultSet.next()){
            warp = new PlayerWarpModel(resultSet.getString("warp_name"),resultSet.getString("description"),resultSet.getString("owner"),resultSet.getInt("visited"), resultSet.getString("world"),resultSet.getString("icon"), resultSet.getString("time_created"), resultSet.getDouble("x"),resultSet.getDouble("y"), resultSet.getDouble("z"),resultSet.getFloat("yaw"),resultSet.getFloat("pitch"));
            statement.close();
            return warp;
        }
        statement.close();
        return null;
    }

    public void updateWarp(PlayerWarpModel playerWarpModel) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("UPDATE player_warps SET description = ?,time_created = ?, icon = ?, world = ?, x = ?, y = ?, z = ?, yaw = ?, pitch = ?, owner = ?, visited = ?  WHERE warp_name = ?");
        statement.setString(1,playerWarpModel.getDescription());
        statement.setString(2,playerWarpModel.getTime_created());
        statement.setString(3, playerWarpModel.getIcon());
        statement.setString(4, playerWarpModel.getWorld());
        statement.setDouble(5, playerWarpModel.getX());
        statement.setDouble(6, playerWarpModel.getY());
        statement.setDouble(7, playerWarpModel.getZ());
        statement.setDouble(8, playerWarpModel.getYaw());
        statement.setDouble(9, playerWarpModel.getPitch());
        statement.setString(10, playerWarpModel.getOwner());
        statement.setInt(11, playerWarpModel.getVisited());
        statement.setString(12, playerWarpModel.getWarp_name());
        statement.executeUpdate();
        statement.close();
    }

    public void deleteWarp(PlayerWarpModel playerWarpModel) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("DELETE FROM player_warps WHERE warp_name = ? AND owner = ?");
        statement.setString(1, playerWarpModel.getWarp_name());
        statement.setString(2, playerWarpModel.getOwner());
        statement.executeUpdate();
        statement.close();
    }

    public List<PlayerWarpModel> getAllPlayerWarps() throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM player_warps");
        ResultSet resultSet = statement.executeQuery();
        List<PlayerWarpModel> warps = new ArrayList<>();;
        while (resultSet.next()) {
           PlayerWarpModel warp = new PlayerWarpModel(resultSet.getString("warp_name"),resultSet.getString("description"),resultSet.getString("owner"),resultSet.getInt("visited"), resultSet.getString("world"),resultSet.getString("icon"), resultSet.getString("time_created"), resultSet.getDouble("x"),resultSet.getDouble("y"), resultSet.getDouble("z"),resultSet.getFloat("yaw"),resultSet.getFloat("pitch"));
            warps.add(warp);
        }
        return warps;
    }

    public int getPlayerCountOfWarps(String name) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("SELECT COUNT(*) AS pocet FROM  player_warps WHERE owner = ?");
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();
        int count;
        if(resultSet.next()){
            count = resultSet.getInt("pocet");
            statement.close();
            return count;
        }
        statement.close();
        return 0;
    }
}
