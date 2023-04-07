import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Participant {
    private String name;
    private ArrayList<Card> hand;
    private int total;
    private int alt_total; //handle aces
    private ArrayList<String> options;

//    constructors
    public Participant(){
        this.name = "?";
        this.hand = new ArrayList<>();
        this.total = 0;
        this.alt_total = 0;
        options = new ArrayList<>();
        options.add("So, you play Black Jack often?");
        options.add("Dunno if you heard - there's a cold snap coming. They say it's another 'Beast from the East' ");
        options.add("...");
        options.add("...");
        options.add("...");
        options.add("...sometimes I feel like they're making snacks smaller, ya know? ...maybe I'm just getting bigger.");
        options.add("I think it's healthy to ponder existence sometimes. Helps keep ya grounded, you know.");
    }
    public Participant(String input_name){
        this.name = input_name;
        this.hand = new ArrayList<>();
        this.total = 0;
        this.alt_total = 0;
    }

//    methods
    public abstract boolean stick_or_twist();
    public void receiveCard(Card input_card){
        this.hand.add(input_card);
        this.total += input_card.getValue();
        this.alt_total += input_card.getAlt_value();
    }
    public void pauseAndTalk(Participant player, String vocalisation, int waitTime){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                player.announce(vocalisation);
            }
        }, waitTime);
    }
    public void narratorAnnounce(String vocalisation){
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
                System.out.println(vocalisation);
//            }
//        }, 100L);
    }
    public void announce(String vocalisation){
        String who = this.getName() + ": ";
        System.out.println(who + vocalisation);
    }
    public String buildMiddleString(String to_insert){

//        total size = 10 characters
        float length_of_spaces_needed = ( 10 - to_insert.length() );
        int number_characters_left_side = (int) Math.floor(length_of_spaces_needed / 2);
        int number_characters_right_side = (int) Math.ceil(length_of_spaces_needed / 2);

        StringBuilder spaces_left_side = new StringBuilder();
        StringBuilder spaces_right_side = new StringBuilder();

        for(int i = 0; i < number_characters_left_side; i++)  { spaces_left_side.append(" "); };
        for(int i = 0; i < number_characters_right_side; i++) { spaces_right_side.append(" "); };

        String newString = spaces_left_side + to_insert + spaces_right_side;
        return newString;
    }

    public String buildTopAndBottomStrings(String to_insert, String t_or_p){
        float length_of_spaces_needed = ( 10 - to_insert.length() );
        int number_characters_to_fill_space = (int) Math.floor(length_of_spaces_needed -1 );

        StringBuilder spaces_string = new StringBuilder();

        for(int i = 0; i < number_characters_to_fill_space; i++)  { spaces_string.append(" "); };

        String newString = "";

        if (t_or_p.equals("t")) {
            newString = " " + to_insert + spaces_string;
        } else {
            newString = spaces_string + to_insert + " ";
        }
        return newString;
    }
    public void announceHand(){ //doesn't handle ace
        this.narratorAnnounce("------------------------");
        this.narratorAnnounce(" *** " + this.getName() + " has total: " + this.getTotal() + " *** ");
        this.buildAndAnnounceCardPattern();
        this.narratorAnnounce("------------------------");
    }
    public void buildAndAnnounceCardPattern(){

        for (Card card_in_hand : this.getHand()){

            // add spaces to make string exact length to fit card
            String insert_suit = buildMiddleString(card_in_hand.getSuit().toString());
            String insert_name = buildMiddleString(card_in_hand.getName());
            String insert_top_char = buildTopAndBottomStrings(card_in_hand.getName(), "t");
            String insert_bottom_char = buildTopAndBottomStrings(card_in_hand.getName(), "b");


            this.announce( buildCard(insert_suit, insert_name) );
        }
    }

    public String buildCard(String suit, String name){
        return ("\n" +
                "    |##################|" + "\n" +
                "    |#  :'''''''''':  #|" + "\n" +
                "    |#  :" + suit + ":  #| " +  "\n" +
                "    |#  :          :  #| " +  "\n" +
                "    |#  :" + name + ":  #| " + "\n" +
                "    |#  :          :  #| " + "\n" +
                "    |#  :..........:  #| " + "\n" +
                "    |##################|" + "\n");
    }
    public void smallTalk(){
        Random rand = new Random();
        int chooseAnOption = (int) rand.nextInt(options.size());
       this.announce(options.get(chooseAnOption));
    }

    //    setters & getters
    public ArrayList<Card> getHand() {
        return this.hand;
    }
    public void discardHand(){
        this.hand.clear();
        this.total = 0;
        this.alt_total = 0;
    }
    public String getName() {return this.name; }
    public void setName(String newName) { this.name = newName; }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public int getAlt_total() {
        return alt_total;
    }
    public void setOptional_total(int optional_value) {
        this.alt_total = optional_value;
    }

}
