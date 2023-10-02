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

    public void ForgotPassword(){
        System.out.print("Enter your email address or type \"C\" to cancel: ");
    }

    public void ForgotPassword(String email){
        System.out.println("If an account for " + email + " is found a ticket will be submitted to reset the password.");
    }

    public void SelectTicket(){
        System.out.print("Enter the id of the ticket you wish to view: ");
    }

    public void DisplayTicket(Ticket ticket){
        String ticketDetails;
        System.out.println("------------------------------");
        ticketDetails = "Ticket ID: " + ticket.getId();
        ticketDetails += ", Requester: " + ticket.getRequesterId() + "\n";
        ticketDetails += "Assigned Technician: " + ticket.getTechnicianAssignedId() + "\n";
        ticketDetails += "Description: " + ticket.getDescription() + "\n";
        ticketDetails += "Severity: " + ticket.getSeverity() + "\n";
        ticketDetails += "Status: " + ticket.getStatus() + "\n";
        ticketDetails += "Creation Date: " + ticket.getCreationDate() + "\n";
        System.out.println(ticketDetails);
        System.out.println("------------------------------");
    }
}