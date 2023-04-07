import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DealerTest {

    Dealer andy;
    Player sandra;
    StandardDeck deck;
    Card two_hearts;
    ArrayList<Card> burnPile;

    @Before
    public void before(){
        deck = new StandardDeck();
        andy = new Dealer(deck);
        sandra = new Player("Sandra", 200);
        two_hearts = new Card("two_hearts", 2, Suits.HEARTS);
        burnPile = new ArrayList<Card>();
    }

    @Test
    public void setup_playerStartsWithNoCards(){
        assertTrue(sandra.getHand().size() == 0);
    }
    @Test
    public void setup_playerCanHaveCards(){
        sandra.receiveCard(two_hearts);
        assertEquals(two_hearts, sandra.getHand().get(0));
    }
    @Test
    public void setup_deckHasCards(){
        assertEquals(9, deck.getCards().size());
    }
    @Test
    public void hasCardsInDeck(){
        assertTrue(andy.getDealers_deck().getCards().size() > 1);
    }
    @Test
    public void whenRemovesCardFromDeckWhenDealing(){ //self and player
        andy.dealCard(sandra);
        andy.dealSelfCard();
        assertEquals(7, deck.getCards().size());
    }
    @Test
    public void canDealCardToPlayer(){
        andy.dealCard(sandra);
        assertTrue(sandra.getHand().size() > 0);
    }
    @Test
    public void canDealCardToSelf(){
        andy.dealSelfCard();
        assertTrue(andy.getHand().size() > 0);
    }
    @Test
    public void canBurnCard(){
        andy.burnCard(burnPile);
        assertEquals(1, burnPile.size());
    }
    @Test
    public void canAddBurnCardToBurnPile(){
        String burnCardName = andy.getDealers_deck().getCards().get(0).getName();
        andy.burnCard(burnPile);
        assertEquals(1, burnPile.size());
        assertEquals(burnPile.get(0).getName(), burnCardName);
    }
    @Test
    public void dealBlackJackBurnsTwoCards(){
        andy.dealBlackJack(sandra, burnPile);
        assertEquals(2, burnPile.size());

    }
    @Test
    public void dealBlackJackGivesPlayerTwoCards(){
        andy.dealBlackJack(sandra, burnPile);
        assertEquals(2, sandra.getHand().size());
    }
    @Test
    public void dealBlackJackGivesDealerTwoCards(){
        andy.dealBlackJack(sandra, burnPile);
        assertEquals(2, andy.getHand().size());
    }

}