package src.views;
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
}