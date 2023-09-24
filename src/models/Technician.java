public class Technician {
    // Required model properties
    int id;
    int accountId;
    int level;

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