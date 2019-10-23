package com.example.armydocs.Class;

import java.util.ArrayList;

public class Survey
{
    private static final Survey ourInstance = new Survey();

    public static Survey getInstance()
    {
        return ourInstance;
    }

    public Survey()
    {

    }
    public int question_num = 0;
    public String survey_title = "";
    ArrayList questions = new ArrayList();

    public void addQuestion(Survey s)
    {
        questions.add(s);
        question_num++;
    }

    public void delQuestion()
    {
        if(question_num>0){
            questions.remove(question_num);
            question_num--;
        }
    }

    public void setSurvey_title(String s){
    	survey_title = s;
    }
    public String getSurvey_title(){
    	return survey_title;
    }
}

