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
        newTicket.setTechnicianAssignedId(assignTechnicianByTicketCount());

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

        newTicket.setCreationDate(LocalDateTime.now());

        // Show what will be saved to the csv
        System.out.println("New ticket created");
        System.out.println(newTicket.getProperties());
        file.Write("OpenTicket", newTicket.getProperties());
    }

    public ArrayList<Ticket> getOpenTicketList(){
        ArrayList<String> openTicketTable = file.Read("OpenTicket");
        ArrayList<Ticket> openTicketList = new ArrayList<Ticket>();
        Ticket openTicket = new Ticket();
        for(int index=0; index < openTicketTable.size(); index++){
            String[] openTicketString = openTicketTable.get(index).split(",",-1);
            openTicket.setId(Integer.parseInt(openTicketString[0]));
            openTicket.setTechnicianAssignedId(Integer.parseInt(openTicketString[1]));
            openTicket.setRequesterId(Integer.parseInt(openTicketString[2]));
            openTicket.setDescription(openTicketString[3]);
            openTicket.setSeverity(openTicketString[4]);
//            openTicket.setCreationDate();
//            openTicket.setResolvedDate();
            openTicketList.add(openTicket);
        }
        return openTicketList;
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

        for(int index=0; index < technicianList.size(); index++){
            System.out.println(technicianList.get(index));
        }

        ArrayList<Ticket> openTicketList = getOpenTicketList();
        ArrayList<Integer> technicianCaseCount = new ArrayList<Integer>();
        int caseCount;
        for(int technicianIndex=0; technicianIndex < technicianList.size(); technicianIndex++){
            caseCount = 0;
            technicianCaseCount.add(caseCount);
        }
        System.out.println(technicianList.get(0) + " " + technicianCaseCount.get(0));

        int assignedTechnicianIndex = 0;
        // checking for -1 in this loop stops IndexOutOfBounds
        for(int caseCountIndex=0; caseCountIndex < technicianCaseCount.size()-1; caseCountIndex++){
            if(assignedTechnicianIndex <= technicianCaseCount.get(caseCountIndex)){
                assignedTechnicianIndex = caseCountIndex;
            }
        }

        return technicianList.get(assignedTechnicianIndex);
    }

    // Functions needed
    // getAllTickets
    // getAllTicketsByAccountId
    // getAllTicketsByTechnicianId
    // getAllOpenTicketsByAccountId
    // getAllOpenTicketsByTechnicianId
    // getLastTicket
}