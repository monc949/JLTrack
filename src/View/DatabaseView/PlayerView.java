package View.DatabaseView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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

import Controller.PlayerController;
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
    JButton editButton = new JButton("Edit Player");
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

    JLabel instructionLabel = new JLabel("Hover over buttons for instructions");

    JLabel IDLabel = new JLabel("Player ID");
    JLabel AddressLabel = new JLabel("Student Number");
    JLabel NameLabel = new JLabel("Name");
    JLabel EmailLabel = new JLabel("Personal Email");
    JLabel StudentEmailLabel = new JLabel("Student Email");
    JLabel PhoneNumberLabel = new JLabel("Phone Number");
    JLabel LeaguePointsLabel = new JLabel("League Points");
    JLabel TeamLabel = new JLabel("Team");

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

        IDField.setSize(12, 23);
        IDField.setFont(font1);
        sidePanel.add(IDLabel);
        sidePanel.add(IDField);


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

        StudentEmailField.setSize(12, 23);
        StudentEmailField.setFont(font1);
        sidePanel.add(StudentEmailLabel);
        sidePanel.add(StudentEmailField);


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




        //Buttons
        buttonPanel.setLayout(new GridLayout(3, 1, 15, 15));
        buttonPanel.setBackground(Color.lightGray);

        sidePanel.add(instructionLabel);

        createButton.addActionListener(new ButtonHandler());
        createButton.setToolTipText("Enter Player info. Do not enter enter Player ID. This will auto-generated");
        buttonPanel.add(createButton);

        editButton.addActionListener(new ButtonHandler());
        editButton.setToolTipText("Enter Player ID and fill in new Player info");
        buttonPanel.add(editButton);

        deleteButton.addActionListener(new ButtonHandler());
        deleteButton.setToolTipText("Enter Player ID only to delete ");
        buttonPanel.add(deleteButton);

        sidePanel.add(buttonPanel);


        //------Final Panel Placement--------//
        container.add(sidePanel, BorderLayout.EAST);

    }


    //-----Both of these next methods control the create and edit requirements for Player database------///////


    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent ae){
            PlayerController cc = new PlayerController();

        if (ae.getSource()==createButton){
            if (NameField.getText() !=null) {
                cc.createNewPlayer(new Player(StudentNumberField.getText(),
                NameField.getText(), 
                EmailField.getText(), 
                PhoneNumberField.getText()));
                table.setModel(cc.retrievePlayerTable());
            }



        //Clear Fields after use
            IDField.setText("");
            NameField.setText(""); 
            StudentNumberField.setText("");
            EmailField.setText("");
            PhoneNumberField.setText("");

        }


         if (ae.getSource()==editButton){
             try {
                cc.updatePlayer(Integer.parseInt(IDField.getText()),
                StudentNumberField.getText(),
                NameField.getText(), 
                EmailField.getText(), 
                StudentEmailField.getText(), 
                PhoneNumberField.getText(),
                Integer.parseInt(LeaguePointsField.getText()),
                TeamField.getText());

             } catch (Exception e) {
                PlayerView.infoBox("You have entered the information incorrectly. \nPlease mouse over the buttons to learn how to use the functions", "Incorrect Information");
             }
            finally {
                    //Clear Fields
                    IDField.setText("");
                    NameField.setText(""); 
                    StudentNumberField.setText("");
                    EmailField.setText("");
                    EmailField.setText("");
                    PhoneNumberField.setText("");
                    table.setModel(cc.retrievePlayerTable());
            }



         }

         if (ae.getSource()==deleteButton){
             try {
                cc.deletePlayer(Integer.parseInt(IDField.getText()));cc.deletePlayer(Integer.parseInt(IDField.getText()));
             } catch (Exception e) {
                PlayerView.infoBox("You have entered the information incorrectly. \nPlease mouse over the buttons to learn how to use the functions", "Incorrect Information");  
             }

             finally {
                //Clear Fields
                IDField.setText(""); 
                table.setModel(cc.retrievePlayerTable());
             }

         }

        }
    }

    

    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
    

