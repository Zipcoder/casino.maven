package io.zipcoder.casino.CardGames;

import io.zipcoder.casino.Interfaces.GamblingGame;
import io.zipcoder.casino.Player.GamblingPlayer;
import io.zipcoder.casino.Player.Player;
import io.zipcoder.casino.utilities.Console;

import java.util.HashMap;


public class BlackJack implements GamblingGame {
    Console input = new Console(System.in, System.out);
    GamblingPlayer gamblingPlayer;
    CardHand gamblingPlayerHand;
    Deck blackJackDeck;
    CardHand computerHand;
    boolean winGame = false;


    private Player user;
    private int wageBucket;
    private Card card;

    public Integer playerRefactoredHandValue;
    public Integer dealerRefactoredHandValue;

    public boolean playerHasBlackJack;
    public boolean dealerHasBlackJack;
    public boolean playerAndDealerHaveBlackJack;
    public boolean playerBust;
    public boolean dealerBust;
    public boolean playerWin;
    public boolean dealerWin;
    public boolean playerDealerPush;


    public BlackJack(Player user) {
        this.gamblingPlayer = new GamblingPlayer(user);
    }


    public void startGame() {
        playBlackJack();
    }

    public void playBlackJack() {
        initializeBlackJackHands();
        checkInitialHandValue(gamblingPlayerHand);
        checkHandForAces(gamblingPlayerHand);

//        playerHasBlackJack = (playerRefactoredHandValue == 21);
//        dealerHasBlackJack = (dealerRefactoredHandValue == 21);
//        playerAndDealerHaveBlackJack = (playerHasBlackJack && dealerHasBlackJack);

        refactorAndCalculateHandValue(checkInitialHandValue(gamblingPlayerHand), checkHandForAces(gamblingPlayerHand));
        checkInitialHandValue(computerHand);
        checkHandForAces(computerHand);
        refactorAndCalculateHandValue(checkInitialHandValue(computerHand), checkHandForAces(computerHand));

    }


    public void promptUserForWagerAmount() {
        double userWagerAmount = input.getDoubleInput("How much would you like to wager?");
        boolean wagerAmountSuccessful = gamblingPlayer.placeWager(userWagerAmount);
        while (!wagerAmountSuccessful) {
            userWagerAmount = input.getDoubleInput("Wager exceeds your balance. Please wage a smaller amount.");
            wagerAmountSuccessful = gamblingPlayer.placeWager(userWagerAmount);
        }
    }

    public void initializeBlackJackHands() {

        blackJackDeck = new Deck(1);
        blackJackDeck.shuffleDeck();
        gamblingPlayerHand = new CardHand(blackJackDeck.dealCards(2));
        computerHand = new CardHand(blackJackDeck.dealCards(2));
    }


    //takes arrayList of cards from hand and separates them by card and totals point based on default Ace value of 11.
    public Integer checkInitialHandValue(CardHand hand) {
        Integer handValueForDefaultAce11 = 0;

        for( Card card : hand.userHand) {
            Rank rank = card.getRank();
            handValueForDefaultAce11 += (Integer) blackJackCardRank.get(rank);
        }
        return handValueForDefaultAce11;
    }

    //counts the number of Aces in cardHand.
    public Integer checkHandForAces(CardHand hand) {
        Integer aceCounter = 0;
        for (Card card : hand.userHand){
            if (card.getRank() == Rank.ACE) {
                aceCounter++;
            }
        }
        return aceCounter;
    }


