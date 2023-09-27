package src.handlers;
import java.util.regex.*;
public class ValidationHandler {
  String regex;

  public boolean Welcome(String input){
    regex = "^[L|C|F|E]$";
    return Pattern.matches(regex, input);
  }
}