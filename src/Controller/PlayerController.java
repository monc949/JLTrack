package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        int rank = newPlayer.getRank();
        int leaguePoints = newPlayer.getLeaguePoints();
        String team = newPlayer.getTeam();

        try {

            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            // create Prepared Statement for inserting into table
            pstat = connection.prepareStatement(
                    "INSERT INTO Players (StudentNumber, Name, PersonalEmail, StudentEmail, PhoneNumber, Rank, LeaguePoints, Team) VALUES (?,?,?,?,?,?,?,?)");
            pstat.setString(1, studentNo);
            pstat.setString(2, name);
            pstat.setString(3, personalEmail);
            pstat.setString(4, studentEmail);
            pstat.setString(5, phoneNumber);
            pstat.setInt(6, rank);
            pstat.setInt(7, leaguePoints);
            pstat.setString(8, team);

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
        model.addColumn("Rank");
        model.addColumn("LeaguePoints");
        model.addColumn("Team");

        // ---retrieve from database---//
        // -and populate table---//
        try {
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Players");
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                model.addRow(new Object[] { resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getInt(8), resultSet.getString(9) });
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return model;
    }
//TODO: got as far as here in the refactoring
    /**
     * Retrieves the Player table in the form of a Combobox model
     * 
     * @return DefaultComboBoxModel<Player>
     */
    public DefaultComboBoxModel<Player> retrievePlayerList() {

        DefaultComboBoxModel<Player> model = new DefaultComboBoxModel<Player>();
        Connection connection = null;

        PreparedStatement pstat = null;
        ResultSet resultSet = null;
        try {

            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            // create Statement for querying table
            pstat = connection.prepareStatement("SELECT * From Players");

            // query database
            resultSet = pstat.executeQuery("SELECT * From Players");

            // process query results

            while (resultSet.next()) {
                Player element = new Player(
                        resultSet.getString("PlayerID"), resultSet.getString("StudentNumber"),
                        resultSet.getString("Name"), resultSet.getString("PersonalEmail"),
                        resultSet.getString("PhoneNumber"), resultSet.getInt("Rank"), resultSet.getInt("LeaguePoints"),
                        resultSet.getString("Team"));

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
     * @param PlayerID
     * @param name
     * @param address
     * @param postcode
     * @param email
     * @param phoneNumber
     */
    public void updatePlayer(int PlayerID, String name, String address, String postcode, String email,
            String phoneNumber) {

        Connection connection = null;
        PreparedStatement pstat = null;

        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            // create Statement for updating table
            pstat = connection.prepareStatement(
                    "UPDATE Players SET Name = ?, Address = ?, Postcode = ?, Email = ?, Phonenumber = ? WHERE PlayerID = ?");
            pstat.setString(1, name);
            pstat.setString(2, address);
            pstat.setString(3, postcode);
            pstat.setString(4, email);
            pstat.setString(5, phoneNumber);
            pstat.setInt(6, PlayerID);

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
            pstat = connection.prepareStatement("Delete FROM Players WHERE PlayerID = ?");
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
}
