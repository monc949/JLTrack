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
                Match match = new Match(players.get(i).getName(), players.get(j).getName());
                if (!isInvalid(match)) {
                    matches.add(match);
                }

            }
        }
    }

    private boolean isInvalid(Match m) {

        if (m.player1 == m.player2) {
            return true;
        }
        return isDuplicate(m);
    }

    private boolean isDuplicate(Match match) {
        for (Match value : matches) {
            if ((match.player1 == value.player1 && match.player2 == value.player2)
                    || (match.player1 == value.player2 && match.player2 == value.player1)) {
                return true;
            }
        }
        return false;
    }

    public void showFixtures() {
        for (Match match : matches) {
            System.out.println(match);
        }
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }


}
