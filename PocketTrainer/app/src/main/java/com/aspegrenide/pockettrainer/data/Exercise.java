package com.aspegrenide.pockettrainer.data;


import java.util.ArrayList;

public class Exercise {

    public static final int WARMUP = 0;
    public static final int GENERAL = 1;
    public static final int RELAX = 2;


    private String name;
    private String shortDescription;
    private String extraString;

    private int type;
    private int duration;
    private ArrayList<Gren> goodFor;
    private boolean isUppvarmning;
    private boolean isSelected;

    Exercise(String name, int duration, ArrayList<Gren> goodFor, int type) {
        this.name = name;
        this.duration = duration;
        if (type < 0 || type > 3) {
            // throw a tantrum, not allowed
        }
        this.type = type;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public ArrayList<Gren> getGoodFor() {
        return goodFor;
    }

    public void setGoodFor(ArrayList<Gren> goodFor) {
        this.goodFor = goodFor;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setTypeWarmup() {
        this.type = WARMUP;
    }
    public void setTypeGeneral() {
        this.type = GENERAL;
    }
    public void setTypeRelax() {
        this.type = RELAX;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
}
