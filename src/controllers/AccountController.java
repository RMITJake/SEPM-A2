package src.controllers;
import src.models.Account;
import src.models.Login;
import src.views.AccountUI;
import src.handlers.*;
import java.util.ArrayList;
import java.time.LocalDateTime;
public class AccountController {
    private FileHandler file = new FileHandler();
    private InputHandler input = new InputHandler();
    private ValidationHandler validate = new ValidationHandler();
    private AccountUI ui = new AccountUI();
    String userInput;

    public void CreateUser(){
        // Initialise account
        Account newAccount = new Account();
        Login newLogin = new Login();
        
        // Get user input for needed properties
        boolean confirmDetails = false;
        while(!confirmDetails){
            do{
                ui.Email();
                userInput = input.getInput();
            } while (!validate.Email(userInput));
            newAccount.setEmail(userInput);
            
            do{
                ui.FullName();
                userInput = input.getInput();
            } while (!validate.FullName(userInput));
            newAccount.setFullName(userInput);
            
            do{
                ui.PhoneNumber();
                userInput = input.getInput();
            } while (!validate.PhoneNumber(userInput));
            newAccount.setPhoneNumber(Integer.parseInt(userInput));

            boolean passwordMatch = false;
            String password;
            String passwordConfirm;
            while(!passwordMatch){
                do{
                ui.Password();
                password = input.getInput();
                } while (!validate.Password(password));
                
                ui.PasswordConfirm();
                passwordConfirm = input.getInput();
                if(password.equals(passwordConfirm)){
                    newLogin.setPassword(password);
                    passwordMatch = true;
                }
            }
            ui.Confirm(newAccount.getEmail(), newAccount.getFullName(), newAccount.getPhoneNumber());
            if(input.getInput().equals("Y")){
                newAccount.setId((getNewestAccount().getId())+1);
                newAccount.setCreationDate(LocalDateTime.now());
                newAccount.setDisabled(false);
                newLogin.setId(setLoginId());
                newLogin.setAccountId(newAccount.getId());
                confirmDetails = true;
            } else if (input.getInput().equals("C")){
                newAccount = null;
                confirmDetails = true;
            }
        }
        
        System.out.println("New account created");
        System.out.println(newAccount.getProperties());
        file.Write("Account", newAccount.getProperties()+"\r\n");
        System.out.println("New login created");
        System.out.println(newLogin.getProperties());
        file.Write("Login", newLogin.getProperties()+"\r\n");
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
                currentAccount.setCreationDate(LocalDateTime.parse(indexDetails[4]));
                currentAccount.setDisabled(Boolean.parseBoolean(indexDetails[5]));

                return currentAccount;
            }
        }

        currentAccount.setId(0);
        return currentAccount;
    }

    public Account getNewestAccount(){
        // Functionally similar to getNewestTicket()
        ArrayList<String> accountTable = file.Read("Account");
        String[] lastAccountInList = accountTable.get(accountTable.size()-1).split(",",-1);
        Account newestAccount = new Account();
        newestAccount.setId(Integer.parseInt(lastAccountInList[0]));
        newestAccount.setEmail(lastAccountInList[1]);
        newestAccount.setFullName(lastAccountInList[2]);
        newestAccount.setPhoneNumber(Integer.parseInt(lastAccountInList[3]));
        newestAccount.setCreationDate(LocalDateTime.parse(lastAccountInList[4]));
        newestAccount.setDisabled(Boolean.parseBoolean(lastAccountInList[5]));

        return newestAccount;
    }

    public int setLoginId(){
        // Streamlined version of getNewestTicket to only return the need ID
        ArrayList<String> loginTable = file.Read("Login");
        String[] lastLoginProperties = loginTable.get(loginTable.size()-1).split(",",-1);
        return Integer.parseInt(lastLoginProperties[0]+1);
    }

}