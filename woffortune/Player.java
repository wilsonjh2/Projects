/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package woffortune;

import java.util.ArrayList;

/**
 * Class that defines a player for a game with monetary winnings and a limited
 * number of choices
 *
 * @author clatulip, James Wilson
 */
public class Player {

    private int winnings = 0; // amount of money won
    private String name; // player's name
    private int numGuesses = 0; // number of times they've tried to solve puzzle
    ArrayList<String> prizes = new ArrayList<String>();
    ArrayList<String> playerPrizes = new ArrayList<String>();

    /**
     * Constructor
     *
     * @param name String that is the player's name
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Getter
     *
     * @return int amount of money won so far
     */
    public int getWinnings() {
        return winnings;
    }

    /**
     * Adds amount to the player's winnings
     *
     * @param amount int money to add
     */
    public void incrementScore(int amount) {
        if (amount < 0) {
            return;
        }
        this.winnings += amount;
    }

    /**
     * Getter
     *
     * @return String player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter
     *
     * @return int the number of guesses used up
     */
    public int getNumGuesses() {
        return numGuesses;
    }

    /**
     * Add 1 to the number of guesses used up
     */
    public void incrementNumGuesses() {
        this.numGuesses++;
    }

    /**
     * Resets for a new game (only number of guesses) This does not reset the
     * winnings.
     */
    public void reset() {
        this.numGuesses = 0;
    }

    public void bankrupt() {
        this.winnings = 0;//resets the players winnings
        for (int i = 0; i < playerPrizes.size(); i++) {//loops through and removes all the players prizes
            this.playerPrizes.remove(i);
        }
        System.out.println("You lose all money and prizes :/");//Message that informs the user of their misfortune

    }

    /**
     * This method generates a random prize
     */
    public void prize() {
        randomPrizes();//adds all prizes the list
        int value = (int) (Math.random() * 6 + 0);//generates a random number
        String prize = this.prizes.get(value);//gets the prize
        playerPrizes.add(prize);//adds the prize to the players list of prizes

    }

    /**
     * This method adds all the prizes to the prizes array list
     */
    public void randomPrizes() {
        String prize1 = "A NEW CAR!";
        prizes.add(prize1);

        String prize2 = "A microwave!";
        prizes.add(prize2);

        String prize3 = "A mail-order bride!";
        prizes.add(prize3);

        String prize4 = "A lolipop!";
        prizes.add(prize4);

        String prize5 = "A Phish concert ticket!";
        prizes.add(prize5);

        String prize6 = "All expense paid trip to Antartica";
        prizes.add(prize6);

        String prize7 = "An A in ITSC 1213!!";
        prizes.add(prize7);

    }
}
