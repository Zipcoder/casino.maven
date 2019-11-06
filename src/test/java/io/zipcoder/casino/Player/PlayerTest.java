package io.zipcoder.casino.Player;

import org.junit.Assert;
import org.junit.Test;


import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void testSetBalance() {
        Player newPlayer = new Player("bogus","bogus");

        newPlayer.setBalance(50);
        double expected = 50.0;
        double actual = newPlayer.balance;

        Assert.assertEquals(expected,actual,0.0);
    }
}
