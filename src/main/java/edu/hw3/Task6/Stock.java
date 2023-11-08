package edu.hw3.Task6;

import org.jetbrains.annotations.NotNull;

public class Stock implements Comparable<Stock> {

    public Stock(String companyName, int price) {
        setCompanyName(companyName);
        setPrice(price);
    }

    @Override
    public int compareTo(@NotNull Stock stock) {
        return (this.getPrice() - stock.getPrice());
    }

    private int price;

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    private String companyName;

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
