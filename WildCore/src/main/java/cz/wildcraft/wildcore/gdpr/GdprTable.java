package cz.wildcraft.wildcore.gdpr;

import cz.wildcraft.wildcore.WildCore;

import java.sql.*;

public class GdprTable {
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
        String sql = "CREATE TABLE IF NOT EXISTS gdpr(username varchar(255) primary key, accepted boolean, accept_date varchar(255))";
        statement.execute(sql);
        statement.close();
    }

    public void createPlayer(GdprModel gdprModel) {

        try {
            PreparedStatement statement = getConnection()
                    .prepareStatement("INSERT INTO gdpr (username,accepted, accept_date) VALUES  (?,?,?)");
            statement.setString(1,gdprModel.getUsername());
            statement.setBoolean(2, gdprModel.isAccepted());
            statement.setString(3, gdprModel.getAccept_date());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public GdprModel findPlayerByName(String name) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM gdpr WHERE username = ?");
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();
        GdprModel gdprModel;
        if(resultSet.next()){
            gdprModel = new GdprModel(resultSet.getString("username"),resultSet.getBoolean("accepted"), resultSet.getString("accept_date"));
            statement.close();
            return gdprModel;
        }
        statement.close();
        return null;
    }

    public void updateGdpr(GdprModel gdprModel) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("UPDATE gdpr SET accepted = ?  WHERE username = ?");
        statement.setBoolean(1,gdprModel.isAccepted());
        statement.setString(2,gdprModel.getUsername());
        statement.executeUpdate();
        statement.close();
    }
}
