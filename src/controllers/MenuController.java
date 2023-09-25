package src.controllers;
import src.handlers.*;
import src.views.MenuUI;
import src.models.Account;
public class MenuController {
    // Create the MenuUI, InputHandler, LoginController, TicketController
    MenuUI ui = new MenuUI();
    InputHandler input = new InputHandler();
    LoginController login = new LoginController();
    TicketController ticket = new TicketController();

    // Keeps track of if the logged in account is a technician or not
    private int accountType = 0;

    // Keeps track of the logged in user
    private Account currentUser = new Account();

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
            if(accountType != 0){
                // set current user up as an object for easy reuse
                currentUser.setId(Integer.parseInt(accountId));
            }
        }
    }

    public void MainMenuLoop(){
        String menuOption = "";
        while(!menuOption.equals("E")){
            ui.MainMenu(accountType); 
            menuOption = input.getInput();
            if(menuOption.equals("C")){
                ticket.CreateNewTicket(currentUser);
            }
        }
    }
}