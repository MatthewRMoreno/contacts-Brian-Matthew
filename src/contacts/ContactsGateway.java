package contacts;

import com.sun.jdi.Value;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactsGateway {

    public static void writeToFile(Contacts contactsList) {
        // 1. make a path object
        Path filePath = getFilePath();
        if(filePath == null) {
            System.out.println("Filepath could not be created. Cannot save.");
            return;
        }


        // 3. use Files.write to send the data to the file
        writeItemStringsToFilePath(filePath, (Map<String, Integer>) itemStrings);
    }


    private static void writeItemStringsToFilePath(Path filePath, Map<String, Integer> itemStrings) {
        try {
            Files.write(filePath, Contacts.);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Contacts readFromFile() {
        Contacts list = new Contacts();

        // 1. make a path object
        Path filePath = getFilePath();
        if(filePath == null) {
            System.out.println("Filepath could not be created. Cannot load.");
            return list;
        }

        // 2. read item strings from file
        List<String> itemStrings = readItemStringsFromFilePath(filePath);

        // 3. make items from the items strings and put them in the groceryList
        for(String itemString : itemStrings) {
            Contacts item = Contacts.createFromString(itemString);
            list.setContact("", 1);
        }
        return list;
    }

    private static List<String> readItemStringsFromFilePath(Path filePath) {
        try {
            return Files.readAllLines(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Path getFilePath() {
        try {
            Path folder = Paths.get("contacts_list");
            Path file = Paths.get("contacts_list", "data.txt");
            if(Files.exists(folder)) {
                System.out.println("Hey the folder already exists!");
            } else {
                Files.createDirectories(folder);
            }
            if(Files.exists(file)) {
                System.out.println("Hey the file already exists!");
            } else {
                Files.createFile(file);
            }
            return file;
        } catch(IOException e) {
            // log an error message so at least we know something went wrong
            System.err.println(e.getMessage());
        }
        return null;
    }

}