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

	// Menu options:
	// General options
	public final String confirmOption = "Y";
	public final String rejectOption = "N";

	// Login Menu
	public final String loginOption = "L";
	public final String createAccountOption = "C";
	public final String forgotPasswordOption = "F";
	public final String quitOption = "Q";

	// Main Menu
	final String backOption = "B";
	final String createNewTicketOption = "N";
	final String myOpenTicketsOption = "O";
	final String logoutOption = "L";

	// Technician Menu
	final String assignedTicketsOption = "A";
	final String pickTicketOption = "P";

	// Ticket Menu
	final String setTicketSeverityOption = "S";
	final String escalateTicketOption = "E";
	final String changeTicketStatusOption = "T";

	// Ticket Status Menu
	final String openTicketOption = "O";
	final String closeTicketOption = "X";
	
	public void menuController() {
		// Print out the welcome message
		ui.welcomeBanner();
	}

	public String welcomeLoop() {
		do {
//			ticket.archiveOldTickets();
			menuOption = "";
			ui.welcomePrompt();
			menuOption = input.getInput().toUpperCase();
		} while (!validate.welcome(menuOption));

		return menuOption;
	}

	public void createUser() {
		do {
			menuOption = "";
			account.createUser();
		} while (!validate.createUser(menuOption));
	}

	public void forgotPassword() {
		ticket.forgotPassword();
	}

	public boolean loginLoop() {
		// Show login prompts and get+validate input
		ui.loginPrompt();
		String accountIdRawInput = input.getInput();
		ui.passwordPrompt();
		String password = input.getSecureInput();
		// Attempt to parse accountId from string to integer
		int accountId = 0;
		try {
			accountId = Integer.parseInt(accountIdRawInput);
		} catch (NumberFormatException ex) {
			// Do nothing because accountId is initialized
		}
		// Login function
		currentUser = login.verifyLogin(accountId, password);

		// Check if user is a technician or not
		currentTechnician = technician.getTechnicianById(currentUser.getId());
	
		if(currentUser.getId() == 0){
			return false;
		}
		
		return true;
	}

	public String mainMenuLoop() {
		do {
			menuOption = "";
			ui.mainMenu(currentTechnician.getId());
			menuOption = input.getInput().toUpperCase();
			if (menuOption.equals(createNewTicketOption)) {
				ticket.createNewTicket(currentUser, ticket.openTicketRecord);
			} else if (menuOption.equals(myOpenTicketsOption)) {
				ArrayList<String> myTickets = ticket.getAllTickets(ticket.openTicketRecord, currentUser, null);
//				ticket.getAllTickets();
				for (int index = 0; index < myTickets.size(); index++) {
					ticket.displayTicketString(myTickets.get(index));
				}
			}
			// Technician menu options
			else if (menuOption.equals(assignedTicketsOption)) {
				ArrayList<String> myTickets = ticket.getAllTickets(ticket.openTicketRecord, currentUser,
						currentTechnician);
				for (int index = 0; index < myTickets.size(); index++) {
					ticket.displayTicketString(myTickets.get(index));
				}
			} else if (menuOption.equals(pickTicketOption) && currentUser.getId()<4005) {
				ticket.selectTicket();
				ticketMenuLoop();
			}
		} while (!menuOption.equals(quitOption));
		return menuOption; 
	}

	public void ticketMenuLoop() {
		while (!menuOption.equals(backOption) && ticket.selectedTicket.getId() != 0){
			menuOption = "";
			ticket.displayTicket(ticket.selectedTicket);
			ui.ticketMenu();
			menuOption = input.getInput().toUpperCase();
			if (menuOption == null || menuOption.trim().isEmpty()) {
				System.out.println("Input is null or empty. Please enter a valid option."); // handle null or empty
				// input
				continue;
			}
			if (menuOption.equals(setTicketSeverityOption)) {
				ticket.changeSeverity(ticket.selectedTicket);
			} else if (menuOption.equals(escalateTicketOption)) {
				ticket.escalateTicket(ticket.selectedTicket, currentTechnician);
			}
			if (menuOption.equals(changeTicketStatusOption)) {
				do {
					menuOption = ticketStatusLoop();
				} while (!menuOption.equals(backOption));
			}
		}
	}

	public String ticketStatusLoop() {
		do {
			menuOption = "";
			ui.ticketStatusMenu();
			menuOption = input.getInput().toUpperCase();
			if (menuOption == null || menuOption.trim().isEmpty()) {
				System.out.println("Input is null or empty. Please enter a valid option."); // handle null or empty
				continue;
			}
			if (menuOption.equals(myOpenTicketsOption)) {
				ticket.changeOpenStatus(ticket.selectedTicket);
				
			}

			if (menuOption.equals(closeTicketOption)) {
				ui.resolvePrompt();
				menuOption = input.getInput().toUpperCase();
				if (menuOption.equals(confirmOption)) {
					ticket.changeStatus(ticket.selectedTicket, menuOption);
				} else if (menuOption.equals(rejectOption)) {
					ticket.changeStatus(ticket.selectedTicket, menuOption);
				}
			}
		} while (!menuOption.equals(backOption));			
		return menuOption;
	}
		

}