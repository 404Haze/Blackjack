//Importing needed libraries.
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

//Declaring main class for running.
public class main{
    public static void main(String[] args){

        //Creates the game with initial money = 500 and minimum bet = 25.
        new BlackJack(new Scanner(System.in), 500, 25);
    }
}

//Enumeration for different menu pages.
enum MenuPageType{
    MAIN,
    RULES,
    CREDITS
}

//Creating a class for main menu.
class BlackJackMenu{

    //Declaring scanners and custom types.
    private BlackJack blackJack;
    private Scanner scanner;
    private MenuPageType menuPage;
    private String errorMessage;

    //Subclass for the menu.
    public BlackJackMenu(BlackJack blackJack, Scanner scanner){
        this.blackJack = blackJack;
        this.scanner = scanner;
        this.menuPage = MenuPageType.MAIN;
        this.errorMessage = null;
    }

    //Displays the main menu page and starts waiting for input.
    public void show(){
        String input = null;

        //Do while loop for menu.
        do{
            if(input == null){

                //If no input is given yet then display main page.
                this.changePage(MenuPageType.MAIN);
            }
            else{
                this.errorMessage = null;

                //If we are on main page then we can listen for inputs play, rules and credits.
                if(this.menuPage == MenuPageType.MAIN){
                    this.executeOption(input);
                }
                else if(input.equalsIgnoreCase("back")){

                    //If we aren't on the main page then there will be only the back command available.
                    this.changePage(MenuPageType.MAIN);
                }
                else{

                    //If there was an error message set when executing the option, display it.
                    this.errorMessage = "Invalid input: `" + input + "`. To go back to the main menu, type `back`";
                    this.printPage();
                }
            }

            //Reads the input.
            input = this.scanner.nextLine();

            //Repeats asking until the user says quit.
        } while (!input.equalsIgnoreCase("quit"));

        //Exits the program after the loop has ended meaning the user types quit.
        System.exit(0);
    }

    //Executes option from input.
    private void executeOption(String option){
        switch (option.toLowerCase()){
            case "play":
                //If the user says play then it starts the game.
                this.blackJack.startGame();
                break;
            case "rules":
                //Switch menu page to rules.
                this.changePage(MenuPageType.RULES);
                break;
            case "credits":
                //Switch menu page to credits.
                this.changePage(MenuPageType.CREDITS);
                break;
            default:
                //For other inputs set error message and print main page again.
                this.errorMessage = "Invalid input: `" + option + "`. Valid options are: play, rules, credits, quit";
                this.printPage();
        }
    }

    //Sets the active page and prints it.
    private void changePage(MenuPageType page){
        this.menuPage = page;
        this.printPage();
    }

