/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TextView extends JFrame {
    private JTextArea textArea;
    private JFileChooser fileChooser;

    public TextView() {
        setTitle("Text Editor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        setUpMenuBar();
        setVisible(true);
    }

    private void initComponents() {
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        fileChooser = new JFileChooser();
    }

    private void setUpMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);

        openItem.addActionListener(e -> {
            try {
                openFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        saveItem.addActionListener(e -> saveFile());

        exitItem.addActionListener(e -> System.exit(0));
    }

    private void openFile() throws IOException {
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile.isDirectory()) {
                new model.TextModel().traverseDirectory(selectedFile);
            } else {
                textArea.setText(new model.TextModel().readFile(selectedFile));
            }
        }
    }

    private void saveFile() {
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnValue = fileChooser.showSaveDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                new model.TextModel().saveFile(selectedFile, textArea.getText());
                JOptionPane.showMessageDialog(this, "File saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error occurred while saving file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}

