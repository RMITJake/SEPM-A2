import java.time.LocalDateTime;
public class Ticket {
    // Required model properties
    int id;
    int technicianAssignedId;
    int requesterId;
    String description;
    String severity;
    LocalDateTime creationDate;
    LocalDateTime resolvedDate;

    // Getters and Setters
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getTechnicianAssignedId(){
        return this.technicianAssignedId;
    }
    public void setTechnicianAssignedId(int technicianAssignedId){
        this.technicianAssignedId = technicianAssignedId;
    }
    public int getRequesterId(){
        return this.requesterId;
    }
    public void setRequesterId(int requesterId){
        this.requesterId = requesterId;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getSeverity(){
        return this.severity;
    }
    public void setSeverity(String severity){
        this.severity = severity;
    }
    public LocalDateTime getCreationDate(){
        return this.creationDate;
    }
    public void setCreationDate(LocalDateTime creationDate){
        this.creationDate = creationDate;
    }
    public LocalDateTime getResolvedDate() {
        return resolvedDate;
    }
    public void setResolvedDate(LocalDateTime resolvedDate){
        this.resolvedDate = resolvedDate;
    }

    // Return model properties in CSV format
    public getProperties(){
        String details = "";
        details += this.id + ",";
        details += this.technicianAssignedId + ",";
        details += this.requesterId + ",";
        details += this.description + ",";
        details += this.severity + ",";
        details += this.creationDate + ",";
        details += this.resolvedDateDate;

        return details;
    }
}