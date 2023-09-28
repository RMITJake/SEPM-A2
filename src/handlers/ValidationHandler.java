package src.handlers;
import java.util.regex.*;
import java.util.ArrayList;
public class ValidationHandler {
  String regex;
  FileHandler file = new FileHandler();

  // Welcome menu validation
  public boolean Welcome(String input){
    regex = "^[L|C|F|E]$";
    return Pattern.matches(regex, input);
  }

  // Validation relevant to creating a new user
  public boolean CreateUser(String input){
    return true;
  }

  public boolean Email(String input){
    return EmailUniqueCheck(input);
  }

  public boolean EmailUniqueCheck(String input){
    // Streamlined version of getNewestTicket to only return the need ID
    ArrayList<String> accountTable = file.Read("Account");
    // String to temporarily store split account properties
    String[] accountProperties;
    boolean emailMatch = false;
    for(int index=0; index < accountTable.size(); index++){
      accountProperties = accountTable.get(index).split(",",-1);
      // check if email input matches
      if(input.equals(accountProperties[1])){
        emailMatch = true;
      }
    }
    return emailMatch;
  }

  public boolean FullName(String input){
    return true;
  }

  public boolean PhoneNumber(String input){
    return true;
  }

  public boolean Password(String input){
    return true;
  }
}