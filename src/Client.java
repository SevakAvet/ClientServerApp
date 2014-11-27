import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Avetisyan Sevak on 27.11.2014.
 */

class Client implements Runnable {
    private String host;
    private int port;
    private int id;

    public Client(String host, int port, int id) {
        this.host = host;
        this.port = port;
        this.id = id;
    }

    @Override
    public void run() {
        try (Socket clientSocket = new Socket(host, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            Integer x = 0;
            while (true) {
                //System.out.println("id=" + id + ", request: " + x);
                out.println(x);
                out.flush();

                x = Integer.parseInt(in.readLine());
                //System.out.println("id=" + id + ", response: " + x);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}