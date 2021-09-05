package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import Controller.MatchController;
import Controller.PlayerController;
import Model.League;
import Model.Match;
import Model.Player;
import View.DatabaseView.Table;

/**
 * Main Menu
 *
 */
public class MainView extends JFrame {
    private static final long serialVersionUID = 1L;

    MatchController mc = new MatchController();
    PlayerController pc = new PlayerController();

    // ---------Components--------------//
    JButton refreshButton = new JButton("Refresh");
    JPanel refreshButtonPanel = new JPanel();

    JButton playerDBButton = new JButton("Player Database");
    JButton matchDBButton = new JButton("Match Database");
    
    JButton buildLeagueButton = new JButton("Start League");
    JPanel buttonPanel = new JPanel();
    JButton player1Button = new JButton("Player 1");
    JButton player2Button = new JButton("Player 2");
    JButton drawButton = new JButton("Draw");

    JLabel matchSelectLabel = new JLabel("Select Match");

    DefaultTableModel model = new DefaultTableModel();
    JTable table = new JTable(model);

    Component playerSelectLabel = new JLabel("Filter matches by player");
    JComboBox<Player> playerCombo = new JComboBox<Player>(pc.retrievePlayerListModel());

    JList<Match> matchSelector = new JList<Match>(mc.retrieveUnplayedMatchList());

    // ---------Constructor-----------------//

    public MainView() {

        JFrame frame = new JFrame();
        JPanel sideMenu1 = new JPanel();
        JPanel sideMenu2 = new JPanel();
        JPanel topMenu = new JPanel();
        JPanel bottomMenu = new JPanel();
        JPanel center = new JPanel();

        // -------Main Panel (frame)-----------//

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 850);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        // -----------Center panel-------------//

        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBackground(new Color(107, 106, 104));
        center.setVisible(true);

                // ---------------Table----------------//

        // Make table uneditable
        table.setEnabled(false);
        setResizable(false);

//---Container---//
        table.setModel(pc.retrievePlayerTableMain());
        
        JScrollPane pg = new JScrollPane(table);
        pg.setBounds(0, 0, 250, frame.getHeight());
        center.add(pg, BorderLayout.CENTER);

        this.pack();

        // -------Side Panel-------//

        sideMenu1.setPreferredSize(new Dimension(350, 0));
        sideMenu1.setMinimumSize(new Dimension(250, 0));
        sideMenu1.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        sideMenu1.setBackground(new Color(107, 106, 104));
        sideMenu1.setVisible(true);


        // -------Side Panel-------//

        sideMenu2.setPreferredSize(new Dimension(150, 0));
        sideMenu2.setMinimumSize(new Dimension(50, 0));
        sideMenu2.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        sideMenu2.setBackground(new Color(107, 106, 104));
        sideMenu2.setVisible(true);


