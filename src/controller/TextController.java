package controller;

import model.TextModel;
import view.TextView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class TextController {
    private TextView view;
    private TextModel model;

    public TextController(TextView view, TextModel model) {
        this.view = view;
        this.model = model;

        view.getOpenMenuItem().addActionListener(e -> openFile());
        view.getSaveMenuItem().addActionListener(e -> saveFile());
        view.getExitMenuItem().addActionListener(e -> System.exit(0));
    }

    private void openFile() {
        view.getTextArea().setText("");
        JFileChooser fileChooser = view.getFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int returnValue = fileChooser.showOpenDialog(view);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile.isDirectory()) {
                model.traverseDirectory(selectedFile, this);
            } else {
                readFile(selectedFile);
            }
        }
    }

    public void readFile(File file) {
        String content = model.readFile(file);
        if (content != null) {
            view.getTextArea().append(content + "\n\n");
        }
    }

    private void saveFile() {
        JFileChooser fileChooser = view.getFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnValue = fileChooser.showSaveDialog(view);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            model.saveFile(selectedFile, view.getTextArea().getText());
            JOptionPane.showMessageDialog(view, "File saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
