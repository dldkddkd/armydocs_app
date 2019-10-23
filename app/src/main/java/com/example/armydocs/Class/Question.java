package com.example.armydocs.Class;

import java.util.ArrayList;

public class Question
{
    private static final Question ourInstance = new Question();

    public static Question getInstance()
    {
        return ourInstance;
    }

    public Question() {}

    public String title = "";
    public String writer = "";
    public String intro = "";
    public String start = "";
    public String end = "";

    public void setTitle(String _title){
        title = _title;
    }
    public void setWriter(String _writer){
        writer = _writer;
    }
    public void setIntro(String _intro){
        intro = _intro;
    }
    public void setStart(String _start){
        start = _start;
    }
    public void setEnd(String _end){
        end = _end;
    }
    public String getTitle(){
        return title;
    }
    public String getWriter(){
        return writer;
    }
    public String getIntro(){
        return intro;
    }
    public String getStart(){
        return start;
    }
    public String getEnd(){
        return end;
    }

}
