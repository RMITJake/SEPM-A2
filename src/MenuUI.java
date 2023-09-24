package src;
import src.controllers.MenuController;
public class MenuUI {
    public MenuUI(){
        System.out.println("Constructing MenuUI");
        // Create an instance of the Menu backend and InputHandler
        MenuController menu = new MenuController();
        InputHandler userInput = new InputHandler();
    }
}