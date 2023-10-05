package src.controllers;
import src.handlers.*;
import src.views.MenuUI;
import src.models.Account;
import src.models.Technician;
public class MenuController {
    // Create the MenuUI, InputHandler, LoginController, TicketController
    MenuUI ui = new MenuUI();
    InputHandler input = new InputHandler();
    ValidationHandler validate = new ValidationHandler();
    LoginController login = new LoginController();
    TicketController ticket = new TicketController();
    AccountController account = new AccountController();
    TechnicianController technician = new TechnicianController();

    // Keeps track of the logged in user
    public Account currentUser = new Account();
    public Technician currentTechnician = new Technician();
    
    // Keeps track of the menu option selected
    String menuOption;

    public MenuController(){
        // Print out the welcome message
        ui.WelcomeBanner();
    }

    public String WelcomeLoop(){
        do{
            menuOption = "";
            ui.WelcomePrompt();
            menuOption = input.getInput().toUpperCase();
        } while (!validate.Welcome(menuOption));

        return menuOption;
    }

    public void CreateUser(){
        do{
            menuOption = "";
            account.CreateUser();
            menuOption = input.getInput().toUpperCase();
        } while (!validate.CreateUser(menuOption));
    }

    public void ForgotPassword(){
        ticket.ForgotPassword();
    }

    public boolean LoginLoop(){
        while(currentUser.getId() == 0){
            // Show login prompts and get+validate input
            ui.LoginPrompt();
            String accountId = input.getInput();
            ui.PasswordPrompt();
            String password = input.getSecureInput();

            // Login function
            currentUser = login.VerifyLogin(accountId, password);

            // Check if user is a technician or not
            currentTechnician = technician.getTechnicianById(currentUser.getId());
        }
        return true;
    }

    public String MainMenuLoop(){
        do{
            menuOption = "";
            ui.MainMenu(currentTechnician.getId()); 
            menuOption = input.getInput().toUpperCase();
            if(menuOption.equals("C")){
                ticket.CreateNewTicket(currentUser, ticket.openTicketRecord);
            } else if (menuOption.equals("S")){
                ticket.SelectTicket();
            }
        }while(!menuOption.equals("E"));
        return menuOption;
    }
}