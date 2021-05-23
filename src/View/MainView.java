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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
    
    JButton buildLeagueButton = new JButton("Generate fixtures");
    JPanel buttonPanel = new JPanel();
    JButton player1Button = new JButton("Player 1");
    JButton player2Button = new JButton("Player 2");
    JButton drawButton = new JButton("Draw");

    JLabel matchSelectLabel = new JLabel("Select Match");

    DefaultTableModel model = new DefaultTableModel();
    JTable table = new JTable(model);

    Component playerSelectLabel = new JLabel("Select Player");
    JComboBox<Player> playerCombo = new JComboBox<Player>(pc.retrievePlayerListModel());

    JList<Match> matchSelector = new JList<Match>(mc.retrieveUnplayedMatchList());

    // ---------Constructor-----------------//

    public MainView() {

        JFrame frame = new JFrame();
        JPanel sideMenu = new JPanel();
        JPanel topMenu = new JPanel();
        JPanel center = new JPanel();

        // -------Main Panel (frame)-----------//

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 850);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        // -----------Center panel-------------//

        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBackground(Color.lightGray);
        center.setVisible(true);

                // ---------------Table----------------//

        // Make table uneditable
        table.setEnabled(false);
        setResizable(false);

//---Container---//
        table.setModel(pc.retrievePlayerTableMain());
        
        JScrollPane pg = new JScrollPane(table);
        center.add(pg, BorderLayout.CENTER);

        this.pack();

        // -------Side Panel-------//

        sideMenu.setPreferredSize(new Dimension(350, 0));
        sideMenu.setMinimumSize(new Dimension(250, 0));
        sideMenu.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        sideMenu.setBackground(Color.lightGray);
        sideMenu.setVisible(true);


            // -----Match Select------//
            sideMenu.add(playerSelectLabel);
            playerCombo.addItemListener (new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        Player player = (Player) playerCombo.getSelectedItem();
                        matchSelector.setModel(mc.retrieveMatchListFiltered(player));
                    }
                }
            });
            sideMenu.add(playerCombo);

            sideMenu.add(matchSelectLabel);

            matchSelector.setPreferredSize(new Dimension(330, 450));

            JScrollPane matchListContainer = new JScrollPane(matchSelector);
            matchListContainer.setPreferredSize(new Dimension(330,450));

            sideMenu.add(matchListContainer);

                // -----Buttons-----//
                player1Button.addActionListener(new ButtonHandler());
                sideMenu.add(player1Button);

                drawButton.addActionListener(new ButtonHandler());
                sideMenu.add(drawButton);

                player2Button.addActionListener(new ButtonHandler());
                sideMenu.add(player2Button);


        // -----Top Panel-------//

        topMenu.setPreferredSize(new Dimension(0, 100));
        topMenu.setMinimumSize(new Dimension(0, 70));
        topMenu.setLayout(new BorderLayout());
        topMenu.setBackground(Color.lightGray);
        buttonPanel.setBackground(Color.lightGray);
        refreshButtonPanel.setBackground(Color.lightGray);



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
        frame.add(sideMenu, BorderLayout.WEST);
        frame.add(topMenu, BorderLayout.NORTH);
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
                League league = new League(pc.retrievePlayerList());
                mc.batchCreateMatch(league.getMatches());
                table.setModel(mc.retrieveMatchTableMain());

            }
            
                
            



            //Player 1 winner
            if (e.getSource() == player1Button) {
                Match match = matchSelector.getSelectedValue();
                match.setWinner(match.getPlayer1());
                match.setCompleted(true);
                mc.updateMatch(match.getMatchID(), match.getWinner(), "Played");
                matchSelector.setModel(mc.retrieveUnplayedMatchList());
            }
            //Player 2 winner
            if (e.getSource() == player2Button) {
                Match match = matchSelector.getSelectedValue();
                match.setWinner(match.getPlayer2());
                match.setCompleted(true);
                mc.updateMatch(match.getMatchID(), match.getWinner(), "Played");
                matchSelector.setModel(mc.retrieveUnplayedMatchList());
            }
            //Draw
            if (e.getSource() == drawButton) {
                Match match = matchSelector.getSelectedValue();
                match.setWinner("Draw");
                match.setCompleted(true);
                mc.updateMatch(match.getMatchID(), match.getWinner(), "Played");
                matchSelector.setModel(mc.retrieveUnplayedMatchList());
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