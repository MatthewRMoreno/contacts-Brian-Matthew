package contacts;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ContactsGateway {
    //Writes contact to file
    public static void writeToFile(Contacts contactsList) {
        // 1. make a path object
        Path filePath = getFilePath();
        if(filePath == null) {
            System.out.println("Filepath could not be created. Cannot save.");
            return;
        }

        // 3. use Files.write to send the data to the file
        writeItemStringsToFilePath(filePath, contactsList);
    }

    //Helper function to write a Contacts object to the file
    private static void writeItemStringsToFilePath(Path filePath, Contacts contactStrings) {
        String contacts = contactStrings.getContact().toString();
        try {
            Files.write(filePath, Contacts.convertContactsToArrayList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //Creates a contact object, fills the contact HashMap from file text, returns the contact object
    public static Contacts readFromFile() {
        Contacts list = new Contacts();
        Path filePath = getFilePath();
        if(filePath == null) {
            System.out.println("Filepath could not be created. Cannot load.");
            return list;
        }
        List<String> contactStrings = readItemStringsFromFilePath(filePath);

        // 3. For each contactString in contactStrings add new contact to list
        for(String contactString : contactStrings) {
            list = Contacts.createFromString(contactString);
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
    //Looks for, creates if not present, and returns the data file
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