import java.util.HashMap;

public class Player {

    private ClientHandler handler;
    private String name;
    private int points;
    private HashMap<String, Integer> scoreSheet;
    private Game game;

    public Player(ClientHandler handler, String name) {
        this.name = name;
        this.points = 0;
        this.scoreSheet = new HashMap<>();
    }

    public String getName() {
        return this.name;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void requestReroll() {
        this.game.requestReroll(this);
    }



}
