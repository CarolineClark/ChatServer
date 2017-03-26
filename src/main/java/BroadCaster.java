import java.util.ArrayList;
import java.util.List;

class BroadCaster {
    private List<ClientConnection> clients;

    BroadCaster() {
        this.clients = new ArrayList<ClientConnection>();
    }

    void add(ClientConnection clientConnection) {
        this.clients.add(clientConnection);
    }

    void remove(ClientConnection clientConnection) {
        this.clients.remove(clientConnection);
    }

    void notifiyAll(String message) {
        for (ClientConnection client: clients) {
            client.send(message);
        }
    }
}
