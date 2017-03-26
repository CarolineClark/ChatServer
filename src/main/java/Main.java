

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(12345);
        final int[] counter = {0};
        int maxUsers = 10;
        final BroadCaster broadCaster = new BroadCaster();

        while (counter[0] < maxUsers) {
            System.out.println("Trying again");
            final Socket clientSocket = server.accept();
            System.out.println("New user joined!");
            counter[0]++;
            final ClientConnection client = new ClientConnection(clientSocket, counter[0], broadCaster);
            broadCaster.add(client);

            // next step - create a thread for when this happens?
            new Thread(new Runnable() {

                public void run() {
                    try {
                        client.handle();
                        System.out.println("Disconnecting");
                        counter[0]--;
                        broadCaster.remove(client);
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Disconnecting due to exception");
                    }
                }
            }).start();
        }
    }
}
