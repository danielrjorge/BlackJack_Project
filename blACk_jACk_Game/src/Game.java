import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Game {

    private LinkedList<Card> dealerHand;

    private LinkedHashMap<Integer,Card> fullDeck, gameDeck;
    private LinkedList<Player> players;
    private int counter = 52;
    private Prompt prompt;

    public Game(LinkedList<Player> players, Prompt prompt){

        this.fullDeck = getAllCards();
        this.gameDeck = fullDeck;
        this.dealerHand = new LinkedList<>();
        this.players = players;
        this.prompt = prompt;

    }

    public void startGame(){

        startRound();

    }

    public void distributeHands(){

        System.out.println("Dealer hand:");

        for (int i = 0; i < 2; i++) {

            Integer[] remaining = new Integer[gameDeck.size()];
            gameDeck.keySet().toArray(remaining);

            int cardForDealer = remaining[Randomizer.getNumber(remaining.length)-1];

            Card currentCard = gameDeck.get(cardForDealer);
            gameDeck.remove(cardForDealer);

            addToDealerHand(currentCard);
            System.out.println(currentCard.getCardName() + " of " + currentCard.getSuit());
        }

        for(Player player: players){

            System.out.println("player hand:");

            for (int i = 0; i < 2; i++) {

                Integer[] remaining = new Integer[gameDeck.size()];
                gameDeck.keySet().toArray(remaining);

                int cardForPlayer = remaining[Randomizer.getNumber(remaining.length)-1];

                Card currentCard = gameDeck.get(cardForPlayer);
                gameDeck.remove(cardForPlayer);
                player.addToHand(currentCard);
                System.out.println(currentCard.getCardName() + " of " + currentCard.getSuit());
            }

        }

    }

    public void startRound(){

        for(Player player: players){
            int chipsBet;
            IntegerInputScanner scanner = new IntegerInputScanner();
            scanner.setMessage("Place your bet - Total chips available -> " + player.getChips());

            while(true) {
                chipsBet = prompt.getUserInput(scanner);

                if(chipsBet > player.getChips()){
                    scanner.setMessage("Not enough chips for this bet, try again");
                } else {
                    break;
                }
            }

            player.setBet(chipsBet);
            player.setChips(player.getChips()-chipsBet);
        }

        distributeHands();
        //need to broadcast hands



    }

    public LinkedHashMap<Integer, Card> getAllCards(){

        String suit;
        fullDeck = new LinkedHashMap<>();

        int counter = 1;

        for (int i = 0; i < 4; i++) {
            switch (i){
                case 0:
                    suit = "spades";
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
                    suit = "clubs";
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
                    suit = "hearts";
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
                    suit = "diamonds";
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

    public void addToDealerHand(Card card){
        dealerHand.add(card);
    }

}
