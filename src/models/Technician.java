package src.models;
public class Technician {
    // Required model properties
    int id;
    int accountId;
    int level;

    // Blank default constructor
    public Technician(){}

    // Secondary constructor to create empty technician
    // Using as a work around to prevent null reference errors
    public Technician(int id, int accountId, int level){
        this.id = 0;
        this.accountId = 0;
        this.level = 0;
    }

    // Getters and Setters
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getAccountId(){
        return this.accountId;
    }
    public void setAccountId(int accountId){
        this.accountId = accountId;
    }
    public int getLevel(){
        return this.level;
    }
    public void setLevel(int level){
        this.level = level;
    }

    // Return model properties in CSV format
    public String getProperties(){
        String details = "";
        details += this.id + ",";
        details += this.accountId + ",";
        details += this.level;

        return details;
    }
}