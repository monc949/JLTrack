import Controller.PlayerController;
import Model.Player;

public class Driver {
    public static void main(String[] args) {
        PlayerController pc = new PlayerController();

        pc.deletePlayer(1);
    }
}
