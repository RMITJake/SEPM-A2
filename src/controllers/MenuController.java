package src.controllers;
import src.handlers.*;
import src.views.MenuUI;
import src.models.Account;
import src.models.Technician;
import java.util.ArrayList;
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
    protected Account currentUser = new Account();
    protected Technician currentTechnician = new Technician();
    
    // Keeps track of the menu option selected
    String menuOption;

    public void menuController(){
        // Print out the welcome message
        ui.welcomeBanner();
    }

    public String welcomeLoop(){
        do{
            menuOption = "";
            ui.welcomePrompt();
            menuOption = input.getInput().toUpperCase();
        } while (!validate.welcome(menuOption));

        return menuOption;
    }

    public void createUser(){
        do{
            menuOption = "";
            account.createUser();
        } while (!validate.createUser(menuOption));
    }

    public void forgotPassword(){
        ticket.forgotPassword();
    }

    public boolean loginLoop(){
        while(currentUser.getId() == 0){
            // Show login prompts and get+validate input
            ui.loginPrompt();
            String accountId = input.getInput();
            ui.passwordPrompt();
            String password = input.getSecureInput();

            // Login function
            currentUser = login.verifyLogin(accountId, password);

            // Check if user is a technician or not
            currentTechnician = technician.getTechnicianById(currentUser.getId());
        }
        return true;
    }

    public String mainMenuLoop(){
        do{
            menuOption = "";
            ui.mainMenu(currentTechnician.getId()); 
            menuOption = input.getInput().toUpperCase();
            if(menuOption.equals("C")){
                ticket.createNewTicket(currentUser, ticket.openTicketRecord);
            } else if (menuOption.equals("S")){
                ticket.selectTicket();
                do{
                    menuOption = ticketMenuLoop();
                } while(!menuOption.equals("M"));
            } else if (menuOption.equals("M")) {   	
                ArrayList<String> myTickets = ticket.getAllTickets(ticket.openTicketRecord, currentUser, null);
                ticket.getAllTickets();
                for (int index = 0; index < myTickets.size(); index++) {
                    ticket.displayTicketString(myTickets.get(index));
                }
            }
            // Technician menu options
            else if (menuOption.equals("A")) {
                ArrayList<String> myTickets = ticket.getAllTickets(ticket.openTicketRecord, currentUser, currentTechnician);
                for (int index = 0; index < myTickets.size(); index++) {
                    ticket.displayTicketString(myTickets.get(index));
                }
            }
        }while(!menuOption.equals("E"));
        return menuOption;
    }

    public String ticketMenuLoop() {
        do {
            menuOption = "";
            ticket.displayTicket(ticket.selectedTicket);
            ui.ticketMenu();
            menuOption = input.getInput().toUpperCase();            
            if (menuOption == null || menuOption.trim().isEmpty()) {
                System.out.println("Input is null or empty. Please enter a valid option.");  //handle null or empty input
                continue;
            }
            if(menuOption.equals("S")){
                ticket.changeSeverity(ticket.selectedTicket);
            }
           if(menuOption.equals("C")){
                ticket.changeStatus(ticket.selectedTicket);
            } else if (menuOption.equals("E")) {
                ticket.escalateTicket(ticket.selectedTicket, currentTechnician);
            }
        } while (!menuOption.equals("M"));
        return menuOption;
        }
    
}