package com.example.bmicalc_v2;

public class ShoppingItem {
    private String name;
    private boolean purchased;
    private final String uniqueId;

    public ShoppingItem(String name, boolean purchased) {
        this.name = name;
        this.purchased = purchased;
        this.uniqueId = generateUniqueId(name);
    }

    private String generateUniqueId(String name) {
        return name.replaceAll("\\s+", "_").toLowerCase();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    public String getUniqueId() {
        return uniqueId;
    }
}