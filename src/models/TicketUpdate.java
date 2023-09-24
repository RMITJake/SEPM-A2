package src.models;
import java.time.LocalDateTime;
public class TicketUpdate {
    // Required model properties
    int id;
    int ticketId;
    String description;
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
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
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
        details += this.description + ",";
        details += this.updateTime;

        return details;
    }
}