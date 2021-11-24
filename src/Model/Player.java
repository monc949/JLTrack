package Model;

public class Player {
    private int playerID;
    private String studentNo;
    private String name;
    private int leaguePoints;
    private String notes;

    // Constructors

    // Initial Registration constructor
    public Player(String studentNo, String name, String notes) {
        setStudentNo(studentNo);
        setName(name);
        setLeaguePoints(0);
        setNotes(notes);
    }

    // for editing
    public Player(int playerID, String studentNo, String name, int leaguePoints, String notes) {
        setPlayerID(playerID);
        setStudentNo(studentNo);
        setName(name);
        setLeaguePoints(leaguePoints);
        setNotes(notes);
    }

    // Getters and Setters

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
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

    /**
     * @return String return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

}
