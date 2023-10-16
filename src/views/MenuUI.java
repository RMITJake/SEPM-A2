package src.views;
public class MenuUI {
    private String uiText;

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
	public final String backOption = "B";
	public final String createNewTicketOption = "N";
	public final String myOpenTicketsOption = "O";
	public final String logoutOption = "L";

	// Technician Menu
	public final String assignedTicketsOption = "A";
	public final String pickTicketOption = "P";
	public final String myArchivedTicketsOption = "U";
	public final String otherArchivedTicketsOption = "V";
	public final String myClosedTicketsOption = "G";
	public final String otherClosedTicketsOption = "H";
	public final String reportOption = "R";

	// Ticket Menu
	public final String setTicketSeverityOption = "S";
	public final String escalateTicketOption = "E";
	public final String changeTicketStatusOption = "T";

	// Ticket Status Menu
	public final String openTicketOption = "O";
	public final String closeTicketOption = "X";

    public void welcomeBanner(){
        // Welcome prompt
        uiText = "------------------------------\n";
        uiText += "    Cinco Ticketing System   \n";
        uiText += "------------------------------";
        System.out.println(uiText); 
    }

    public void welcomePrompt(){
        uiText = "Press the corresponding key in brackets to make a selection\n";
        uiText += "[" + loginOption + "] Login\n";
        uiText += "[" + createAccountOption + "] Create an Account\n";
        uiText += "[" + forgotPasswordOption + "] Forgot Password\n";
        uiText += "[" + quitOption + "] Quit\n";
        uiText += "Selection: ";
        System.out.print(uiText);
    }

    public void loginPrompt(){
        // Get users AccountId
        uiText = "Enter your account ID: ";
        System.out.print(uiText);
    }
    
    public void passwordPrompt(){
        // Get users AccountId
        uiText = "Enter your password: ";
        System.out.print(uiText);
    }

    // The techOptions input dependency controls if technician commands are visible
    public void mainMenu(int techOptions){
        // Main menu
        uiText = "------------------------------\n";
        uiText += "--------  Main Menu  ---------\n";
        uiText += "------------------------------\n";
        uiText += "[" + createNewTicketOption + "] Create a new ticket\n";
        uiText += "[" + myOpenTicketsOption + "] My Open Tickets\n";
        if(techOptions > 0){
            uiText += technicianMenu();
        }
        uiText += "[" + logoutOption + "] Logout\n";
        uiText += "[" + quitOption + "] Quit\n";
        System.out.print(uiText);
    }

    public String technicianMenu(){
        uiText = "--------  Tech Menu  ---------\n";
        uiText += "[" + assignedTicketsOption + "] Assigned Tickets\n";
        uiText += "[" + pickTicketOption + "] Pick Ticket\n";
        uiText += "[" + myClosedTicketsOption + "] My Closed Tickets\n";
        uiText += "[" + otherClosedTicketsOption + "] Other Technician's Closed Tickets\n";
        uiText += "[" + myArchivedTicketsOption + "] My Archived Tickets\n";
        uiText += "[" + otherArchivedTicketsOption + "] Other Technician's Archived Tickets\n";
        uiText += "[" + reportOption + "] Generate Report\n";

        return uiText;
    }
    
    public void ticketMenu(){
        String menu = "";       
        menu += "[" + setTicketSeverityOption + "] Set ticket severity\n";
        menu += "[" + escalateTicketOption + "] Escalate ticket\n";
        menu += "[" + changeTicketStatusOption + "] Change ticket status\n";
        menu += "[" + backOption + "] Back to main menu"; 
        System.out.println(menu);
    }
    public void ticketStatusMenu(){
        String menu = "";   
        menu += "------------------------------\r\n";
        menu += "[" + openTicketOption + "] Open Ticket\n";
        menu += "[" + closeTicketOption + "] Close ticket\n";
        menu += "[" + backOption + "] Back to main menu\n";
        menu += "------------------------------\r\n";
        System.out.println(menu);
    }
    
    public void resolvePrompt() {
    	 uiText = "Was the ticket issue resolved? (Y/N input only:) ";
         System.out.print(uiText);
    }

    public void reportStartDatePrompt(){
        uiText = "Enter the date to start the report (in the format DDMMYY): ";
        System.out.print(uiText);
    }

    public void reportEndDatePrompt(){
        uiText = "Enter the date to end the report (in the format DDMMYY): ";
        System.out.print(uiText);
    }

    public void reportConfirmPrompt(String start, String end){
        uiText = "Generate a report for tickets between " + start + " and " + end + " (Y/N input only): ";
        System.out.print(uiText);
    }
}