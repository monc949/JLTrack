import Model.Player;
import Model.Team;

import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) {
        Player player1 = new Player("C00253212", "Ciaran", "ciaranmaye8@gmail.com");
        Player player2 = new Player("C00253234", "Stephen", "ste@gmail.com");
        Player player3 = new Player("C00241223", "Sean", "sean8@gmail.com");
        Player player4 = new Player("C00543242", "Dan", "danturner@gmail.com");

        //Teams player Arrays

        //Team Objects
        Team ATeam = new Team('A');
        Team BTeam = new Team('B');
        Team CTeam = new Team('C');
        Team DTeam = new Team('D');


        ATeam.addPlayer(player1);
        ATeam.addPlayer(player2);
        ATeam.addPlayer(player3);
        ATeam.addPlayer(player4);

        ATeam.teamOverview();
        ATeam.teamOverviewSanNo();


    }
}
