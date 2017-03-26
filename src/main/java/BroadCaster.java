import java.util.ArrayList;
import java.util.List;

public class BroadCaster {
    private List<ClientConnection> clients;

    public BroadCaster() {
        this.clients = new ArrayList<ClientConnection>();
    }

    public void add(ClientConnection clientConnection) {
        this.clients.add(clientConnection);
    }

    public void remove(ClientConnection clientConnection) {
        this.clients.remove(clientConnection);
    }

    public void notifiyAll(String message) {
        for (ClientConnection client: clients) {
            client.send(message);
        }
    }
}
