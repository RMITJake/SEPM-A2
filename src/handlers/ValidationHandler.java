package src.handlers;
import java.util.regex.*;
public class ValidationHandler {
  String regex;

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
    return true;
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