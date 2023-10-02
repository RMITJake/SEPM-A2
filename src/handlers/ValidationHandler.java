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
    boolean emailMatch;
    regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    emailMatch = Pattern.matches(regex, input);
    if(emailMatch){
      EmailUniqueCheck(input);
    }
    return emailMatch;
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
    boolean fullNameMatch;
    regex = "^[\\w]{1,}";
    fullNameMatch = Pattern.matches(regex, input);
    return fullNameMatch;
  }

  public boolean PhoneNumber(String input){
    boolean phoneNumberMatch;
    regex = "^[\\d]{10,10}$";
    phoneNumberMatch = Pattern.matches(regex, input);
    return phoneNumberMatch;
  }

  public boolean Password(String input){
    int index;
    boolean lengthMatch;
    boolean uppercaseMatch;
    boolean lowercaseMatch;
    boolean digitMatch;

    // Per spec we check for uppercase and lowercase alphanumeric, no symbols
    // TEMPORARY - ONLY CHECKING FOR 4 MIN CHARACTERS FOR TESTING, PER SPEC CHECK FOR 20
    // Check length of the string
    regex = "^[a-zA-Z\\d]{4,}";
    lengthMatch = Pattern.matches(regex, input);

    // Check uppercase
    regex = "[A-Z]";
    index = 0;
    do{
      uppercaseMatch = Pattern.matches(regex, input.substring(index, index+1));
      index++;
    } while(!uppercaseMatch && index < input.length());

    // Check lowercase
    regex = "[a-z]";
    index = 0;
    do{
      lowercaseMatch = Pattern.matches(regex, input.substring(index, index+1));
      index++;
    } while(!lowercaseMatch && index < input.length());

    // check digits
    regex = "[\\d]";
    index = 0;
    do{
      digitMatch = Pattern.matches(regex, input.substring(index, index+1));
      index++;
    } while(!digitMatch && index < input.length());

    if(lengthMatch && uppercaseMatch && lowercaseMatch && digitMatch){
      return true;
    }
    
    return false;
  }

  public boolean TicketId(String input){
    regex = "^[\\d]+";
    boolean ticketIdMatch = Pattern.matches(regex, input);
    return ticketIdMatch;
  }
}