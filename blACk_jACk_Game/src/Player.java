import java.net.Socket;
import java.util.LinkedList;

public class Player extends Client {

    LinkedList<Card> playerHand;

    public Player(Socket socket) {
        super(socket);
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
