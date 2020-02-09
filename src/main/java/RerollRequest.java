public class RerollRequest extends Request {

    public RerollRequest(String message) {
        this.message = message;
    }

    @Override
    public void process(Player player) {
        player.requestReroll();
    }
}
