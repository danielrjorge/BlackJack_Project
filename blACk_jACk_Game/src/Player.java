import java.net.Socket;
import java.util.LinkedList;

public class Player extends Client {

    private LinkedList<Card> playerHand;
    private String name;
    private int chips;
    private int bet;
    private int points = 0;
    private boolean isBust;
    private boolean hasStood;

    //for tests
    public Player(){
        super(new Socket(), new Server());
        playerHand = new LinkedList<>();
        this.chips = 500;
    }

    //for real
    public Player(Socket socket, Server server) {
        super(socket, server);
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

    public void resetPlayerHand(){
        this.playerHand = new LinkedList<>();
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

    public void sumPoints(int points) {
        this.points += points;
    }

    public int getPoints() {
        return points;
    }

    public void resetPoints(){
        this.points = 0;
    }

    public boolean isBust() {
        return isBust;
    }

    public boolean isHasStood() {
        return hasStood;
    }

    public void setBust() {
        isBust = true;
    }

    public void resetBust() {
        isBust = false;
    }

    public void setHasStood() {
        hasStood = true;
    }
}
