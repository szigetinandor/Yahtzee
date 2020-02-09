import java.util.Arrays;

public abstract class Request {

	protected String message;

	public abstract void process(Player player);

	public static Request make(String message) throws WrongRequestFormatException {
		String type = message.substring(0, message.indexOf(";"));
		String[] request = (String[]) Arrays.stream(message.split(";")).skip(1).toArray();
		if(type.equals("WriteScore"))
			return new WriteScoreRequest(message);
		else if(type.equals("Reroll"))
			return new RerollRequest(message);
		else throw new WrongRequestFormatException("Wrong request type " + type);
	}

}
