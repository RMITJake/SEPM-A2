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
    int index = 0;
    do{
      accountProperties = accountTable.get(index).split(",",-1);
      if(input.equals(accountProperties[1])){
        emailMatch = true;
      }
      index++;
    } while (!emailMatch && index < accountTable.size());
    return emailMatch;
  }

  public boolean FullName(String input){
    return true;
  }

  public boolean PhoneNumber(String input){
    return true;
  }

  public boolean Password(String input){
    boolean passwordMatch;
    // Per spec we check for uppercase and lowercase alphanumeric, no symbols
    // Check length
    // Could check length with string.length() but that returns an int where we want a boolean
    passwordMatch = Pattern.matches("^[\\S]{20,}", input);
    // Check uppercase
    passwordMatch = Pattern.matches("[A-Z]", input);
    // Check lowercase
    passwordMatch = Pattern.matches("[a-z]", input);
    // check digits
    passwordMatch = Pattern.matches("[\\d]", input);
    
    return passwordMatch;
  }
}