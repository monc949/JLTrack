package View.DatabaseView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.MatchController;
import Controller.PlayerController;
import Model.Match;
import Model.Player;


/**
 * Player Database Manangement Window
 */
public class PlayerView extends JFrame{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    PlayerController pc = new PlayerController();

    DefaultTableModel model = new DefaultTableModel();
    Container container = this.getContentPane();
    JTable table = new JTable(model);
    JPanel sidePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JButton createButton = new JButton("Create Player");
    JButton deleteButton = new JButton("Delete Player");


//FONT//
    Font font1 = new Font("SansSerif", Font.PLAIN, 14);


//Fields and Labels

    JTextField IDField = new JTextField();
    JTextField StudentNumberField = new JTextField();
    JTextField NameField = new JTextField();
    JTextField EmailField = new JTextField();
    JTextField StudentEmailField = new JTextField();
    JTextField PhoneNumberField = new JTextField();
    JTextField LeaguePointsField = new JTextField();
    JTextField TeamField = new JTextField();
    JTextField NotesField = new JTextField();

    JLabel instructionLabel = new JLabel("Hover over buttons for instructions");

    JLabel IDLabel = new JLabel("Player ID");
    JLabel AddressLabel = new JLabel("Student Number");
    JLabel NameLabel = new JLabel("Name");
    JLabel EmailLabel = new JLabel("Personal Email");
    JLabel StudentEmailLabel = new JLabel("Student Email");
    JLabel PhoneNumberLabel = new JLabel("Phone Number");
    JLabel LeaguePointsLabel = new JLabel("League Points");
    JLabel TeamLabel = new JLabel("Team");
    JLabel NotesLabel = new JLabel("Notes");

    public PlayerView() {

        // ---------------Table----------------//

        // Make table uneditable
        table.setEnabled(false);
        setResizable(false);

//---Container---//
        container.setLayout(new BorderLayout());
        table.setModel(pc.retrievePlayerTable());
        
        JScrollPane pg = new JScrollPane(table);
        container.add(pg, BorderLayout.CENTER);

        this.pack();

        // -----------------Side panel-----------------//
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBackground(Color.lightGray);
        sidePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 345, 15));
        sidePanel.setVisible(true);



        //-----Text Fields-----////////




        NameField.setSize(12, 23);
        NameField.setFont(font1);
        sidePanel.add(NameLabel);
        sidePanel.add(NameField);


        StudentNumberField.setSize(12, 23);
        StudentNumberField.setFont(font1);
        sidePanel.add(AddressLabel);
        sidePanel.add(StudentNumberField);


        EmailField.setSize(12, 23);
        EmailField.setFont(font1);
        sidePanel.add(EmailLabel);
        sidePanel.add(EmailField);


        PhoneNumberField.setSize(12, 23);
        PhoneNumberField.setFont(font1);
        sidePanel.add(PhoneNumberLabel);
        sidePanel.add(PhoneNumberField);

        LeaguePointsField.setSize(12, 23);
        LeaguePointsField.setFont(font1);
        sidePanel.add(LeaguePointsLabel);
        sidePanel.add(LeaguePointsField);

        TeamField.setSize(12, 23);
        TeamField.setFont(font1);
        sidePanel.add(TeamLabel);
        sidePanel.add(TeamField);

        NotesField.setSize(12, 23);
        NotesField.setFont(font1);
        sidePanel.add(NotesLabel);
        sidePanel.add(NotesField);




        //Buttons
        buttonPanel.setLayout(new GridLayout(5, 1, 15, 15)); 
        buttonPanel.setBackground(Color.lightGray);

        sidePanel.add(instructionLabel);

        createButton.addActionListener(new ButtonHandler());
        createButton.setToolTipText("Enter Player info. Do not enter enter Player ID. This will auto-generated");
        buttonPanel.add(createButton);

        IDField.setSize(12, 23);
        IDField.setFont(font1);
        buttonPanel.add(IDLabel);
        buttonPanel.add(IDField);

        deleteButton.addActionListener(new ButtonHandler());
        deleteButton.setToolTipText("Enter Player ID only to delete ");
        buttonPanel.add(deleteButton);

        sidePanel.add(buttonPanel);


        //------Final Panel Placement--------//
        container.add(sidePanel, BorderLayout.WEST);

    }


    //-----Both of these next methods control the create and edit requirements for Player database------///////


    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent ae){
            PlayerController pc = new PlayerController();
            MatchController mc = new MatchController();

        if (ae.getSource()==createButton){
            Player newPlayer = null;
            if (NameField.getText() !=null) {
                    newPlayer = new Player(StudentNumberField.getText(),
                    NameField.getText(), 
                    EmailField.getText(), 
                    PhoneNumberField.getText(),
                    NotesField.getText());
                table.setModel(pc.retrievePlayerTable());
                pc.createNewPlayer(newPlayer);
            }

            ArrayList<Player> players = pc.retrievePlayerList();
            ArrayList<Match> newMatchList = new ArrayList<Match>();

            for (Player player : players) {
                newMatchList.add(new Match(newPlayer.getName(), player.getName()));
            }

            int input = JOptionPane.showConfirmDialog(null, "Would you like to generate matches for " + newPlayer.getName() + " at this time?", null, JOptionPane.YES_NO_OPTION);
            if (input == JOptionPane.YES_OPTION) {
                mc.batchAmendMatch(newMatchList);
            }

            

        //Clear Fields after use
            IDField.setText("");
            NameField.setText(""); 
            StudentNumberField.setText("");
            EmailField.setText("");
            PhoneNumberField.setText("");


        }






         if (ae.getSource()==deleteButton){
             try {
                pc.deletePlayer(Integer.parseInt(IDField.getText()));
             } catch (Exception e) {
                PlayerView.infoBox("You have entered the information incorrectly. \nPlease mouse over the buttons to learn how to use the functions", "Incorrect Information");  
             }

             finally {
                //Clear Fields
                IDField.setText(""); 
                table.setModel(pc.retrievePlayerTable());
             }

         }

        }
    }

    

    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
    

