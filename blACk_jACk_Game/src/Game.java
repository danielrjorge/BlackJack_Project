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

            broadcastMessage("Dealer final hand points are: " + dealerPoints);

            if (player.isBust()) {
                broadcastMessage(player.getName() + " is bust, no reward for you!");
            } else if (player.getPoints() > dealerPoints || dealerBust) {
                player.setChips(player.getChips() + player.getBet() * 2);
                broadcastMessage(player.getName() + " won the hand! He got " + player.getBet() + " chips!");
            } else if (player.getPoints() == dealerPoints) {
                player.setChips(player.getChips() + player.getBet());
                broadcastMessage(player.getName() + " tied with dealer! He got his chips back!");
            } else {
                broadcastMessage(player.getName() + " lost to dealer! He lost " + player.getBet() + " chips!");
            }
        }
    }

    public void showHands() {
        for (Player player : players) {
            for (Player each : players) {
                player.getPrintStream().println("\n" + each.getName() + " hand is:");
                for (Card card : each.getPlayerHand()) {
                    player.getPrintStream().println(card.getCardName() + " of " + card.getSuit() + " -> " + card.getCardPoints());
                }
                player.getPrintStream().println("\n" + each.getName() + " total points: " + each.getPoints());
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
                player.getPrintStream().println("\n" + each.getName() + " total chips is: " + each.getChips());
            }
        }
    }

    public void showDealerFirstCard() {

        for (Player player : players) {
            player.getPrintStream().println("\nThe dealer's first card is:\n"
                    + dealerHand.get(0).getCardName()
                    + " of " + dealerHand.get(0).getSuit() + " -> " + dealerHand.get(0).getCardPoints());
        }
    }

    public void showDealerSecondCard() {

        for (Player player : players) {
            player.getPrintStream().println("\nThe dealer's second card is:\n"
                    + dealerHand.get(1).getCardName()
                    + " of " + dealerHand.get(1).getSuit()+ " -> " + dealerHand.get(1).getCardPoints() +"\n");
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void broadcastDealerCards(){

        broadcastMessage("Dealer cards are: ");
        for(Card card: dealerHand) {
            broadcastMessage(card.getCardName() + " of " + card.getSuit() + " -> " + card.getCardPoints());
        }
        broadcastMessage("Dealer hand points: " + dealerPoints + "\n");

    }

    public void dealerLogic() {

        broadcastDealerCards();

        while (!dealerBust && dealerPoints < 17) {

            addCardAndRemoveFromDeckDealer();

            Card latestDealerCard = dealerHand.get(dealerHand.size() - 1);
            broadcastMessage("Dealer drew card: " + latestDealerCard.getCardName() + " of " + latestDealerCard.getSuit() + " -> " + latestDealerCard.getCardPoints() + "\n");


            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            broadcastDealerCards();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (dealerPoints > MAXPOINTS) {
                dealerBust = true;
            }

        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        comparePoints();



    }


    public void hit(Player player) {
        addCardAndRemoveFromDeck(player);
        Card thisCard = player.getPlayerHand().get(player.getPlayerHand().size() - 1);
        player.getPrintStream().println("You got the card "
                + thisCard.getCardName() + " of " + thisCard.getSuit() + " -> " + thisCard.getCardPoints() + "\nYour total points are " + player.getPoints());

    }

    public void stay(Player player) {
        player.getPrintStream().print(player.getName() + " has stayed");
        player.setHasStood();
    }

    public void doubleHit(Player player) {
        addCardAndRemoveFromDeck(player);
        player.setChips(player.getChips() - player.getBet());
        player.setBet(player.getBet() * 2);
        player.setHasStood();

        Card thisCard = player.getPlayerHand().get(player.getPlayerHand().size() - 1);
        player.getPrintStream().println("You got the card "
                + thisCard.getCardName() + " of " + thisCard.getSuit() + " -> " + thisCard.getCardPoints() + "\nYour total points are " + player.getPoints());

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
        addToDealerHand(currentCard);
        dealerPoints += currentCard.getCardPoints();
        System.out.println(currentCard.getCardName() + " of " + currentCard.getSuit());
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
