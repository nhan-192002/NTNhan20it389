package controller;

import view.TextView;

public class TextController {
    private TextView view;

    public TextController(TextView view) {
        this.view = view;
    }

    public void displayText(String text) {
        view.getTextArea().setText(text);
    }

    public static void main(String[] args) {
        TextView view = new TextView();
        TextController controller = new TextController(view);
    }
}
