public class WriteScoreRequest extends Request {


    public WriteScoreRequest(String message) {
        this.message = message;
    }

    @Override
    public void process(Player player) {

        player.requestWriteScore(Roll roll);
    }
}