    //evaluates score of blackjack hand and determines whether or not the value of each Ace should remain at 11 points or be reassigned to 1 point. Returns new hand value.
    public Integer refactorAndCalculateHandValue(Integer handValueForDefaultAce11, Integer numberOfAces) {
        Integer refactoredHandValue = handValueForDefaultAce11;     //default set to no changes.
        if (numberOfAces == 1 && handValueForDefaultAce11 > 21) {
            refactoredHandValue = handValueForDefaultAce11 - 10;        //one Ace value changed to 1 point.
        } else if (numberOfAces == 2 && handValueForDefaultAce11 > 21) {
            if (handValueForDefaultAce11 - 10 <= 21) {
                refactoredHandValue = handValueForDefaultAce11 - 10;    //one Ace value changed to 1 point.
            } else {
                refactoredHandValue = handValueForDefaultAce11 - 20;    //two Ace values changed to 1 point.
            }
        } else if (numberOfAces == 3 && handValueForDefaultAce11 > 21) {
            if (handValueForDefaultAce11 - 20 <= 21) {
                refactoredHandValue = handValueForDefaultAce11 - 20;
            } else {
                refactoredHandValue = handValueForDefaultAce11 - 30;        //three Ace values change to 1 point.
            }
        }
        return refactoredHandValue;
    }

//    public Integer playerRefactoredHandValue = refactorAndCalculateHandValue(checkInitialHandValue(gamblingPlayerHand), checkHandForAces(gamblingPlayerHand));
//    public Integer dealerRefactoredHandValue = refactorAndCalculateHandValue(checkInitialHandValue(computerHand), checkHandForAces(computerHand));
//
//    public boolean playerHasBlackJack = playerRefactoredHandValue == 21;
//    public boolean dealerHasBlackJack = dealerRefactoredHandValue == 21;
//    public boolean playerAndDealerHaveBlackJack = playerHasBlackJack && dealerHasBlackJack;
//    public boolean playerBust = playerRefactoredHandValue > 21;
//    public boolean dealerBust = dealerRefactoredHandValue > 21;
//    public boolean playerWin = !playerBust && playerRefactoredHandValue > dealerRefactoredHandValue;
//    public boolean dealerWin = !dealerBust && dealerRefactoredHandValue > playerRefactoredHandValue;
//    public boolean playerDealerPush = playerBust && dealerBust || playerRefactoredHandValue == dealerRefactoredHandValue;


//    public void displayPostInitialFlopWinner() {
//        if(playerHasBlackJack && !dealerHasBlackJack)   {
//            input.getIntegerInput();
//
//    }


    public Integer displayScore() {
        if (playerAndDealerHaveBlackJack) {
            return displayPlayerNDealerPushWithBlackJack();
        } else if (playerHasBlackJack && !dealerHasBlackJack) {
            return displayPlayerWinsWithBlackJack(" You got BlackJack!\n", "Your hand: ", "Press 1 to play again or 2 to quit.\n");
        } else if (dealerHasBlackJack && !playerHasBlackJack) {
            return dealerWinsWithBlackJack(" Dealer got BlackJack!\n", "Your hand: ", "Press 1 to play again or 2 to quit.\n");
        } else {
            return displayPlayerNDealerPush("Push!\n", "Your hand: ");
        }

    }

    private Integer displayPlayerWinsWithBlackJack(String s, String s2, String s3) {
        return input.getIntegerInput(s +
                s2 + gamblingPlayerHand.toString() + "\n" +
                "Dealer hand: " + computerHand.toString() + "\n" +
                s3);
    }

    private Integer dealerWinsWithBlackJack(String s, String s2, String s3) {
        return displayPlayerWinsWithBlackJack(s, s2, s3);
    }

    private Integer displayPlayerNDealerPush(String s, String s2) {
        return dealerWinsWithBlackJack(s, s2, "Press 1 to play again or 2 to quit.\n");
    }

    private Integer displayPlayerNDealerPushWithBlackJack() {
        return displayPlayerNDealerPush(" You and Dealer both got BlackJack!\n", "Your hand:  ");
    }


    public Integer promptHitOrStay() {
        Integer hitOrStayUserInput = input.getIntegerInput("Press 1 to hit or 2 to stay.");
        while (hitOrStayUserInput != 1 || hitOrStayUserInput != 0) {
            hitOrStayUserInput = input.getIntegerInput("Invalid key, press 1 to hit or 2 to stay.");
        }
        return hitOrStayUserInput;
    }

    public void hitOrStayAction(CardHand hand, Integer hitOrStayUserInput) {
        if (hitOrStayUserInput == 1) {
            hand.userHand.add(blackJackDeck.drawCard());
        } else {

        }

    }

    public void stay() {
    }

    public void distributeWinnings() {
    }

    public void checkCardValue(Card firstCard, Card secondCard) {
    }

    public void getWinner(CardHand dealer, CardHand player) {
    }

    public void promptLeaveGame() {
    }

    private HashMap blackJackCardRank = new HashMap() {{

        put(Rank.TWO, 2);
        put(Rank.THREE, 3);
        put(Rank.FOUR, 4);
        put(Rank.FIVE, 5);
        put(Rank.SIX, 6);
        put(Rank.SEVEN, 7);
        put(Rank.EIGHT, 8);
        put(Rank.NINE, 9);
        put(Rank.TEN, 10);
        put(Rank.ACE, 11);
        put(Rank.JACK, 10);
        put(Rank.QUEEN, 10);
        put(Rank.KING, 10);
    }};


}



