import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientConnection {
    private PrintWriter writer;
    private BufferedReader in;
    private int id;
    private BroadCaster broadCaster;

    public ClientConnection(Socket clientSocket, int id, BroadCaster broadCaster) throws IOException {
        this.id = id;
        writer = new PrintWriter(clientSocket.getOutputStream(), true);
        writer.write(id);
        writer.flush();
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.broadCaster = broadCaster;
    }

    public void send(String message) {
        writer.write(message);
        writer.flush();
    }

    public void handle() throws IOException {
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            if (inputLine.equals("")) {
                continue;
            }

            inputLine = id + "," + inputLine + "\n";
            System.out.print("Received input = " + inputLine);
            broadCaster.notifiyAll(inputLine);

            if (inputLine.equals("bye")) {
                writer.write("disconnecting\n");
                return;
            }
        }
    }
}
