public class serverTest {
    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.listen();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
