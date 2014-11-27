import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Avetisyan Sevak on 27.11.2014.
 */

class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(4567);

        try (Socket client = server.accept();
             BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {

            String input;
            while ((input = in.readLine()) != null) {
                if (input.equalsIgnoreCase("exit")) {
                    break;
                }

                out.println(input.toUpperCase());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.close();
        }
    }
}