    //Prints the current page into the terminal.
    private void printPage(){

        //Make a 50 lines space, so it looks like the terminal was cleaned and replaced.
        for(int i = 0; i < 50; i++){
            System.out.println("\n");
        }

        //Printing out logo.
        System.out.println(" _______  ___      _______  _______  ___   _      ___  _______  _______  ___   _  __");
        System.out.println("|  _    ||   |    |   _   ||       ||   | | |    |   ||   _   ||       ||   | | ||  |");
        System.out.println("| |_|   ||   |    |  |_|  ||       ||   |_| |    |   ||  |_|  ||       ||   |_| ||  |");
        System.out.println("|       ||   |    |       ||       ||      _|    |   ||       ||       ||      _||  |");
        System.out.println("|  _   | |   |___ |       ||      _||     |_  ___|   ||       ||      _||     |_ |__|");
        System.out.println("| |_|   ||       ||   _   ||     |_ |    _  ||       ||   _   ||     |_ |    _  | __");
        System.out.println("|_______||_______||__| |__||_______||___| |_||_______||__| |__||_______||___| |_||__|");
        System.out.println("_______________________________________________________________________________________");

        //Depending on the menu page we are in, show different screens.
        switch (this.menuPage){

            //Printing out the introductory menu.
            case MAIN:{
                System.out.println("");
                System.out.println("Welcome to BlackJack!");
                System.out.println("Your current balance is: $" + this.blackJack.getMoney());
                System.out.print("\n");
                System.out.println("Type one of the following options:");
                System.out.print("\n");
                System.out.println("play - Enters the game");
                System.out.println("rules - Opens the game rules");
                System.out.println("credits - Displays the credits");
                System.out.println("quit - Quits the application");
                break;
            }

            //Printing out the rules for player.
            case RULES:{
                System.out.println("");
                System.out.println("The goal of the game is to have a hand value of 21 or as close to 21 as possible, without going over.");
                System.out.println("Each player is dealt two cards, face up. The dealer is also dealt two cards, but one is face down (the 'hole' card).");
                System.out.println("The value of the cards is as follows: 2-10 are worth their face value, face cards (jack, queen, king) are worth 10, and an ace can be worth 1 or 11, depending on which value would be more beneficial for the player's hand.");
                System.out.println("Players can choose to 'hit' and take additional cards, one at a time, in order to improve their hand value.");
                System.out.println("If a player's hand value exceeds 21, they lose (this is called 'busting').");
                System.out.println("Once all players have finished taking hits, the dealer reveals their hole card. The dealer must hit if their hand value is less than 17, and must stand (stop taking cards) if their hand value is 17 or higher.");
                System.out.println("If the dealer busts, all remaining players win.");
                System.out.println("If the dealer does not bust, then the player's hands are compared to the dealer's hand, and whoever has the hand value closest to 21 without going over wins.");
                System.out.println("If a player and the dealer have the same hand value, it is a tie (this is called a 'push').");
                System.out.println("Players can also choose to 'surrender' and give up half their bet before the dealer checks for a blackjack.");
                System.out.println("Players can also 'split' their hand if they are dealt two cards of the same value.");
                System.out.println("Players can also 'double down' by doubling their bet and receive one more card.");
                System.out.println("\nType `back` to return back to the main page");
                break;
            }

            //Printing out the credits
            case CREDITS:{
                System.out.println("");
                System.out.println("Created by Arche.\nSpecial thanks to my computer science teacher for teaching me Java and for providing guidance and knowledge that has allowed me to excel in this field.");
                System.out.println("\nType `back` to return back to the main page.");
                break;
            }
        }

        //Checking for error messages.
        if(this.errorMessage != null){
            System.out.println("\n[" + this.errorMessage + "]");
        }
    }
}

//Enumeration for different cards for their values and names.
enum Card{
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    ACE(11, "A");

    public int value;
    public String name;
    Card(int value, String name){
        this.value = value;
        this.name = name;
    }
}

//This class represents one hand of cards.
class Hand{

    //Declaring cards array.
    public Card[] cards;
    private int bet;
    private int index;

    public Hand(){
        //Create array with length 21 because it's the maximum number of cards we can get until we reach 21 points.
        this.cards = new Card[21];
        this.bet = 0;
        this.index = 0;
    }

    //Adds a card to the card array.
    public void addCard(Card card){
        this.cards[this.index++] = card;
    }

    //Finds if cards contains ace and if yes then return its index.
    public int findAce(){
        for(int i = 0; i < this.index; i++){
            if(this.cards[i] == Card.ACE){
                return i;
            }
        }
        return -1;
    }

    //Draws a random card.
    public Card drawCard(Random random){
        Card card = Deck.cards[random.nextInt(10)];
        this.addCard(card);
        return card;
    }

    //Calculates the total value of all cards.
    public int getValue(){
        int result = 0;
        for(int i = 0; i < this.index; i++){
            result += this.cards[i].value;
        }
        return result;
    }

    //Sets the index in the array at which we are setting cards.
    public void setIndex(int index){
        this.index = index;
    }

    //Gets index.
    public int getIndex(){
        return index;
    }

    //Places bet on this hand.
    public void placeBet(int amount){
        this.bet += amount;
    }

    //Doubles current bet.
    public void doubleBet(){
        this.bet *= 2;
    }

    //Returns current bet.
    public int getBet(){
        return bet;
    }
}

//This class holds two hands, where the second hand is used in case of splitting.
class Deck{
    public static Card[] cards = Card.values();
    private Random random;

    private Hand hand;
    private Hand splitHand;

    private boolean split;

    public Deck(){
        this.random = new Random();
        this.hand = new Hand();
        this.split = false;

        //Draws two initial cards for the main hand.
        this.drawCard(true);
        this.drawCard(true);
    }

