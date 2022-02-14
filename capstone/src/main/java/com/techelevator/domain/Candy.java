package com.techelevator.domain;

public class Candy extends VendingItem {
    private static final String DISPENSE_MESSAGE_PREFIX = "Munch Munch";

    //TODO Candy Constructor gives appropriate name and price
    public Candy(String itemName, double price) {
        super(itemName, price);
    }

    // TODO getDispenseMessage() returns "Munch Munch, Yum!"
    @Override
    public String getDispenseMessage() {
        return dispenseVendingItemMessage(DISPENSE_MESSAGE_PREFIX);
    }

    // TODO getString() method returns itemName + NumberFormat.getCurrencyInstance.format(getPrice())
}
