import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class FileHandler {
    // Set the basic path for the string. Filename.csv will be appended to open files
    private String path = "../records/";
    FileHandler(){
        System.out.println("Constructing FileHandler");
    }

    public void Write(String file){
        String workingPath = path + file + ".csv";
    }

    public String Read(String file){
        // Add the input filename to the path so we can read a file
        String workingPath = path + file + ".csv";
        // Create a blank string to temporarily store the file contents which will be returned
        String fileContents = "";
        // Similar to above fileContents but on a micro level per line basis
        String line;
        try {
            // Using BufferedReader to read file contents
            BufferedReader reader = new BufferedReader(new FileReader(workingPath));

            // Iterate through the lines in the file until there are no more lines
            while((line = reader.readLine()) != null)
            {
                // Save the line to our fileContents string
                fileContents += line;
                fileContents += "\n";
            }
            // return the fileContents to be worked with in another part of the App
            return fileContents;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        // This will need to return something useful, currently returning a blank string for compilation purposes
        return "";
    }
}