    //Splits the deck into two hands.
    public void split(){
        this.split = true;

        //Initializes second hand.
        this.splitHand = new Hand();

        //Copies one of the values into the second hand.
        this.splitHand.cards[0] = this.hand.cards[1];

        //Removes second value from main hand.
        this.hand.cards[1] = null;

        //Sets array indexes to 1.
        this.hand.setIndex(1);
        this.splitHand.setIndex(1);
    }

    //Returns the hand.
    public Hand getHand(boolean mainHand){
        return mainHand ? hand : splitHand;
    }

    //Decides if the deck can be split.
    public boolean canSplit(){
        return !this.split && this.hand.cards[0].value == this.hand.cards[1].value;
    }

    //Returns first card of the deck which is used to display face-up card for dealer.
    public Card getFirstCard(){
        return this.hand.cards[0];
    }

    //Draws a card for specified hand.
    public Card drawCard(boolean intoMainHand){
        Card card;
        if(intoMainHand){
            card = this.hand.drawCard(random);
        } else{
            card = this.splitHand.drawCard(random);
        }
        return card;
    }

    //Returns the total value of a hand.
    public int getValue(boolean mainHand){

        //Unworthy return.
        return(mainHand ? this.hand : this.splitHand).getValue();
    }

    //Creates string representation of the cards in a hand.
    public String toString(boolean mainHand){
        Card[] cards = mainHand ? this.hand.cards : this.splitHand.cards;

        //String builder for rendering main hand.
        StringBuilder result = new StringBuilder("[");
        int length = (mainHand ? this.hand.getIndex() : this.splitHand.getIndex());

        //Declaring loop to count the cards.
        for(int i = 0; i < length; i++){
            result.append(cards[i].name);
            if(i < length - 1){
                result.append(", ");
            }
        }
        result.append("]");

        //Providing return for method.
        return result.toString();
    }

    //Returns if the deck is split into two.
    public boolean isSplit(){
        return split;
    }
}

//This class controls the entire game.
class BlackJack{

    //Creating scanners and menu.
    private Scanner scanner;
    private BlackJackMenu menu;

    //Declaring players and dealers decks.
    private Deck playerDeck;
    private Deck dealerDeck;

    //Declaring money variables.
    private int minBet;
    private int money;

    //Declaring gameloop and essential variables and booleans.
    private boolean gameLoopActive;
    private String message;
    private boolean isStaying;
    private boolean playingSplitFirstHand;

    //Preparing blackjack class with scanners.
    public BlackJack(Scanner scanner, int initialMoney, int minBet){
        this.scanner = scanner;
        this.money = initialMoney;
        this.minBet = minBet;
        this.reset();

        //Creates and shows the main menu.
        this.menu = new BlackJackMenu(this, this.scanner);
        this.menu.show();
    }

    //Using the reset values after a game is finished.
    private void reset(){
        this.gameLoopActive = true;
        this.message = null;
        this.isStaying = false;
        this.playingSplitFirstHand = false;
    }

