package com.zey.simpleapp;

public class Question {

    public Question(String question, String answer) {
        questionS = question;
        answerS = answer;
    }

    public String getQuestion() {
        return questionS;
    }

    public void setQuestion(String question) {
        questionS = question;
    }

    public String getAnswer() {
        return answerS;
    }

    public void setAnswer(String answer) {
        answerS = answer;
    }

    private String questionS;
    private String answerS;


}
