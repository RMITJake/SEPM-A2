package src.controllers;
import src.handlers.*;
import src.models.Account;
import src.models.Ticket;
import src.views.TicketUI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
public class TicketController {
    // TicketController will handle both the Ticket and TicketUpdate Models
    private FileHandler file = new FileHandler();
    private InputHandler input = new InputHandler();
    private ValidationHandler validate = new ValidationHandler();
    private TicketUI ui = new TicketUI();
    private String userInput;
    private Ticket selectedTicket = new Ticket();

    // Record strings, used to minimize hard coded file references
    protected String openTicketRecord = file.openTicketRecord;
    private String technicianRecord = file.technicianRecord;

    public void CreateNewTicket(Account currentUser, String record){
        // Initialise ticket
        Ticket newTicket = new Ticket();
        // Set the requesterId to the currentUserId
        newTicket.setRequesterId(currentUser.getId());
        
        // Get user input for description and severity properties
        boolean confirmDetails = false;
        while(!confirmDetails){
            ui.description();
            userInput = input.getInput();
            newTicket.setDescription(userInput);
            
            // Loop and validate this
            ui.severity();
            userInput = input.getInput();
            newTicket.setSeverity(userInput);
            
            ui.confirm(newTicket.getRequesterId(), newTicket.getDescription(), newTicket.getSeverity());
            if(input.getInput().toUpperCase().equals("Y")){
                confirmDetails = true;
            }
        }
        
        // Use getNewestTicket() to calculate the ticket Id
        newTicket.setId(GetNewestTicket().getId()+1);
        // Use assignTechnicianByTicketCount() to calculate assigned tech
        newTicket.setTechnicianAssignedId(AssignTechnician(newTicket.getSeverity()));
        // Set the ticket status to open
        newTicket.setStatus("open");
        // Set the creationDate to now
        newTicket.setCreationDate(LocalDateTime.now());

        // Show what will be saved to the csv
        // Show ticket confirmation
        System.out.println("New ticket created");
        // Show ticket details
        System.out.println(newTicket.getProperties());
        // Write the ticket to OpenTicket.csv
        file.Write(record, newTicket.getProperties());
    }

    public Ticket GetNewestTicket(){
        // Read OpenTicket.csv into an array
        ArrayList<String> openTicketTable = file.Read(openTicketRecord);
        // write the last ticket in the open ticket array into it's own array for processing
        String[] lastTicketInList = openTicketTable.get(openTicketTable.size()-1).split(",",-1);
        // Initialise an blank ticket
        Ticket newestTicket = new Ticket();
        // Write the extracted ticket information to ticket properties
        newestTicket.setId(Integer.parseInt(lastTicketInList[0]));
        newestTicket.setTechnicianAssignedId(Integer.parseInt(lastTicketInList[1]));
        newestTicket.setRequesterId(Integer.parseInt(lastTicketInList[2]));
        newestTicket.setDescription(lastTicketInList[3]);
        newestTicket.setSeverity(lastTicketInList[4]);
        newestTicket.setStatus(lastTicketInList[5]);
        newestTicket.setCreationDate(LocalDateTime.parse(lastTicketInList[6]));
        // newestTicket.setResolvedDate();

        // return the ticket
        return newestTicket;
    }

    private int AssignTechnician(String severity){
        return AssignTechnicianByTicketCount(AssignTechnicianBySeverity(severity));
    }

    private ArrayList<Integer> AssignTechnicianBySeverity(String severity){
        // Read Technician.csv into an array
        ArrayList<String> technicianTable = file.Read(technicianRecord);
        ArrayList<Integer> technicianList = new ArrayList<Integer>();

        String technicianLevel;
        if(!severity.equals("high")){
            technicianLevel = "1";
        } else {
            technicianLevel = "2";
        }
        
        // Loop through technicianTable
        // Split the string into parts
        // Save the technicianId to the list of technicians if the level is correct
        // String to temporarily hold split Technician information
        String[] currentTechnician;
        for(int index=0; index < technicianTable.size(); index++){
            currentTechnician = technicianTable.get(index).split(",",-1);
            if(currentTechnician[2].equals(technicianLevel)){
                technicianList.add(Integer.parseInt(currentTechnician[0]));
            }
        }

        return technicianList;
    }

    // Takes in a list storing technicianIds extracted from the array
    private int AssignTechnicianByTicketCount(ArrayList<Integer> technicianList){
        // Int to store the assigned technician
        int assignedTechnicianId = 0;
        // Int to store the case count for the assigned technician
        int assignedTechnicianCaseCount = -1;
        // Read the OpenTicket.csv to an array
        ArrayList<String> openTicketTable = file.Read(openTicketRecord);
        // Initialise an array to store the case counts
        ArrayList<Integer> technicianCaseCount = new ArrayList<Integer>();
        // String to temporarily hold split OpenTicket information
        String[] openTicketString;
        // Int to hold the case count for the technician being iterated over
        int caseCount;

        // Iterate over the list of technicianIds
        for(int technicianIndex=0; technicianIndex < technicianList.size(); technicianIndex++){
            // reset the case count for each tech
            caseCount = 0;
            // Iterate over the open ticket list
            for(int caseIndex=0; caseIndex < openTicketTable.size(); caseIndex++){
                // split the string to allow the technicianId to be extracted
                openTicketString = openTicketTable.get(caseIndex).split(",",-1);
                // If the technicianId matches the current technicianId which is being iterated over, increase case count by 1
                if(technicianList.get(technicianIndex) == Integer.parseInt(openTicketString[1])){
                    caseCount++;
                }
            }

            // Add the casecount to the list of case counts
            technicianCaseCount.add(caseCount);

            // if the current number of cases is less than that of the currently assigned tech, then assign the tech with less cases
            if(caseCount < assignedTechnicianCaseCount || assignedTechnicianCaseCount == -1){
                // assignedTechnicianId = technicianList.get(technicianIndex);
                assignedTechnicianCaseCount = caseCount;
            }
        }

        // Array to store the techs with the lowest case count
        ArrayList<Integer> technicianLowestCaseCount = new ArrayList<Integer>();
        // Get a list indexes with the lowest ticket counts
        for(int index=0; index < technicianCaseCount.size(); index++){
            if(assignedTechnicianCaseCount == technicianCaseCount.get(index)){
                technicianLowestCaseCount.add(technicianList.get(index));
            }
        }

        // return the techId
        return AssignTechnicianAtRandom(technicianLowestCaseCount);
    }
    
