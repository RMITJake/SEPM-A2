package src.controllers;
import src.handlers.FileHandler;
public class MenuController {
    public MenuController(){
        System.out.println("Constructing MenuController");

        // Create the FileHandler
        FileHandler file = new FileHandler();
        System.out.println(file.Read("Login"));
    }
}