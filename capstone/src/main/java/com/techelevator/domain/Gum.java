package com.techelevator.domain;

public class Gum extends VendingItem {
    private static final String DISPENSE_MESSAGE_PREFIX = "Chew Chew";
    //TODO Gum Constructor gives appropriate name and price
    public Gum(String itemName,double price) {
        super(itemName, price);
    }

    // TODO getDispenseMessage() returns "Chew Chew, Yum!"
    @Override
    public String getDispenseMessage() {
        return dispenseVendingItemMessage(DISPENSE_MESSAGE_PREFIX);
    }

    // TODO getString() method returns itemName + NumberFormat.getCurrencyInstance.format(getPrice())
}
