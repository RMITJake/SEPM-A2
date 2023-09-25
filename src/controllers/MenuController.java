package src.controllers;
import src.handlers.*;
import src.views.MenuUI;
public class MenuController {
    // Create the MenuUI, InputHandler, LoginController, TicketController
    MenuUI ui = new MenuUI();
    InputHandler input = new InputHandler();
    LoginController login = new LoginController();
    TicketController ticket = new TicketController();

    // Keeps track of if the logged in account is a technician or not
    private int accountType = 0;

    public MenuController(){
        System.out.println("Constructing MenuController");

        // Print out the welcome message
        ui.Welcome();
    }

    public void LoginLoop(){
        while(accountType == 0){
            // Show login prompts and get+validate input
            ui.LoginPrompt();
            String accountId = input.getInput();
            ui.PasswordPrompt();
            String password = input.getSecureInput();

            // Login function
            accountType = login.VerifyLogin(accountId, password);
        }
    }

    public void MainMenuLoop(){
        String menuOption = "";
        while(!menuOption.equals("E")){
            ui.MainMenu(accountType); 
            menuOption = input.getInput();
            if(menuOption.equals("M")){
                ticket.OpenTickets();
            }
        }
    }
}