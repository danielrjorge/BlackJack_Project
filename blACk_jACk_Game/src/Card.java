public class Card {

    private int cardPoints;
    private String suit;
    private CardNames cardName;

    public Card(CardNames card, String suit){
        this.cardName = card;
        this.suit = suit;
        this.cardPoints = card.getPoints();
    }

    public String getSuit() {
        return suit;
    }

    public CardNames getCardName() {
        return cardName;
    }

    public int getCardPoints() {
        return cardPoints;
    }
}
