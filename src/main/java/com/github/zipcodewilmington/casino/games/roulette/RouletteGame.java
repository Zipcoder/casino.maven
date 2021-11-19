package com.github.zipcodewilmington.casino.games.roulette;

import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;
import com.sun.org.apache.bcel.internal.generic.ARETURN;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class RouletteGame implements GameInterface {
    private Integer[] red;
    private Integer[] black;
    private Integer choice;
    private Integer wins;
    private Integer loses;
    private Integer spins;
    private Double betAmount;
    private Double balance;
    private String response;
    private Integer rouletteNumber;
    private RoulettePlayer roulettePlayer;


    private final IOConsole consoleRed = new IOConsole(AnsiColor.PURPLE);
    // private final IOConsole console2 = new IOConsole(AnsiColor.GREEN);

    public RouletteGame() {

        this.roulettePlayer = new RoulettePlayer(); //Composition
        this.balance = roulettePlayer.getArcadeAccount().getBalance();
        this.rouletteNumber = null;
        this.red = new Integer[]{1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
        this.black = new Integer[]{2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35};
    }


    public void printBalance(){
        System.out.println(getBalance());

    }


    public String evenChoice(Integer randomNumber, Double betAmount) {

      //this.rouletteNumber = ThreadLocalRandom.current().nextInt(1, 36);
       consoleRed.println("Roulette Number: " + randomNumber);
        String result = "";

        if (randomNumber % 2 == 0) {
           balance += betAmount * 2;
            result = ("you won $" + betAmount * 2);


        } else {
           balance -= betAmount;
            result = "you lost $" + (betAmount);
        }

//        consoleRed.println(result);
//        consoleRed.println("your balance is: " + balance);
        return  (result + "\nYour balance is: $" + balance);
    }

    public String oddChoice(Integer randomNumber, Double betAmount) {
       // this.rouletteNumber = ThreadLocalRandom.current().nextInt(1, 36);
        consoleRed.println("Roulette Number: " + randomNumber);
        String result = "";

        if (randomNumber % 2 == 1) {
            balance += betAmount * 2;
            result = "you won $" + (betAmount * 2);

        } else {
            balance -= betAmount;
            result = "you lost $" + (betAmount);
        }

//      consoleRed.print(result);
//       consoleRed.println("\nYour Balance is: $" + balance);
       return (result + "\nYour balance is: $"+ balance);
    }

    public String redChoice(Integer randomNumber, Double betAmount) {
     //   this.rouletteNumber = ThreadLocalRandom.current().nextInt(1, 36);
        consoleRed.println("Roulette Number: " + randomNumber);
        String result = "";


       // for (Integer num : getRed()) {


            if (Arrays.asList(this.red).contains(randomNumber)) {
                balance += betAmount * 2;
                result = "you won $" + (betAmount * 2);
            } else {
                balance -= betAmount;
                result = "you lost $" + (betAmount);


            }

        //}

//        consoleRed.println(result);
//        consoleRed.println("Your Balance is: $" + balance);
        return (result + "\nYour balance is: $" + balance);
    }

    public String blackChoice(Integer randomNumber, Double betAmount) {
      //  this.rouletteNumber = ThreadLocalRandom.current().nextInt(1, 36);
        String result = "";
        consoleRed.println("Roulette Number: " + randomNumber);

            if (Arrays.asList(this.black).contains(randomNumber)) {
                balance += betAmount * 2;
                result = "you won $" + (betAmount * 2);

            }
            else {
                balance -= betAmount;
                result = "you lost $" + (betAmount);

            }

//        consoleRed.println(result);
//        consoleRed.println("Your Balance is: $" + balance);
        return (result + "\nYour balance is: $" + balance);
    }

    public String pickNumberChoice(Integer randomNumber, Double betAmount, Integer pickedNumber){
      //  this.rouletteNumber = ThreadLocalRandom.current().nextInt(1, 36);
        String result = "";

        Integer number = pickedNumber;

        if(randomNumber == number){
         balance += betAmount * 35;
            result = ("you won $" + betAmount * 35);

        } else {
        balance -= betAmount;
            result = "you lost $" + (betAmount);
        }
//     consoleRed.println(result);
//     consoleRed.println("Your Balance is: $" + balance);
     return (result + "\nYour balance is: $" + balance);
    }

    public String betweenOneAndEighteen(Integer randomNumber, Double betAmount){
     //   this.rouletteNumber = ThreadLocalRandom.current().nextInt(1, 36);
     String result ="" ;
      consoleRed.println("Roulette Number: " + randomNumber);
     if(randomNumber >= 1 && randomNumber <= 18){
         balance += betAmount * 2;
        result = "you won $" + (betAmount * 2);

     }else {
         balance -= betAmount;
       result = "you lost $" + (betAmount)  ;
     }

//     consoleRed.println(result);
//        consoleRed.println("Your Balance is: $" + balance);
        return (result + "\nYour balance is: $" + balance);
    }

    public String betweenNineteenAndThirtySix(Integer randomNumber, Double betAmount){
      //  this.rouletteNumber = ThreadLocalRandom.current().nextInt(1, 36);
        String result ="" ;
        consoleRed.println("Roulette Number: " + randomNumber);

        if(randomNumber >= 19 && randomNumber <= 36){
            balance += betAmount * 2;
            result = "you won $" + (betAmount *2);

        }else {
            balance -= betAmount;
            result = "you lost $" + (betAmount)  ;
        }

//        consoleRed.println(result);
//        consoleRed.println("Your Balance is: $" + balance);
        return (result + "\nYour balance is: $" + balance);
    }


    public Double rouletteGame() {
        consoleRed.println("Welcome to the Roulette Game!!!");
        this.balance = consoleRed.getDoubleInput("Please make deposit to your account ");
     do {

            this.betAmount = consoleRed.getDoubleInput("Please enter amount you would like to bet");
            consoleRed.println("PLease choose from the options below");
            choice = consoleRed.getIntegerInput("(1)Red  (2)Black  (3)Even  (4)Odd  (5)Pick Number (6)Low 1 to 18  (7)High 19 to 36");

            while (choice < 0 || choice > 7) {
                consoleRed.println("PLease choose from the options below");
                choice = consoleRed.getIntegerInput("(1)Red  (2)Black  (3)Even  (4)Odd  (5)Pick Number (6)Low 1 to 18  (7)High 19 to 36");
            }

            if (choice == 1) {
                this.rouletteNumber = ThreadLocalRandom.current().nextInt(1, 36);
               consoleRed.println(redChoice(this.rouletteNumber, this.betAmount));

            } else if (choice == 2) {
                this.rouletteNumber = ThreadLocalRandom.current().nextInt(1, 36);
              consoleRed.println(blackChoice(this.rouletteNumber, this.betAmount));

            } else if (choice == 3) {
                this.rouletteNumber = ThreadLocalRandom.current().nextInt(1, 36);
               consoleRed.println(evenChoice(this.rouletteNumber, this.betAmount));

            } else if (choice == 4) {
                this.rouletteNumber = ThreadLocalRandom.current().nextInt(1, 36);
               consoleRed.println(oddChoice(this.rouletteNumber, this.betAmount));

            } else if (choice == 5) {
                this.rouletteNumber = ThreadLocalRandom.current().nextInt(1, 36);
                //System.out.println(rouletteNumber); //to test if number matches
              consoleRed.println(pickNumberChoice(this.rouletteNumber, this.betAmount, consoleRed.getIntegerInput("Place your bet on number(1-36): ")));

            } else if (choice == 6) {
                this.rouletteNumber = ThreadLocalRandom.current().nextInt(1, 36);
               consoleRed.println(betweenOneAndEighteen(this.rouletteNumber, this.betAmount));

            } else if (choice == 7) {
                this.rouletteNumber = ThreadLocalRandom.current().nextInt(1, 36);
               consoleRed.println(betweenNineteenAndThirtySix(this.rouletteNumber, this.betAmount));
            }


         response =  consoleRed.getStringInput("Would you like to play another game? (Y/N) ");
            if(response.equals("N") || response.equals("n")){
               break;
            }

        }while (true);

     consoleRed.println("THANK YOU!!!");
        return null;
    }


    public Integer[] getRed() {
        return red;
    }

    public Integer[] getBlack() {
        return black;
    }

    public Integer getChoice() {
        return choice;
    }

    public void setChoice(Integer choice) {
        this.choice = choice;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getLoses() {
        return loses;
    }

    public void setLoses(Integer loses) {
        this.loses = loses;
    }

    public Integer getSpins() {
        return spins;
    }

    public void setSpins(Integer spins) {
        this.spins = spins;
    }

    public Double getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(Double betAmount) {
        this.betAmount = betAmount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getRouletteNumber() {
        return rouletteNumber;
    }


    @Override
    public void add(PlayerInterface player) {

    }

    @Override
    public void remove(PlayerInterface player) {

    }

    @Override
    public void run() {
        RouletteGame rouletteGame = new RouletteGame();
        Double money = rouletteGame.rouletteGame();
    }
}
