import java.time.LocalDateTime;
public class Account {
    // Required model properties
    int id;
    String email;
    String fullName;
    int phoneNumber;
    LocalDateTime creationDate;
    boolean disabled;

    // Getters and Setters
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getFullName(){
        return this.fullName;
    }
    public void setFullName(String fullName){
        this.fullName = fullName;
    }
    public int getPhoneNumber(){
        return this.phoneNumber;
    }
    public void setPhoneNumber(int phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDateTime creationDate){
        this.creationDate = creationDate;
    }
    public boolean getDisabled(){
        return this.disabled;
    }
    public void setDisabled(boolean disabled){
        this.disabled = disabled;
    }

    // Return model properties in CSV format
    public String getProperties(){
        String details = "";
        details += this.id + ",";
        details += this.email + ",";
        details += this.fullName + ",";
        details += this.phoneNumber + ",";
        details += this.creationDate + ",";
        details += this.disabled;

        return details;
    }
}