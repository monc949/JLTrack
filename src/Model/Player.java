package Model;

public class Player {
    private String studentNo;
    private String name;
    private String studentEmail;
    private String personalEmail;
    private int rank;
    private int leaguePoints;
    private char team;

//Constructors
    //Initial Registration constructor
    public Player(String studentNo, String name, String personalEmail) {
        setStudentNo(studentNo);
        setName(name);
        setStudentEmail(studentNo);
        setPersonalEmail(personalEmail);
        setRank(0);
        setLeaguePoints(0);
        setTeam('n');
    }

    //Getters and Setters
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

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public char getTeam() {
        return team;
    }

    public void setTeam(char team) {
        this.team = team;
    }
}
