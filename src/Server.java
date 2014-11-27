import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Avetisyan Sevak on 27.11.2014.
 */

public class Server implements Runnable {
    private int serverPort = 8080;
    private ServerSocket serverSocket = null;
    private boolean isStopped = false;
    private Thread runningThread;

    public Server(int port) {
        this.serverPort = port;
    }

    public void run() {
        synchronized (this) {
            this.runningThread = Thread.currentThread();
        }
        openServerSocket();

        while (!isStopped()) {
            Socket clientSocket;

            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                if (isStopped()) {
                    System.out.println("Server stopped.");
                    return;
                }

                throw new RuntimeException("Error accepting client connection", e);
            }
            try {
                processClientRequest(clientSocket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Server stopped.");
    }

    private void processClientRequest(Socket clientSocket) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        long time = System.currentTimeMillis();

        Integer number;
        while((number = Integer.parseInt(input.readLine())) <= 1000) {
            output.write(String.valueOf(number + 1) + "\n");
            output.flush();
        }

        output.close();
        input.close();

        System.out.println("Requests processed: " + time);
    }

    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop() {
        this.isStopped = true;

        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Can't open port 8080", e);
        }
    }
}