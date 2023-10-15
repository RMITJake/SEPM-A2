package src.models;
import java.time.LocalDateTime;
public class Ticket {
    // Required model properties
    int id;
    int technicianAssignedId;
    int requesterId;
    String description;
    String severity;
    String status;
    LocalDateTime creationDate;
    LocalDateTime resolvedDate;

    // Getters and Setters
    public int getId(){ return this.id; }
    public void setId(int id){ this.id = id; }

    public int getTechnicianAssignedId(){ return this.technicianAssignedId; }
    public void setTechnicianAssignedId(int technicianAssignedId){ this.technicianAssignedId = technicianAssignedId; }

    public int getRequesterId(){ return this.requesterId; }
    public void setRequesterId(int requesterId){ this.requesterId = requesterId; }

    public String getDescription(){ return description; }
    public void setDescription(String description){ this.description = description; }

    public String getSeverity(){ return this.severity; }
    public void setSeverity(String severity){ this.severity = severity; }

    public String getStatus(){ return this.status; }
    public void setStatus(String status){ this.status = status; }

    public LocalDateTime getCreationDate(){ return this.creationDate; }
    public void setCreationDate(LocalDateTime creationDate){ this.creationDate = creationDate; }

    public LocalDateTime getResolvedDate() { return resolvedDate; }
    public void setResolvedDate(LocalDateTime resolvedDate){ this.resolvedDate = resolvedDate; }

    // Return model properties in CSV format
    public String getProperties(){
        String details = "";
        details += this.id + ",";
        details += this.technicianAssignedId + ",";
        details += this.requesterId + ",";
        details += this.description + ",";
        details += this.severity + ",";
        details += this.status + ",";
        details += this.creationDate + ",";
        details += this.resolvedDate + "\r\n";
        return details;
    }
    public String getTicketDetails() {
    	String ticketDetails = "";
    	ticketDetails += "----------------------------\n";
    	ticketDetails += "Ticket ID: "+this.id + "\n";
    	ticketDetails += "Assigned Technician ID: "+this.technicianAssignedId + "\n";
    	ticketDetails += "Requester ID: "+this.requesterId + "\n";
    	ticketDetails += "Issue Description: "+this.description + "\n";
    	ticketDetails += "Severity: "+this.severity + "\n";
    	ticketDetails += "Status: "+this.status + "\n";
    	ticketDetails += "Creation Date: "+this.creationDate + "\n";
    	ticketDetails += "----------------------------";
    	return ticketDetails;
    }
}