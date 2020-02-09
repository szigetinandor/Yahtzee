import java.util.HashMap;

public class Player {

    private String name;
    private int points;
    private HashMap<String, Integer> scoreSheet;
    private Game game;

    public Player(String name, Game game) {
        this.name = name;
        this.points = 0;
        this.scoreSheet = new HashMap<>();
        this.game = game;
    }

}
