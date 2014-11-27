public class Main {
    public static void main(String[] args) {
        Server server = new Server(8080);
        new Thread(server).start();

        int N = 210;
        for (int i = 0; i < N; i++) {
            Client client = new Client("localhost", 8080, i + 1);
            new Thread(client).start();
        }
    }
}
