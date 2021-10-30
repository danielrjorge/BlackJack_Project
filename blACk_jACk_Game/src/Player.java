import java.net.Socket;
import java.util.LinkedList;

public class Player extends Client {

    private LinkedList<Card> playerHand;
    private String name;

    //for tests
    public Player(){
        super(new Socket(), new Server());
        playerHand = new LinkedList<>();
    }

    public Player(Socket socket, Server server) {
        super(socket, new Server());
        playerHand = new LinkedList<>();
    }

    @Override
    public void run() {
        super.run();
    }

    public void addToHand(Card card) {
        playerHand.add(card);
    }

    public LinkedList<Card> getPlayerHand() {
        return playerHand;
    }
}
