import Factory.ClientFactory;
import Models.Client;

// Project starts from here
public class Main {
    public static void main(String[] args) {
        Client client = ClientFactory.buildClient();
        client.start();
    }
}
