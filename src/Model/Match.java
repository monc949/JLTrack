package Model;

public class Match {

    int matchID;
    String player1;
    String player2;
    Boolean draw;
    Boolean completed;
    String winner;

    public Match(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.draw = false;
        this.winner = null;
        this.completed = false;
    }

    public Match(int matchID, String player1, String player2, String winner, Boolean completed) {
        this.matchID = matchID;
        this.player1 = player1;
        this.player2 = player2;
        if (winner == "DRAW") {
            draw = true;
        }
        this.winner = winner;
        this.completed = completed;
    }

    public Match(String player1, String player2, Boolean draw, String winner, Boolean completed) {
        this.player1 = player1;
        this.player2 = player2;
        this.draw = draw;
        this.completed = completed;

        if (draw || !completed) {
            this.winner = null; 
        } else {
            this.winner = winner;
        }
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1.getName();
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2.getName();
    }

    public Boolean getDraw() {
        return draw;
    }

    public void setDraw(Boolean draw) {
        this.draw = draw;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    @Override
    public String toString() {
            return player1 + " vs " + player2 + "\n";
    }
}

