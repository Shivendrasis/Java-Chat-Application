import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    Socket socket;
    BufferedReader br;  // For reading server messages
    PrintWriter out;    // For writing to the server

    // constructor
    public Client() {
        try {
            System.out.println("Sending request to server...");
            socket = new Socket("localhost", 8888);  // Ensure correct IP and port
            System.out.println("Connection done");

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));  // Read server messages
            out = new PrintWriter(socket.getOutputStream());  // Send messages to server

            startReading();
            startWriting();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startReading() {
        Runnable r1 = () -> {
            System.out.println("Reader started...");

            while (true) {
                try {
                    String msg = br.readLine();
                    if (msg.equals("exit")) {
                        System.out.println("Server has terminated the chat");
                        socket.close();
                        break;
                    }
                    System.out.println("Server: " + msg);
                } catch (Exception e) {
                    System.out.println("Connection closed.");
                    break;
                }
            }

        };
        new Thread(r1).start();
    }

    public void startWriting() {
        Runnable r2 = () -> {
            System.out.println("Writer started...");
            try {
                BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));  // Read user input
                while (!socket.isClosed()) {
                    String content = br1.readLine();
                    out.println(content);
                    out.flush();

                    if (content.equals("exit")) {
                        socket.close();
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Connection closed.");
            }
        };
        new Thread(r2).start();
    }

    public static void main(String[] args) {
        System.out.println("Client started...");
        new Client();
    }
}
