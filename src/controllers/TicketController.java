package src.controllers;
import src.handlers.*;
import src.models.Account;
import src.models.Ticket;
import src.models.Technician;
import src.views.TicketUI;
import java.time.LocalDateTime;
import java.util.ArrayList;
public class TicketController {
    // TicketController will handle both the Ticket and TicketUpdate Models
    private FileHandler file = new FileHandler();
    private InputHandler input = new InputHandler();
    private TicketUI ui = new TicketUI();
    private String userInput;
    public TicketController(){
        System.out.println("Constructing TicketController");
        
        // Test the file handler
        System.out.println(file.Read("OpenTicket"));
        System.out.println(file.Read("TicketUpdate"));
        System.out.println(file.Read("ArchivedTicket"));
    }

    public void CreateNewTicket(Account currentUser){
        Ticket newTicket = new Ticket();
        // Next 2 to be calculated and assigned
        newTicket.setId(getNewestTicket().getId()+1);

        newTicket.setRequesterId(currentUser.getId());

        // Loop and validate this
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
            if(input.getInput().equals("y")){
                confirmDetails = true;
            }
        }

        newTicket.setTechnicianAssignedId(assignTechnicianByTicketCount());
        newTicket.setCreationDate(LocalDateTime.now());

        // Show what will be saved to the csv
        System.out.println("New ticket created");
        System.out.println(newTicket.getProperties());
        file.Write("OpenTicket", newTicket.getProperties());
    }

    public ArrayList<String> getOpenTicketList(){
        ArrayList<String> openTicketTable = file.Read("OpenTicket");
        return openTicketTable;
    }

    public Ticket getNewestTicket(){
        ArrayList<String> openTicketTable = file.Read("OpenTicket");
        String[] lastTicketInList = openTicketTable.get(openTicketTable.size()-1).split(",",-1);
        Ticket newestTicket = new Ticket();
        newestTicket.setId(Integer.parseInt(lastTicketInList[0]));
        newestTicket.setTechnicianAssignedId(Integer.parseInt(lastTicketInList[1]));
        newestTicket.setRequesterId(Integer.parseInt(lastTicketInList[2]));
        newestTicket.setDescription(lastTicketInList[3]);
        newestTicket.setSeverity(lastTicketInList[4]);
        // newestTicket.setCreationDate();
        // newestTicket.setResolvedDate();

        return newestTicket;
    }

    private int assignTechnicianByTicketCount(){
        // Read Technician.csv into an array
        ArrayList<String> technicianTable = file.Read("Technician");
        // Initialise a list to store the technicianIds extracted from the array
        ArrayList<Integer> technicianList = new ArrayList<Integer>();
        // String to temporarily hold split Technician information
        String[] technicianString;

        // Loop through technicianTable
        // Split the string into parts
        // Save the technicianId to the list of technicians
        for(int index=0; index < technicianTable.size(); index++){
            technicianString = technicianTable.get(index).split(",",-1);
            technicianList.add(Integer.parseInt(technicianString[0]));
        }

        // Int to store the assigned technician
        int assignedTechnicianId = 0;
        // Int to store the case count for the assigned technician
        int assignedTechnicianCaseCount = -1;
        // Read the OpenTicket.csv to an array
        ArrayList<String> openTicketTable = file.Read("OpenTicket");
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
                assignedTechnicianId = technicianList.get(technicianIndex);
                assignedTechnicianCaseCount = caseCount;
            }

            // print confirmation message
            System.out.println(technicianList.get(technicianIndex) + " " + technicianCaseCount.get(technicianIndex));
        }

        // return the techId
        return assignedTechnicianId;
    }
}