    //Starts a new game.
    public void startGame(){
        Random random = new Random();

        //Resets the variables.
        this.reset();

        //Creates decks for the player and the dealer.
        this.playerDeck = new Deck();
        this.dealerDeck = new Deck();
        
        boolean isFirstMove = true;
        int bet;
        String betWarningMessage = null;

        //Do while loop for projecting tasks.
        do{

            //Clears the terminal.
            this.clear();

            //Adding money out of the goodness of my heart, no alterior motives here.
            if(this.money < 25){
                this.money += 25;
            }

            //Asks how much the user wants to bet.
            System.out.println("How much do you want to bet? Minimum bet amount is $" + minBet + " and you currently have $" + this.money);

            //If a warning message exists then it displays it.
            if(betWarningMessage != null){
                System.out.println("> " + betWarningMessage);
            }
            betWarningMessage = null;


            //Gets the input.
            try{

                //Reads int from input.
                bet = scanner.nextInt();

                //Removes new line which nextInt doesn't pick up and would skip when I next use the scanner.nextLine().
                scanner.nextLine();

            }
            catch(InputMismatchException e){

                //Clears the bad input
                scanner.nextLine();

                //Catches the error and sets warning message.
                bet = 0;
                betWarningMessage = "Please input a number";
                continue;
            }

            //Checking minimum betting amount.
            if(bet > this.money){
                betWarningMessage = "You don't have enough money to place a $" + bet + " bet !";
            }
            if(bet < minBet){
                betWarningMessage = "Minimum bet is $" + minBet;
            }
        } while(bet > this.money || bet < minBet); //Ask until the input is right.

        //Subtracts the money the player had bet.
        this.money -= bet;

        //Adds the bet to his hand.
        this.playerDeck.getHand(true).placeBet(bet);

        //While loop for the main game.
        while(this.gameLoopActive){
            this.clear();

            //Conditional to check for messages.
            if(this.message != null){
                System.out.println("> " + this.message + "\n");
            }
            this.message = null;

            //Check for busts.
            String afterMessage = null;
            boolean hasGameEnded = false;

            //Declaring current hand for player.
            Hand currentHand = this.playerDeck.getHand(!this.playerDeck.isSplit() || playingSplitFirstHand);
            int value = currentHand.getValue();

            //Checking if the value is 21.
            if(value == 21){
                this.isStaying = true;
            }
            //Checking if the value is greater than 21.
            else if(value > 21){

                //Indexing the ace value in hand.
                int aceIndex = currentHand.findAce();

                //Printing the ace value changes.
                if(aceIndex > -1){
                    afterMessage = "Ace value was changed from 11 to 1 because the value of your deck would exceed 21.";
                    currentHand.cards[aceIndex].value = 1;
                }
                //Printing that player got busted.
                else{
                    afterMessage = "Busted! You lost with " + value + " points. (-$" + currentHand.getBet() + ")";
                    hasGameEnded = true;
                }
            }

            //Checking if player is standing or not.
            if(this.isStaying){

                //Play as the dealer here.
                Hand dealerHand = dealerDeck.getHand(true);

                //While loop for playing as dealer.
                while(dealerHand.getValue() < 17){
                    dealerHand.drawCard(random);
                    int aceIndex = dealerHand.findAce();
                    if(aceIndex > -1){
                        dealerHand.cards[aceIndex].value = 1;
                    }
                }

                //Declaring dealer and player hand values.
                int dealerHandValue = dealerHand.getValue();
                int playerValue = currentHand.getValue();

                //Printing out dealers cards and your total.
                System.out.println("Dealer Cards: " + dealerDeck.toString(true));
                System.out.println("Dealer Total: " + dealerHandValue);
                System.out.print("\n");

                //Checking dealer hand values for wins or busts.
                if(dealerHandValue > 21){
                    afterMessage = "Dealer busted with " + dealerHandValue + " points! You won with " + playerValue + " points. (+$" + currentHand.getBet() + ")";
                    this.money += currentHand.getBet() * 2;
                }
                else if(dealerHandValue > playerValue){
                    afterMessage = "Dealer won with " + dealerHandValue + " points! You lost with " + playerValue + " points. (-$" + currentHand.getBet() + ")";
                }
                else if(dealerHandValue < playerValue){
                    afterMessage = "You won with " + playerValue + " points! Dealer lost with " + dealerHandValue + " points. (+$" + currentHand.getBet() + ")";
                    this.money += currentHand.getBet() * 2;
                }
                else{
                    afterMessage = "You tied with " + dealerHandValue + " points! (+$0)";
                    this.money += currentHand.getBet(); //Money returns
                }

                //Setting the game to end.
                hasGameEnded = true;
            }
            //Stating the dealers hand.
            else{
                System.out.println("The dealer's first card is [" + this.dealerDeck.getFirstCard().name + "], his second card is hidden.\n");
            }

            //Checking if the deck is split.
            if(this.playerDeck.isSplit()){

                //Printing hand 1.
                System.out.println("Your Cards (First Hand): " + this.playerDeck.toString(true));
                System.out.println("Your Total (First Hand): " + this.playerDeck.getValue(true));

                //Printing hand 2.
                System.out.println("\nYour Cards (Second Hand): " + this.playerDeck.toString(false));
                System.out.println("Your Total (Second Hand): " + this.playerDeck.getValue(false));
            }
            else{
                //Printing totals.
                System.out.println("Your Cards: " + this.playerDeck.toString(true));
                System.out.println("Your Total: " + this.playerDeck.getValue(true));
            }

            //Printing space.
            System.out.print("\n");

            //Printing the result message at the end of the game.
            if(afterMessage != null){
                System.out.println("> " + afterMessage);
            }

            //Detecting if game ended and if second hand still exists.
            if(hasGameEnded && this.playingSplitFirstHand){
                System.out.println("> You finished playing with your first hand. You will now play with second hand.");
                hasGameEnded = false;
                this.playingSplitFirstHand = false;
                this.isStaying = false;
                currentHand = this.playerDeck.getHand(false);

                //Regenerating the dealer's deck, so it's not the same as playing with the first hand.
                this.dealerDeck = new Deck();
            }

            System.out.print("\n");

            //Checking if game ended.
            if(hasGameEnded){
                break;
            }

            //Hint to split.
            if(isFirstMove && this.playerDeck.canSplit()){
                System.out.println("Your initial cards have the same value so you have the opportunity to split by typing `sp`.");
            }

            //Makes the player make a move.
            this.play(this.playerDeck, currentHand, random, this.playerDeck.isSplit() ? playingSplitFirstHand ? 1 : 2 : 0, isFirstMove);

            //Checking if its first move.
            if(isFirstMove){
                isFirstMove = false;
            }
        }

        //Responce for surrendering.
        if(!gameLoopActive){
            System.out.println("> You have surrendered. (+$0)\n");
            this.money += playerDeck.getHand(true).getBet() / 2;
        }

        //Telling user they can go back to menu.
        System.out.println("Press Enter to go back into the menu.");
        scanner.nextLine();
        menu.show();
    }

