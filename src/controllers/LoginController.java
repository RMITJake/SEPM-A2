package src.controllers;
import src.models.Login;
import src.handlers.FileHandler;
public class LoginController {
    private Login testLogin = new Login();
    private FileHandler file = new FileHandler();
    public LoginController(){
        System.out.println("Constructing LoginController");

        // Test the file handler
        System.out.println(file.Read("Login"));

        // Populate the testLogin model
        testLogin.setId(1);
        testLogin.setAccountId(1000);
        testLogin.setPassword("1234");
    }

    public boolean VerifyLogin(String id, String password){
        if(password.equals(testLogin.getPassword().toString())){
            System.out.println("Logged In!");
            return true;
        }
        return false;
    }
}