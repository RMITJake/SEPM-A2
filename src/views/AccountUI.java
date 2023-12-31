package src.views;

public class AccountUI {
  public void email(){
    System.out.println("Enter your email address: ");
  }

  public void fullName(){
    System.out.println("Enter your full name: ");
  }

  public void phoneNumber(){
    System.out.println("Enter your phone number: ");
  }

  public void password(){
    System.out.println("Enter a new password (Password must have a lower case letter, a uppercase letter, one number and minimum 6 characters): ");
  }

  public void passwordConfirm(){
    System.out.println("Confirm the password: ");
  }

  public void passwordNoMatch(){
    System.out.println("Passwords did not match! Try again or press \"B\" to cancel");
  }

  public void confirm(String email, String fullName, int phoneNumber){
    System.out.println("----------------------------");
    System.out.println("Account Summary");
    System.out.println("Email: " + email);
    System.out.println("Full Name: " + fullName);
    System.out.println("Phone Number: " + phoneNumber);
    System.out.println("----------------------------");
    System.out.print("Are the following details correct? [Y/N] ");
  }
}
