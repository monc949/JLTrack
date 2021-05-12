package Model;

public class Match {

    Player player1;
    Player player2;
    Boolean draw;
    Player winner;

    public Match (Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
        this.draw = false;
        this.winner = null;
    }

    public Match (Player player1, Player player2, Boolean draw, Player winner){
        this.player1 = player1;
        this.player2 = player2;
        this.draw = draw;
        if (draw) {
            this.winner = null;
        }
        else {
            this.winner = winner;
        }
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Boolean getDraw() {
        return draw;
    }

    public void setDraw(Boolean draw) {
        this.draw = draw;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    @Override
    public String toString() {
        if (draw){
            return player1 + " vs " + player2 +
                    "\nDraw";
        }
        else {
            return player1 + " vs " + player2 +
                    "\n" + "Winner : " + winner;
        }
    }
}
