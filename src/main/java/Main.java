

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(12345);
        final int[] counter = {0};
        int maxUsers = 2;

        while (counter[0] < maxUsers) {
            final Socket clientSocket = server.accept();
            System.out.println("New user joined!");
            counter[0]++;

            // next step - create a thread for when this happens?
            new Thread(new Runnable() {

                public void run() {
                    try {
                        new ClientConnection(clientSocket).handle();
                        System.out.println("Disconnecting");
                        counter[0]--;
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Disconnecting due to exception");
                    }
                }
            }).run();
        }
    }
}
