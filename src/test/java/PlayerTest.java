import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    Player sandra;
    Card ace_spades;
    Card king_hearts;
    Card two_hearts;

    @Before
    public void before(){
        sandra = new Player("Sandra", 200);
        ace_spades = new Card("Ace", 1, 11, Suits.SPADES);
        king_hearts = new Card("King", 10, Suits.HEARTS);
        two_hearts = new Card("Jack", 2, Suits.HEARTS);
    }

    @Test
    public void checkPlayerHasName(){
        assertEquals("Sandra", sandra.getName());
    }
    @Test
    public void checkPlayerHasCash(){
        assertEquals(200, sandra.getCash());
    }
    @Test
    public void playerHasTotal(){
        assertEquals(0, sandra.getTotal());
    }
    @Test
    public void receiveCard_addsToTotal(){
        sandra.receiveCard(ace_spades);
        assertEquals(1, sandra.getTotal());
    }
    @Test
    public void receiveCard_addsAceToAltTotal(){
        sandra.receiveCard(ace_spades);
        assertEquals(11, sandra.getAlt_total());
    }
    @Test
    public void receiveCard_addsToHand(){
        sandra.receiveCard(ace_spades);
        assertEquals(Arrays.asList(ace_spades), sandra.getHand());
    }
    @Test
    public void newCardsAddToTotal(){
        sandra.receiveCard(king_hearts);
        sandra.receiveCard(king_hearts);
        assertEquals(20, sandra.getTotal());
    }
    @Test
    public void twist_whenUnder16(){
        sandra.receiveCard(king_hearts);
        sandra.receiveCard(two_hearts);
        assertEquals(true, sandra.stick_or_twist());
    }
    @Test
    public void stick_whenOver16(){
        sandra.receiveCard(king_hearts);
        sandra.receiveCard(king_hearts);
        assertEquals(false, sandra.stick_or_twist());
    }
    @Test
    public void twist_usingAltCardValue(){
        sandra.receiveCard(king_hearts);
        sandra.receiveCard(ace_spades);
        assertEquals(true, sandra.stick_or_twist());
    }
    @Test
    public void twist_usingAltCardValueWithMoreCards() {
        sandra.receiveCard(king_hearts);
        sandra.receiveCard(ace_spades);
        sandra.receiveCard(ace_spades);
        sandra.receiveCard(ace_spades);
        assertEquals(true, sandra.stick_or_twist());
    }

}