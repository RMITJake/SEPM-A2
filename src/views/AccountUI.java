package src.views;

public class AccountUI {
  public void Email(){
    System.out.println("Enter your email address: ");
  }

  public void FullName(){
    System.out.println("Enter your full name: ");
  }

  public void PhoneNumber(){
    System.out.println("Enter your phone number: ");
  }

  public void Password(){
    System.out.println("Enter a new password: ");
  }

  public void PasswordConfirm(){
    System.out.println("Confirm the password: ");
  }

  public void Confirm(String email, String fullName, int phoneNumber){
    System.out.println("----------------------------");
    System.out.println("Account Summary");
    System.out.println("Email: " + email);
    System.out.println("Full Name: " + fullName);
    System.out.println("Phone Number: " + phoneNumber);
    System.out.println("----------------------------");
    System.out.print("Are the following details correct? [Y/N] ");
  }
}
