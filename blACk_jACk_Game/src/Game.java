import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Game {

    private LinkedList<Card> dealerHand;

    private LinkedHashMap<Integer, Card> fullDeck, gameDeck;
    private LinkedList<Player> players;
    public final int MAXPOINTS = 21;
    private int dealerPoints = 0;
    private boolean dealerBust, dealerBlackJack;
    public static String ANSI_RED = "";
    public static String ANSI_GREEN = "";
    public static String ANSI_YELLOW = "";
    public static String ANSI_CYAN = "";
    public static String ANSI_RESET = "";

    public static String SPADES_ICON = "";
    public static String CLUBS_ICON = "";
    public static String HEARTS_ICON = "";
    public static String DIAMONDS_ICON = "";

    private boolean isWindows;

    public Game(LinkedList<Player> players) {
        this.fullDeck = getAllCards();
        this.gameDeck = getAllCards();
        this.dealerHand = new LinkedList<>();
        this.players = players;
        this.isWindows = isWindows();
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

        addCardAndRemoveFromDeckDealer();
        addCardAndRemoveFromDeckDealer();

        //check if double ace
        for(Card card: dealerHand){
            if(card.getCardName() == CardNames.ACE && dealerPoints > 21){
                card.setCardPoints(1);
            }
        }

        for (Player player : players) {

            addCardAndRemoveFromDeck(player);
            addCardAndRemoveFromDeck(player);

            for(Card card: player.getPlayerHand()){
                if(card.getCardName() == CardNames.ACE && dealerPoints > 21){
                    card.setCardPoints(1);
                }
            }
        }
    }

    public void startRound() throws InterruptedException {
        LinkedList<Thread> threadList = new LinkedList<>();

        makeBets(threadList);

        distributeHands();
        
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
                    suit = ANSI_GREEN + "SPADES " + SPADES_ICON + ANSI_RESET;
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
                    suit = ANSI_GREEN + "CLUBS " + CLUBS_ICON + ANSI_RESET;
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
                    suit = ANSI_RED + "HEARTS " + HEARTS_ICON + ANSI_RESET;
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
                    suit =ANSI_RED + "DIAMONDS " + DIAMONDS_ICON + ANSI_RESET;
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
            player.resetStood();
            player.setBlackJack(false);
        }
        dealerBust = false;
        dealerBlackJack = false;
        dealerPoints = 0;
        dealerHand = new LinkedList<>();
        gameDeck = fullDeck;
    }

    public void comparePoints() {
        for (Player player : players) {

            player.getPrintStream().println(ANSI_YELLOW + "\nDealer total points is: " + dealerPoints + ANSI_RESET + "\n");

            broadcastTotalPoints(player);

            if (player.isBust()) {
                broadcastMessage(ANSI_CYAN + player.getName() + " is bust, no reward for you!" + ANSI_RESET + "\n");
            } else if (player.hasBlackJack() && !dealerBlackJack){
                player.setChips(player.getChips() + player.getBet() * 3);
                broadcastMessage(ANSI_CYAN + player.getName() + " won the hand with a BLACKJACK! He got " + player.getBet() * 3 + " chips!" + ANSI_RESET + "\n");
            } else if(player.hasBlackJack() && dealerBlackJack){
                player.setChips(player.getChips() + player.getBet());
                broadcastMessage(ANSI_CYAN + player.getName() + " tied with dealer! He got his chips back!" + ANSI_RESET + "\n");
            }
            else if (player.getPoints() > dealerPoints || dealerBust) {
                player.setChips(player.getChips() + player.getBet() * 2);
                broadcastMessage(ANSI_CYAN + player.getName() + " won the hand! He got " + player.getBet() * 2 + " chips!" + ANSI_RESET + "\n");
            } else if (player.getPoints() == dealerPoints) {
                player.setChips(player.getChips() + player.getBet());
                broadcastMessage(ANSI_CYAN + player.getName() + " tied with dealer! He got his chips back!" + ANSI_RESET + "\n");
            } else {
                broadcastMessage(ANSI_CYAN + player.getName() + " lost to dealer! He lost " + player.getBet() + " chips!" + ANSI_RESET + "\n");
            }
        }
    }

    public void showHands() {
        for (Player player : players) {
            for (Player each : players) {

                //covered for double ace scenario at first hand
                int tempPoints = each.getPoints();
                if(each.getPoints() == 22){
                    tempPoints = 12;
                }
                player.getPrintStream().println(ANSI_CYAN + "\n" + each.getName() + " hand is:" + ANSI_RESET);
                for (Card card : each.getPlayerHand()) {
                    player.getPrintStream().println(card.getCardName()+ ANSI_CYAN  +  " of " + card.getSuit() + ANSI_RESET);
                }
                player.getPrintStream().println(ANSI_CYAN + "\n" + each.getName() + " total points: " + tempPoints + ANSI_RESET);
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
                player.getPrintStream().println(ANSI_CYAN + each.getName() + " total chips is: " + each.getChips() + ANSI_RESET + "\n");
            }
        }
    }

    public void broadcastTotalPoints(Player player) {
        for (Player play : players) {
            player.getPrintStream().println(ANSI_CYAN + play.getName() + " total points is: " + play.getPoints() + ANSI_RESET + "\n");
        }
    }

    public void showDealerFirstCard() {
        for (Player player : players) {
            player.getPrintStream().println(ANSI_YELLOW + "\nThe dealer's first card is:\n" + ANSI_RESET
                    + dealerHand.get(0).getCardName() + ANSI_YELLOW
                    + " of " + dealerHand.get(0).getSuit() + ANSI_RESET);
        }
    }

    public void showDealerSecondCard() {

        //covered double ace scenario
        int tempDealerPoints = dealerPoints;
        if(dealerPoints == 22){
            tempDealerPoints = 12;
        }
        for (Player player : players) {
            player.getPrintStream().println( ANSI_YELLOW +"\nThe dealer's second card is:\n"
                    + dealerHand.get(1).getCardName()
                    + " of " + dealerHand.get(1).getSuit()
                    + ANSI_YELLOW + "\nDealer's point total:" + tempDealerPoints + ANSI_RESET);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void dealerLogic() {

        if(dealerPoints == 21){
            dealerBlackJack = true;
            broadcastMessage("Dealer got BLACKJACK!");
            comparePoints();
            return;
        }

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
        player.setChips(player.getChips() - player.getBet());
        player.setBet(player.getBet() * 2);
        player.setHasStood();
        Card thisCard = player.getPlayerHand().get(player.getPlayerHand().size() - 1);
        player.getPrintStream().println("You got the card "
                + thisCard.getCardName() + " of " + thisCard.getSuit());
    }

    public void addCardAndRemoveFromDeck(Player player) {

        Integer[] remaining = new Integer[gameDeck.size()];
        gameDeck.keySet().toArray(remaining);

        int remainingCards = remaining[Randomizer.getNumber(remaining.length) - 1];

        Card currentCard = gameDeck.get(remainingCards);
        player.sumPoints(currentCard.getCardPoints());
        gameDeck.remove(remainingCards);
        player.addToHand(currentCard);
    }

    public void addCardAndRemoveFromDeckDealer() {

        Integer[] remaining = new Integer[gameDeck.size()];
        gameDeck.keySet().toArray(remaining);

        int remainingCards = remaining[Randomizer.getNumber(remaining.length) - 1];

        Card currentCard = gameDeck.get(remainingCards);
        gameDeck.remove(remainingCards);
        addToDealerHand(currentCard);
        dealerPoints += currentCard.getCardPoints();
    }

    private void makeBets(LinkedList<Thread> threadList) {
        for (int i = 0; i < players.size(); i++) {

            threadList.add(new Thread(new Bet(players.get(i),this)));
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

    public LinkedList<Player> getPlayers() {
        return players;
    }

    public boolean isWindows() {
        if(System.getProperty("os.name").startsWith("Windows")){
            return true;
        }
        else{
            setColorsAndIcons();
            return false;
        }
    }

    public void setColorsAndIcons(){
        //sets colors and icons to the terminal if client is not on Windows OS
        if(!isWindows()){
            ANSI_RED = "\u001B[31m";
            ANSI_GREEN = "\u001B[32m";
            ANSI_YELLOW = "\u001B[33m";
            ANSI_CYAN = "\u001B[36m";
            ANSI_RESET = "\u001B[0m";
            SPADES_ICON = "♠ ";
            CLUBS_ICON = "♣ ";
            HEARTS_ICON = "♥ ";
            DIAMONDS_ICON = "♦ ";
        }
    }


}
