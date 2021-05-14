package Model;

public class Match {

    Player player1;
    Player player2;
    Boolean draw;
    Boolean completed;
    Player winner;

    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.draw = false;
        this.winner = null;
        this.completed = false;
    }

    public Match(Player player1, Player player2, Boolean draw, Player winner, Boolean completed) {
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

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    @Override
    public String toString() {
        if (draw) {
            return player1.getName() + " vs " + player2.getName() + "\nDraw";
        }
        if (!completed) {
            return player1.getName() + " vs " + player2.getName();
        } else {
            return player1.getName() + " vs " + player2.getName() + "\n" + "Winner : " + winner;
        }
    }
}
