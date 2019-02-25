package io.zipcoder.casino.Casino;

import io.zipcoder.casino.Cards.Card;
import io.zipcoder.casino.Cards.Games.BlackJack;
import io.zipcoder.casino.utilities.Console;
import org.junit.Assert;
import org.junit.Test;
import sun.tools.tree.AssignShiftLeftExpression;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("all")
public class BlackJackTest {



    private Console console;

    @Test
    public void dealTwoCardsFirstCardTest() {

        //Given
        BlackJack blackJack = new BlackJack();
        List<Card> userHand = new ArrayList<Card>();

       //When
        blackJack.dealTwoCards(userHand);


        //Then
        Assert.assertTrue(userHand.get(0)!=null);

    }

    @Test

    public void dealTwoCardsSecondCardTest(){

        //Given
        BlackJack blackJack = new BlackJack();
        List<Card> userHand = new ArrayList<Card>();
        int expected = 2;
        //When
        blackJack.dealTwoCards(userHand);

        //Then
        Assert.assertEquals(expected, userHand.size());

    }

    @Test

    public void dealOneCardTest() {

        //Given
        BlackJack blackJack = new BlackJack();
        List<Card> userhand = new ArrayList<Card>();

        //When
        blackJack.dealOneCard(userhand);

        //Then
        Assert.assertTrue(userhand.get(0) !=null);
    }
    @Test

    public void testTakingUserBet() {
        String input = "10\n";
        ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Console testConsole = new Console(bais, new PrintStream(baos));

        String output = baos.toString();

        //Given
        BlackJack blackJack = new BlackJack(testConsole);
        blackJack.getUserBet();

        //Then
        Assert.assertEquals((int)blackJack.getUserBetAsInteger(), 10);
    }

//    private Console getConsoleWithBufferedInput(String inputString) {
//        byte[] inputBytes = inputString.getBytes();
//        ByteArrayInputStream inputByteArray = new ByteArrayInputStream(inputBytes);
//        Scanner scanner = new Scanner(inputByteArray);
//        Console blackJackConsole = Console.getConsole();
//        return blackJackConsole;
//    }

    public Console getConsoleWithBufferedInputAndOutput(String input, ByteArrayOutputStream baos){
        ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
        Console testConsole = new Console(bais, new PrintStream(baos));

       return testConsole;
    }


}
