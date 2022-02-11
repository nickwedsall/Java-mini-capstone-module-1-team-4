package com.techelevator.model;

import com.techelevator.domain.VendingItem;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class VendingMachine {
    private double balance; // Possible currency Instance of NumberFormat type... maybe
    private Map<String, List<VendingItem>> vendingItems; //TODO: make VendingItem abstract class and at least one child class

    public VendingMachine() {
        this.balance = 0;
        this.vendingItems = new TreeMap<>();
        // If we populate the vending machine by reading from a file
        // we should have a load() function called in the constructor
        // load();
    }
}
