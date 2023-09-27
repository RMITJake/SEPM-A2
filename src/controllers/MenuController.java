package src.controllers;
import src.handlers.*;
import src.views.MenuUI;
import src.models.Account;
import src.models.Technician;
public class MenuController {
    // Create the MenuUI, InputHandler, LoginController, TicketController
    MenuUI ui = new MenuUI();
    InputHandler input = new InputHandler();
    LoginController login = new LoginController();
    TicketController ticket = new TicketController();
    AccountController account = new AccountController();
    TechnicianController technician = new TechnicianController();

    // Keeps track of if the logged in account is a technician or not
    private int accountType = 0;

    // Keeps track of the logged in user
    public Account currentUser = new Account();
    public Technician currentTechnician = new Technician();

    public MenuController(){
        // Print out the welcome message
        ui.WelcomeBanner();
    }

    public void LoginLoop(){
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
    }

    public void MainMenuLoop(){
        String menuOption = "";
        while(!menuOption.equals("E")){
            ui.MainMenu(currentTechnician.getId()); 
            menuOption = input.getInput();
            if(menuOption.equals("C")){
                ticket.CreateNewTicket(currentUser);
            }
            menuOption = "";
        }
    }
}