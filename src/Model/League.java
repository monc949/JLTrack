package Model;

import java.util.ArrayList;

public class League {

    ArrayList<Player> players;
    ArrayList<Match> matches = new ArrayList<>(50);

    public League(ArrayList<Player> players) {
        this.players = players;
        generateMatches();
    }

    @SuppressWarnings("ForLoopReplaceableByForEach")
    private void generateMatches() {
        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < players.size(); j++) {
                matches.add(new Match(players.get(i), players.get(j)));
            }
        }
    }

}
