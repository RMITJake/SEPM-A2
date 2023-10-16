package src.controllers;

import src.handlers.*;
import src.views.MenuUI;
import src.models.Account;
import src.models.Technician;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class MenuController {
	// Create the MenuUI, InputHandler, LoginController, TicketController
	MenuUI ui = new MenuUI();
	InputHandler input = new InputHandler();
	ValidationHandler validate = new ValidationHandler();
	LoginController login = new LoginController();
	TicketController ticket = new TicketController();
	AccountController account = new AccountController();
	TechnicianController technician = new TechnicianController();
	ReportController report = new ReportController();
	FileHandler file = new FileHandler();

	// Keeps track of the logged in user
	protected Account currentUser = new Account();
	protected Technician currentTechnician = new Technician();

	// Keeps track of the menu option selected
	String menuOption;

	// Menu functions needed to pass to App class
	public final String loginOption = ui.loginOption;
	public final String createAccountOption = ui.createAccountOption;
	public final String forgotPasswordOption = ui.forgotPasswordOption;
	public final String quitOption = ui.quitOption;
	public final String logoutOption = "L";

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
			if (menuOption.equals(ui.createNewTicketOption)) {
				ticket.createNewTicket(currentUser, ticket.openTicketRecord);
			} else if (menuOption.equals(ui.myOpenTicketsOption)) {
				ArrayList<String> myTickets = ticket.getAllTickets(ticket.openTicketRecord, currentUser, null);
//				ticket.getAllTickets();
				for (int index = 0; index < myTickets.size(); index++) {
					ticket.displayTicketString(myTickets.get(index));
				}
			}
			// Technician menu options
			else if (menuOption.equals(ui.assignedTicketsOption)) {
				ArrayList<String> myTickets = ticket.getAllTickets(ticket.openTicketRecord, currentUser, currentTechnician);
				String[] currentTicket;
				for (int index = 0; index < myTickets.size(); index++) {
					currentTicket = myTickets.get(index).split(",",-1);
					ticket.displayTicketString(myTickets.get(index));
				}
			} else if (menuOption.equals(ui.pickTicketOption) && currentTechnician.getId() != 0) {
				ticket.selectTicket();
				ticketMenuLoop();
			// } else if (menuOption.equals(myArchivedTicketsOption) && currentTechnician.getId() != 0){
			} else if (menuOption.equals(ui.myArchivedTicketsOption) && currentTechnician.getId() != 0){
				ArrayList<String> archivedTickets = ticket.getUserTickets("archived", currentTechnician, 1);
				displatTicketList(archivedTickets);
			} else if (menuOption.equals(ui.otherArchivedTicketsOption) && currentTechnician.getId() != 0){
				ArrayList<String> archivedTickets = ticket.getUserTickets("archived", currentTechnician, 2);
				displatTicketList(archivedTickets);
			} else if (menuOption.equals(ui.myClosedTicketsOption) && currentTechnician.getId() != 0){
				ArrayList<String> archivedTickets = ticket.getUserTickets("closed", currentTechnician, 1);
				displatTicketList(archivedTickets);
			} else if (menuOption.equals(ui.otherClosedTicketsOption) && currentTechnician.getId() != 0){
				ArrayList<String> archivedTickets = ticket.getUserTickets("closed", currentTechnician, 2);
				displatTicketList(archivedTickets);
			} else if (menuOption.equals(ui.reportOption) && currentTechnician.getId() != 0){
				generateReportLoop();
			}
		} while (!menuOption.equals(ui.quitOption) && !menuOption.equals(ui.logoutOption));
		return menuOption; 
	}

	public void ticketMenuLoop() {
		while (!menuOption.equals(ui.backOption) && ticket.selectedTicket.getId() != 0){
			menuOption = "";
			ticket.displayTicket(ticket.selectedTicket);
			ui.ticketMenu();
			menuOption = input.getInput().toUpperCase();
			if (menuOption == null || menuOption.trim().isEmpty()) {
				System.out.println("Input is null or empty. Please enter a valid option."); // handle null or empty
				// input
				continue;
			}
			if (menuOption.equals(ui.setTicketSeverityOption)) {
				ticket.changeSeverity(ticket.selectedTicket);
			} else if (menuOption.equals(ui.escalateTicketOption)) {
				ticket.escalateTicket(ticket.selectedTicket, currentTechnician);
			}
			if (menuOption.equals(ui.changeTicketStatusOption)) {
				do {
					menuOption = ticketStatusLoop();
				} while (!menuOption.equals(ui.backOption));
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
			if (menuOption.equals(ui.myOpenTicketsOption)) {
				ticket.changeOpenStatus(ticket.selectedTicket);
			}

			if (menuOption.equals(ui.closeTicketOption)) {
				ui.resolvePrompt();
				menuOption = input.getInput().toUpperCase();
				if (menuOption.equals(ui.confirmOption)) {
					ticket.changeStatus(ticket.selectedTicket, menuOption);
				} else if (menuOption.equals(ui.rejectOption)) {
					ticket.changeStatus(ticket.selectedTicket, menuOption);
				}
			}
		} while (!menuOption.equals(ui.backOption));			
		return menuOption;
	}
		
	private void displatTicketList(ArrayList<String> ticketList){
		if(ticketList.size() == 0){
			System.out.println("There are no tickets in this list available to display.");
		} else {
			for (int index = 0; index < ticketList.size(); index++) {
				String[] currentTicket = ticketList.get(index).split(",",-1);
				ticket.displayTicketString(ticketList.get(index));
			}
		}
	}

	private void generateReportLoop(){
		do{
			menuOption = "";
			ui.reportStartDatePrompt();
			menuOption = input.getInput();
			if(validate.reportDate(menuOption)){
				String start = menuOption
				ui.reportEndDatePrompt();
				menuOption = input.getInput();
			}
			if(validate.reportDate(menuOption)){
				String end = menuOption
				ui.reportConfirmPrompt();
				menuOption = input.getInput().toUpperCase;
			}
		} while(!menuOption.equals(confirmOption) && !menuOption.equals(backOption));
		if(menuOption.equals(confirmOption)){
			ArrayList<String> allTickets = ticket.getAllTickets(file.openTicketRecord);
			LocalDateTime startDate = LocalDateTime.parse("2023-10-02T00:00:00.000000000");
			LocalDateTime endDate = LocalDateTime.parse("2023-10-07T00:00:00.000000000");
			report.generateReport(allTickets, startDate, endDate);
		}
	}
}