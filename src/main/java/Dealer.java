import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Dealer extends Participant{
    private StandardDeck dealers_deck;
//    constructor
    public Dealer( StandardDeck input_deck){
        this.dealers_deck = input_deck;
        setName("Dealer");
    }
    public Dealer(String input_name, StandardDeck input_deck){
        super(input_name);
//        NB no super parameters for Participant
        this.dealers_deck = input_deck;
    }

    //    setters & getters
    public StandardDeck getDealers_deck() {
        return dealers_deck;
    }
//    methods
    public void dealCard(Participant input_to_who){
        Card drawnCard = this.dealers_deck.drawCard();
        input_to_who.receiveCard(drawnCard);
    }
    public void dealSelfCard(){
        Card drawnCard = this.dealers_deck.drawCard();
        this.receiveCard(drawnCard);
    }
    public void burnCard(ArrayList<Card> burn_pile){
        this.announce("Dealer: Burning card " + this.dealers_deck.getCards().get(0).getName());
        Card burnCard = this.dealers_deck.drawCard();
        burn_pile.add(burnCard);
    }
    public void shuffleDeck(){
        this.getDealers_deck().shuffleDeck();
    }
    public void delaySmallTalk(int delay){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                smallTalk();
            }
        }, delay);
    }
    public void delayBurn(ArrayList<Card> burn_pile, int delay){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                burnCard(burn_pile);
            }
        }, delay);
    }
    public void delayDeal(Participant who_is_playing, int delay){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                dealCard(who_is_playing);
            }
        }, delay);
    }
    public boolean dealBlackJack(Participant who_is_playing, ArrayList<Card> burn_pile){
        System.out.println("Game beginning.");
        pauseAndTalk(this, "Let me shuffle the deck and we'll get on our way.", 500);
        this.shuffleDeck();

        delaySmallTalk(2000);
        delayBurn(burn_pile, 2400);
        delayDeal(who_is_playing, 2800);
        delayDeal(who_is_playing, 3000);
        delayBurn(burn_pile, 3400);
        dealSelfCard();
        dealSelfCard();
        return true;
    }
    @Override
    public boolean stick_or_twist(){
        return (this.getTotal() < 16 || this.getAlt_total() < 16);
    }

    public void reshuffle(ArrayList<Card> burnPile) {
        this.dealers_deck.getCards().addAll(burnPile);
        burnPile.clear();
    }
}
