public class Card {

    private int cardPoints;
    private String suit;
    private CardNames cardName;

    public Card(CardNames card, String suit){
        this.cardName = card;
        this.suit = suit;
    }
}
