import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardsTest {

    Card king_hearts;
    Card ace_spades;

    @Before
    public void before(){
        king_hearts = new Card("King", 10, Suits.HEARTS);
        ace_spades = new Card("Ace", 1, 11, Suits.SPADES);
    }

    @Test
    public void cardHasName(){
        assertEquals("King", king_hearts.getName());
        assertEquals("Ace", ace_spades.getName());
    }
    @Test
    public void cardHasValue(){
        assertEquals(10, king_hearts.getValue());
        assertEquals(1, ace_spades.getValue());
    }
    @Test
    public void cardHasSuit(){
        assertEquals(Suits.HEARTS, king_hearts.getSuit());
        assertEquals(Suits.SPADES, ace_spades.getSuit());
    }
    @Test
    public void aceHasAlt_value(){
        assertEquals(11, ace_spades.getAlt_value());
    }
    @Test
    public void faceCardHasNoAlt_value(){
        assertEquals(21, king_hearts.getAlt_value());
    }
}
