package src.handlers;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class FileHandler {
    // Set the basic path for the string. Filename.csv will be appended to open files
    private String path = "records/";

    public void Write(String file, String input){
        // Add the input filename to the path so we can write to a file
        String workingPath = path + file + ".csv";
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(workingPath, true));
            writer.append(input);
            writer.close();
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

    public ArrayList<String> Read(String file){
        // Add the input filename to the path so we can read a file
        String workingPath = path + file + ".csv";
        // Create a blank string to temporarily store the file contents which will be returned
        ArrayList<String> fileContents = new ArrayList<String>();
        // Similar to above fileContents but on a micro level per line basis
        String line;
        try {
            // Using BufferedReader to read file contents
            BufferedReader reader = new BufferedReader(new FileReader(workingPath));

            // Iterate through the lines in the file until there are no more lines
            while((line = reader.readLine()) != null)
            {
                // Save the line to our fileContents string
                fileContents.add(line);
            }
            reader.close();
            // return the fileContents to be worked with in another part of the App
            return fileContents;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        // This will need to return something useful, currently returning a blank string for compilation purposes
        return fileContents;
    }
}