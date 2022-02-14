package com.techelevator.domain;

import org.junit.Assert;
import org.junit.Test;

public class CandyTest {

    @Test
    public void Candy_constructor_gives_correct_instance_variable_assignment() {
        // Arrange
        Candy testCandy = new Candy("testCandyName", 1.00);
        String expectedName = "testCandyName";
        double expectedPrice = 1.00;

        // Act
        String actualName = testCandy.getItemName();
        double actualPrice = testCandy.getPrice();

        // Assert
        Assert.assertEquals("String itemName in constructor DID NOT assign name properly", expectedName, actualName);
        Assert.assertEquals("double price in constructor DID NOT assign price properly", expectedPrice, actualPrice, 0.0);
    }
}
