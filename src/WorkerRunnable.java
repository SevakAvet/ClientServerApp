import java.io.*;
import java.net.Socket;

public class WorkerRunnable implements Runnable {
    protected Socket clientSocket;
    protected String serverText;

    public WorkerRunnable(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText = serverText;
    }

    @Override
    public void run() {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedWriter output = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

            while (true) {
                int number = Integer.parseInt(input.readLine());
                //System.out.println(number);

                output.write(String.valueOf(number + 1) + "\n");
                output.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}