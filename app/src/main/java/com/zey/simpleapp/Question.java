package com.zey.simpleapp;

public class Question {

    public Question(String question, String answer) {
        questionS = question;
        answerS = answer;
    }

    public String getquestionS() {
        return questionS;
    }

    public void setquestionS(String question) {
        questionS = question;
    }

    public String getanswerS() {
        return answerS;
    }

    public void setanswerS(String answer) {
        answerS = answer;
    }

    private String questionS;
    private String answerS;


}
