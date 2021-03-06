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


/**
 * Match Database Manangement Window
 */
public class MatchView extends JFrame{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    MatchController mc = new MatchController();

    DefaultTableModel model = new DefaultTableModel();
    Container container = this.getContentPane();
    JTable table = new JTable(model);
    JPanel sidePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JButton deleteButton = new JButton("Delete Match");
    JButton clearMatchTableButton = new JButton("Clear Match Table");


//FONT//
    Font font1 = new Font("SansSerif", Font.PLAIN, 14);


//Fields and Labels

    JTextField IDField = new JTextField();

    ArrayList<JTextField> textFieldArray = new ArrayList<JTextField>();

    JLabel instructionLabel = new JLabel("Hover over buttons for instructions");

    JLabel IDLabel = new JLabel("Match ID");

    public MatchView() {

        textFieldArray.add(IDField);


        // ---------------Table----------------//

        // Make table uneditable
        setResizable(false);

//---Container---//
        container.setLayout(new BorderLayout());
        table.setModel(mc.retrieveMatchTable());
        
        JScrollPane pg = new JScrollPane(table);
        container.add(pg, BorderLayout.CENTER);

        this.pack();

        // -----------------Side panel-----------------//
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBackground(Color.lightGray);
        sidePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 345, 15));
        sidePanel.setVisible(true);




        buttonPanel.setLayout(new GridLayout(3, 1, 15, 15));
        buttonPanel.setBackground(Color.lightGray);

        sidePanel.add(instructionLabel);


        IDField.setSize(12, 23);
        IDField.setFont(font1);
        sidePanel.add(IDLabel);
        sidePanel.add(IDField);


        deleteButton.addActionListener(new ButtonHandler());
        deleteButton.setToolTipText("Enter Match ID only to delete ");
        buttonPanel.add(deleteButton);

        clearMatchTableButton.addActionListener(new ButtonHandler());
        clearMatchTableButton.setToolTipText("Clear Match Table");
        buttonPanel.add(clearMatchTableButton);

        sidePanel.add(buttonPanel);


        //------Final Panel Placement--------//
        container.add(sidePanel, BorderLayout.EAST);

    }


    //-----Both of these next methods control the create and edit requirements for Match database------///////


    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent ae){
         if (ae.getSource()==deleteButton){
             try {
                mc.deleteMatch(Integer.parseInt(IDField.getText()));
             } catch (Exception e) {
                MatchView.infoBox("You have entered the information incorrectly. \nPlease mouse over the buttons to learn how to use the functions", "Incorrect Information");  
             }

             finally {
                //Clear Fields
                IDField.setText(""); 
                table.setModel(mc.retrieveMatchTable());
             }

         }
         if (ae.getSource()==clearMatchTableButton){
             try {
                int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to clear the match table?", null, JOptionPane.YES_NO_OPTION);
                if (input == JOptionPane.YES_OPTION) {
                    mc.clearMatchTable();
                }
             } catch (Exception e) {
                 System.out.println(e);
             }

             finally {
                table.setModel(mc.retrieveMatchTable());
             }

         }

        }
    }

    

    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    
}

    

