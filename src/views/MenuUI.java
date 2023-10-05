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
        uiText += "[L]ogin\n";
        uiText += "[C]reate an Account\n";
        uiText += "[F]orgot Password\n";
        uiText += "[E]xit\n";
        uiText += "Selection: ";
        System.out.print(uiText);
    }

    public void loginPrompt(){
        // Get users AccountId
        uiText = "Enter your acount number: ";
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
        uiText += "[C]reate a new ticket\n";
        uiText += "[S]elect Ticket\n";
        uiText += "[M]y Open Tickets\n";
        if(techOptions > 0){
            uiText += "--------  Tech Menu  ---------\n";
            uiText += "[A]ssigned Tickets\n";
        }
        uiText += "[L]ogout\n";
        uiText += "[E]xit\n";
        System.out.print(uiText);
    }
    
    public void ticketMenu(){
        String menu = "";
        menu += "[S]et ticket severity\n";
        menu += "[E]scalate ticket\n";
        menu += "[M]ain menu";
        System.out.println(menu);
    }
}