package com.pizza.command;

public class ProvideFeedbackCommand implements Command {
    private String feedback;
    public ProvideFeedbackCommand(String feedback) { this.feedback = feedback; }
    public void execute() {
        System.out.println("Feedback: " + feedback);
    }
}