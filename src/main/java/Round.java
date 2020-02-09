import java.util.List;

public class Round {

    private Game game;
    private List<Player> players;

    public Round(Game game, List<Player> players) {
        this.game = game;
        this.players = players;
    }

    public void play() {
        for(Player player : players) {
            Roll roll = new Roll();
            roll.perform();
            roll.send(player);
            player.wait();
        }
    }
}
