package com.techelevator.domain;

public class Drink extends VendingItem {
    private static final String DISPENSE_MESSAGE_PREFIX = "Glug Glug";

    //TODO Drink Constructor gives appropriate name and price
    public Drink(String itemName, double price) {
        super(itemName, price);
    }

    // TODO getDispenseMessage() returns "Glug Glug, Yum!"
    @Override
    public String getDispenseMessage() {
        return dispenseVendingItemMessage(DISPENSE_MESSAGE_PREFIX);
    }

    // TODO getString() method returns itemName + NumberFormat.getCurrencyInstance.format(getPrice())
}
