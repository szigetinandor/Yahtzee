import java.util.List;

public class PointsInformation implements Sendable {

    private List<Player> players;

    public PointsInformation(List<Player> players) {
        this.players = players;
    }

    @Override
    public void send() {

    }
}
