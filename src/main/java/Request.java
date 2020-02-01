public abstract class Request {

    protected String message;

    public abstract void process();

    public static Request make(String message) {
        String type = message.split(";")[0];
        if(type.equals("WriteScore"))
            return new WriteScoreRequest(message);
        else if(type.equals("Reroll"))
            return new RerollRequest(message);
    }

}
