package src.controllers;
import src.handlers.*;
import src.views.MenuUI;
public class MenuController {
    public MenuController(){
        System.out.println("Constructing MenuController");

        // Create the MenuUI
        MenuUI ui = new MenuUI();

        // Create the InputHandler
        InputHandler input = new InputHandler();

        // Create the FileHandler
        FileHandler file = new FileHandler();
        // Test the file handler
        System.out.println(file.Read("Login"));
    }
}