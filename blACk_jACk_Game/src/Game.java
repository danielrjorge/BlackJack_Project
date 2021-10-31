import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Game {

    private LinkedList<Card> dealerHand;

    private LinkedHashMap<Integer, Card> fullDeck, gameDeck;
    private LinkedList<Player> players;
    private int counter = 52;
    public final int MAXPOINTS = 21;
    private int dealerPoints = 0;
    private boolean dealerBust;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";

    public Game(LinkedList<Player> players) {

        this.fullDeck = getAllCards();
        this.gameDeck = getAllCards();
        this.dealerHand = new LinkedList<>();
        this.players = players;

    }

    public void startGame() throws InterruptedException {
        for (Player player : players) {
            while (player.getName() == null || player.isPlayAlone() == -1 ) {

            }

        }

        while(players.size() > 0) {
            startRound();
        }

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

    public void startRound() throws InterruptedException {
        LinkedList<Thread> threadList = new LinkedList<>();

        makeBets(threadList);

        distributeHands();

        Thread.sleep(2000);
        //need to broadcast hands
        showDealerFirstCard();

        Thread.sleep(2000);
        showHands();

        playHands(threadList);

        Thread.sleep(2000);

        showDealerSecondCard();

        Thread.sleep(2000);

        dealerLogic();

        Thread.sleep(2000);

        broadcastTotalChips();

        Thread.sleep(2000);

        prepareNextRound();

        Thread.sleep(2000);

    }

    public LinkedHashMap<Integer, Card> getAllCards() {

        String suit;
        fullDeck = new LinkedHashMap<>();

        int counter = 1;

        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    suit =ANSI_GREEN + "SPADES ♠ " + ANSI_RESET;
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
                    suit =ANSI_GREEN + "CLUBS ♣ " + ANSI_RESET;
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
                    suit = ANSI_RED + "HEARTS ♥ " + ANSI_RESET;
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
                    suit =ANSI_RED + "DIAMONDS ♦ " + ANSI_RESET;
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

    public void prepareNextRound() {
        for (Player player : players) {
            player.resetBust();
            player.resetPoints();
            player.resetPlayerHand();
        }
        dealerBust = false;
        dealerPoints = 0;
        dealerHand = new LinkedList<>();
        gameDeck = fullDeck;
    }

    public void comparePoints() {
        for (Player player : players) {

            player.getPrintStream().println(ANSI_YELLOW + "\nDealer total points is: " + dealerPoints + ANSI_RESET);

            broadcastTotalPoints(player);

            if (player.isBust()) {
                broadcastMessage(ANSI_CYAN + player.getName() + " is bust, no reward for you!" + ANSI_RESET);
            } else if (player.getPoints() > dealerPoints || dealerBust) {
                player.setChips(player.getChips() + player.getBet() * 2);
                broadcastMessage(ANSI_CYAN + player.getName() + " won the hand! He got " + player.getBet() * 2 + " chips!" + ANSI_RESET);
            } else if (player.getPoints() == dealerPoints) {
                player.setChips(player.getChips() + player.getBet());
                broadcastMessage(ANSI_CYAN + player.getName() + " tied with dealer! He got his chips back!" + ANSI_RESET);
            } else {
                broadcastMessage(ANSI_CYAN + player.getName() + " lost to dealer! He lost " + player.getBet() + " chips!" + ANSI_RESET);
            }
        }
    }

    public void showHands() {
        for (Player player : players) {
            for (Player each : players) {
                player.getPrintStream().println(ANSI_CYAN + "\n" + each.getName() + " hand is:" + ANSI_RESET);
                for (Card card : each.getPlayerHand()) {
                    player.getPrintStream().println(ANSI_CYAN + card.getCardName() + " of " + card.getSuit() + ANSI_RESET);
                }
                player.getPrintStream().println(ANSI_CYAN + "\n" + each.getName() + " total points: " + each.getPoints() + ANSI_RESET);
            }
        }
    }

    public void broadcastMessage(String message) {
        for (Player player : players) {
            player.getPrintStream().println(message);
        }
    }

    public void broadcastTotalChips() {
        for (Player player : players) {
            for (Player each : players) {
                player.getPrintStream().println(ANSI_CYAN + "\n" + each.getName() + " total chips is: " + each.getChips() + ANSI_RESET);
            }
        }
    }

    public void broadcastTotalPoints(Player player) {
        for (Player play : players) {
            player.getPrintStream().println(ANSI_CYAN + "\n" + play.getName() + " total points is: " + play.getPoints() + ANSI_RESET);
        }
    }

    public void showDealerFirstCard() {

        for (Player player : players) {
            player.getPrintStream().println(ANSI_YELLOW + "\nThe dealer's first card is:\n"
                    + dealerHand.get(0).getCardName()
                    + " of " + dealerHand.get(0).getSuit() + ANSI_RESET);
        }
    }

    public void showDealerSecondCard() {

        for (Player player : players) {
            player.getPrintStream().println( ANSI_YELLOW +"\nThe dealer's second card is:\n"
                    + dealerHand.get(1).getCardName()
                    + " of " + dealerHand.get(1).getSuit()
                    + ANSI_YELLOW + "\n Dealer's point total:" + dealerPoints + ANSI_RESET);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void dealerLogic() {

        while (!dealerBust && dealerPoints < 17) {

            addCardAndRemoveFromDeckDealer();

            //change ace value to 1
            for(Card card: dealerHand){
                if(card.getCardName() == CardNames.ACE && dealerPoints > 21){
                    card.setCardPoints(1);
                }
            }

            Card latestDealerCard = dealerHand.get(dealerHand.size() - 1);
            broadcastMessage(ANSI_YELLOW + "Dealer drew card: " + latestDealerCard.getCardName() + " of " + latestDealerCard.getSuit() + ANSI_RESET);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (dealerPoints > MAXPOINTS) {
                dealerBust = true;
            }

        }

        comparePoints();

    }

    public void hit(Player player) {
        addCardAndRemoveFromDeck(player);
        Card thisCard = player.getPlayerHand().get(player.getPlayerHand().size() - 1);
        player.getPrintStream().println(ANSI_CYAN + "You got the card "
                + thisCard.getCardName() + " of " + thisCard.getSuit() + ANSI_RESET);

    }

    public void stay(Player player) {
        player.getPrintStream().print(ANSI_CYAN + player.getName() + " has stayed\n" + ANSI_RESET);
        player.setHasStood();

    }

    public void doubleHit(Player player) {
        addCardAndRemoveFromDeck(player);
        player.setBet(player.getBet() * 2);
        player.setChips(player.getChips() - player.getBet());
        player.setHasStood();

    }

    public void isBust(Player player) {
        if (player.getPoints() > MAXPOINTS) {
            player.setBust();
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
        System.out.println(ANSI_CYAN + currentCard.getCardName() + " of " + currentCard.getSuit() + ANSI_RESET);
    }

    public void addCardAndRemoveFromDeckDealer() {

        Integer[] remaining = new Integer[gameDeck.size()];
        gameDeck.keySet().toArray(remaining);

        int remainingCards = remaining[Randomizer.getNumber(remaining.length) - 1];

        Card currentCard = gameDeck.get(remainingCards);
        gameDeck.remove(remainingCards);
        addToDealerHand(currentCard);
        dealerPoints += currentCard.getCardPoints();
        System.out.println(ANSI_YELLOW + currentCard.getCardName() + " of " + currentCard.getSuit() + ANSI_RESET);
    }

    private void makeBets(LinkedList<Thread> threadList) {
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


        while (threadList.size() != 0) {
            threadList.remove(0);
        }
    }

    private void playHands(LinkedList<Thread> threadList) {
        for (int i = 0; i < players.size(); i++) {
            threadList.add(new Thread(new PlayHand(players.get(i), this)));
            threadList.get(i).start();
        }

        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
