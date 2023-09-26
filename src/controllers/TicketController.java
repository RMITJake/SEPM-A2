package src.controllers;
import src.handlers.*;
import src.models.Account;
import src.models.Ticket;
import src.views.TicketUI;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        newTicket.setId(getNewestTicket().getId()+1);
        newTicket.setTechnicianAssignedId(assignTechnician());

        newTicket.setRequesterId(currentUser.getId());

        // Loop and validate this
        boolean confirmDetails = false;
        while(!confirmDetails){
            ui.description();
            userInput = input.getInput();
            newTicket.setDescription(userInput);

            // Loop and validate this
            ui.severity();
            userInput = input.getInput();
            newTicket.setSeverity(userInput);

            ui.confirm(newTicket.getRequesterId(), newTicket.getDescription(), newTicket.getSeverity());
            if(input.getInput().equals("y")){
                confirmDetails = true;
            }
        }

        newTicket.setCreationDate(LocalDateTime.now());

        // Show what will be saved to the csv
        System.out.println("New ticket created");
        System.out.println(newTicket.getProperties());
    }

    public void OpenTickets(){
        System.out.println(file.Read("OpenTicket"));
    }

    public Ticket getNewestTicket(){
        ArrayList<String> openTicketTable = file.Read("OpenTicket");
        String[] lastTicketInList = openTicketTable.get(openTicketTable.size()-1).split(",",-1);
        Ticket newestTicket = new Ticket();
        newestTicket.setId(Integer.parseInt(lastTicketInList[0]));
        newestTicket.setTechnicianAssignedId(Integer.parseInt(lastTicketInList[1]));
        newestTicket.setRequesterId(Integer.parseInt(lastTicketInList[2]));
        newestTicket.setDescription(lastTicketInList[3]);
        newestTicket.setSeverity(lastTicketInList[4]);
        // newestTicket.setCreationDate();
        // newestTicket.setResolvedDate();

        return newestTicket;
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
    // getLastTicket
}