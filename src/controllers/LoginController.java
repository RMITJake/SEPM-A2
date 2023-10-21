package src.controllers;
import java.util.ArrayList;
import src.models.Login;
import src.handlers.FileHandler;
import src.models.Account;
public class LoginController {
    private FileHandler file = new FileHandler();
    private Account currentUser = new Account();

    public Account verifyLogin(int accountId, String password){
        // loginTable temporarily stores the contents of file.Read()
        ArrayList<String> loginTable = file.read(FileHandler.loginRecord);
        // Login object which temporarily holds data to be checked
        Login checkIndex = new Login();
        for(int index=0; index < loginTable.size(); index++) {
            // split the current string into chunks
            String[] indexDetails = loginTable.get(index).split(",", -1);

            // set the account properties with the split data
            checkIndex.setId(Integer.parseInt(indexDetails[0]));
            checkIndex.setAccountId(Integer.parseInt(indexDetails[1]));
            checkIndex.setPassword(indexDetails[2]);

            // compare password with the extracted password
            if (accountId == checkIndex.getAccountId() && password.equals(checkIndex.getPassword())) {
                // Delcaring a temporary account controller here as it should only be single use
                AccountController temporaryController = new AccountController();
                currentUser = temporaryController.getAccountById(checkIndex.getAccountId());
                return currentUser;
            }
        }
        currentUser.setId(0);
        return currentUser;
    }
}