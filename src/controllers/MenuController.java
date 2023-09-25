package src.controllers;
import src.handlers.*;
import src.views.MenuUI;
public class MenuController {
    // Create the MenuUI, FileHandler, and InputHandler
    MenuUI ui = new MenuUI();
    FileHandler file = new FileHandler();
    InputHandler input = new InputHandler();

    public MenuController(){
        System.out.println("Constructing MenuController");

        // Test the file handler
        System.out.println(file.Read("Login"));

        // Print out the welcome message
        ui.Welcome();
    }

    public void LoginLoop(){
        // Show login prompts and get+validate input
        ui.LoginPrompt();
        input.getInput();
        ui.PasswordPrompt();
        input.getSecureInput();
    }
}