    //SplitType: 0 = not split, 1 = first hand, 2 = second hand.
    private void play(Deck deck, Hand hand, Random random, int splitType, boolean isFirstMove){

        //Conditionals for split deck.
        if(splitType != 0){
            System.out.println("[Playing split deck, " + (splitType == 1 ? "first" : "second") + " hand]");
        }
        System.out.println("Do you want to hit or stand? Type `h` to hit, `s` to stand, `dd` to double down or `r` to surrender.");

        //Declaring split values.
        int splitValue = hand.cards[0].value;
        int value = hand.getValue();

        //Hints for splitting and doubling down.
        if(isFirstMove && deck.canSplit() && (splitValue == 11 || splitValue == 8)){
            System.out.println("-> Hint: Now it's a good time to split!");
        }
        else if(!isFirstMove && value >= 10 && value <= 14){
            System.out.println("-> Hint: Now it's a good time to double down!");
        }

        //Getting user input.
        String input = scanner.nextLine();

        //Switch cases for each input.
        switch (input.toLowerCase()){
            case "h":{
                Card card = hand.drawCard(random);
                this.message = "You drew " + card.name + "!";
                break;
            }
            //Case if player stands.
            case "s":{
                this.isStaying = true;
                break;
            }
            //Case if player splits.
            case "sp":{
                if(deck.canSplit()){
                    int bet = hand.getBet();
                    if(this.money >= bet){
                        deck.split();
                        deck.getHand(false).placeBet(bet);
                        this.money -= bet;
                        this.message = "You have just split your deck. You are now playing with your first hand.";
                        this.playingSplitFirstHand = true;
                    }
                    else{
                        this.message = "You don't have $" + bet + " to place a bet on the split deck.";
                    }
                    break;
                }
            }
            //Case if player doubles down.
            case "dd":{
                int bet = hand.getBet();
                if(this.money >= bet){
                    this.isStaying = true;

                    hand.doubleBet();
                    this.money -= bet;

                    Card card = hand.drawCard(random);
                    this.message = "You have doubled your bet and your last card is " + card.name + "!";
                }
                else{
                    this.message = "You don't have $" + bet + " to double the bet.";
                }
                break;
            }
            //Another case for check.
            case "r":{
                this.gameLoopActive = false;
                break;
            }
            //Default casing for splitting hands.
            default:{
                if(input.equalsIgnoreCase("sp")){
                    this.message = "You are not able to split your deck because your two initial cards have different values.";
                }
                else{
                    this.message = "Invalid choice.";
                }
            }
        }
    }

    //Clearing method.
    private void clear(){
        for(int i = 0; i < 50; i++){
            System.out.println("\n");
        }
    }

    //Money method.
    public int getMoney(){
        return money;
    }
}

