package view;

import javax.swing.*;
import java.awt.*;

public class TextView extends JFrame {
    private JTextArea textArea;
    private JFileChooser fileChooser;
    private JMenuItem openMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem exitMenuItem;

    public TextView() {
        setTitle("Text Editor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        setUpMenuBar();
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

        openMenuItem = new JMenuItem("Open");
        saveMenuItem = new JMenuItem("Save");
        exitMenuItem = new JMenuItem("Exit");

        // Set preferred size for each JMenuItem
        Dimension menuItemSize = new Dimension(200, 40); // Adjust size as needed
        openMenuItem.setPreferredSize(menuItemSize);
        saveMenuItem.setPreferredSize(menuItemSize);
        exitMenuItem.setPreferredSize(menuItemSize);

        menuBar.add(openMenuItem);
        menuBar.add(saveMenuItem);
        menuBar.add(exitMenuItem);
    }

    public JMenuItem getOpenMenuItem() {
        return openMenuItem;
    }

    public JMenuItem getSaveMenuItem() {
        return saveMenuItem;
    }

    public JMenuItem getExitMenuItem() {
        return exitMenuItem;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }
}
