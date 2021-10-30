import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Game {

    private LinkedList<Card> dealerHand;

    private LinkedHashMap<Integer, Card> fullDeck, gameDeck;
    private LinkedList<Player> players;
    private int counter = 52;
    private final int MAXPOINTS = 21;
    private int dealerPoints = 0;

    public Game(LinkedList<Player> players) {

        this.fullDeck = getAllCards();
        this.gameDeck = getAllCards();
        this.dealerHand = new LinkedList<>();
        this.players = players;

    }

    public void startGame() {
        for (Player player : players) {
            while (player.getName() == null) {

            }

        }
        startRound();

    }

    public void distributeHands() {

        System.out.println("Dealer hand:");

        for (int i = 0; i < 2; i++) {

            addCardAndRemoveFromDeckDealer();
        }

        for (Player player : players) {

            System.out.println("player hand:");

            for (int i = 0; i < 2; i++) {
                addCardAndRemoveFromDeck(player);
            }

        }

    }

    public void startRound() {
        Thread playerPlay = new Thread();
        LinkedList<Thread> threadList = new LinkedList<>();

        for (int i = 0; i < players.size(); i++) {

            threadList.add(new Thread(new Bet(players.get(i))));
            threadList.get(i).start();

        }
        for (int i = 0; i < threadList.size(); i++) {
            try {
                threadList.get(i).join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


        while (threadList.size() != 0){
            threadList.remove(0);
        }
        distributeHands();
        //need to broadcast hands
        showHands();

        for (int i = 0; i < players.size(); i++) {
            threadList.add(new Thread(new PlayHand(players.get(i),this)));
            threadList.get(i).start();
        }



        for (Thread thread: threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public LinkedHashMap<Integer, Card> getAllCards() {

        String suit;
        fullDeck = new LinkedHashMap<>();

        int counter = 1;

        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    suit = "SPADES";
                    fullDeck.put(counter++, new Card(CardNames.ACE, suit));
                    fullDeck.put(counter++, new Card(CardNames.KING, suit));
                    fullDeck.put(counter++, new Card(CardNames.QUEEN, suit));
                    fullDeck.put(counter++, new Card(CardNames.JACK, suit));
                    fullDeck.put(counter++, new Card(CardNames.TEN, suit));
                    fullDeck.put(counter++, new Card(CardNames.NINE, suit));
                    fullDeck.put(counter++, new Card(CardNames.EIGHT, suit));
                    fullDeck.put(counter++, new Card(CardNames.SEVEN, suit));
                    fullDeck.put(counter++, new Card(CardNames.SIX, suit));
                    fullDeck.put(counter++, new Card(CardNames.FIVE, suit));
                    fullDeck.put(counter++, new Card(CardNames.FOUR, suit));
                    fullDeck.put(counter++, new Card(CardNames.THREE, suit));
                    fullDeck.put(counter++, new Card(CardNames.TWO, suit));
                    break;

                case 1:
                    suit = "CLUBS";
                    fullDeck.put(counter++, new Card(CardNames.ACE, suit));
                    fullDeck.put(counter++, new Card(CardNames.KING, suit));
                    fullDeck.put(counter++, new Card(CardNames.QUEEN, suit));
                    fullDeck.put(counter++, new Card(CardNames.JACK, suit));
                    fullDeck.put(counter++, new Card(CardNames.TEN, suit));
                    fullDeck.put(counter++, new Card(CardNames.NINE, suit));
                    fullDeck.put(counter++, new Card(CardNames.EIGHT, suit));
                    fullDeck.put(counter++, new Card(CardNames.SEVEN, suit));
                    fullDeck.put(counter++, new Card(CardNames.SIX, suit));
                    fullDeck.put(counter++, new Card(CardNames.FIVE, suit));
                    fullDeck.put(counter++, new Card(CardNames.FOUR, suit));
                    fullDeck.put(counter++, new Card(CardNames.THREE, suit));
                    fullDeck.put(counter++, new Card(CardNames.TWO, suit));
                    break;

                case 2:
                    suit = "HEARTS";
                    fullDeck.put(counter++, new Card(CardNames.ACE, suit));
                    fullDeck.put(counter++, new Card(CardNames.KING, suit));
                    fullDeck.put(counter++, new Card(CardNames.QUEEN, suit));
                    fullDeck.put(counter++, new Card(CardNames.JACK, suit));
                    fullDeck.put(counter++, new Card(CardNames.TEN, suit));
                    fullDeck.put(counter++, new Card(CardNames.NINE, suit));
                    fullDeck.put(counter++, new Card(CardNames.EIGHT, suit));
                    fullDeck.put(counter++, new Card(CardNames.SEVEN, suit));
                    fullDeck.put(counter++, new Card(CardNames.SIX, suit));
                    fullDeck.put(counter++, new Card(CardNames.FIVE, suit));
                    fullDeck.put(counter++, new Card(CardNames.FOUR, suit));
                    fullDeck.put(counter++, new Card(CardNames.THREE, suit));
                    fullDeck.put(counter++, new Card(CardNames.TWO, suit));
                    break;

                case 3:
                    suit = "DIAMONDS";
                    fullDeck.put(counter++, new Card(CardNames.ACE, suit));
                    fullDeck.put(counter++, new Card(CardNames.KING, suit));
                    fullDeck.put(counter++, new Card(CardNames.QUEEN, suit));
                    fullDeck.put(counter++, new Card(CardNames.JACK, suit));
                    fullDeck.put(counter++, new Card(CardNames.TEN, suit));
                    fullDeck.put(counter++, new Card(CardNames.NINE, suit));
                    fullDeck.put(counter++, new Card(CardNames.EIGHT, suit));
                    fullDeck.put(counter++, new Card(CardNames.SEVEN, suit));
                    fullDeck.put(counter++, new Card(CardNames.SIX, suit));
                    fullDeck.put(counter++, new Card(CardNames.FIVE, suit));
                    fullDeck.put(counter++, new Card(CardNames.FOUR, suit));
                    fullDeck.put(counter++, new Card(CardNames.THREE, suit));
                    fullDeck.put(counter++, new Card(CardNames.TWO, suit));
                    break;

                default:
                    System.out.println("Error in creating full set of cards");
                    break;

            }
        }

        return fullDeck;

    }

    public void addToDealerHand(Card card) {
        dealerHand.add(card);
    }

    public void setPlayers(LinkedList<Player> players) {
        this.players = players;
    }


    public void showHands() {
        for (Player player : players) {
            for (Player each : players) {
                player.getPrintStream().println("\n" + each.getName() + " hand is:");
                for (Card card : each.getPlayerHand()) {
                    player.getPrintStream().println(card.getCardName() + " of " + card.getSuit());
                }
            }
        }
    }

    public void hit(Player player) {
        addCardAndRemoveFromDeck(player);
    }

    public void hitDealer() {
        addCardAndRemoveFromDeckDealer();
    }
    
    public  void stay(Player player){
        player.getPrintStream().print(player.getName() + " has stayed");
        
    }

    public boolean isBust(Player player){
        if(player.getPoints() > MAXPOINTS){

            return true;
        } else {
            return false;
        }
    }

    public void addCardAndRemoveFromDeck(Player player) {

        Integer[] remaining = new Integer[gameDeck.size()];
        gameDeck.keySet().toArray(remaining);

        int remainingCards = remaining[Randomizer.getNumber(remaining.length) - 1];

        Card currentCard = gameDeck.get(remainingCards);
        player.sumPoints(currentCard.getCardPoints());
        gameDeck.remove(remainingCards);
        player.addToHand(currentCard);
        System.out.println(currentCard.getCardName() + " of " + currentCard.getSuit());
    }

    public void addCardAndRemoveFromDeckDealer() {

        Integer[] remaining = new Integer[gameDeck.size()];
        gameDeck.keySet().toArray(remaining);

        int remainingCards = remaining[Randomizer.getNumber(remaining.length) - 1];

        Card currentCard = gameDeck.get(remainingCards);
        gameDeck.remove(remainingCards);
        dealerPoints += currentCard.getCardPoints();
        addToDealerHand(currentCard);
        System.out.println(currentCard.getCardName() + " of " + currentCard.getSuit());
    }

}
