package com.example.frenge;

public class sliderhelper {
    int image;
    String head;
    String des;

    public sliderhelper() {
    }

    public sliderhelper(int image, String head, String des) {
        this.image = image;
        this.head = head;
        this.des = des;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
