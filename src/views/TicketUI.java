package src.views;
import src.models.Ticket;
public class TicketUI {
    public void description(){
        System.out.println("What is the issue? ");
    }

    public void severity(){
        System.out.println("How severe is this issue? ");
    }

    public void confirm(int requestedId, String description, String severity){
        System.out.println("----------------------------");
        System.out.println("Ticket Summary");
        System.out.println("Account Id: " + requestedId);
        System.out.println("Problem description: " + description);
        System.out.println("Severity: " + severity);
        System.out.println("----------------------------");
        System.out.print("Are the following details correct? [Y/N] ");
    }

    public void forgotPassword(){
        System.out.print("Enter your email address or type \"B\" to go back: ");
    }

    public void forgotPassword(String email){
        System.out.println("If an account for " + email + " is found a ticket will be submitted to reset the password.");
    }

    public void selectTicket(){
        System.out.print("Enter the id of the ticket you wish to view: ");
    }

    public void displayTicket(Ticket ticket){
        String ticketDetails;
        System.out.println("------------------------------");
        ticketDetails = "Ticket ID: " + ticket.getId();
        ticketDetails += ", Requester: " + ticket.getRequesterId() + "\n";
        ticketDetails += "Assigned Technician: " + ticket.getTechnicianAssignedId() + "\n";
        ticketDetails += "Description: " + ticket.getDescription() + "\n";
        ticketDetails += "Severity: " + ticket.getSeverity() + "\n";
        ticketDetails += "Status: " + ticket.getStatus() + "\n";
        ticketDetails += "Creation Date: " + ticket.getCreationDate()+ "\n";
        ticketDetails += "Resolution Date: " + ticket.getResolvedDate() + "\n";
        System.out.println(ticketDetails);
        System.out.println("------------------------------");
    }
    
    
    
    public void changeSeverity(){
        String menu = "";
        menu += "What is the severity of the ticket: ";
        System.out.print(menu);
    }

    public void escalationReason(){
        String menu = "";
        menu += "Why is this ticket being escalated: ";
        System.out.print(menu);
    }
    
}