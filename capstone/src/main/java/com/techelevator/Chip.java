package com.techelevator;

public class Chip extends VendingItem {
    private static final String DISPENSE_MESSAGE_PREFIX = "Crunch Crunch";

    public Chip(String itemName, double price) {
        super(itemName, price);
    }

    @Override
    public String getDispenseMessage() {
        return dispenseVendingItemMessage(DISPENSE_MESSAGE_PREFIX);
    }
}
