package Model;

public class Player {
    private String playerID;

    private String studentNo;
    private String name;
    private String studentEmail;
    private String personalEmail;
    private String phoneNumber;
    private int leaguePoints;
    private String team;

    // Constructors

    // Initial Registration constructor
    public Player() {
        setStudentNo(studentNo);
        setName(name);
        setStudentEmail(studentNo);
        setPersonalEmail(personalEmail);
        setPhoneNumber(phoneNumber);
        setLeaguePoints(0);
        setTeam("n");
    }

    public Player(String playerID, String studentNo, String name, String personalEmail, String phoneNumber,
            int leaguePoints, String team) {
        setPlayerID(playerID);
        setStudentNo(studentNo);
        setName(name);
        setStudentEmail(studentNo);
        setPersonalEmail(personalEmail);
        setPhoneNumber(phoneNumber);
        setLeaguePoints(leaguePoints);
        setTeam(team);
    }

    // Getters and Setters

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
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
        return name + "\n" + studentNo + "\n" + team + " team\n";
    }

}
