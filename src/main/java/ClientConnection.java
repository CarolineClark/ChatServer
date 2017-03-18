import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientConnection {
    private PrintWriter writer;
    BufferedReader in;

    public ClientConnection(Socket clientSocket) throws IOException {
        writer = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void handle() throws IOException {
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.print(inputLine);
            // send the information back to the client with the writer?
            writer.write(inputLine);

            if (inputLine.equals("bye")) {
                writer.write("disconnecting\n");
                return;
            }
        }
    }
}
