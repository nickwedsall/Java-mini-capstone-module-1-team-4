package com.techelevator;

import java.util.List;
import java.util.Map;

public class VendingMachine {
    private double balance; // Possible currency Instance of NumberFormat type... maybe
    private Map<String, List<VendingItem>> vendingItems; //TODO: make VendingItem abstract class and at least one child class

    public VendingMachine() {
        // If we populate the vending machine by reading from a file
        // we should have a load() function called in the constructor
        this.balance = 0;
        load();
    }

    private void load() {
        // populate our map
        // this is where we will read from a file
        // try-with-resources bullshit

    }
}
