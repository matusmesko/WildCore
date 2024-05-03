package cz.wildcraft.wildcore.vote;

import cz.wildcraft.wildcore.WildCore;
import cz.wildcraft.wildcore.warps.model.PlayerWarpModel;

import java.sql.*;

public class VoteTable {
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
        String sql = "CREATE TABLE IF NOT EXISTS wild_votes(username varchar(255), service varchar(255), vote_date varchar(255))";
        statement.execute(sql);
        statement.close();
    }

    public void createVote(VoteModel voteModel) {

        try {
            PreparedStatement statement = getConnection()
                    .prepareStatement("INSERT INTO wild_votes (username,service,vote_date) VALUES  (?,?,?)");
            statement.setString(1, voteModel.getUsername());
            statement.setString(2, voteModel.getService());
            statement.setString(3, voteModel.getVote_date());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getPlayerVotes(String playername) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("SELECT COUNT(*) AS sucet FROM wild_votes WHERE username = ?;");
        statement.setString(1, playername);
        ResultSet resultSet = statement.executeQuery();
        int count;
        if(resultSet.next()){
            count = resultSet.getInt("sucet");
            statement.close();
            return count;
        }
        statement.close();
        return 0;
    }
}
