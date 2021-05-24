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

    public DefaultTableModel retrievePlayerTableMain() {
        DefaultTableModel model = new DefaultTableModel();
        Connection connection = null;

        model.addColumn("Rank");
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
            int i = 1;
            while (resultSet.next()) {
                model.addRow(new Object[] { i, resultSet.getString(2), resultSet.getString(3), resultSet.getInt(7), resultSet.getString(8) });
                i++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return model;
    }



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


    public int retrievePlayerLeaguePoints(String player) {
        Connection connection = null;
        PreparedStatement pstat = null;
        int points = 0;
        ResultSet resultSet = null;
        

        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            pstat = connection.prepareStatement("SELECT LeaguePoints FROM Player WHERE StudentName = ?");
                pstat.setString(1, player);

                resultSet = pstat.executeQuery();
            
            while (resultSet.next()) {
                points = resultSet.getInt(1);
            }

             
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

        return points;

    }
    public void clearPlayerLeaguePoints() {
        Connection connection = null;
        PreparedStatement pstat = null;
        

        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            pstat = connection.prepareStatement("Update Player SET LeaguePoints = 0");

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

    public int retrievePlayerDrawCount(String player) {
        Connection connection = null;
        PreparedStatement pstat = null;
        int draws = 0;
        ResultSet resultSet = null;

        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            pstat = connection.prepareStatement("SELECT Draws FROM Player WHERE StudentName = ?");
                pstat.setString(1, player);

                resultSet = pstat.executeQuery();
            while (resultSet.next()) {
                draws = resultSet.getInt(1);
            }


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

        return draws;

    }

    public int retrievePlayerWinCount(String player) {
        Connection connection = null;
        PreparedStatement pstat = null;
        int wins = 0;
        ResultSet resultSet = null;

        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            pstat = connection.prepareStatement("SELECT Wins FROM Player WHERE StudentName = ?");
                pstat.setString(1, player);

                resultSet = pstat.executeQuery();
                while (resultSet.next()) {
                    wins = resultSet.getInt(1);
                }

             
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

        return wins;

    }

    public int retrievePlayerLossCount(String player) {
        Connection connection = null;
        PreparedStatement pstat = null;
        ResultSet resultSet = null;
        int losses = 0;

        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            pstat = connection.prepareStatement("SELECT Losses FROM Player WHERE StudentName = ?");
                pstat.setString(1, player);
                
                resultSet = pstat.executeQuery();

            while (resultSet.next()) {
                losses = resultSet.getInt(1);
            }

             
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

        return losses;

    }

    public void updateLeaguePointsWinner(String player) {

        Connection connection = null;
        PreparedStatement pstat = null;
        int currentPoints = retrievePlayerLeaguePoints(player);
        int updatedPoints = currentPoints + 2;

        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            // create Statement for updating table
            pstat = connection.prepareStatement("UPDATE Player SET LeaguePoints = ? WHERE StudentName = ?");
                pstat.setInt(1, updatedPoints);
                pstat.setString(2, player);

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


    public void updateLeaguePointsDraw(String player1, String player2) {

        Connection connection = null;
        PreparedStatement pstat1 = null;
        PreparedStatement pstat2 = null;
        int currentPoints1 = retrievePlayerLeaguePoints(player1);
        int updatedPoints1 = currentPoints1 + 1;

        int currentPoints2 = retrievePlayerLeaguePoints(player2);
        int updatedPoints2 = currentPoints2 + 1;

        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            // create Statement for updating table
            pstat1 = connection.prepareStatement("UPDATE Player SET LeaguePoints = ? WHERE StudentName = ?");
            pstat2 = connection.prepareStatement("UPDATE Player SET LeaguePoints = ? WHERE StudentName = ?");

            pstat1.setInt(1, updatedPoints1);
            pstat2.setInt(1, updatedPoints2);

            pstat1.setString(2, player1);
            pstat2.setString(2, player2);

            // Update data in database
            pstat1.executeUpdate();
            pstat2.executeUpdate();
             
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
                } finally {
            try {
                pstat1.close();
                pstat2.close();
                connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void updateDrawCount(String player) {

        Connection connection = null;
        PreparedStatement pstat1 = null;
        int currentDrawCount = retrievePlayerDrawCount(player);
        int updatedDrawCount = currentDrawCount + 1;

        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            // create Statement for updating table
            pstat1 = connection.prepareStatement("UPDATE Player SET Draws = ? WHERE StudentName = ?");

            pstat1.setInt(1, updatedDrawCount);

            pstat1.setString(2, player);

            // Update data in database
            pstat1.executeUpdate();
             
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
                } finally {
            try {
                pstat1.close();
                connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
    public void updateWinCount(String player) {

        Connection connection = null;
        PreparedStatement pstat1 = null;
        int currentWinCount = retrievePlayerWinCount(player);
        int updatedWinCount = currentWinCount + 1;

        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            // create Statement for updating table
            pstat1 = connection.prepareStatement("UPDATE Player SET Wins = ? WHERE StudentName = ?");

            pstat1.setInt(1, updatedWinCount);

            pstat1.setString(2, player);

            // Update data in database
            pstat1.executeUpdate();
             
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
                } finally {
            try {
                pstat1.close();
                connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
    public void updateLossCount(String player) {

        Connection connection = null;
        PreparedStatement pstat1 = null;
        int currentLossCount = retrievePlayerLossCount(player);
        int updatedLossCount = currentLossCount + 1;

        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            // create Statement for updating table
            pstat1 = connection.prepareStatement("UPDATE Player SET Losses = ? WHERE StudentName = ?");

            pstat1.setInt(1, updatedLossCount);

            pstat1.setString(2, player);

            // Update data in database
            pstat1.executeUpdate();
             
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
                } finally {
            try {
                pstat1.close();
                connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

}
