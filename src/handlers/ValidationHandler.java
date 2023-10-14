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

  public String email(String input){
    regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    boolean emailMatch = Pattern.matches(regex, input);

    if(!emailMatch){
      return "**Invalid email format. Please enter a valid email address format (e.g. example@example.com).\n";
    }
    return null;
  }

  public String emailUniqueCheck(String input){
	  ArrayList<String> accountTable = file.read("Account");
	  Account checkIndex = new Account();
	  boolean emailMatch = false;
	  boolean match = false;
	  for(int index=0; index < accountTable.size(); index++) {
		  String[] indexDetails = accountTable.get(index).split(",", -1);
      checkIndex.setEmail((indexDetails[1]));
      if (!input.equals(checkIndex.getEmail()) && !match) {
        emailMatch = true;
      }
      if (input.equals(checkIndex.getEmail())) {
        match = true;
        emailMatch = false;
      }
	  }
    if (emailMatch){
      return null;
    } else{
      return "**The entered email address is already in use. Please try again with different one.\n";
    }
  }

  public String fullName(String input){
    regex = "^[\\w|\\s|']{1,}";
    boolean fullNameMatch = Pattern.matches(regex, input);

    if (!fullNameMatch) {
      return "**Invalid full name. Please try again.\n";
    }

    return null;
  }

  public String phoneNumber(String input){
    regex = "^[\\d]{10,10}$";
    boolean phoneNumberMatch = Pattern.matches(regex, input);
    
    if (!phoneNumberMatch) {
      return "**Invalid phone number. Must be 10 digits long. Please try again.\n";
    }

    return null;
  }

  public String password(String input){
    int index;
    boolean lengthMatch;
    boolean uppercaseMatch;
    boolean lowercaseMatch;
    boolean digitMatch;
    String errorMessage = null;

    // Per spec we check for uppercase and lowercase alphanumeric, no symbols
    // TEMPORARY - ONLY CHECKING FOR 4 MIN CHARACTERS FOR TESTING, PER SPEC CHECK FOR 20
    // Check length of the string
    regex = "^[a-zA-Z\\d]{20,}";
    lengthMatch = Pattern.matches(regex, input);

    if (!lengthMatch){
      errorMessage = "**Password must be at least 20 characters long.\n";
    }

    // Check uppercase
    regex = "[A-Z]";
    index = 0;
    do{
      uppercaseMatch = Pattern.matches(regex, input.substring(index, index+1));
      index++;
    } while(!uppercaseMatch && index < input.length());

    if (!uppercaseMatch && errorMessage == null){
      errorMessage = "**Password must contain at least one uppercase letter.\n";
    }

    // Check lowercase
    regex = "[a-z]";
    index = 0;
    do{
      lowercaseMatch = Pattern.matches(regex, input.substring(index, index+1));
      index++;
    } while(!lowercaseMatch && index < input.length());

    if (!lowercaseMatch && errorMessage == null){
      errorMessage = "**Password must contain at least one lowercase letter.\n";
    }

    // check digits
    regex = "[\\d]";
    index = 0;
    do{
      digitMatch = Pattern.matches(regex, input.substring(index, index+1));
      index++;
    } while(!digitMatch && index < input.length());

    if (!digitMatch && errorMessage == null){
      errorMessage = "**Password must contain at least one digit.\n";
    }
    
    return errorMessage;
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