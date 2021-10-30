import java.util.LinkedHashSet;

public class GameLogic {

    private LinkedHashSet<Card> link;

    public GameLogic(){

        link = getAllCards();
    }

    public LinkedHashSet<Card> getAllCards(){

        String suit;
        link = new LinkedHashSet<>();

        for (int i = 0; i < 4; i++) {
            switch (i){
                case 0:
                    suit = "spades";
                    link.add(new Card(CardNames.ACE, suit));
                    link.add(new Card(CardNames.KING, suit));
                    link.add(new Card(CardNames.QUEEN, suit));
                    link.add(new Card(CardNames.JACK, suit));
                    link.add(new Card(CardNames.TEN, suit));
                    link.add(new Card(CardNames.NINE, suit));
                    link.add(new Card(CardNames.EIGHT, suit));
                    link.add(new Card(CardNames.SEVEN, suit));
                    link.add(new Card(CardNames.SIX, suit));
                    link.add(new Card(CardNames.FIVE, suit));
                    link.add(new Card(CardNames.FOUR, suit));
                    link.add(new Card(CardNames.THREE, suit));
                    link.add(new Card(CardNames.TWO, suit));
                    break;

                case 1:
                    suit = "clubs";
                    link.add(new Card(CardNames.ACE, suit));
                    link.add(new Card(CardNames.KING, suit));
                    link.add(new Card(CardNames.QUEEN, suit));
                    link.add(new Card(CardNames.JACK, suit));
                    link.add(new Card(CardNames.TEN, suit));
                    link.add(new Card(CardNames.NINE, suit));
                    link.add(new Card(CardNames.EIGHT, suit));
                    link.add(new Card(CardNames.SEVEN, suit));
                    link.add(new Card(CardNames.SIX, suit));
                    link.add(new Card(CardNames.FIVE, suit));
                    link.add(new Card(CardNames.FOUR, suit));
                    link.add(new Card(CardNames.THREE, suit));
                    link.add(new Card(CardNames.TWO, suit));
                    break;

                case 2:
                    suit = "hearts";
                    link.add(new Card(CardNames.ACE, suit));
                    link.add(new Card(CardNames.KING, suit));
                    link.add(new Card(CardNames.QUEEN, suit));
                    link.add(new Card(CardNames.JACK, suit));
                    link.add(new Card(CardNames.TEN, suit));
                    link.add(new Card(CardNames.NINE, suit));
                    link.add(new Card(CardNames.EIGHT, suit));
                    link.add(new Card(CardNames.SEVEN, suit));
                    link.add(new Card(CardNames.SIX, suit));
                    link.add(new Card(CardNames.FIVE, suit));
                    link.add(new Card(CardNames.FOUR, suit));
                    link.add(new Card(CardNames.THREE, suit));
                    link.add(new Card(CardNames.TWO, suit));
                    break;

                case 3:
                    suit = "diamonds";
                    link.add(new Card(CardNames.ACE, suit));
                    link.add(new Card(CardNames.KING, suit));
                    link.add(new Card(CardNames.QUEEN, suit));
                    link.add(new Card(CardNames.JACK, suit));
                    link.add(new Card(CardNames.TEN, suit));
                    link.add(new Card(CardNames.NINE, suit));
                    link.add(new Card(CardNames.EIGHT, suit));
                    link.add(new Card(CardNames.SEVEN, suit));
                    link.add(new Card(CardNames.SIX, suit));
                    link.add(new Card(CardNames.FIVE, suit));
                    link.add(new Card(CardNames.FOUR, suit));
                    link.add(new Card(CardNames.THREE, suit));
                    link.add(new Card(CardNames.TWO, suit));
                    break;

                default:
                    System.out.println("Error in creating full set of cards");
                    break;

            }
        }

        return link;

    }

    public static void main(String[] args) {

        GameLogic game = new GameLogic();

        game = null;

    }

}
