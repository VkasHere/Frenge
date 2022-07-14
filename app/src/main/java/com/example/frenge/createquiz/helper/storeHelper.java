package com.example.frenge.createquiz.helper;

public class storeHelper {

    String refe,num;

    public storeHelper() {
    }

    public storeHelper(String refe, String num) {
        this.refe = refe;
        this.num = num;
    }

    public String getRefe() {
        return refe;
    }

    public void setRefe(String refe) {
        this.refe = refe;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
