package src.controllers;
import src.handlers.*;
import src.views.MenuUI;
public class MenuController {
    // Create the MenuUI, and InputHandler
    MenuUI ui = new MenuUI();
    InputHandler input = new InputHandler();
    LoginController login = new LoginController();

    private boolean loggedIn = false;

    public MenuController(){
        System.out.println("Constructing MenuController");

        // Print out the welcome message
        ui.Welcome();
    }

    public void LoginLoop(){
        while(!loggedIn){
            // Show login prompts and get+validate input
            ui.LoginPrompt();
            String accountId = input.getInput();
            ui.PasswordPrompt();
            String password = input.getSecureInput();

            // Login function
            loggedIn = login.VerifyLogin(accountId, password);
        }
    }

    public void MainMenuLoop(){
        String menuOption = "";
        while(!menuOption.equals("X")){
            ui.MainMenu(); 
            menuOption = input.getInput();
        }
    }
}