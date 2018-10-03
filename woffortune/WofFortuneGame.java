/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package woffortune;
//Imports
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * WofFortuneGame class Contains all logistics to run the game
 *
 * @aauthor clatulip, James Wilson
 */
public class WofFortuneGame {

    private boolean puzzleSolved = false;//determines if the puzzle is solved or not

    private Wheel wheel;
    private Player player1;
    private String phrase = "Once upon a time";
    private Letter[] letter_array = new Letter[16];

    //Array List to hold the letters for the user generated phrase
    private ArrayList<Letter> userPhrase = new ArrayList<Letter>();
    private ArrayList<String> defaultPhrases = new ArrayList<String>();//Array List to hold the default phrases
    private ArrayList<Player> players = new ArrayList<>();//Array List to hold player objects

    Scanner sc = new Scanner(System.in);//New Scanner object

    /**
     * Constructor
     *
     * @param wheel Wheel
     * @throws InterruptedException
     */
    public WofFortuneGame(Wheel wheel) throws InterruptedException {
        // get the wheel
        this.wheel = wheel;

        // do all the initialization for the game
        setUpGame();

    }

    /**
     * Plays the game
     *
     * @throws InterruptedException
     */
    public void playGame() throws InterruptedException {
        // while the puzzle isn't solved, keep going
        while (!puzzleSolved) {
            // let the current player play
            for (int i = 0; i < players.size(); i++) {
                playTurn(players.get(i));
                if (puzzleSolved == true) {
                    break;
                }
            }

        }
    }

    /**
     * Sets up all necessary information to run the game
     */
    private void setUpGame() {
        randomPhrases();//Method to add the phrases into the Array List
        Scanner input = new Scanner(System.in);//new scanner object
        int numPlayers = 0;//initializes the number of players to 0
        Boolean bError = true;//used to break the do while loop
        do {
            try {
                System.out.println("How many people are going to play?");
                numPlayers = input.nextInt();
                bError = false;//Sets bError to false to break the loop
            } catch (InputMismatchException e) {
                //Error message when a user don't enter an integer value
                System.out.println("ERROR! Please enter an integer");
                input.next();
            }
        } while (bError);

        String pName = null;//initializes the name to null
        for (int p = 0; p < numPlayers; p++) {
            try {
                System.out.println("Player's name: ");
                pName = sc.nextLine();
            } catch (Exception e) {
                System.out.println("Error please enter a valid name");
            }
            Player player;
            player = new Player(pName);//creates a new player object
            players.add(player);//adds the new player to the list of players

        }

        // print out the rules
        System.out.println("RULES!");
        System.out.println("Each player gets to spin the wheel, to get a number value");
        System.out.println("Each player then gets to guess a letter. If that letter is in the phrase, ");
        System.out.println(" the player will get the amount from the wheel for each occurence of the letter");
        System.out.println("If you have found a letter, you will also get a chance to guess at the phrase");
        System.out.println("Each player only has three guesses, once you have used up your three guesses, ");
        System.out.println("you can still guess letters, but no longer solve the puzzle.");
        System.out.println();

        System.out.println("Would you like to enter your own phrase? (Y/N)");
        char ans = '\0';
        
        //loop that ensures that the user enters either a Y or N
        do {

            ans = sc.next().charAt(0);

            if ((ans == 'y') || (ans == 'Y')) {
                Scanner user_input = new Scanner(System.in);

                System.out.print("Enter your phrase: ");
                phrase = user_input.nextLine();
                for (int i = 0; i < phrase.length(); i++) {
                    userPhrase.add(new Letter(phrase.charAt(i)));//adds each letter object to the userPhrase array list
                }
                break;

            } else if ((ans == 'N') || (ans == 'n')) {

                Random rand = new Random();//new random object

                int random = rand.nextInt(10) + 0;
                phrase = (defaultPhrases.get(random));
                // for each character in the phrase, create a letter and add to letters array

                for (int i = 0; i < phrase.length(); i++) {
                    //letter_array[i] = new Letter(phrase.charAt(i));
                    userPhrase.add(new Letter(phrase.charAt(i)));//adds each letter object to the userPhrase array list

                }
                break;
            }
            System.out.println("Please enter Y or N");
        } while (ans != 'Y' || ans != 'y' || ans != 'N' || ans != 'n');

        // setup done
    }

