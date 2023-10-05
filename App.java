import src.controllers.MenuController;

class App {
    String applicationLoop;
    boolean loggedIn;

    private void Run(){
        // Create an instance of the MenuUI frontend
        MenuController menu = new MenuController();
        do{
            applicationLoop = menu.WelcomeLoop().toUpperCase();
            if(applicationLoop.equals("C")){
                menu.CreateUser();
            }
            if(applicationLoop.equals("F")){
                menu.ForgotPassword();
            }
            if(applicationLoop.equals("L")){
                loggedIn = menu.LoginLoop();
            }
            if(loggedIn){
                applicationLoop = menu.MainMenuLoop();
            }
        } while (!applicationLoop.equals("E"));
    }

    public static void main(String[] args){
        // Run the app
        App app = new App();
        app.Run();
        System.exit(0);
    }
}