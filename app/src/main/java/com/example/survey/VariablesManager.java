package com.example.survey;

import android.content.Context;

public class VariablesManager {
    public boolean tutorial;

    private static VariablesManager variablesManager = null;

    public VariablesManager(){

    }
    public static void Init(){
        getInstance().setTutorial(true);
    }
    public static VariablesManager getInstance(){
        if(variablesManager == null){
            variablesManager = new VariablesManager();
        }
        return variablesManager;
    }

    public boolean getTutorial() {
        return tutorial;
    }

    public void setTutorial(boolean tutorial) {
        this.tutorial = tutorial;
    }



}
