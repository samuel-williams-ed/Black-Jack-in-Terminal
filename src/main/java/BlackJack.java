import com.sun.xml.internal.ws.wsdl.writer.document.Part;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class BlackJack {

    StandardDeck deck;
    Dealer dealer;
    Player player;

    ArrayList<Card> burnPile;
    int pot;

//    constructor
    public BlackJack(String player_name, int player_cash){
        this.deck = new StandardDeck();
        this.dealer = new Dealer(this.deck);
        this.player = new Player(player_name, player_cash);
        this.burnPile = new ArrayList<Card>();
    }

//    setters & getters
    public StandardDeck getDeck(){
        return this.deck;
    }
    public Dealer getDealer(){ return this.dealer; }
    public Player getPlayer() { return this.player; }
    public ArrayList<Card> getBurnPile() { return this.burnPile; }
    public int getPot() { return this.pot; }
    public void setPot(int value) { this.pot = value; }
    public void addToPot(int cash_value) { this.pot += cash_value; }
    public void removeFromPot( int cash_value ) { this.pot -= cash_value; }

//    methods
    public boolean checkIfBust(Participant player_to_check) {
//        System.out.println("-- debug: checking if bust " + player_to_check.getTotal());
        return ( player_to_check.getTotal() > 21 );
    }
    public boolean didPayerWin(){
        int wager = this.getPot();
//        System.out.println(" ~~ debug: didPlayerWin: pot = " + Integer.toString(wager));
//        TODO: doesn't handle aces yet
        if (this.player.getTotal() > this.dealer.getTotal() || this.dealer.getTotal() > 21){
            dealer.narratorAnnounce("You won!");
            player.addCash( wager );
            this.setPot( 0 );
            dealer.narratorAnnounce("Your Cash: " + player.getCash());
            return true;
        } else {
            dealer.narratorAnnounce("House wins");
            player.looseCash( wager );
            this.setPot( 0 );
            dealer.narratorAnnounce("Your Cash: " + player.getCash());
        }
        return false;
    }

//      Timer methods
    public void pauseAndAnnounce(Participant player, String vocalisation, int delay){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                player.announce(vocalisation);
            }
        }, delay);
    }
    public void pauseAndNarrate(String vocalisation, int delay){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                player.narratorAnnounce(vocalisation);
            }
        }, delay);
    }
    public void pauseAndShowHand(Participant choose_player, int delay){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                choose_player.announceHand();
            }
        }, delay);
    }
    public void pauseAndCheckWinner(int delay){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                didPayerWin();
            }
        }, delay);
    }



    public void startGame(){

        // nested delay - 500 - 3400s
        dealer.dealBlackJack(this.player, burnPile);
        // 4000
        pauseAndAnnounce(dealer, "Place bets: House blind £5 ", 4000);
        this.addToPot(5);
        pauseAndNarrate(" (enter number) ", 4100);
        // wait for user input...
        this.addToPot(player.getPlayerBet());

        //reset (timing)
        // 500 - 1200
        pauseAndAnnounce(dealer, "Current pot is £" + getPot(), 500);
        pauseAndShowHand(player, 1200); //new Timer() function with 2 sec delay

        // 1800 - 2000
        // note - doesn't stop flow!!!
        if (checkIfBust(player)) {
            pauseAndAnnounce(dealer, "You're bust! House wins.", 1800);

            return;
        } else {
            pauseAndAnnounce(dealer, "Stick or Twist?", 1800);
            pauseAndAnnounce(dealer, " ( s / t ) ", 2000);

            // 800 - 1500
            // wait for user input... exit when false (stick)
            while (!checkIfBust(player) && player.stick_or_twist() ) {
                dealer.burnCard(burnPile);
                dealer.dealCard(player);

                // if haven't gone bust!
                if (!checkIfBust(player)) {
                    pauseAndShowHand(player, 1200);
                    pauseAndAnnounce(dealer, "Stick or Twist?", 1800);
                    pauseAndAnnounce(dealer, " ( s / t ) ", 2000);
                }
            }

            // reset (timing)
            // 800 - 2100
            if (checkIfBust(player)) {
                pauseAndShowHand(player, 1200);
                pauseAndAnnounce(dealer, "You're bust! House wins.", 1800);
//                take money off player
                pauseAndAnnounce(dealer, " ^R to restart", 2100);
                return;

            } else {
                if (dealer.stick_or_twist()){
                    pauseAndAnnounce(dealer, "dealer twists", 800);
                    dealer.delayBurn(burnPile, 1200);
                    dealer.delayDeal(dealer, 1900);
                }
            }

            // 2600 - 6100
            pauseAndAnnounce(dealer, "Alright, cards on the table, let's see who won.", 2600);
            pauseAndShowHand(player, 4000);
            pauseAndShowHand(dealer, 4800);
            pauseAndCheckWinner(5500);

            pauseAndNarrate("Would you like another round? ( y / n ) ", 6000);
            // handle resetting deck
            dealer.reshuffle(burnPile);
        }

    }

    public void playBlackJack(){
//        get user name
        dealer.narratorAnnounce("Welcome to the digital Casino. Tell us a name for your badge and we'll get you started. \n ( enter name )");
        String userName = player.askForPlayersName();
        player.setName(userName);

//        TODO: track pot
//        TODO: while, user wants another round

        dealer.narratorAnnounce("Would you like to play? ( y / n ) ");

        while(player.playerPlays() && !checkIfBust(player)){
            startGame();
        }
    }

}
