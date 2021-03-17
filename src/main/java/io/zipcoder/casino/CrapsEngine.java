package io.zipcoder.casino;

import com.sun.tools.javac.util.Assert;
import io.zipcoder.casino.utilities.Console;

public class CrapsEngine {
    Console console = new Console(System.in, System.out);
    //boolean gameOn = true;
    //PlayerWarehouse pWare = new PlayerWarehouse();
    CrapsScreens cScreens = new CrapsScreens();
    Player player = new Player("gerg", 500);
    Craps craps = new Craps(player);



    public static void main(String[] args) {
        CrapsEngine craps = new CrapsEngine();
        craps.startCrapsGame();
    }

    private void startCrapsGame() {
        cScreens.crapsWelcomeScreen();
        player.setChipBalance(100);
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input.equals(1)) {
                passOrNotPassRoundOneScreen(craps.getGameRound());
            } else if (input.equals(2)) {
                casinoLobby();
            } else {
                crapsInvalidWelcomeScreen();
            }
        }
    }

    private void crapsInvalidWelcomeScreen() {
        cScreens.crapsInvalidWelcomeScreen();
    }

    //TEMPORARY
    private void casinoLobby() {
        System.exit(0);
    }

    private void passOrNotPassRoundOneScreen(int gameRound) {
        cScreens.passOrNotPassRoundOneScreen(craps.getGameRound());
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input.equals(1)) {
                craps.setBetStatus("Pass");
                betAmountRoundOneScreen();
            } else if (input.equals(2)) {
                craps.setBetStatus("Not Pass");
                betAmountRoundOneScreen();
            } else {
                System.out.println("Please enter 1 or 2");
            }
        }
    }

    private void betAmountRoundOneScreen() {
        cScreens.betAmountRoundOneScreen(craps.getGameRound());
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input < player.getChipBalance()) {
                craps.addToPot(input);
                rollTheDice();
            } else {
                System.out.println("Insufficient Chips");
            }
        }
    }

    private void rollTheDice() {
        cScreens.rollTheDice();
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input == 1) {
                craps.sumOfDice();
                if (craps.betStatus.equals("Pass") && craps.currentSum == 7 || craps.currentSum == 11) {
                    winRollScreen();
                } else if (craps.betStatus.equals("Not Pass") && craps.currentSum == 2 || craps.currentSum == 3 || craps.currentSum == 12) {
                    winRollScreen();
                } else if (craps.betStatus.equals("Pass") && craps.currentSum == 2 || craps.currentSum == 3 || craps.currentSum == 12) {
                    loseRollScreen();
                } else if (craps.betStatus.equals("Not Pass") && craps.currentSum == 7 || craps.currentSum == 11) {
                    loseRollScreen();
                } else {
                    craps.setPointer(craps.currentSum);
                    rollAgainScreen();
                }
            }
        }
    }

    private void loseRollScreen() {
        cScreens.loseRollScreen(craps.getGameRound(), craps.getPot(), craps.currentSum, craps.getBetStatus());
    }

    private void rollAgainScreen() {
        cScreens.rollAgainScreen(craps.getGameRound(), craps.getPot(), craps.currentSum, craps.getBetStatus(), craps.getPointer());
    }

    private void winRollScreen() {
        cScreens.winRollScreen(craps.getGameRound(), craps.getPot(), craps.currentSum, craps.getBetStatus());
    }


}