            // -----Match Select------//
            playerSelectLabel.setForeground(Color.WHITE);
            sideMenu1.add(playerSelectLabel);
            playerCombo.addItemListener (new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        Player player = (Player) playerCombo.getSelectedItem();
                        matchSelector.setModel(mc.retrieveMatchListFiltered(player));
                    }
                }
            });

            Color highlight = new Color(168, 213, 247, 150);
            Color shadow = new Color(168, 213, 247, 150);
            playerCombo.setBorder(new BevelBorder(EXIT_ON_CLOSE, highlight, shadow));
            sideMenu1.add(playerCombo);

            matchSelectLabel.setForeground(Color.WHITE);
            sideMenu1.add(matchSelectLabel);


            JScrollPane matchListContainer = new JScrollPane(matchSelector);
            matchListContainer.setPreferredSize(new Dimension(330,450));

            sideMenu1.add(matchListContainer);

                // -----Buttons-----//
                player1Button.addActionListener(new ButtonHandler());
                sideMenu1.add(player1Button);

                drawButton.addActionListener(new ButtonHandler());
                sideMenu1.add(drawButton);

                player2Button.addActionListener(new ButtonHandler());
                sideMenu1.add(player2Button);


        // -----Top Panel-------//

        topMenu.setPreferredSize(new Dimension(0, 100));
        topMenu.setMinimumSize(new Dimension(0, 70));
        topMenu.setLayout(new BorderLayout());
        topMenu.setBackground(new Color(107, 106, 104));

        buttonPanel.setBackground(new Color(107, 106, 104));
        refreshButtonPanel.setBackground(new Color(107, 106, 104));


        // -----Bottom Panel-------//

        bottomMenu.setPreferredSize(new Dimension(0, 100));
        bottomMenu.setMinimumSize(new Dimension(0, 70));
        bottomMenu.setLayout(new BorderLayout());
        bottomMenu.setBackground(new Color(107, 106, 104));



                // -----Buttons-----//
                playerDBButton.setSize(90, 5);
                playerDBButton.setFocusable(false);
                playerDBButton.addActionListener(new ButtonHandler());
                buttonPanel.add(playerDBButton);

                matchDBButton.setSize(90, 5);
                matchDBButton.setFocusable(false);
                matchDBButton.addActionListener(new ButtonHandler());
                buttonPanel.add(matchDBButton);

                refreshButton.setSize(90, 5);
                refreshButton.setFocusable(false);
                refreshButton.addActionListener(new ButtonHandler());
                refreshButtonPanel.add(refreshButton);

                buildLeagueButton.setSize(90, 5);
                buildLeagueButton.setFocusable(false);
                buildLeagueButton.addActionListener(new ButtonHandler());
                refreshButtonPanel.add(buildLeagueButton);

                topMenu.add(refreshButtonPanel, BorderLayout.WEST);
                topMenu.add(buttonPanel, BorderLayout.NORTH);

        topMenu.setVisible(true);

        // -----Add components ------>
        frame.add(sideMenu1, BorderLayout.WEST);
        frame.add(sideMenu2, BorderLayout.EAST);
        frame.add(topMenu, BorderLayout.NORTH);
        frame.add(bottomMenu, BorderLayout.SOUTH);
        frame.add(center, BorderLayout.CENTER);
        frame.setVisible(true);
        

    }

    // ----------Action listener---------------//

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //Player Database
            if (e.getSource() == playerDBButton) {
                new Table(1);
            }

            //Match Database
            if (e.getSource() == matchDBButton) {
                new Table(2);
            }

            //Build League
            if (e.getSource() == buildLeagueButton) {
                int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to start a new League?\nThis will erase all current match records\n from the database ", null, JOptionPane.YES_NO_OPTION);
                if (input == JOptionPane.YES_OPTION) {
                    League league = new League(pc.retrievePlayerList());
                    mc.batchCreateMatch(league.getMatches());
                    JOptionPane.showMessageDialog(null, "A new league has been created!");
                    pc.clearPlayerLeaguePoints();
                    table.setModel(pc.retrievePlayerTableMain());
                    playerCombo.setModel(pc.retrievePlayerListModel());
                    matchSelector.setModel(mc.retrieveUnplayedMatchList());


                }


            }
            
                
            



            //Player 1 winner
            if (e.getSource() == player1Button) {
                Match match = matchSelector.getSelectedValue();
                if (match !=null) {
                    int input = JOptionPane.showConfirmDialog(null, "Winner : " +match.getPlayer1() + "\nIs this correct?", null, JOptionPane.YES_NO_OPTION);
                    if (input == JOptionPane.YES_OPTION) {
                        match.setCompleted(true);
    
                        match.setWinner(match.getPlayer1());
                        mc.updateMatch(match.getMatchID(), match.getWinner(), "Played");
                        pc.updateLeaguePointsWinner(match.getPlayer1());
                        pc.updateWinCount(match.getPlayer1());
                        pc.updateLossCount(match.getPlayer2());
    
                        matchSelector.setModel(mc.retrieveUnplayedMatchList());
                        table.setModel(pc.retrievePlayerTableMain());
                }

                }
                else {
                    JOptionPane.showMessageDialog(null, "No match has been selected.\nPlease select a match and try again.");
                }
            }
            //Player 2 winner
            if (e.getSource() == player2Button) {
                Match match = matchSelector.getSelectedValue();
                if (match !=null) {
                    int input = JOptionPane.showConfirmDialog(null, "Winner : " +match.getPlayer2() + "\nIs this correct?", null, JOptionPane.YES_NO_OPTION);
                    if (input == JOptionPane.YES_OPTION) {
                        match.setCompleted(true);
                        match.setWinner(match.getPlayer2());
                        mc.updateMatch(match.getMatchID(), match.getWinner(), "Played");
                        pc.updateLeaguePointsWinner(match.getPlayer2());
                        pc.updateWinCount(match.getPlayer2());
                        pc.updateLossCount(match.getPlayer1());
    
                        matchSelector.setModel(mc.retrieveUnplayedMatchList());
                        table.setModel(pc.retrievePlayerTableMain());
                }

                }
                else {
                    JOptionPane.showMessageDialog(null, "No match has been selected.\nPlease select a match and try again.");
                }

                }

            //Draw
            if (e.getSource() == drawButton) {
                Match match = matchSelector.getSelectedValue();
                if (match !=null) {
                    int input = JOptionPane.showConfirmDialog(null, "Match Result : DRAW\nIs this correct?", null, JOptionPane.YES_NO_OPTION);
                    if (input == JOptionPane.YES_OPTION) {
                        match.setWinner("Draw");
                        match.setCompleted(true);
                        mc.updateMatch(match.getMatchID(), match.getWinner(), "Played");
                        pc.updateLeaguePointsDraw(match.getPlayer1(),match.getPlayer2());
                        pc.updateDrawCount(match.getPlayer1());
                        pc.updateDrawCount(match.getPlayer2());
                        
                        matchSelector.setModel(mc.retrieveUnplayedMatchList());
                        table.setModel(pc.retrievePlayerTableMain());
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "No match has been selected.\nPlease select a match and try again.");
                }


                }


            //Refresh
            if (e.getSource() == refreshButton) {
                table.setModel(pc.retrievePlayerTableMain());
                matchSelector.setModel(mc.retrieveUnplayedMatchList());
                playerCombo.setModel(pc.retrievePlayerListModel());
            }




        }
    }
    
}