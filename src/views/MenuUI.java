package src.views;
import src.controllers.MenuController;
import src.handlers.InputHandler;
public class MenuUI {
    public MenuUI(){
        System.out.println("Constructing MenuUI");
        // Create an instance of the Menu backend and InputHandler
        MenuController menu = new MenuController();
        InputHandler userInput = new InputHandler();
    }
}