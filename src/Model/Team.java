package Model;

import java.util.ArrayList;

public class Team {

    public ArrayList<Player> players = new ArrayList<>(5);
    public char teamID;

    public Team(char teamID) {
        this.teamID = teamID;
    }
    public Team(ArrayList<Player> players, char teamID) {
        this.teamID = teamID;
        this.players = players;
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

    public void teamOverview() {
        System.out.println("**********************");

        System.out.println(teamID + "Team\n");
        for (Player p : players) {
            System.out.println(p.toString());
        }
        System.out.println("**********************");
    }

    public void teamOverviewSanNo() {
        System.out.println("**********************");

        System.out.println(teamID + "Team\n");
        for (Player p : players) {
            System.out.println(p.getName());
        }
        System.out.println("**********************");
    }

    @Override
    public String toString() {
        return "Team = " + teamID +
                "\n\n" +
                "Players;  \n" + players;
    }
}
