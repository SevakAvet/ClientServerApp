public class Main {
    public static void main(String[] args) {
        Server server = new Server(8080);
        new Thread(server).start();

        for (int i = 0; i < 10000; i++) {
            Client client = new Client("localhost", 8080, i + 1);
            new Thread(client).start();
        }
    }
}
