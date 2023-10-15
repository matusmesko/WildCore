package cz.wildcraft.wildcore.warps.database;

import cz.wildcraft.wildcore.WildCore;
import cz.wildcraft.wildcore.warps.model.ServerWarpModel;
import org.bukkit.Server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServerWarpTable {

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

    public void initializeServerWarpsTable() throws SQLException {
        Statement statement = getConnection().createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS server_warps(warp_name varchar(255) primary key, description varchar(255),icon varchar(255), world varchar(255),x double , y double, z double, time_created DATE, yaw float, pitch float )";
        statement.execute(sql);
        statement.close();
    }

    public void createWarp(ServerWarpModel serverWarpModel) {
        try {
            PreparedStatement statement = getConnection()
                    .prepareStatement("INSERT INTO server_warps (warp_name,description,icon,world,x,y,z,time_created,yaw,pitch) VALUES  (?,?,?,?,?,?,?,?,?,?)");
            statement.setString(1, serverWarpModel.getWarp_name());
            statement.setString(2, serverWarpModel.getDescription());
            statement.setString(3, serverWarpModel.getIcon());
            statement.setString(4, serverWarpModel.getWorld());
            statement.setDouble(5, serverWarpModel.getX());
            statement.setDouble(6, serverWarpModel.getY());
            statement.setDouble(7, serverWarpModel.getZ());
            statement.setDate(8, new Date(serverWarpModel.getTime_created().getTime()));
            statement.setFloat(9, serverWarpModel.getYaw());
            statement.setFloat(10, serverWarpModel.getPitch());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ServerWarpModel findServerWarpByName(String name) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM server_warps WHERE warp_name = ?");
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();
        ServerWarpModel warp;
        if(resultSet.next()){
            warp = new ServerWarpModel(resultSet.getString("warp_name"),resultSet.getString("description"), resultSet.getString("icon"), resultSet.getString("world"),resultSet.getLong("x"),resultSet.getLong("y"),resultSet.getLong("z"),resultSet.getDate("time_created"), resultSet.getFloat("yaw"), resultSet.getFloat("pitch"));
            statement.close();
            return warp;
        }
        statement.close();
        return null;
    }

    public void updateWarp(ServerWarpModel serverWarpModel) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("UPDATE server_warps SET description = ?,time_created = ?, icon = ?, world = ?, x = ?, y = ?, z = ?, yaw = ?, pitch = ?  WHERE warp_name = ?");
        statement.setString(1,serverWarpModel.getDescription());
        statement.setDate(2,new Date(serverWarpModel.getTime_created().getTime()));
        statement.setString(3, serverWarpModel.getIcon());
        statement.setString(4, serverWarpModel.getWorld());
        statement.setDouble(5, serverWarpModel.getX());
        statement.setDouble(6, serverWarpModel.getY());
        statement.setDouble(7, serverWarpModel.getZ());
        statement.setDouble(8, serverWarpModel.getYaw());
        statement.setDouble(9, serverWarpModel.getPitch());
        statement.setString(10, serverWarpModel.getWarp_name());
        statement.executeUpdate();
        statement.close();
    }

    public List<ServerWarpModel> getAllServerWarps() throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM server_warps");
        ResultSet resultSet = statement.executeQuery();
        List<ServerWarpModel> warps = new ArrayList<>();;
        while (resultSet.next()) {
            ServerWarpModel warp = new ServerWarpModel(resultSet.getString("warp_name"),resultSet.getString("description"), resultSet.getString("icon"), resultSet.getString("world"),resultSet.getLong("x"),resultSet.getLong("y"),resultSet.getLong("z"),resultSet.getDate("time_created"), resultSet.getFloat("yaw"), resultSet.getFloat("pitch"));
            warps.add(warp);
        }
        return warps;
    }
}
