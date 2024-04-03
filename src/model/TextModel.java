package model;

import controller.TextController;
import java.io.*;
import java.nio.file.Files;
import java.util.stream.Collectors;

public class TextModel {
    public String readFile(File file) {
        if (file.getName().endsWith(".txt")) {
            try {
                return Files.lines(file.toPath()).collect(Collectors.joining("\n"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void traverseDirectory(File directory, TextController controller) {
        try {
            Files.walk(directory.toPath())
                    .filter(Files::isRegularFile)
                    .forEach(file -> controller.readFile(file.toFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveFile(File file, String content) {
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.write(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
