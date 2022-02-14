package com.techelevator.domain;

public class Chip extends VendingItem {
    private static final String DISPENSE_MESSAGE_PREFIX = "Crunch Crunch";

    //TODO Chip Constructor gives appropriate name and price
    public Chip(String itemName, double price) {
        super(itemName, price);
    }

    // TODO getDispenseMessage() returns "Crunch Crunch, Yum!"
    @Override
    public String getDispenseMessage() {
        return dispenseVendingItemMessage(DISPENSE_MESSAGE_PREFIX);
    }

    // TODO getString() method returns itemName + NumberFormat.getCurrencyInstance.format(getPrice())
}
