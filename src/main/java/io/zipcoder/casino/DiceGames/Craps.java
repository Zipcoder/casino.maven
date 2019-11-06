package io.zipcoder.casino.DiceGames;

import io.zipcoder.casino.Interfaces.GamblingGame;
import io.zipcoder.casino.Player.GamblingPlayer;
import io.zipcoder.casino.Player.Player;
import io.zipcoder.casino.utilities.Console;


public class Craps implements GamblingGame {


    private Dice[] crapsDice;
    private GamblingPlayer crapsPlayer;
    private Console console;
    private boolean continuePlay;
    private boolean winGame;
    private Integer turn;
    private Integer targetScore;

    //------------- Constructor----------------------------
    public Craps(Player player) {
        this.crapsPlayer = new GamblingPlayer(player);
        this.crapsDice = createDice();
        this.console = new Console(System.in, System.out);
        this.continuePlay = true;
        this.turn = 0;

    }

    //------------------------------------------------------
    public Dice[] createDice() {
        Dice[] crapsDice = new Dice[2];
        for (int i = 0; i < crapsDice.length; i++) {
            crapsDice[i] = new Dice();
        }
        return crapsDice;
    }


    public void startGame() {
        console.println("Welcome to Craps!");
        do {
            promptUserForWagerAmount();
            createDice();
            crapsRoll();
            evaluateRoll();
            turn++;
        }
        while (continuePlay == true);
        promptLeaveGame();
    }



    public void crapsRoll() {
        for (Dice s : this.crapsDice) {
            console.getStringInput("Press enter to roll.");
            s.rollDice();

        }
    }

    public void evaluateRoll() {
        int sum = crapsDice[0].getValue() + crapsDice[1].getValue();
        if (turn == 0) {
            if (sum == 7 || sum == 11) {
                crapsPlayer.getWinnings();
                console.println("Winner Winner, Chicken Dinner! " );
                continuePlay = false;

            } else if (sum == 2 || sum == 3 || sum == 12) {
                console.println("You've Crapped out!!");
                continuePlay = false;

            } else {
                targetScore = sum;
                console.println("You've rolled a " + sum);
            }
        } else {
            if (sum == 7) {
                console.println("You've Crapped out!!");
                continuePlay = false;
            } else if (sum == targetScore) {
                console.println("Your Last Roll Was " + sum + "\nYour Target Score is " + targetScore);
                crapsPlayer.getWinnings();

                console.println("Winner Winner, Chicken Dinner! " );

                continuePlay = false;
            }


        }
    }


    public void promptUserForWagerAmount() {
        double userWagerAmount = console.getDoubleInput("How much would you like to wager?");
        boolean wagerAmountSuccessful = crapsPlayer.placeWager(userWagerAmount);
        while (!wagerAmountSuccessful) {
            userWagerAmount = console.getDoubleInput("Wager exceeds your balance. Please wage a smaller amount.");
            wagerAmountSuccessful = crapsPlayer.placeWager(userWagerAmount);
        }
    }


    public void promptLeaveGame() {
        console.println("Do you want to play again?");

    }


    public void displayResults() {


    }


    public void distributeWinnings() {
    }
}
