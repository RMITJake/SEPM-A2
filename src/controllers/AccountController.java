package src.controllers;
import src.models.Account;
import src.handlers.FileHandler;
import java.util.ArrayList;
//temporary impot
import java.time.LocalDateTime;
public class AccountController {
    private FileHandler file = new FileHandler();
    public AccountController(){
        System.out.println("Constructing AccountController");
                
        // Test the file handler
        System.out.println(file.Read("Account"));
    }

    public Account getAccountById(int accountId){
        // accountTable arraylist to store the file contents
        ArrayList<String> accountTable = file.Read("Account");
        // Account object to temporarily store account details
        Account currentAccount = new Account();
        for(int index=0; index < accountTable.size(); index++){
            // split the string into chunks for operation
            String[] indexDetails = accountTable.get(index).split(",", -1);
            // if account id matches the id in the line set the account properties
            if(accountId == Integer.parseInt(indexDetails[0])){
                currentAccount.setId(Integer.parseInt(indexDetails[0]));
                currentAccount.setEmail(indexDetails[1]);
                currentAccount.setFullName(indexDetails[2]);
                currentAccount.setPhoneNumber(Integer.parseInt(indexDetails[3]));
                // currentAccount.setCreationDateString(indexDetails[4]);
                // placeholder
                currentAccount.setCreationDate(LocalDateTime.now());
                currentAccount.setDisabled(Boolean.parseBoolean(indexDetails[5]));

                return currentAccount;
            }
        }

        currentAccount.setId(0);
        return currentAccount;
    }
}