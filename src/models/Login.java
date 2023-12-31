package src.models;
public class Login {
    // Required model properties
    int id;
    int accountId;
    String password;

    // Getters and Setters
    public int getId(){ return this.id; }
    public void setId(int id){ this.id = id; }

    public int getAccountId(){ return this.accountId; }
    public void setAccountId(int accountId){ this.accountId = accountId; }

    public String getPassword(){ return this.password; }
    public void setPassword(String password){ this.password = password; }

    // Return model properties in CSV format
    public String getProperties(){
        String details = "";
        details += this.id + ",";
        details += this.accountId + ",";
        details += this.password;
        return details;
    }
    
    public String getAccountDetails() {
    	String accountDetails = "";
    	accountDetails += "----------------------------\n";
    	accountDetails += "Account ID: "+this.accountId + "\n";
    	accountDetails += "Password: "+this.password + "\n";
    	accountDetails += "----------------------------";
    	return accountDetails;
    }
}
