package com.lecai.quwen.MainActivity.Fragment.Mine.Help;

public class HelpBean {
    private String question;
    private String Answer;
    private boolean isOpen = false;

    public HelpBean(String question, String answer) {
        this.question = question;
        Answer = answer;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }
}
