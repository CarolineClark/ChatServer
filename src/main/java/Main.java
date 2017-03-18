

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(12345);

        Socket clientSocket = server.accept();

        // next step - create a thread for when this happens?
        new ClientConnection(clientSocket).handle();
    }
}
