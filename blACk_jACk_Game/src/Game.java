import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Game {

    private LinkedList dealerHand;

    private LinkedHashMap<Card,Integer> deck;

    public Game(){

        deck = getAllCards();

    }

    public void startGame(LinkedList<Player> players){

        for(Player player: players){

        }

    }

    public void startRound(){

    }

    public LinkedHashMap<Card, Integer> getAllCards(){

        String suit;
        deck = new LinkedHashMap<>();

        int counter = 1;

        for (int i = 0; i < 4; i++) {
            switch (i){
                case 0:
                    suit = "spades";
                    deck.put(new Card(CardNames.ACE, suit),counter++);
                    deck.put(new Card(CardNames.KING, suit),counter++);
                    deck.put(new Card(CardNames.QUEEN, suit),counter++);
                    deck.put(new Card(CardNames.JACK, suit),counter++);
                    deck.put(new Card(CardNames.TEN, suit),counter++);
                    deck.put(new Card(CardNames.NINE, suit),counter++);
                    deck.put(new Card(CardNames.EIGHT, suit),counter++);
                    deck.put(new Card(CardNames.SEVEN, suit),counter++);
                    deck.put(new Card(CardNames.SIX, suit),counter++);
                    deck.put(new Card(CardNames.FIVE, suit),counter++);
                    deck.put(new Card(CardNames.FOUR, suit),counter++);
                    deck.put(new Card(CardNames.THREE, suit),counter++);
                    deck.put(new Card(CardNames.TWO, suit),counter++);
                    break;

                case 1:
                    suit = "clubs";
                    deck.put(new Card(CardNames.ACE, suit),counter++);
                    deck.put(new Card(CardNames.KING, suit),counter++);
                    deck.put(new Card(CardNames.QUEEN, suit),counter++);
                    deck.put(new Card(CardNames.JACK, suit),counter++);
                    deck.put(new Card(CardNames.TEN, suit),counter++);
                    deck.put(new Card(CardNames.NINE, suit),counter++);
                    deck.put(new Card(CardNames.EIGHT, suit),counter++);
                    deck.put(new Card(CardNames.SEVEN, suit),counter++);
                    deck.put(new Card(CardNames.SIX, suit),counter++);
                    deck.put(new Card(CardNames.FIVE, suit),counter++);
                    deck.put(new Card(CardNames.FOUR, suit),counter++);
                    deck.put(new Card(CardNames.THREE, suit),counter++);
                    deck.put(new Card(CardNames.TWO, suit),counter++);
                    break;

                case 2:
                    suit = "hearts";
                    deck.put(new Card(CardNames.ACE, suit),counter++);
                    deck.put(new Card(CardNames.KING, suit),counter++);
                    deck.put(new Card(CardNames.QUEEN, suit),counter++);
                    deck.put(new Card(CardNames.JACK, suit),counter++);
                    deck.put(new Card(CardNames.TEN, suit),counter++);
                    deck.put(new Card(CardNames.NINE, suit),counter++);
                    deck.put(new Card(CardNames.EIGHT, suit),counter++);
                    deck.put(new Card(CardNames.SEVEN, suit),counter++);
                    deck.put(new Card(CardNames.SIX, suit),counter++);
                    deck.put(new Card(CardNames.FIVE, suit),counter++);
                    deck.put(new Card(CardNames.FOUR, suit),counter++);
                    deck.put(new Card(CardNames.THREE, suit),counter++);
                    deck.put(new Card(CardNames.TWO, suit),counter++);
                    break;

                case 3:
                    suit = "diamonds";
                    deck.put(new Card(CardNames.ACE, suit),counter++);
                    deck.put(new Card(CardNames.KING, suit),counter++);
                    deck.put(new Card(CardNames.QUEEN, suit),counter++);
                    deck.put(new Card(CardNames.JACK, suit),counter++);
                    deck.put(new Card(CardNames.TEN, suit),counter++);
                    deck.put(new Card(CardNames.NINE, suit),counter++);
                    deck.put(new Card(CardNames.EIGHT, suit),counter++);
                    deck.put(new Card(CardNames.SEVEN, suit),counter++);
                    deck.put(new Card(CardNames.SIX, suit),counter++);
                    deck.put(new Card(CardNames.FIVE, suit),counter++);
                    deck.put(new Card(CardNames.FOUR, suit),counter++);
                    deck.put(new Card(CardNames.THREE, suit),counter++);
                    deck.put(new Card(CardNames.TWO, suit),counter++);
                    break;

                default:
                    System.out.println("Error in creating full set of cards");
                    break;

            }
        }

        return deck;

    }

    public static void main(String[] args) {

        Game game = new Game();
        Server server = new Server();
        server.listen();

        game = null;

    }

}
