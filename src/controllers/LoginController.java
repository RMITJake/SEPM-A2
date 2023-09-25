package src.controllers;
import java.util.ArrayList;
import src.models.Login;
import src.handlers.FileHandler;
public class LoginController {
    private Login testLogin = new Login();
    private FileHandler file = new FileHandler();
    public LoginController(){
        System.out.println("Constructing LoginController");

        // Test the file handler
        file.Read("Login");

        // Populate the testLogin model
        testLogin.setId(1);
        testLogin.setAccountId(1000);
        testLogin.setPassword("1234");
    }

    public int VerifyLogin(String id, String password){
        ArrayList<String> loginTable = file.Read("Login");
        Login checkIndex = new Login();
        for(int index=0; index < loginTable.size(); index++) {
            // split the current string into chunks
            String[] indexArray = loginTable.get(index).split(",", -1);
            checkIndex.setId(Integer.parseInt(indexArray[0]));
            checkIndex.setAccountId(Integer.parseInt(indexArray[1]));
            checkIndex.setPassword(indexArray[2]);
            if (password.equals(checkIndex.getPassword())) {
                System.out.println("Logged In!");
                return 2;
            }
        }
        return 0;
    }
}