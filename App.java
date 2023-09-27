import java.io.*;
import src.controllers.MenuController;

class App {
    String applicationLoop;
    boolean loggedIn;
    boolean applicationExit;

    App(){
        // Create an instance of the MenuUI frontend
        MenuController menu = new MenuController();
        do{
            applicationLoop = menu.WelcomeLoop();
            if(applicationLoop.equals("C")){
                menu.CreateUser();
            }
            if(applicationLoop.equals("F")){}
            if(applicationLoop.equals("L")){
                loggedIn = menu.LoginLoop();
            }
            if(loggedIn){
                menu.MainMenuLoop();
            }
            if(applicationLoop.equals("E")){}
        } while (!applicationLoop.equals("E"));
    }
    public static void main(String[] args){
        // Run the app
        App app = new App();
    }
}