    /**
     * One player's turn in the game Spin wheel, pick a letter, choose to solve
     * puzzle if letter found
     *
     * @param player
     * @throws InterruptedException
     */
    private void playTurn(Player player) throws InterruptedException {

        int money = 0;//initializes money to 0

        Scanner sc = new Scanner(System.in);//new Scanner object

        System.out.println(player.getName() + ", you have $" + player.getWinnings());//prints out the players winnings
        System.out.println("Spin the wheel! <press enter>");
        sc.nextLine();
        System.out.println("<SPINNING>");
        try {
            Thread.sleep(200);//Throws exception if the thread is interrupted
        } catch (InterruptedException e) {

            System.out.println("Thread interrupted during sleep");//Error message

        }
        int p = 0;//varible to give the prize only if the player guesses the letter correctly
        Wheel.WedgeType type = wheel.spin();//"spins" the wheel
        System.out.print("The wheel landed on: ");
        switch (type) {
            case MONEY:
                money = wheel.getAmount();//gets the amount of money on that wedge
                System.out.println("$" + money);
                break;

            case LOSE_TURN:
                System.out.println("LOSE A TURN");//player landed on a lose a turn wedge
                System.out.println("So sorry, you lose a turn.");
                return; // doesn't get to guess letter

            case BANKRUPT:
                System.out.println("BANKRUPT");//player landed on a BANKRUPT wedge
                player.bankrupt();
                return; // doesn't get to guess letter

            case PRIZE:
                System.out.println("Prize!");//player landed on a prize wedge
                p = 10;//if prize wedge is landed on set p to 10
            default:

        }
        System.out.println("");
        System.out.println("Here is the puzzle:");
        showPuzzle();
        System.out.println();
        System.out.println(player.getName() + ", please guess a letter.");
        //String guess = sc.next();

        char letter = sc.next().charAt(0);

        if (!Character.isAlphabetic(letter)) {
            System.out.println("Sorry, but only alphabetic characters are allowed. You lose your turn.");
        } else {
            // search for letter to see if it is in
            int numFound = 0;
            for (Letter l : userPhrase) {
                if ((l.getLetter() == letter) || (l.getLetter() == Character.toUpperCase(letter))) {
                    l.setFound();
                    numFound += 1;
                    p += 5;//makes it so that the player gets the prize 
                }
            }
            if (numFound == 0) {
                System.out.println("Sorry, but there are no " + letter + "'s.");
            } else {
                if (numFound == 1) {
                    System.out.println("Congrats! There is 1 letter " + letter + ":");
                    if(p == 15){
                         player.prize();//gives the player the prize
                    }
                } else {
                    System.out.println("Congrats! There are " + numFound + " letter " + letter + "'s:");
                     if(p== 15){
                         player.prize();//gives the player a prize
                     }
                }
                System.out.println();
                showPuzzle();//shows the puzzle
                System.out.println();
                player.incrementScore(numFound * money);//increments the score of the player
                System.out.println("Prizes " + player.playerPrizes);//displays the players prizes
                //displays the earned money and total winnings
                System.out.println("You earned $" + (numFound * money) + ", and you now have: $" + player.getWinnings());
                
                System.out.println("Would you like to try to solve the puzzle? (Y/N)");
                try{
                letter = sc.next().charAt(0);
                System.out.println();
                }catch(Exception e){
                    System.out.println("Please enter a Y or N");
                }
                if ((letter == 'Y') || (letter == 'y')) {
                    solvePuzzleAttempt(player);
                }
            }

        }

    }

    /**
     * Logic for when user tries to solve the puzzle
     *
     * @param player
     */
    private void solvePuzzleAttempt(Player player) {

        if (player.getNumGuesses() >= 3) {
            System.out.println("Sorry, but you have used up all your guesses.");
            return;
        }

        player.incrementNumGuesses();
        System.out.println("What is your solution?");
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        String guess = sc.next();
        if (guess.compareToIgnoreCase(phrase) == 0) {
            System.out.println("Congratulations! You guessed it!");
            puzzleSolved = true;

            int winner = player.getWinnings();//gets the winning player's money value

            Player winningPlayer = new Player(player.getName());
            for (int i = 0; i < players.size(); i++) {
                player = players.get(i);

                System.out.println("Player: " + player.getName());
                System.out.println(player.getWinnings());
                System.out.print("Prize(s): ");
                for (int j = 0; j < player.playerPrizes.size(); j++) {
                    System.out.println(player.playerPrizes.get(j));
                    
                }
                System.out.println("");
                if (player.getWinnings() > winner) {
                    winner = player.getWinnings();//determines which player has the most money
                }

            }
            System.out.println("");
            System.out.println(winningPlayer.getName() + " is the winner!");//displays the winner

        } else {
            System.out.println("Sorry, but that is not correct.");
        }
    }

    /**
     * Display the puzzle on the console
     */
    private void showPuzzle() {
        System.out.print("\t\t");
        for (Letter l : userPhrase) {
            if (l.isSpace()) {
                System.out.print("   ");
            } else if (l.isFound()) {
                System.out.print(Character.toUpperCase(l.getLetter()) + " ");
            } else {
                System.out.print(" _ ");
            }
        }
        System.out.println();

    }

    /**
     * For a new game reset player's number of guesses to 0
     */
    public void reset() {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).reset();
        }

    }

    /**
     * Method to generate a random phrase if the user chooses to not make his or
     * her own phrase
     */
    public void randomPhrases() {
        String phrase1 = "The Way She Goes";
        defaultPhrases.add(phrase1);
        String phrase2 = "Girls Just Wanna Have Fun";
        defaultPhrases.add(phrase2);
        String phrase3 = "I get older they stay the same age";
        defaultPhrases.add(phrase3);
        String phrase4 = "Dead Head";
        defaultPhrases.add(phrase4);
        String phrase5 = "Bruce Almighty Long";
        defaultPhrases.add(phrase5);
        String phrase6 = "Sixty Minute Man";
        defaultPhrases.add(phrase6);
        String phrase7 = "Lynyrd Skynyrd";
        defaultPhrases.add(phrase7);
        String phrase8 = "Lawrence Kansas";
        defaultPhrases.add(phrase8);
        String phrase9 = "Rocky Mountain High";
        defaultPhrases.add(phrase9);
        String phrase10 = "Meet me tonight in Atlantic City";
        defaultPhrases.add(phrase10);

    }

}
