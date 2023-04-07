public class Card {
//    fields
    private String name;
    private int value;
    private int alt_value;
    private Suits suit;


//    constructors
    public Card(String input_name, int input_value, Suits input_suit) {
        this.name = input_name;
        this.value = input_value;
        this.alt_value = 21;
        this.suit = input_suit;
    }
//    constructor overload with alt_value
    public Card(String input_name, int input_value, int input_alt_value, Suits input_suit){
        this.name = input_name;
        this.value = input_value;
        this.alt_value = input_alt_value;
        this.suit = input_suit;
    }
//  setters & getters (no value setters)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getValue() {
        return value;
    }
    public int getAlt_value() {
        return alt_value;
    }
    public Suits getSuit() {
        return suit;
    }

//    methods
}
