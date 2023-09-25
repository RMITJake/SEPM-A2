package src.controllers;
import src.handlers.FileHandler;
public class TicketController {
    // TicketController will handle both the Ticket and TicketUpdate Models
    private FileHandler file = new FileHandler();
    public TicketController(){
        System.out.println("Constructing TicketController");
        
        // Test the file handler
        System.out.println(file.Read("Ticket"));
        System.out.println(file.Read("TicketUpdate"));
    }
}