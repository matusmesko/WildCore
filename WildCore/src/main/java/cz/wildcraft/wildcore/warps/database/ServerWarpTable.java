package cz.wildcraft.wildcore.warps.database;

import cz.wildcraft.wildcore.WildCore;
import cz.wildcraft.wildcore.warps.model.ServerWarpModel;
import org.bukkit.Server;

import java.sql.*;

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
        String sql = "CREATE TABLE IF NOT EXISTS server_warps (warp_name varchar(255) primary key, description varchar(255),icon varchar(255), world varchar(255),x long , y long, z long, time_created DATE)";
        statement.execute(sql);
        statement.close();
    }

    public void createWarp(ServerWarpModel serverWarpModel) {
        try {
            PreparedStatement statement = getConnection()
                    .prepareStatement("INSERT INTO server_warps (warp_name,description,icon,world,x,y,z,time_created) VALUES  (?,?,?,?,?,?,?,?)");
            statement.setString(1, serverWarpModel.getWarp_name());
            statement.setString(2, serverWarpModel.getDescription());
            statement.setString(3, serverWarpModel.getIcon());
            statement.setString(4, serverWarpModel.getWorld());
            statement.setLong(5, serverWarpModel.getX());
            statement.setLong(6, serverWarpModel.getY());
            statement.setLong(7, serverWarpModel.getZ());
            statement.setDate(8, new Date(serverWarpModel.getTime_created().getTime()));
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
            warp = new ServerWarpModel(resultSet.getString("warp_name"),resultSet.getString("description"), resultSet.getString("icon"),resultSet.getString("world"),resultSet.getLong("x"),resultSet.getLong("y"),resultSet.getLong("z"),resultSet.getDate("time_created"));
            statement.close();
            return warp;
        }
        statement.close();
        return null;
    }

    public void updateWarp(ServerWarpModel serverWarpModel) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("UPDATE server_warps SET description = ?,time_created = ?, icon = ?, world = ?, x = ?, y = ?, z = ?  WHERE warp_name = ?");
        statement.setString(1,serverWarpModel.getDescription());
        statement.setDate(2,new Date(serverWarpModel.getTime_created().getTime()));
        statement.setString(3, serverWarpModel.getIcon());
        statement.setString(4, serverWarpModel.getWorld());
        statement.setLong(5, serverWarpModel.getX());
        statement.setLong(6, serverWarpModel.getY());
        statement.setLong(7, serverWarpModel.getZ());
        statement.setString(7, serverWarpModel.getWarp_name());
        statement.executeUpdate();
        statement.close();
    }
}
