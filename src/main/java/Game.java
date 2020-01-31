import java.util.ArrayList;
import java.util.List;

public class Game {

    private Server server;
    private List<Player> players;
    private List<Round> rounds;
    private final int roundsCount = 13;

    public Game(Server server, List<Player> players) {
        this.server = server;
        this.players = players;
        this.rounds = new ArrayList<>();
        for(int i = 0; i < roundsCount; i++) {
            rounds.add(new Round(players));
        }
    }

    public void play() {
        for(Round round : rounds)
            round.play();
        sendPointsInformation();
    }

    private void sendPointsInformation() {
        PointsInformation points = new PointsInformation(players);
        server.sendPointsInformationToAllClients(points);
    }
}
