package src.controllers;

import src.handlers.*;
import src.models.Account;
import src.models.Technician;
import src.models.Ticket;
import src.views.TicketUI;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.*;

public class TicketController {
	// TicketController will handle both the Ticket and TicketUpdate Models
	private FileHandler file = new FileHandler();
	private InputHandler input = new InputHandler();
	private ValidationHandler validate = new ValidationHandler();
	private TicketUI ui = new TicketUI();
	private String userInput;
	protected Ticket selectedTicket;

	// Record strings, used to minimize hard coded file references
	protected final String openTicketRecord = file.openTicketRecord;
	protected List<String> allTickets;
	private final String escalationRecord = file.escalationRecord;
	private final String technicianRecord = file.technicianRecord;
	List<Ticket> tickets;
	
	public void createNewTicket(Account currentUser, String record) {
		// Initialise ticket
		Ticket newTicket = new Ticket();
		// Set the requesterId to the currentUserId
		newTicket.setRequesterId(currentUser.getId());

		// Get user input for description and severity properties
		boolean confirmDetails = false;
		while (!confirmDetails) {
			ui.description();
			userInput = input.getInput();
			newTicket.setDescription(userInput);

			// Loop and validate this
			ui.severity();
			userInput = input.getInput();
			while (!userInput.equals("1") && !userInput.equals("2") && !userInput.equals("3")) {
				System.out.println("Retry, you must enter one of the options in the []. ");
				ui.severity();
				userInput = input.getInput();
			}
			if (userInput.equals("1")){
				userInput = "low";
			}
			if (userInput.equals("2")){
				userInput = "medium";
			}
			if (userInput.equals("3")){
				userInput = "high";
			}
			newTicket.setSeverity(userInput);

			ui.confirm(newTicket.getRequesterId(), newTicket.getDescription(), newTicket.getSeverity());
			userInput = input.getInput();
			while (!userInput.toUpperCase().equals("Y") && !userInput.toUpperCase().equals("N")) {
				System.out.println("You must enter 'Y' for yes or 'N' for No. Try again:");
				ui.confirm(newTicket.getRequesterId(), newTicket.getDescription(), newTicket.getSeverity());
				userInput = input.getInput();
			}
			if (userInput.toUpperCase().equals("Y")) {
				confirmDetails = true;
			}
			if (userInput.toUpperCase().equals("N")) {
				System.out.println("Please start again!");
			}
		}

		// Use getNewestTicket() to calculate the ticket Id
		newTicket.setId(getNewestTicket(openTicketRecord).getId() + 1);
		// Use assignTechnicianByTicketCount() to calculate assigned tech
		newTicket.setTechnicianAssignedId(assignTechnician(newTicket.getSeverity()));
		// Set the ticket status to open
		newTicket.setStatus("open and unresolved");
		// Set the creationDate to now
		newTicket.setCreationDate(LocalDateTime.now());

		// Show what will be saved to the csv
		// Show ticket confirmation
		System.out.println("New ticket created");
		// Show ticket details
		System.out.println(newTicket.getTicketDetails());
		// Write the ticket to OpenTicket.csv
		file.write(record, newTicket.getProperties());
	}

	public Ticket getNewestTicket(String record) {
		// Read OpenTicket.csv into an array
		ArrayList<String> openTicketTable = file.read(record);
		// write the last ticket in the open ticket array into it's own array for
		// processing
		String[] lastTicketInList = openTicketTable.get(openTicketTable.size() - 1).split(",", -1);
		// Initialise an blank ticket
		Ticket newestTicket = new Ticket();
		// Write the extracted ticket information to ticket properties
		newestTicket.setId(Integer.parseInt(lastTicketInList[0]));
		newestTicket.setTechnicianAssignedId(Integer.parseInt(lastTicketInList[1]));
		newestTicket.setRequesterId(Integer.parseInt(lastTicketInList[2]));
		newestTicket.setDescription(lastTicketInList[3]);
		newestTicket.setSeverity(lastTicketInList[4]);
		newestTicket.setStatus(lastTicketInList[5]);
		newestTicket.setCreationDate(LocalDateTime.parse(lastTicketInList[6]));
		// newestTicket.setResolvedDate();

		// return the ticket
		return newestTicket;
	}

