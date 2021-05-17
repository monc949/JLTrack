package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

import Model.Match;
import Model.Player;

/**
 * Controller for the Match Table
 *
 *
 */
public class MatchController implements Globals {

    // Constructor
    public MatchController() {

    }

    /**
     * Creates a new record in the Match Table using a Match Object
     * 
     * @param newMatch
     */
    public void createNewMatch(Match newMatch) {
        Connection connection = null;
        PreparedStatement pstat = null;
        String player1 = newMatch.getPlayer1().getName();
        String player2 = newMatch.getPlayer2().getName();
        String winner = newMatch.getWinner();
        String status;
        if (newMatch.getCompleted()) {
            status = "Played";
        }
        else {
            status = "Not Played";
        }


        try {

            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            // create Prepared Statement for inserting into table
            pstat = connection.prepareStatement(
                    "INSERT INTO Match (Player1, Player2, Winner, Status) VALUES (?,?,?,?)");
                    pstat.setString(1, player1);
                    pstat.setString(2, player2);
                    pstat.setString(3, winner);
                    pstat.setString(4, status);

            pstat.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                pstat.close();
                connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        }
    }

    /**
     * Retrieves the Match table in a the form of a table model
     * 
     * @return DefaultTableModel
     */
    public DefaultTableModel retrieveMatchTable() {
        DefaultTableModel model = new DefaultTableModel();
        Connection connection = null;

        model.addColumn("Player 1");
        model.addColumn("Player 2");
        model.addColumn("Winner");
        model.addColumn("Status");

        // ---retrieve from database---//
        // -and populate table---//
        try {
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM MatchDetails");
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                model.addRow(new Object[] { resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5) });
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return model;
    }



    /**
     * Updates a Match in the Match table using the Users Entries
     * 
     * @param matchID
     * @param winner
     * @param status
     */
    public void updateMatch(int matchID, String winner, String status) {

        Connection connection = null;
        PreparedStatement pstat = null;

        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            // create Statement for updating table
            pstat = connection.prepareStatement("UPDATE MatchDetails SET Winner = ?, Status = ?, WHERE MatchID = ?");
                pstat.setString(1, winner);
                pstat.setString(2, status);
                pstat.setInt(3, matchID);

            // Update data in database
            pstat.executeUpdate();
             
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
                } finally {
            try {
                pstat.close();
                connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * Deletes a record from the Match Table using a Match ID
     * 
     * @param matchID
     */
    public void deleteMatch(int matchID) {

        Connection connection = null;
        PreparedStatement pstat = null;
        try {

            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            // create Statement for deleting from table
            pstat = connection.prepareStatement("Delete FROM MatchDetails WHERE MatchID = ?");
            pstat.setInt(1, matchID);

            // Delete data in database
            pstat.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                pstat.close();
                connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

/** Retrieves the Match Table in the form of a List Model
 * @return DefaultListModel<Product>
 */
public DefaultListModel<Match> retrieveMatchList() {


    DefaultListModel<Match> model = new DefaultListModel<Match>();

    Connection connection = null;
    PreparedStatement pstat = null;
    ResultSet resultSet = null;
    PlayerController pc = new PlayerController();
    Player player1 = null;
    Player player2 = null;
    String winner = null;
    Boolean status = null;
    try{
    
        // establish connection to database
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        
        // create Statement for querying table
        pstat = connection.prepareStatement("SELECT * FROM Match");
        
        // query database
        resultSet = pstat.executeQuery("SELECT * FROM Match");
        
        // process query results
        ArrayList<Player> players = pc.retrievePlayerList();
        

        while(resultSet.next() ){

                    for (Player player : players) {
                        if (player.getName() == resultSet.getString("Player1")){
                            player1 = player;
                        }
                        if (player.getName() == resultSet.getString("Player2")){
                            player2 = player;
                        }
                        if (player.getName() == resultSet.getString("Player2")){
                            winner = player.getName();
                        }
                    }

                    if (resultSet.getString("Status") == "NOT PLAYED") {
                        status = false;
                    }
                    
                    Match element = new Match(resultSet.getInt("MatchID"),
                    player1,
                    player2,
                    winner,
                    status);

                    model.addElement(element);

            }
    }
            catch(SQLException sqlException ) {
                sqlException.printStackTrace();
        }
            finally{
                try{
                    resultSet.close();
                    pstat.close();
                    connection.close();
                }
                catch ( Exception exception ){
                    exception.printStackTrace();
                }
        }
            return model;

}
}
