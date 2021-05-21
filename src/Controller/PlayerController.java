package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import Model.Player;

/**
 * Controller for the Player Table
 *
 *
 */
public class PlayerController implements Globals {

    // Constructor
    public PlayerController() {

    }

    /**
     * Creates a new record in the Player Table using a Player Object
     * 
     * @param newPlayer
     */
    public void createNewPlayer(Player newPlayer) {
        Connection connection = null;
        PreparedStatement pstat = null;
        String studentNo = newPlayer.getStudentNo();
        String name = newPlayer.getName();
        String personalEmail = newPlayer.getPersonalEmail();
        String studentEmail = newPlayer.getStudentEmail();
        String phoneNumber = newPlayer.getPhoneNumber();
        int leaguePoints = newPlayer.getLeaguePoints();
        String team = newPlayer.getTeam();

        try {

            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            // create Prepared Statement for inserting into table
            pstat = connection.prepareStatement(
                    "INSERT INTO Player (StudentNumber, StudentName, PersonalEmail, StudentEmail, PhoneNumber, LeaguePoints, Team) VALUES (?,?,?,?,?,?,?)");
            pstat.setString(1, studentNo);
            pstat.setString(2, name);
            pstat.setString(3, personalEmail);
            pstat.setString(4, studentEmail);
            pstat.setString(5, phoneNumber);
            pstat.setInt(6, leaguePoints);
            pstat.setString(7, team);

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
     * Retrieves the Player table in a the form of a table model
     * 
     * @return DefaultTableModel
     */
    public DefaultTableModel retrievePlayerTable() {
        DefaultTableModel model = new DefaultTableModel();
        Connection connection = null;

        model.addColumn("PlayerID");
        model.addColumn("Student Number");
        model.addColumn("Name");
        model.addColumn("Personal Email");
        model.addColumn("Student Email");
        model.addColumn("PhoneNumber");
        model.addColumn("LeaguePoints");
        model.addColumn("Team");

        // ---retrieve from database---//
        // -and populate table---//
        try {
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Player");
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                model.addRow(new Object[] { resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getString(8) });
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return model;
    }
    /**
     * Retrieves the Player table in a the form of a table model
     * 
     * @return DefaultTableModel
     */
    public DefaultTableModel retrievePlayerTableMain() {
        DefaultTableModel model = new DefaultTableModel();
        Connection connection = null;

        model.addColumn("Student Number");
        model.addColumn("Name");
        model.addColumn("LeaguePoints");
        model.addColumn("Team");

        // ---retrieve from database---//
        // -and populate table---//
        try {
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Player ORDER BY LeaguePoints DESC");
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                model.addRow(new Object[] { resultSet.getString(2), resultSet.getString(3), resultSet.getInt(7), resultSet.getString(8) });
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return model;
    }


    /**
     * Retrieves the Player table in the form of a Combobox model
     * 
     * @return DefaultComboBoxModel<Player>
     */
    public DefaultComboBoxModel<Player> retrievePlayerListModel() {

        DefaultComboBoxModel<Player> model = new DefaultComboBoxModel<Player>();
        Connection connection = null;

        PreparedStatement pstat = null;
        ResultSet resultSet = null;
        try {

            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            // create Statement for querying table
            pstat = connection.prepareStatement("SELECT * From Player");

            // query database
            resultSet = pstat.executeQuery("SELECT * From Player");

            // process query results

            while (resultSet.next()) {
                Player element = new Player(
                        resultSet.getInt("PlayerID"), resultSet.getString("StudentNumber"),
                        resultSet.getString("StudentName"), resultSet.getString("PersonalEmail"),
                        resultSet.getString("PhoneNumber"), resultSet.getInt("LeaguePoints"),
                        resultSet.getString("Team"), resultSet.getString("Notes"));

                model.addElement(element);

            }
        }

        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                resultSet.close();
                pstat.close();
                connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return model;

    }


    /**
     * Updates a Player in the Player table using the Users Entries
     * 
     * @param playerID
     * @param studentNumber
     * @param name
     * @param personalEmail
     * @param studentEmail
     * @param phoneNumber
     * @param leaguePoints
     * @param team
     */
    public void updatePlayer(int playerID, String studentNumber, String name, String personalEmail, String studentEmail, String phoneNumber, int leaguePoints, String team ) {

        Connection connection = null;
        PreparedStatement pstat = null;

        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            // create Statement for updating table
            pstat = connection.prepareStatement("UPDATE Player SET StudentNumber = ?, StudentName = ?, PersonalEmail = ?, StudentEmail = ?, Phonenumber = ?, LeaguePoints = ?, Team = ? WHERE PlayerID = ?");
                pstat.setString(1, studentNumber);
                pstat.setString(2, name);
                pstat.setString(3, personalEmail);
                pstat.setString(4, studentEmail);
                pstat.setString(5, phoneNumber);
                pstat.setInt(6, leaguePoints);
                pstat.setString(7, team);
                pstat.setInt(8, playerID);

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
     * Deletes a record from the Player Table using a Player ID
     * 
     * @param PlayerID
     */
    public void deletePlayer(int PlayerID) {

        Connection connection = null;
        PreparedStatement pstat = null;
        try {

            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            // create Statement for deleting from table
            pstat = connection.prepareStatement("Delete FROM Player WHERE PlayerID = ?");
            pstat.setInt(1, PlayerID);

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

     /**
     * Retrieves the Player table in a the form of a table model
     * 
     * @return playerList
     */
    public ArrayList<Player> retrievePlayerList() {
        ArrayList<Player> playersList = new ArrayList<>();
        Connection connection = null;

        // ---retrieve from database---//
        // -and populate table---//
        try {
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Player");
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                playersList.add(new Player(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getString(8), resultSet.getString(9)));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return playersList;
    }

     /**
     * Retrieves the Player table in a the form of a table model
     * 
     * @return playerList
     */
    public ArrayList<Player> retrievePlayerListFiltered(Player player) {
        ArrayList<Player> playersList = new ArrayList<>();
        Connection connection = null;

        // ---retrieve from database---//
        // -and populate table---//
        try {
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Player WHERE StudentName = ?");
            pstm.setString(1, player.getName());
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                playersList.add(new Player(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getString(8), resultSet.getString(9)));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return playersList;
    }

    /**
     * Retrieves the Player table in a the form of a table model
     *
     * @return playerList
     */
    public Player retrievePlayerObject(String name) {
        Player player = null;
        Connection connection = null;

        // ---retrieve from database---//
        // -and populate table---//
        try {
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Player");
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                player = new Player(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getString(8), resultSet.getString(9));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return player;
    }

}
