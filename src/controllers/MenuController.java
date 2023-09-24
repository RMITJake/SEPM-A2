package src.controllers;
import src.FileHandler;
public class MenuController {
    public MenuController(){
        System.out.println("Constructing Menu");

        // Create the FileHandler
        FileHandler file = new FileHandler();
        System.out.println(file.Read("Login"));
    }
}