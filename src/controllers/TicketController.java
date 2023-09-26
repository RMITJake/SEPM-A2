package src.controllers;
import src.handlers.*;
import src.models.Account;
import src.models.Ticket;
import src.views.TicketUI;
import java.time.LocalDateTime;
public class TicketController {
    // TicketController will handle both the Ticket and TicketUpdate Models
    private FileHandler file = new FileHandler();
    private InputHandler input = new InputHandler();
    private TicketUI ui = new TicketUI();
    private String userInput;
    public TicketController(){
        System.out.println("Constructing TicketController");
        
        // Test the file handler
        System.out.println(file.Read("OpenTicket"));
        System.out.println(file.Read("TicketUpdate"));
        System.out.println(file.Read("ArchivedTicket"));
    }

    public void CreateNewTicket(Account currentUser){
        Ticket newTicket = new Ticket();
        // Next 2 to be calculated and assigned
        newTicket.setId(5);
        newTicket.setTechnicianAssignedId(assignTechnician());

        newTicket.setRequesterId(currentUser.getId());

        // Loop and validate this
        ui.description();
        userInput = input.getInput();
        newTicket.setDescription(userInput);

        // Loop and validate this
        ui.severity();
        userInput = input.getInput();
        newTicket.setSeverity(userInput);

        newTicket.setCreationDate(LocalDateTime.now());

        // Show what will be saved to the csv
        System.out.println("New ticket created");
        System.out.println(newTicket.getProperties());
    }
    public void OpenTickets(){
        System.out.println(file.Read("OpenTicket"));
    }

    private int assignTechnician(){
        // read through the ticket table and count which tech has which tickets
        // return the technician id with the least tickets
        return 1000;
    }

    // Functions needed
    // getAllTickets
    // getAllTicketsByAccountId
    // getAllTicketsByTechnicianId
    // getAllOpenTicketsByAccountId
    // getAllOpenTicketsByTechnicianId
}