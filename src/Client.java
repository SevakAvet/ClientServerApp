import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Avetisyan Sevak on 27.11.2014.
 */

class Client {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("127.0.0.1", 4567);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {

            String request;
            while ((request = console.readLine()) != null) {
                if (request.equalsIgnoreCase("close") || request.equalsIgnoreCase("exit")) {
                    break;
                }

                System.out.println("Request: " + request);
                out.println(request);
                System.out.println("Response: " + in.readLine());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            clientSocket.close();
        }
    }
}