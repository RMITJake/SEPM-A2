package src.handlers;
import java.util.regex.*;

import src.models.Account;

import java.util.ArrayList;
public class ValidationHandler {
  String regex;
  FileHandler file = new FileHandler();

  // Welcome menu validation
  public boolean welcome(String input){
    regex = "^[L|C|F|Q]$";
    return Pattern.matches(regex, input);
  }

  // Validation relevant to creating a new user
  public boolean createUser(String input){
    return true;
  }

  public boolean email(String input){
    boolean emailMatch;
    regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    emailMatch = Pattern.matches(regex, input);
    return emailMatch;
  }

  public boolean emailUniqueCheck(String input){
	  ArrayList<String> accountTable = file.read("Account");
	  Account checkIndex = new Account();
	  boolean emailMatch = false;
	  boolean match = false;
	  for(int index=0; index < accountTable.size(); index++) {
		  String[] indexDetails = accountTable.get(index).split(",", -1);;
          checkIndex.setEmail((indexDetails[1]));
          if (input != checkIndex.getEmail() && !match) {
        	  emailMatch = true;
          }
          if (input.equals(checkIndex.getEmail())) {
        	  match = true;
        	  emailMatch = false;
          }
	  }
	    return emailMatch;
  }

  public boolean fullName(String input){
    boolean fullNameMatch;
    regex = "^[\\w|\\s|']{1,}";
    fullNameMatch = Pattern.matches(regex, input);
    return fullNameMatch;
  }

  public boolean phoneNumber(String input){
    boolean phoneNumberMatch;
    regex = "^[\\d]{10,10}$";
    phoneNumberMatch = Pattern.matches(regex, input);
    return phoneNumberMatch;
  }

  public boolean password(String input){
    int index;
    boolean lengthMatch;
    boolean uppercaseMatch;
    boolean lowercaseMatch;
    boolean digitMatch;

    // Per spec we check for uppercase and lowercase alphanumeric, no symbols
    // TEMPORARY - ONLY CHECKING FOR 4 MIN CHARACTERS FOR TESTING, PER SPEC CHECK FOR 20
    // Check length of the string
    regex = "^[a-zA-Z\\d]{20,}";
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

  public boolean ticketId(String input){
    regex = "^[\\d]+";
    boolean ticketIdMatch = Pattern.matches(regex, input);
    return ticketIdMatch;
  }

  public boolean ticketSeverity(String input){
    regex = "^(low|medium|high)$";
    boolean ticketStatusMatch = Pattern.matches(regex, input);
    return ticketStatusMatch;
  }
}