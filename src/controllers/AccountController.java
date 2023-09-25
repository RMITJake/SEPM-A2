package src.controllers;
import src.handlers.FileHandler;
public class AccountController {
    private FileHandler file = new FileHandler();
    public AccountController(){
        System.out.println("Constructing AccountController");
                
        // Test the file handler
        System.out.println(file.Read("Account"));
    }
}