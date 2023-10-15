package src.views;
public class MenuUI {
    private String uiText;

    public void welcomeBanner(){
        // Welcome prompt
        uiText = "------------------------------\n";
        uiText += "    Cinco Ticketing System   \n";
        uiText += "------------------------------";
        System.out.println(uiText); 
    }

    public void welcomePrompt(){
        uiText = "Press the corresponding key in brackets to make a selection\n";
        uiText += "[L] Login\n";
        uiText += "[C] Create an Account\n";
        uiText += "[F] Forgot Password\n";
        uiText += "[Q] Quit\n";
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
        uiText += "[N] Create a new ticket\n";
        //uiText += "[P] Pick Ticket\n";
        uiText += "[O] My Open Tickets\n";
        if(techOptions > 0){
            uiText += "--------  Tech Menu  ---------\n";
            uiText += "[A] Assigned Tickets\n";
            uiText += "[P] Pick Ticket\n";
            uiText += "[U] My Archived Tickets\n";
            uiText += "[V] Other Technician's Archived Tickets\n";
        }
        uiText += "[L] Logout\n";
        uiText += "[Q] Quit\n";
        System.out.print(uiText);
    }
    
    public void ticketMenu(){
        String menu = "";       
        menu += "[S] Set ticket severity\n";
        menu += "[E] Escalate ticket\n";
        menu += "[T] Change ticket status\n";
        menu += "[B] Back to main menu"; 
        System.out.println(menu);
    }
    public void ticketStatusMenu(){
        String menu = "";   
        menu += "------------------------------\r\n";
        menu += "[O] Open Ticket\n";
        menu += "[X] Close ticket\n";
        menu += "[B] Back to main menu\n";
        menu += "------------------------------\r\n";
        System.out.println(menu);
    }
    
    public void resolvePrompt() {
    	 uiText = "Was the ticket issue resolved? (Y/N input only:) ";
         System.out.print(uiText);
    }

	
    
    
}