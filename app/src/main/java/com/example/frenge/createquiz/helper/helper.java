package com.example.frenge.createquiz.helper;

import androidx.annotation.Keep;

@Keep
public class helper {

    String img1,img2,img3,img4;
    String opp1,opp2,opp3,opp4,num,que,right;

    public helper() {
    }

    public helper(String img1, String img2, String img3, String img4, String opp1, String opp2, String opp3, String opp4, String num, String que, String right) {
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.img4 = img4;
        this.opp1 = opp1;
        this.opp2 = opp2;
        this.opp3 = opp3;
        this.opp4 = opp4;
        this.num = num;
        this.que = que;
        this.right = right;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getImg4() {
        return img4;
    }

    public void setImg4(String img4) {
        this.img4 = img4;
    }

    public String getOpp1() {
        return opp1;
    }

    public void setOpp1(String opp1) {
        this.opp1 = opp1;
    }

    public String getOpp2() {
        return opp2;
    }

    public void setOpp2(String opp2) {
        this.opp2 = opp2;
    }

    public String getOpp3() {
        return opp3;
    }

    public void setOpp3(String opp3) {
        this.opp3 = opp3;
    }

    public String getOpp4() {
        return opp4;
    }

    public void setOpp4(String opp4) {
        this.opp4 = opp4;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getQue() {
        return que;
    }

    public void setQue(String que) {
        this.que = que;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }
}
