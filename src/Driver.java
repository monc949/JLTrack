import Model.League;
import Model.Player;
import Model.Team;

import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) {
        Player player1 = new Player("C00253212", "Ciaran", "ciaranmaye8@gmail.com");
        Player player2 = new Player("C00253234", "Stephen", "ste@gmail.com");
        Player player3 = new Player("C00241223", "Sean", "sean8@gmail.com");
        Player player4 = new Player("C00543242", "Dan", "danturner@gmail.com");

        // Teams player Arrays

        // Team Objects
        // Team ATeam = new Team('A');
        // Team BTeam = new Team('B');
        // Team CTeam = new Team('C');
        // Team DTeam = new Team('D');

        ArrayList<Player> allPlayers = new ArrayList<>(50);
        allPlayers.add(player1);
        allPlayers.add(player2);
        allPlayers.add(player3);
        allPlayers.add(player4);

        League league = new League(allPlayers);
        league.showFixtures();

    }
}
