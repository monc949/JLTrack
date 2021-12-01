package Model;

public class Player {
    private int playerID;
    private String name;
    private int leaguePoints;

    // Constructors

    // Initial Registration constructor
    public Player(String studentNo, String name, String notes) {
        setName(name);
        setLeaguePoints(0);
    }

    // for editing
    public Player(int playerID, String name, int leaguePoints) {
        setPlayerID(playerID);
        setLeaguePoints(leaguePoints);
        setName(name);
    }

    // Getters and Setters

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    @Override
    public String toString() {
        return name;
    }
}
