package com.example.frenge.dashboard;

import androidx.annotation.Keep;

@Keep
public class dashhelper {
   private String Name,Score,Key,Frenge;

    public dashhelper() {
    }
    public dashhelper(String Name, String Score, String Key, String Frenge) {
        this.Name = Name;
        this.Score = Score;
        this.Key = Key;
        this.Frenge = Frenge;
    }
    public String getFrenge() {
        return Frenge;
    }

    public void setFrenge(String frenge) {
        Frenge = frenge;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        this.Score = score;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        this.Key = key;
    }
}
