import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class StandardDeck {
    private ArrayList<Card> cards;

//    constructor
    public StandardDeck(){
        cards = new ArrayList<>();
        buildDeckOfCards();
    }

//    setters & getters
    public ArrayList<Card> getCards() {
        return cards;
    }
    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }
    public void receiveCard(Card input_card){
        this.cards.add(input_card);
    }

    public Card drawCard(){
        Card dealtCard = this.cards.get(0);
        this.cards.remove(0);
        return dealtCard;
    }

    public void shuffleDeck(){
        Collections.shuffle(this.cards);
    }

    private ArrayList<Card> buildNumberCards(){
        ArrayList<Card> returnList = new ArrayList<>();
        Card two_hearts = new Card("Two", 2, Suits.HEARTS);
        Card three_hearts = new Card("Three", 3, Suits.HEARTS);
        Card four_hearts = new Card("Four", 4, Suits.HEARTS);
        Card five_hearts = new Card("Five", 5, Suits.HEARTS);
        Card six_hearts = new Card("Six", 6, Suits.HEARTS);
        Card seven_hearts = new Card("Seven", 7, Suits.HEARTS);
        Card eight_hearts = new Card("Eight", 8, Suits.HEARTS);
        Card nine_hearts = new Card("Nine", 9, Suits.HEARTS);
        Card ten_hearts = new Card("Ten", 10, Suits.HEARTS);
        Card jack_hearts = new Card("Jack", 10, Suits.HEARTS);
        Card queen_hearts = new Card("Queen", 10, Suits.HEARTS);
        Card king_hearts = new Card("King", 10, Suits.HEARTS);
        Card ace_hearts = new Card("Ace", 11, 1, Suits.HEARTS);
        returnList.add(two_hearts);
        returnList.add(three_hearts);
        returnList.add(four_hearts);
        returnList.add(five_hearts);
        returnList.add(six_hearts);
        returnList.add(seven_hearts);
        returnList.add(eight_hearts);
        returnList.add(nine_hearts);
        returnList.add(ten_hearts);
        returnList.add(jack_hearts);
        returnList.add(queen_hearts);
        returnList.add(king_hearts);
        returnList.add(ace_hearts);

        return returnList;
    }
    private void buildDeckOfCards(){
        ArrayList<Card> hearts_suit = buildNumberCards();
        this.cards.addAll(hearts_suit);
    }
//    currently only builds hearts number cards

}


