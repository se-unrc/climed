import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class Main {
	
	public static void main(String args[]) {
		WebResource wr;
                // Friends del tucu
                final String BaseURI = "https://api.twitter.com/1/friends/ids.json?screen_name=tucumetal";
                // ultimo status del tucu
		// final String BaseURI = "http://twitter.com/statuses/user_timeline/tucumetal.json?count=1";

		// Config client
		ClientConfig config = new DefaultClientConfig();

		// Create client
		Client client = Client.create(config);
		wr = client.resource(BaseURI);

		// do the get to twitter
		String s = wr.get(String.class);
		System.out.println(s);

		try{
			@SuppressWarnings("unused")
			Mediador_Principal med = new Mediador_Principal();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
