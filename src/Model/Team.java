package Model;

import java.util.ArrayList;

public class Team {

    public ArrayList<Player> players;
    public char teamID;

    public Team(char teamID) {
        this.teamID = teamID;
    }


    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public char getTeamID() {
        return teamID;
    }

    public void setTeamID(char teamID) {
        this.teamID = teamID;
    }

    public void addPlayer(Player p) {
        players.add(p);
    }

    @Override
    public String toString() {
        return "Team = " + teamID +
                "Players  \n" + players;
    }
}
