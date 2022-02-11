package com.techelevator.domain;

public class Candy extends VendingItem {
    private static final String DISPENSE_MESSAGE_PREFIX = "Munch Munch";

    public Candy(String itemName, double price) {
        super(itemName, price);
    }

    @Override
    public String getDispenseMessage() {
        return dispenseVendingItemMessage(DISPENSE_MESSAGE_PREFIX);
    }
}
