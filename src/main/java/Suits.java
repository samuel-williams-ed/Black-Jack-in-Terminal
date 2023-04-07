public enum Suits {
    CLUBS(1),
    HEARTS(2),
    SPADES(3),
    DIAMONDS(4);

    private final int priority;

    Suits(int input_priority){
        this.priority = input_priority;
    }

    public int getPriority(){
        return this.priority;
    }

}
