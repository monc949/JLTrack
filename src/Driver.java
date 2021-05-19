import Controller.MatchController;
import Controller.PlayerController;
import Model.League;
import View.MainView;

public class Driver {
    public static void main(String[] args) {
        PlayerController pc = new PlayerController();
        MatchController mc = new MatchController();

        League league = new League(pc.retrievePlayerList());
        mc.batchCreateMatch(league.getMatches());
        
        new MainView();




    }
}
