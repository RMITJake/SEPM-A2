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
        // read through the ticket table and count which tech has which tickets
        // return the technician id with the least tickets

        // Read through the technician table to get all the techIds
        // Read through the open tickets table and save all the tech cases to an array
        ArrayList<String> technicianTable = file.Read("Technician");
        ArrayList<Integer> technicianList = new ArrayList<Integer>();
        String[] technicianString;
        int technicianId;
        for(int index=0; index < technicianTable.size(); index++){
            technicianString = technicianTable.get(index).split(",",-1);
            technicianId = Integer.parseInt(technicianString[0]);
            technicianList.add(technicianId);
        }

        int assignedTechnicianId = 0;
        int assignedTechnicianCaseCount = -1;
        ArrayList<String> openTicketTable = file.Read("OpenTicket");
        ArrayList<Integer> technicianCaseCount = new ArrayList<Integer>();
        String[] openTicketString;
        int caseCount;
        for(int technicianIndex=0; technicianIndex < technicianList.size(); technicianIndex++){
            caseCount = 0;
            for(int caseIndex=0; caseIndex < openTicketTable.size(); caseIndex++){
                openTicketString = openTicketTable.get(caseIndex).split(",",-1);
                technicianId = Integer.parseInt(openTicketString[1]);
                if(technicianList.get(technicianIndex) == technicianId){
                    caseCount++;
                }
            }
            technicianCaseCount.add(caseCount);
            if(caseCount < assignedTechnicianCaseCount || assignedTechnicianCaseCount == -1){
                assignedTechnicianId = technicianList.get(technicianIndex);
                assignedTechnicianCaseCount = caseCount;
            }

            System.out.println(technicianList.get(technicianIndex) + " " + technicianCaseCount.get(technicianIndex));
        }

        return assignedTechnicianId;
    }

    // Functions needed
    // getAllTickets
    // getAllTicketsByAccountId
    // getAllTicketsByTechnicianId
    // getAllOpenTicketsByAccountId
    // getAllOpenTicketsByTechnicianId
    // getLastTicket
}