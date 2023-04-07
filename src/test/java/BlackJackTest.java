import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BlackJackTest {
    BlackJack match;

    @Before
    public void before(){
        match = new BlackJack("samuel", 200);
    }

    @Test
    public void hasStandardDeck(){
        assertEquals(StandardDeck.class, match.getDeck().getClass());
    }
    @Test
    public void checkDeckIsLarge(){
        assertTrue(match.getDeck().getCards().size() > 6);
    }
    @Test
    public void checkDealerExists(){
        assertEquals(Dealer.class, match.getDealer().getClass());
    }
    @Test
    public void checkDealerHasCardsInDeck(){
        assertTrue(match.getDealer().getDealers_deck().getCards().size() > 6);
    }
    @Test
    public void checkDealerBurnsWhenDealing(){
        match.getDealer().burnCard(match.getBurnPile());
        assertEquals(1, match.getBurnPile().size());
    }
    @Test
    public void checkDealerIsMutatingBlackJackDeck(){
        assertEquals(9, match.getDealer().getDealers_deck().getCards().size());
        match.dealer.dealCard(match.getPlayer());
        match.dealer.dealCard(match.getPlayer());
        assertEquals(7, match.getDeck().getCards().size());
    }



}
