import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class FileHandler {
    private String path = "../records/";
    FileHandler(){
        System.out.println("Constructing FileHandler");
    }

    public void Write(String file){
        String workingPath = path + file + ".csv";
    }

    public String Read(String file){
        String workingPath = path + file + ".csv";
        String fileContents = "";
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(workingPath));
            while((line = reader.readLine()) != null)
            {
                fileContents += line;
                fileContents += "\n";
            }
            return fileContents;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return "";
    }
}