import java.io.*;
import src.controllers.MenuController;

class App {
    App(){
        System.out.println("Starting Program");
        // Create an instance of the MenuUI frontend
        MenuController menu = new MenuController();
    }
    public static void main(String[] args){
        // Run the app
        App app = new App();
    }
}