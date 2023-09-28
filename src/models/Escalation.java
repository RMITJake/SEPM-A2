package src.models;
import java.time.LocalDateTime;
public class Escalation {
    // Required model properties
    int id;
    int ticketId;
    int requesterId;
    String reason;
    LocalDateTime updateTime;

    // Getters and Setters
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getTicketId(){
        return this.ticketId;
    }
    public void setTicketId(int ticketId){
        this.ticketId = ticketId;
    }
    public int getRequesterId(){
        return this.requesterId;
    }
    public void setRequesterId(int requesterId){
        this.requesterId = requesterId;
    }
    public String getReason(){
        return this.reason;
    }
    public void setReason(String reason){
        this.reason = reason;
    }

    public LocalDateTime getUpdateTime(){
        return updateTime;
    }
    public void setUpdateTime(LocalDateTime updateTime){
        this.updateTime = updateTime;
    }

    // Return model properties in CSV format
    public String getProperties(){
        String details = "";
        details += this.id + ",";
        details += this.ticketId + ",";
        details += this.requesterId + ",";
        details += this.reason + ",";
        details += this.updateTime;

        return details;
    }
}