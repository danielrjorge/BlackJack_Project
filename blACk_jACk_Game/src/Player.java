import java.net.Socket;
import java.util.LinkedList;

public class Player extends Client {

    private LinkedList<Card> playerHand;
    private String name;
    private int chips;
    private int bet;

    //for tests
    public Player(){
        super(new Socket(), new Server());
        playerHand = new LinkedList<>();
        this.chips = 500;
    }

    //for real
    public Player(Socket socket, Server server) {
        super(socket, new Server());
        playerHand = new LinkedList<>();
        this.chips = 500;
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

    public void setChips(int chips) {
        this.chips = chips;
    }

    public int getChips() {
        return chips;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public int getBet() {
        return bet;
    }
}
