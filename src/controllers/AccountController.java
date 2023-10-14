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
    private String accountRecord = file.accountRecord;
    private String loginRecord = file.loginRecord;
    private String exitLoop = "B";

    private boolean exitLoopCheck(String input){
        if(input.toUpperCase().equals(exitLoop)){
            return true;
        }
        return false;
    }

    public void createUser(){
        // Initialise account
        Account newAccount = new Account();
        Login newLogin = new Login();
        
        // Get user input for needed properties
        userInput = "";
        boolean confirmDetails = false;
        while(!confirmDetails && !exitLoopCheck(userInput)){
        	String emailValidationResult = "";
        	String emailUniqueCheckResult = null;
            while(!exitLoopCheck(userInput) && (emailValidationResult != null || emailUniqueCheckResult != null)){
            // while(!exitLoopCheck(userInput)){
                userInput = "";
                ui.email();
                userInput = input.getInput();
                emailValidationResult = validate.email(userInput);
                if(!exitLoopCheck(userInput) && emailValidationResult != null) {
                	System.out.println(emailValidationResult);
                } else {
                	emailUniqueCheckResult = validate.emailUniqueCheck(userInput);
                	if(emailUniqueCheckResult != null) {
                		System.out.println(emailUniqueCheckResult);
                	}
                }
            }
            if(!exitLoopCheck(userInput)){
                newAccount.setEmail(userInput);
            }
            
            String fullNameValidationResult = "";
            while(!exitLoopCheck(userInput) && fullNameValidationResult != null){
                userInput = "";
                ui.fullName();
                userInput = input.getInput();
                fullNameValidationResult = validate.fullName(userInput);
                if(fullNameValidationResult != null) {
                	System.out.println(fullNameValidationResult);
                }
            }
            if(!exitLoopCheck(userInput)){
                newAccount.setFullName(userInput);
            }
            
            String phoneNumberValidationResult = "";
            while(!exitLoopCheck(userInput) && phoneNumberValidationResult != null){
                ui.phoneNumber();
                userInput = input.getInput();
                phoneNumberValidationResult = validate.phoneNumber(userInput);
                if(phoneNumberValidationResult != null) {
                	System.out.println(phoneNumberValidationResult);
                }
            }
            if(!exitLoopCheck(userInput)){
                newAccount.setPhoneNumber(Integer.parseInt(userInput));
            }

            boolean passwordMatch = false;
            String password;
            String passwordConfirm;
            while(!exitLoopCheck(userInput) && !passwordMatch){
            	String passwordValidationResult = "";
                while (!exitLoopCheck(userInput) && passwordValidationResult != null){
                	ui.password();
                	userInput = input.getInput();
                    password = userInput;
                	passwordValidationResult = validate.password(password);
                	if(passwordValidationResult != null) {
                		System.out.println(passwordValidationResult);
                	}
                
                    if(!exitLoopCheck(userInput) && passwordValidationResult == null){
                        ui.passwordConfirm();
                        passwordConfirm = input.getInput();
                        if(password.equals(passwordConfirm)){
                            newLogin.setPassword(password);
                            passwordMatch = true;
                        }
                    }
                }
            }
            if(!exitLoopCheck(userInput)){
                ui.confirm(newAccount.getEmail(), newAccount.getFullName(), newAccount.getPhoneNumber());
                userInput = input.getInput().toUpperCase();
                if(userInput.equals("Y")){
                    newAccount.setId(getNewestAccountId()+1);
                    System.out.println("old account id: " +getNewestAccountId());
                    System.out.println("new account id: " +(getNewestAccountId()+1));
                    newAccount.setCreationDate(LocalDateTime.now());
                    newAccount.setDisabled(false);
                    newLogin.setId(setLoginId());
                    System.out.println("new login id: " +setLoginId());
                    newLogin.setAccountId(newAccount.getId());
                    confirmDetails = true;
                } else if (userInput.equals("C")){
                    newAccount = null;
                    confirmDetails = true;
                }
                System.out.println("New account created");
                System.out.println(newAccount.getAccountDetails());
                file.write(accountRecord, newAccount.getProperties()+"\r\n");
                System.out.println("New login created");
                System.out.println(newLogin.getAccountDetails());
                file.write(loginRecord, newLogin.getProperties()+"\r\n");
            } else {
                System.out.println("Account creation cancelled.");
            }
        }
    }
        

    public Account getAccountById(int accountId){
        // accountTable arraylist to store the file contents
        ArrayList<String> accountTable = file.read(accountRecord);
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

    public int getNewestAccountId(){
        // Functionally similar to getNewestTicket()
        ArrayList<String> accountTable = file.read(accountRecord);
        String[] lastAccountInList = accountTable.get(accountTable.size()-1).split(",",-1);
        return Integer.parseInt(lastAccountInList[0]);
    }

    public int setLoginId(){
        // Streamlined version of getNewestTicket to only return the need ID
        ArrayList<String> loginTable = file.read(loginRecord);
        String[] lastLoginProperties = loginTable.get(loginTable.size()-1).split(",",-1);
        return Integer.parseInt(lastLoginProperties[0])+1;
    }
}