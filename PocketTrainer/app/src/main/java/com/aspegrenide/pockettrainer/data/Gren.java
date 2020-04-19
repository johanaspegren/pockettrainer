package com.aspegrenide.pockettrainer.data;


import java.security.PublicKey;

public class Gren {
    private String name;
    private int id;

    public Gren(String name){
        this.name = name;
    }

    public Gren(String name, int id){
        this.name = name;
        this.id = id;
    }

    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
