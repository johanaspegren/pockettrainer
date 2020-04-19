package com.aspegrenide.pockettrainer.data;

import java.util.ArrayList;
import java.util.Arrays;

public class Resources {

    private ArrayList<Gren> allaGrenar;

    private static Gren mSprint = new Gren("Sprint");
    private static Gren mHack = new Gren("Häck");
    private static Gren mMedeldistans = new Gren("Medeldistans");
    private static Gren mStafett = new Gren("Stafett");
    private static Gren mLangdistans = new Gren("Långdistans");

    private static Gren mLangd = new Gren("Längd");
    private static Gren mHojd = new Gren("Höjd");
    private static Gren mTresteg = new Gren("Tresteg");
    private static Gren mStav = new Gren("Stav");

    private static Gren mKula = new Gren("Kula");
    private static Gren mDiskuss = new Gren("Diskuss");
    private static Gren mSpjut = new Gren("Spjut");
    private static Gren mSlagga = new Gren("Slägga");

    private static Gren mKoordination = new Gren("Koordination");
    private static Gren mKondition = new Gren("Kondition");
    private static Gren mStyrka = new Gren("Styrka");
    private static Gren mSnabbhet = new Gren("Snabbhet");


    public static ArrayList<Gren> getGrenar() {

        ArrayList<Gren> allGrenar = new ArrayList<Gren>();
        allGrenar.add(mSprint);
        allGrenar.add(mHack);
        allGrenar.add(mMedeldistans);
        allGrenar.add(mStafett);
        allGrenar.add(mLangdistans);

        allGrenar.add(mHojd);
        allGrenar.add(mLangd);
        allGrenar.add(mTresteg);
        allGrenar.add(mStav);

        allGrenar.add(mKula);
        allGrenar.add(mDiskuss);
        allGrenar.add(mSpjut);
        allGrenar.add(mSlagga);

        allGrenar.add(mKondition);
        allGrenar.add(mKoordination);
        allGrenar.add(mStyrka);
        allGrenar.add(mSnabbhet);

        return allGrenar;
    }

    public static ArrayList<Exercise> getExercises() {

        ArrayList<Gren> allGrenar = getGrenar();
        ArrayList<Exercise> allExersices = new ArrayList<Exercise>();

        Exercise simringen = new Exercise("Simringen", 3, null, Exercise.WARMUP);
        Exercise radbandet = new Exercise("Radbandet", 7, null, Exercise.WARMUP);
        Exercise triangledramat = new Exercise("Triangeldramet", 7, null, Exercise.WARMUP);
        Exercise lopskola = new Exercise("Löpskola", 15, null, Exercise.GENERAL);

        simringen.setGoodFor(allGrenar);
        simringen.setShortDescription("simringen är kul därför att...");
        radbandet.setGoodFor(new ArrayList<Gren> (Arrays.asList(mSprint, mStafett)));
        radbandet.setShortDescription("Bra när du vill att alla ska springa runt varvet i hygglig takt");
        triangledramat.setGoodFor(new ArrayList<Gren> (Arrays.asList(mSprint)));
        triangledramat.setShortDescription("snurrar på ....");
        lopskola.setGoodFor(new ArrayList<Gren> (Arrays.asList(mSprint)));
        lopskola.setShortDescription("klassiker");

        allExersices.add(simringen);
        allExersices.add(radbandet);
        allExersices.add(triangledramat);
        allExersices.add(lopskola);

        return allExersices;
    }
}

