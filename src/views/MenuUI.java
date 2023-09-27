package src.views;
public class MenuUI {
    private String uiText;

    public void WelcomeBanner(){
        // Welcome prompt
        uiText = "------------------------------\n";
        uiText += "Sales Power Ticketing System\n";
        uiText += "------------------------------";
        System.out.println(uiText);
    }

    public void WelcomePrompt(){
        uiText = "Press the corresponding key in brackets to make a selection\n";
        uiText += "[L]ogin\n";
        uiText += "[C]reate an Account\n";
        uiText += "[F]orgot Password\n";
        uiText += "[E]xit\n";
        System.out.println(uiText);
    }

    public void LoginPrompt(){
        // Get users AccountId
        uiText = "Enter your acount number: ";
        System.out.print(uiText);
    }
    
    public void PasswordPrompt(){
        // Get users AccountId
        uiText = "Enter your password: ";
        System.out.print(uiText);
    }

    // The techOptions input dependency controls if technician commands are visible
    public void MainMenu(int techOptions){
        // Main menu
        uiText = "------------------------------\n";
        uiText += "--------  Main Menu  ---------\n";
        uiText += "------------------------------\n";
        uiText += "[C]reate a new ticket\n";
        if(techOptions > 0){
            uiText += "[M]y Open Tickets\n";
        }
        uiText += "[L]ogout\n";
        uiText += "[E]xit\n";
        System.out.print(uiText);
    }
}