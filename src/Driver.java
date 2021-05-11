import Model.Player;
import Model.Team;

import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) {
        Player player1 = new Player("C00253212", "Ciaran", "ciaranmaye8@gmail.com");
        Player player2 = new Player("C00253234", "Stephen", "ste@gmail.com");
        Player player3 = new Player("C00241223", "Sean", "sean8@gmail.com");
        Player player4 = new Player("C00543242", "Dan", "danturner@gmail.com");

        ArrayList<Player> ATeamPlayers = new ArrayList<Player>(5);

        Team ATeam = new Team('A');
        ATeam.addPlayer(player1);
        ATeam.addPlayer(player2);
        ATeam.addPlayer(player3);
        ATeam.addPlayer(player4);

        System.out.println(ATeam);


    }
}
