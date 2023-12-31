package src.controllers;

import src.handlers.*;
import src.views.MenuUI;
import src.models.Account;
import src.models.Technician;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import src.models.Ticket;

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
			if (!validate.welcome(menuOption)) {
				System.out.println("You must enter a letter in the [] to select an option.");
			}
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
		String loginIdResult;
		String accountIdRawInput;
		do{
			ui.loginPrompt();
			accountIdRawInput = input.getInput();
			loginIdResult = validate.loginId(accountIdRawInput);
			if(loginIdResult != null && !accountIdRawInput.toUpperCase().equals(ui.backOption)){
				System.out.println(loginIdResult);
			}
		} while(loginIdResult != null && !accountIdRawInput.toUpperCase().equals(ui.backOption));

		if(loginIdResult == null){
			ui.passwordPrompt();
			String password = input.getSecureInput();
			int accountId = Integer.parseInt(accountIdRawInput);
			// Login function
			currentUser = login.verifyLogin(accountId, password);
			// Check if user is a technician or not
			currentTechnician = technician.getTechnicianById(currentUser.getId());
		
			if(currentUser.getId() == 0){
				ui.loginFailed();
				return false;
			}
			
			return true;
		}
		return false;	
	}

	public String mainMenuLoop() {
		do {
			menuOption = "";
			ui.mainMenu(currentTechnician.getId());
			menuOption = input.getInput().toUpperCase();
			if (menuOption.equals(ui.createNewTicketOption)) {
				ticket.createNewTicket(currentUser, FileHandler.ticketRecord);
			} else if (menuOption.equals(ui.myOpenTicketsOption)) {
				ArrayList<String> myTickets = ticket.getAllTickets(FileHandler.ticketRecord, currentUser, null);
				for (int index = 0; index < myTickets.size(); index++) {
					ticket.displayTicketString(myTickets.get(index));
				}
			} else if (currentTechnician.getId()==0 && !menuOption.equals(ui.createNewTicketOption) && !menuOption.equals(ui.myOpenTicketsOption) && !menuOption.equals(ui.logoutOption) && !menuOption.equals(ui.quitOption)){
				
				System.out.println("You must enter a letter in the [] to select an option.");
			}
			
			// Technician menu options
			else if (menuOption.equals(ui.assignedTicketsOption)) {
				ArrayList<String> myTickets = ticket.getAllTickets(FileHandler.ticketRecord, currentUser, currentTechnician);
				String[] currentTicket;
				for (int index = 0; index < myTickets.size(); index++) {
					currentTicket = myTickets.get(index).split(",",-1);
					ticket.displayTicketString(myTickets.get(index));
				}
			} else if (menuOption.equals(ui.pickTicketOption) && currentTechnician.getId() != 0) {
				ticket.selectTicket();
				ticketMenuLoop();
			} else if (menuOption.equals(ui.myArchivedTicketsOption) && currentTechnician.getId() != 0){
				ArrayList<String> archivedTickets = ticket.getUserTickets(Ticket.archivedStatus, currentTechnician, 1);
				displayTicketList(archivedTickets);
			} else if (menuOption.equals(ui.otherArchivedTicketsOption) && currentTechnician.getId() != 0){
				ArrayList<String> archivedTickets = ticket.getUserTickets(Ticket.archivedStatus, currentTechnician, 2);
				displayTicketList(archivedTickets);
			} else if (menuOption.equals(ui.myClosedTicketsOption) && currentTechnician.getId() != 0){
				ArrayList<String> closedResolvedTickets = ticket.getUserTickets(Ticket.closedResolvedStatus, currentTechnician, 1);
				displayTicketList(closedResolvedTickets);
				ArrayList<String> closedUnresolvedTickets = ticket.getUserTickets(Ticket.closedUnresolvedStatus, currentTechnician, 1);
				displayTicketList(closedUnresolvedTickets);
			} else if (menuOption.equals(ui.otherClosedTicketsOption) && currentTechnician.getId() != 0){
				ArrayList<String> closedResolvedTickets = ticket.getUserTickets(Ticket.closedResolvedStatus, currentTechnician, 2);
				displayTicketList(closedResolvedTickets);
				ArrayList<String> closedUnresolvedTickets = ticket.getUserTickets(Ticket.closedUnresolvedStatus, currentTechnician, 2);
				displayTicketList(closedUnresolvedTickets);
			} else if (menuOption.equals(ui.reportOption) && currentTechnician.getId() != 0){
				generateReportLoop();
			} else if (currentTechnician.getId()>0 && !menuOption.equals(ui.createNewTicketOption) && !menuOption.equals(ui.myOpenTicketsOption) && !menuOption.equals(ui.assignedTicketsOption) && !menuOption.equals(ui.pickTicketOption)
					&& !menuOption.equals(ui.myArchivedTicketsOption) && !menuOption.equals(ui.otherArchivedTicketsOption) && !menuOption.equals(ui.myClosedTicketsOption) && !menuOption.equals(ui.otherClosedTicketsOption)
					&& !menuOption.equals(ui.reportOption) && !menuOption.equals(ui.logoutOption) && !menuOption.equals(ui.quitOption)){
				
				System.out.println("You must enter a letter in the [] to select an option.");
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
			while (!menuOption.equals(ui.setTicketSeverityOption) &&
					!menuOption.equals(ui.changeTicketStatusOption) && !menuOption.equals(ui.backOption)) {
				System.out.println("Incorrect Input. Please enter a value in the []:");
				menuOption = input.getInput().toUpperCase();
			}
			if (menuOption.equals(ui.setTicketSeverityOption)) {
				ticket.changeSeverity(ticket.selectedTicket);
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
			while (!menuOption.equals(ui.myOpenTicketsOption) && !menuOption.equals(ui.closeTicketOption)&& !menuOption.equals(ui.backOption)) {
				System.out.println("Incorrect Input. Please enter a value in the []:");
				menuOption = input.getInput().toUpperCase();
			}
			if (menuOption.equals(ui.myOpenTicketsOption)) {
				ticket.changeOpenStatus(ticket.selectedTicket);
			}

			if (menuOption.equals(ui.closeTicketOption)) {
				ui.resolvePrompt();
				menuOption = input.getInput().toUpperCase();
				while (!menuOption.equals(ui.confirmOption) && !menuOption.equals(ui.rejectOption) && !menuOption.equals(ui.backOption)) {
					System.out.println("Incorrect Input. Please enter 'Y' for yes or 'N' for no:");
					menuOption = input.getInput().toUpperCase();
				}
				if (menuOption.equals(ui.confirmOption)) {
					ticket.changeStatus(ticket.selectedTicket, menuOption, currentTechnician);
				} else if (menuOption.equals(ui.rejectOption)) {
					ticket.changeStatus(ticket.selectedTicket, menuOption, currentTechnician);
				}
			}
		} while (!menuOption.equals(ui.backOption));			
		return menuOption;
	}
		
	private void displayTicketList(ArrayList<String> ticketList){
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
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("ddMMyy");
		LocalDate start = null;
		LocalDate end = null;
		String startDateValidationResult;
		String endDateValidationResult;
		do{
			menuOption = "";
			ui.reportStartDatePrompt();
			menuOption = input.getInput();
			startDateValidationResult = validate.reportDate(menuOption);
			if(startDateValidationResult == null){
				start = LocalDate.parse(menuOption, dateFormat);
				ui.reportEndDatePrompt();
				menuOption = input.getInput();
				endDateValidationResult = validate.reportDate(menuOption);
				if(endDateValidationResult == null && startDateValidationResult == null){
					end = LocalDate.parse(menuOption, dateFormat);
					ui.reportConfirmPrompt(start.toString(), end.toString());
					menuOption = input.getInput().toUpperCase();
				} else {
					System.out.println(endDateValidationResult);
				}
			} else {
				System.out.println(startDateValidationResult);
			}
		} while(!menuOption.equals(ui.confirmOption) && !menuOption.toUpperCase().equals(ui.backOption));
		if(menuOption.equals(ui.confirmOption)){
			ArrayList<String> allTickets = ticket.getAllTickets(FileHandler.ticketRecord);
			LocalDateTime startDate = start.atStartOfDay();
			LocalDateTime endDate = end.atStartOfDay();
			report.generateReport(allTickets, startDate, endDate);
		}
	}
}