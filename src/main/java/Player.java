import java.util.Scanner;

public class Player extends Participant{
    private String name;
    private int cash;

//    constructor
    public Player(String input_name, int input_cash){
//        NB. no super parameters
        this.name = input_name;
        this.cash = input_cash;
    }

//    setters & getters
    public int getCash() {
        return cash;
    }
    public void setCash(int input_cash) {
        this.cash = input_cash;
    }
    public void addCash(int input_cash) { this.cash += input_cash;}
    public void looseCash(int cash) { this.cash -= cash;}
    public String getName() {
        return name;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }

    //    methods
    public String getPlayerInput(){
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
    public String askForPlayersName(){
        return getPlayerInput();
    }
    public boolean playerPlays(){

        String userChoice = this.getPlayerInput();

        if (
                userChoice.equals("y") ||
                userChoice.equals("yes") ||
                userChoice.equals("deal") ||
                userChoice.equals("deal me") ||
                userChoice.equals("deal me in") ||
                userChoice.equals("lets") ||
                userChoice.equals("lets go")
        ){
            return true;
        }
        return false;
    }
    public int getPlayerBet(){

        Scanner input = new Scanner(System.in);
        int player_bet = input.nextInt();

        if ( player_bet > this.getCash() ){
            System.out.println( "You don't have that much money!" );
            player_bet = 0;
        }
        return player_bet;
    }
    @Override
    public boolean stick_or_twist(){

        String user_choice = this.getPlayerInput();

        if (user_choice.equals("y") ||
            user_choice.equals("yes") ||
            user_choice.equals("t") ||
            user_choice.equals("twist") ||
            user_choice.equals("more") ||
            user_choice.equals("another")) {

            this.announce("Twist.");
            return true;
        }

        this.announce("Stick.");
        return false;
    }


}