    public int AssignTechnicianAtRandom(ArrayList<Integer> technicianLowestCaseCount){
        // If there is only 1 technician in the list assign the case
        if(technicianLowestCaseCount.size() == 1){
            return technicianLowestCaseCount.get(0);
        }

        // Create the Random object to generate the random number
        Random randomiser = new Random();
        // Get the upper limit for the randomiser
        int upperLimit = technicianLowestCaseCount.size();
        // Generate the random number
        int randomNumber = randomiser.nextInt(upperLimit);
        // Return the techId at the random index
        return technicianLowestCaseCount.get(randomNumber);
    }

    public void ForgotPassword(){
        do{
            ui.ForgotPassword();
            userInput = input.getInput();
        } while (!validate.Email(userInput) && userInput.equals("C"));
        if(validate.Email(userInput))
        {
            ui.ForgotPassword(userInput);
            ResetPassword(userInput);
        }
    }

    public void ResetPassword(String email){
        Ticket newTicket = new Ticket();
        // Set the requesterId to the system user
        newTicket.setRequesterId(1);
        newTicket.setDescription("Password Reset: " + email);
        newTicket.setSeverity("low");
        newTicket.setId(GetNewestTicket().getId()+1);
        newTicket.setTechnicianAssignedId(AssignTechnician(newTicket.getSeverity()));
        newTicket.setStatus("open");
        newTicket.setCreationDate(LocalDateTime.now());
        // Write the ticket to OpenTicket.csv
        file.Write(openTicketRecord, newTicket.getProperties());
    }

    public void SelectTicket(){
        // Needs checks so users can only select their own tickets
        // Techs can check all tickets
        // Create an empty ticket
        ui.SelectTicket();
        userInput = input.getInput();
        if(validate.TicketId(userInput)){
            ArrayList<String> ticketTable = file.Read(openTicketRecord);
            String[] ticketString;
            for(int ticketIndex=0; ticketIndex < ticketTable.size(); ticketIndex++){
                ticketString = ticketTable.get(ticketIndex).split(",",-1);
                if(Integer.parseInt(userInput) == Integer.parseInt(ticketString[0])){
                    selectedTicket.setId(Integer.parseInt(ticketString[0]));
                    selectedTicket.setTechnicianAssignedId(Integer.parseInt(ticketString[1]));
                    selectedTicket.setRequesterId(Integer.parseInt(ticketString[2]));
                    selectedTicket.setDescription(ticketString[3]);
                    selectedTicket.setSeverity(ticketString[4]);
                    selectedTicket.setStatus(ticketString[5]);
                    selectedTicket.setCreationDate(LocalDateTime.parse(ticketString[6]));
                    // selectedTicket.setResolvedDate(LocalDateTime.parse(ticketString[7]));
                    this.selectedTicket = selectedTicket;
                    DisplayTicket(this.selectedTicket);
                }
            }
        }
    }
    
    public void DisplayTicket(Ticket ticket){
        do {
            ui.DisplayTicket(ticket);
            ui.TicketMenu();
            userInput = input.getInput();
            if(userInput.toUpperCase().equals("S")){
                ui.ChangeSeverity();
                userInput = input.getInput();
                ticket.setSeverity(userInput);
                UpdateTicketRecord(ticket);
            }
        } while (!userInput.toUpperCase().equals("M"));
    }

    public void UpdateTicketRecord(Ticket ticket){
        ArrayList<String> oldTicketTable = file.Read(openTicketRecord);
        String newTicketTable = "";
        String[] ticketString;
        for(int ticketIndex=0; ticketIndex < oldTicketTable.size(); ticketIndex++){
            ticketString = oldTicketTable.get(ticketIndex).split(",",-1);
            if(ticket.getId() == Integer.parseInt(ticketString[0])){
                newTicketTable += ticket.getProperties();
            } else {
                newTicketTable += oldTicketTable.get(ticketIndex) + "\r\n";
            }
        }
        file.WriteOver(openTicketRecord, newTicketTable);
    }

    public void EscalateTicket(Ticket ticket){
        // Options to escalate ticket
        // 1. Create a new ticket in the escalate table which is assigned to L2
        // - Pros = original CO maintains ticket ownership, L2 can open and close tickets for escalation
        // - Cons
        // 2. Assign ticket to L2
        // - Pros = easy
        // - Cons

        Ticket escalationTicket = new Ticket();

    }
}