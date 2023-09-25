package src.views;
public class MenuUI {
    private String uiText;

    public MenuUI(){
        System.out.println("Constructing MenuUI");
    }

    public void Welcome(){
        // Welcome prompt
        uiText = "------------------------------\n";
        uiText += "Sales Power Ticketing System\n";
        uiText += "------------------------------";
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
}