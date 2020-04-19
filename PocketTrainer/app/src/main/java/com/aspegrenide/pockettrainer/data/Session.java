package com.aspegrenide.pockettrainer.data;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Session {


    private Date date;
    private ArrayList<Gren> grenar;
    private ArrayList<Exercise> exercisesWarmup;
    private ArrayList<Exercise> exercisesMain;

    public Session(Date date) {
        this.date = date;
    }


    public Session(Date date, ArrayList<Gren> grenar,
            ArrayList<Exercise> exercisesWarmup, ArrayList<Exercise> exercisesMain) {

        this.date = date;
        this.grenar = grenar;
        this.exercisesWarmup = exercisesWarmup;
        this.exercisesMain = exercisesMain;
    }

    public String toString(){
        String grenBeskrivning = "";

        for (Gren g : getGrenar()){
            grenBeskrivning += g.toString() + "+";
        }

        if (grenBeskrivning != null && grenBeskrivning.length() > 0 &&
                grenBeskrivning.charAt(grenBeskrivning.length() - 1) == '+') {
            grenBeskrivning = grenBeskrivning.substring(0, grenBeskrivning.length() - 1);
        }

        String retVal = date.toString() + " " + grenBeskrivning;

        return retVal;
    }


    public int getWeekNr(){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<Gren> getGrenar() {
        return grenar;
    }

    public void setGrenar(ArrayList<Gren> grenar) {
        this.grenar = grenar;
    }

    public void addGren(Gren gren) {
        this.grenar.add(gren);
    }

    public ArrayList<Exercise> getExercisesWarmup() {
        return exercisesWarmup;
    }

    public void setExercisesWarmup(ArrayList<Exercise> exercisesWarmup) {
        this.exercisesWarmup = exercisesWarmup;
    }

    public void addExerciseWarmup(Exercise exercisesWarmup) {
        this.exercisesWarmup.add(exercisesWarmup);
    }

    public ArrayList<Exercise> getExercisesMain() {
        return exercisesMain;
    }

    public void setExercisesMain(ArrayList<Exercise> exercisesMain) {
        this.exercisesMain = exercisesMain;
    }
    public void addExerciseMain(Exercise exercisesMain) {
        this.exercisesMain.add(exercisesMain);
    }

}
