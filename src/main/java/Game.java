import java.util.ArrayList;
import java.util.List;

public class Game {

    private Server server;
    private List<Player> players;
    private List<Round> rounds;
    private final int roundsCount = 13;

    public Game(Server server) {
        this.server = server;
        this.players = new ArrayList<>();
        this.rounds = new ArrayList<>();
        for(int i = 0; i < roundsCount; i++) {
            rounds.add(new Round(this, players));
        }
    }

    public void assignPlayer(Player player) {
        this.players.add(player);
    }

    public void play() {
        for(Round round : rounds)
            round.play();
        sendPointsInformation();
    }

    private void sendPointsInformation() {
        PointsInformation points = new PointsInformation(players);
        points.send();
    }

    public void requestReroll(Player player) {

    }
}
