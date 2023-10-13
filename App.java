import src.controllers.ArchiveController;
import src.controllers.MenuController;

class App {
    String applicationLoop;
    boolean loggedIn;

    private void Run(){
        // Create an instance of the MenuUI frontend
        MenuController menu = new MenuController();
        ArchiveController archive = new ArchiveController();
        archive.getAllTickets();
        do{
            applicationLoop = menu.welcomeLoop().toUpperCase();
            if(applicationLoop.equals("C")){
                menu.createUser();
            }
            if(applicationLoop.equals("F")){
                menu.forgotPassword();
            }
            if(applicationLoop.equals("L")){
                loggedIn = menu.loginLoop();
            }
            if(loggedIn){
                applicationLoop = menu.mainMenuLoop();
            }
        } while (!applicationLoop.equals("Q"));
        System.out.println("Thank you for using the program");
    }

    public static void main(String[] args){
        // Run the app
        App app = new App();
        app.Run();
        System.exit(0);
    }
}