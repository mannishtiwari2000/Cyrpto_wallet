package com.crypto.croytowallet.Model;

public class Coin_Model {
    String Coin_amount;
    String Coin_name;
    String Coin_Change;
    String Coin_Current_Change;
    String Image;

    public Coin_Model(String coin_amount, String coin_name, String coin_Change, String coin_Current_Change, String image) {
        Coin_amount = coin_amount;
        Coin_name = coin_name;
        Coin_Change = coin_Change;
        Coin_Current_Change = coin_Current_Change;
        Image = image;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public Coin_Model() {
    }

    public String getCoin_amount() {
        return Coin_amount;
    }

    public void setCoin_amount(String coin_amount) {
        Coin_amount = coin_amount;
    }

    public String getCoin_name() {
        return Coin_name;
    }

    public void setCoin_name(String coin_name) {
        Coin_name = coin_name;
    }

    public String getCoin_Change() {
        return Coin_Change;
    }

    public void setCoin_Change(String coin_Change) {
        Coin_Change = coin_Change;
    }

    public String getCoin_Current_Change() {
        return Coin_Current_Change;
    }

    public void setCoin_Current_Change(String coin_Current_Change) {
        Coin_Current_Change = coin_Current_Change;
    }
}
