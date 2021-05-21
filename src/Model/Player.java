package Model;

public class Player {
    private int playerID;

    private String studentNo;
    private String name;
    private String studentEmail;
    private String personalEmail;
    private String phoneNumber;
    private int leaguePoints;
    private String team;
    private String notes;

    // Constructors

    // Initial Registration constructor
    public Player(String studentNo, String name, String personalEmail, String phoneNumber, String notes) {
        setStudentNo(studentNo);
        setName(name);
        setStudentEmail(studentNo);
        setPersonalEmail(personalEmail);
        setPhoneNumber(phoneNumber);
        setLeaguePoints(0);
        setTeam("n");
        setNotes(notes);
    }
    // for editing
    public Player(int playerID, String studentNo, String name, String personalEmail, String phoneNumber,
            int leaguePoints, String team, String notes) {
        setPlayerID(playerID);
        setStudentNo(studentNo);
        setName(name);
        setStudentEmail(studentNo);
        setPersonalEmail(personalEmail);
        setPhoneNumber(phoneNumber);
        setLeaguePoints(leaguePoints);
        setTeam(team);
        setNotes(notes);
    }

    //for retrieving
    public Player(int playerID, String studentNo, String name, String personalEmail, String studentEmail, String phoneNumber,
            int leaguePoints, String team, String notes) {
        setPlayerID(playerID);
        setStudentNo(studentNo);
        setName(name);
        this.studentEmail = studentEmail;
        setPersonalEmail(personalEmail);
        setPhoneNumber(phoneNumber);
        setLeaguePoints(leaguePoints);
        setTeam(team);
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

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentNo) {
        this.studentEmail = studentNo + "@itcarlow.ie";
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
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