	// Get all open tickets
	// If technician is null then only tickets submitted by the user will be shown
	// If technician is not null then tickets assigned to the technician will be
	// shown
	public ArrayList<String> getAllTickets(String record, Account currentUser, Technician currentTechnician) {
		ArrayList<String> userTickets = new ArrayList<String>();
		ArrayList<String> ticketTable = file.read(record);
		String[] ticket;
		for (int index = 0; index < ticketTable.size(); index++) {
			ticket = ticketTable.get(index).split(",", -1);
			// get tickets assigned to technician
			if (currentTechnician != null && Integer.parseInt(ticket[1]) == currentTechnician.getId()) {
				userTickets.add(ticketTable.get(index));
			}
			// get tickets owned by user
			else if (currentTechnician == null && Integer.parseInt(ticket[2]) == currentUser.getId()) {
				userTickets.add(ticketTable.get(index));
			}
		}
		if(userTickets.size() == 0){
			ui.emptyList();
		}
		return userTickets;
	}

	public ArrayList<String> getAllTickets(String record) {
		ArrayList<String> ticketTable = file.read(record);
		return ticketTable;
	}

	public ArrayList<String> getUserTickets(String status, Technician currentTechnician, int flow){
		// pass ints in to this function to control the flow
		// 1 = my user tickets
		// 2 = other users archived tickets
		// This cuts down on code duplication
		ArrayList<String> userTickets = new ArrayList<String>();
		ArrayList<String> ticketTable = getAllTickets(openTicketRecord);
		String[] ticket;
		for (int index = 0; index < ticketTable.size(); index++) {
			ticket = ticketTable.get(index).split(",", -1);
			if(flow == 1 && Integer.parseInt(ticket[1]) == currentTechnician.getId() && ticket[5].equals(status)){
				userTickets.add(ticketTable.get(index));
			} else if (flow == 2 && Integer.parseInt(ticket[1]) != currentTechnician.getId() && ticket[5].equals(status)) {
				userTickets.add(ticketTable.get(index));
			}
		}
		if(userTickets.size() == 0){
			ui.emptyList();
		}
		return userTickets;
	}


//    public void archiveOldTickets() {
//        tickets = getAllTickets();
//        for (Ticket ticket : tickets) {
//            System.out.println(ticket.getStatus());
//            if (ticket.getResolvedDate() != null) { 	
//                System.out.println(ticket.getStatus());
//                if (ticket.getResolvedDate().isAfter(LocalDateTime.now().plusDays(1)) 
//                    && (ticket.getStatus().equals("closed and resolved") 
//                    || ticket.getStatus().equals("closed and unresolved"))) {
//                    ticket.setStatus("archived");
//                    updateTicketRecordArchive(ticket);                   
//                }
//            }
//        }
//    }
//
    public void updateTicketRecordArchive(Ticket updatedTicket) {
        // Update the list of tickets in memory
        for (int i = 0; i < tickets.size(); i++) {
            if (tickets.get(i).getId() == updatedTicket.getId()) {
                tickets.set(i, updatedTicket);
                break;
            }
        }

        // Re-write the entire file with the updated list
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("records/OpenTicket.csv"))) {
            for (Ticket ticket : tickets) {
                writer.write(ticket.getProperties());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


	private int assignTechnician(String severity) {
		return assignTechnicianByTicketCount(assignTechnicianBySeverity(severity));
	}

	private ArrayList<Integer> assignTechnicianBySeverity(String severity) {
		// Read Technician.csv into an array
		ArrayList<String> technicianTable = file.read(technicianRecord);
		ArrayList<Integer> technicianList = new ArrayList<Integer>();

		String technicianLevel;
		if (!severity.equals("high")) {
			technicianLevel = "1";
		} else {
			technicianLevel = "2";
		}

		// Loop through technicianTable
		// Split the string into parts
		// Save the technicianId to the list of technicians if the level is correct
		// String to temporarily hold split Technician information
		String[] currentTechnician;
		for (int index = 0; index < technicianTable.size(); index++) {
			currentTechnician = technicianTable.get(index).split(",", -1);
			if (currentTechnician[2].equals(technicianLevel)) {
				technicianList.add(Integer.parseInt(currentTechnician[0]));
			}
		}

		return technicianList;
	}

	// Takes in a list storing technicianIds extracted from the array
	private int assignTechnicianByTicketCount(ArrayList<Integer> technicianList) {
		// Int to store the case count for the assigned technician
		int assignedTechnicianCaseCount = -1;
		// Read the OpenTicket.csv to an array
		ArrayList<String> openTicketTable = file.read(openTicketRecord);
		// Initialise an array to store the case counts
		ArrayList<Integer> technicianCaseCount = new ArrayList<Integer>();
		// String to temporarily hold split OpenTicket information
		String[] openTicketString;
		// Int to hold the case count for the technician being iterated over
		int caseCount;

		// Iterate over the list of technicianIds
		for (int technicianIndex = 0; technicianIndex < technicianList.size(); technicianIndex++) {
			// reset the case count for each tech
			caseCount = 0;
			// Iterate over the open ticket list
			for (int caseIndex = 0; caseIndex < openTicketTable.size(); caseIndex++) {
				// split the string to allow the technicianId to be extracted
				openTicketString = openTicketTable.get(caseIndex).split(",", -1);
				// If the technicianId matches the current technicianId which is being iterated
				// over, increase case count by 1
				if (technicianList.get(technicianIndex) == Integer.parseInt(openTicketString[1])) {
					caseCount++;
				}
			}

			// Add the casecount to the list of case counts
			technicianCaseCount.add(caseCount);

			// if the current number of cases is less than that of the currently assigned
			// tech, then assign the tech with less cases
			if (caseCount < assignedTechnicianCaseCount || assignedTechnicianCaseCount == -1) {
				// assignedTechnicianId = technicianList.get(technicianIndex);
				assignedTechnicianCaseCount = caseCount;
			}
		}

		// Array to store the techs with the lowest case count
		ArrayList<Integer> technicianLowestCaseCount = new ArrayList<Integer>();
		// Get a list indexes with the lowest ticket counts
		for (int index = 0; index < technicianCaseCount.size(); index++) {
			if (assignedTechnicianCaseCount == technicianCaseCount.get(index)) {
				technicianLowestCaseCount.add(technicianList.get(index));
			}
		}

		// return the techId
		return assignTechnicianAtRandom(technicianLowestCaseCount);
	}

	public int assignTechnicianAtRandom(ArrayList<Integer> technicianLowestCaseCount) {
		// If there is only 1 technician in the list assign the case
		if (technicianLowestCaseCount.size() == 1) {
			return technicianLowestCaseCount.get(0);
		}

		// Create the Random object to generate the random number
		Random randomiser = new Random();
		// Get the upper limit for the randomiser
		int upperLimit = technicianLowestCaseCount.size();
		// Generate the random number
		int randomNumber = randomiser.nextInt(upperLimit);
		// Return the techId at the random index
		return technicianLowestCaseCount.get(randomNumber);
	}

	public void forgotPassword() {
		do {
			ui.forgotPassword();
			userInput = input.getInput();
		} while (validate.email(userInput) != null && !userInput.equals("B") && !userInput.equals("b"));
		if (validate.email(userInput) != null) {
			ui.forgotPassword(userInput);
			resetPassword(userInput);
		}
	}

	public void resetPassword(String email) {
		Ticket newTicket = new Ticket();
		// Set the requesterId to the system user
		newTicket.setRequesterId(1);
		newTicket.setDescription("Password Reset: " + email);
		newTicket.setSeverity("low");
		newTicket.setId(getNewestTicket(openTicketRecord).getId() + 1);
		newTicket.setTechnicianAssignedId(assignTechnician(newTicket.getSeverity()));
		newTicket.setStatus("open and unresolved");
		newTicket.setCreationDate(LocalDateTime.now());
		// Write the ticket to OpenTicket.csv
		file.write(openTicketRecord, newTicket.getProperties());
	}

	public void selectTicket() {
		// Needs checks so users can only select their own tickets
		// Techs can check all tickets
		// Create an empty ticket
		selectedTicket = new Ticket();
		ui.selectTicket();
		userInput = input.getInput();
		while (!validate.ticketId(userInput)){
			System.out.println("You must enter a whole number. Try again:");
			userInput = input.getInput();
		}
		if (validate.ticketId(userInput)){
			ArrayList<String> ticketTable = file.read(openTicketRecord);
			String[] ticketString;
			int ticketIndex = 0;
			do {
				ticketString = ticketTable.get(ticketIndex).split(",", -1);
				if (Integer.parseInt(userInput) == Integer.parseInt(ticketString[0])) {
					selectedTicket.setId(Integer.parseInt(ticketString[0]));
					selectedTicket.setTechnicianAssignedId(Integer.parseInt(ticketString[1]));
					selectedTicket.setRequesterId(Integer.parseInt(ticketString[2]));
					selectedTicket.setDescription(ticketString[3]);
					selectedTicket.setSeverity(ticketString[4]);
					selectedTicket.setStatus(ticketString[5]);
					selectedTicket.setCreationDate(LocalDateTime.parse(ticketString[6]));
					if (ticketString[7] != null && !ticketString[7].equals("null")) {
						selectedTicket.setResolvedDate(LocalDateTime.parse(ticketString[7]));
					}
				}
				ticketIndex++;
			} while (selectedTicket.getId() == 0 && ticketIndex < ticketTable.size());
			if(selectedTicket.getId() == 0){
				System.out.println("No ticket found.");
			}
		}
	}

	public void updateTicketRecord(Ticket ticket) {
		ArrayList<String> oldTicketTable = file.read(openTicketRecord);
		String newTicketTable = "";
		String[] ticketString;
		for (int ticketIndex = 0; ticketIndex < oldTicketTable.size(); ticketIndex++) {
			ticketString = oldTicketTable.get(ticketIndex).split(",", -1);
			if (ticket.getId() == Integer.parseInt(ticketString[0])) {
				newTicketTable += ticket.getProperties();
			} else {
				newTicketTable += oldTicketTable.get(ticketIndex) + "\r\n";
			}
		}
		file.writeOver(openTicketRecord, newTicketTable);
	}

	public void displayTicket(Ticket ticket) {
		ui.displayTicket(ticket);
	}

	public void displayTicketString(String ticket) {
		Ticket newTicket = new Ticket();
		String[] ticketString;
		ticketString = ticket.split(",", -1);
		newTicket.setId(Integer.parseInt(ticketString[0]));
		newTicket.setTechnicianAssignedId(Integer.parseInt(ticketString[1]));
		newTicket.setRequesterId(Integer.parseInt(ticketString[2]));
		newTicket.setDescription(ticketString[3]);
		newTicket.setSeverity(ticketString[4]);
		newTicket.setStatus(ticketString[5]);
		newTicket.setCreationDate(LocalDateTime.parse(ticketString[6]));
		// selectedTicket.setResolvedDate(LocalDateTime.parse(ticketString[7]));
		ui.displayTicket(newTicket);
	}

	public void changeSeverity(Ticket ticket) {
		userInput = "";
		ui.changeSeverity();
		userInput = input.getInput();
		while (!userInput.equals("1") && !userInput.equals("2") && !userInput.equals("3")) {
			System.out.println("Retry, you must enter one of the options in the []. ");
			ui.severity();
			userInput = input.getInput();
		}
		if (userInput.equals("1")){
			userInput = "low";
		}
		if (userInput.equals("2")){
			userInput = "medium";
		}
		if (userInput.equals("3")){
			userInput = "high";
		}
		if(validate.ticketSeverity(userInput)){
			if((!ticket.getSeverity().equals("high") && userInput.equals("high")) || ticket.getSeverity().equals("high") && !userInput.equals("high")){
				ticket.setSeverity(userInput);
				ticket.setTechnicianAssignedId(assignTechnician(ticket.getSeverity()));
				updateTicketRecord(ticket);
			} else {
				ticket.setSeverity(userInput);
				updateTicketRecord(ticket);
			}
		}
	}

	public void escalateTicket(Ticket ticket, Technician currentTechnician) {
		// Options to escalate ticket
		// 1. Create a new ticket in the escalate table which is assigned to L2
		// - Pros = original CO maintains ticket ownership, L2 can open and close
		// tickets for escalation
		// - Cons
		// 2. Assign ticket to L2
		// - Pros = easy
		// - Cons - L1 looses track of the ticket

		Ticket escalationTicket = ticket;
		ui.escalationReason();
		String confirm;
		do {
			confirm = "";
			userInput = input.getInput();
			System.out.println("Is the below escalation reason correct? [Y/N] ");
			System.out.println(userInput);
			confirm = input.getInput().toUpperCase();
		} while (!confirm.equals("Y"));
		escalationTicket.setDescription(userInput);
		escalationTicket.setRequesterId(currentTechnician.getId());
		escalationTicket.setTechnicianAssignedId(assignTechnician("high"));
		escalationTicket.setId(getNewestTicket(escalationRecord).getId() + 1);
		escalationTicket.setCreationDate(LocalDateTime.now());
		file.write(escalationRecord, escalationTicket.getProperties());
	}

	public void changeStatus(Ticket ticket, String menuOption) {
		Ticket statusTicket = ticket;
		statusTicket.setResolvedDate(LocalDateTime.now());
		if (menuOption.equals("Y")) {
			statusTicket.setStatus(Ticket.closedResolvedStatus);
			ui.displayTicket(statusTicket);
		} else if (menuOption.equals("N")) {
			statusTicket.setStatus(Ticket.closedUnresolvedStatus);
			ui.displayTicket(statusTicket);
		}
		updateTicketRecord(statusTicket);
	}

	

	public void changeOpenStatus(Ticket ticket) {
		Ticket statusTicket = ticket;
		if (statusTicket.getStatus().equals(Ticket.archivedStatus)) {
			ui.archivedPrompt();
			ui.displayTicket(statusTicket);
		} else {
			statusTicket.setStatus(Ticket.openStatus);
			ui.displayTicket(statusTicket);
		}
		updateTicketRecord(statusTicket);
	}
}