package cz.wildcraft.wildcore.gender;

import cz.wildcraft.wildcore.WildCore;
import cz.wildcraft.wildcore.warps.model.PlayerWarpModel;

import java.sql.*;

public class GenderTable {
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
        String sql = "CREATE TABLE IF NOT EXISTS genders(username varchar(255) primary key, gender varchar(255))";
        statement.execute(sql);
        statement.close();
    }

    public void createPlayer(GenderModel genderModel) {

        try {
            PreparedStatement statement = getConnection()
                    .prepareStatement("INSERT INTO genders (username,gender) VALUES  (?,?)");
            statement.setString(1,genderModel.getUsername());
            statement.setString(2, genderModel.getGender());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public GenderModel findPlayerByName(String name) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM genders WHERE username = ?");
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();
        GenderModel gender;
        if(resultSet.next()){
            gender = new GenderModel(resultSet.getString("username"),resultSet.getString("gender"));
            statement.close();
            return gender;
        }
        statement.close();
        return null;
    }

    public void updateGender(GenderModel genderModel) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("UPDATE genders SET gender = ?  WHERE username = ?");
        statement.setString(1,genderModel.getGender());
        statement.setString(2,genderModel.getUsername());
        statement.executeUpdate();
        statement.close();
    }
}
