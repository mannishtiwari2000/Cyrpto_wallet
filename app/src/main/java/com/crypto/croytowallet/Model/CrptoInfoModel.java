package com.crypto.croytowallet.Model;

public class CrptoInfoModel {
    String id,Name,Image,CurrentPrice,currencyRate,high_price,low_price,symbol;

    public CrptoInfoModel(String id, String name, String image, String currentPrice, String currencyRate,String high_price,String low_price,String symbol) {
        this.id = id;
        this.Name = name;
        this.Image = image;
        this.CurrentPrice = currentPrice;
        this.currencyRate = currencyRate;
        this.high_price=high_price;
        this.low_price=low_price;
        this.symbol=symbol;
    }

    public CrptoInfoModel() {
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getHigh_price() {
        return high_price;
    }

    public void setHigh_price(String high_price) {
        this.high_price = high_price;
    }

    public String getLow_price() {
        return low_price;
    }

    public void setLow_price(String low_price) {
        this.low_price = low_price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getCurrentPrice() {
        return CurrentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        CurrentPrice = currentPrice;
    }

    public String getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(String currencyRate) {
        this.currencyRate = currencyRate;
    }
}
