import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class StandardDeckTest {

    StandardDeck deck;
    Card two_hearts;

    @Before
    public void before(){
        deck = new StandardDeck();
        two_hearts = new Card("Two", 2, Suits.HEARTS);
    }

    @Test
    public void hasCard_two_hearts(){ //matching name value on top card
        assertEquals("Two", deck.drawCard().getName());
    }
    @Test
    public void hasMultipleCardsInDeck(){
        assertTrue(deck.getCards().size() > 1);
    }

    @Test
    public void canGetCards(){
        System.out.println(deck.getCards().get(0).getName());
        assertEquals(two_hearts.getName(), deck.getCards().get(0).getName());
    }
    @Test
    public void canDrawCard(){
        Card hand = deck.drawCard();
        assertEquals("Two", hand.getName());
    }
    @Test
    public void drawCards_removesFromDeck(){
        int length = deck.getCards().size();
        deck.drawCard();
        assertEquals(length-1, deck.getCards().size());
    }
    @Test
    public void shuffleChangesCards(){
        String original = deck.getCards().get(0).getName();
        deck.shuffleDeck();
        assertNotEquals(original, deck.getCards().get(0).getName());
    }
}
