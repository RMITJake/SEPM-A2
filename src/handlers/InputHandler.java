package src.handlers;
import java.util.Scanner;
import java.io.Console;
public class InputHandler {
    // Create the Scanner to actually take stdin
    Scanner scn = new Scanner(System.in);
    String input;

    // Create the Console object to obfuscate input for passwords
    Console console = System.console();

    // General getInput function
    public String getInput(){
        input = scn.nextLine();
        return input;
    }

    // Get input hide the input from the UI
    public String getSecureInput(){
        input = String.valueOf(console.readPassword());
        return input;
    }
}