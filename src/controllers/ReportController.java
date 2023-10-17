package src.controllers;

import src.models.Ticket;
import src.handlers.FileHandler;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReportController {
    private FileHandler file = new FileHandler();
    final String openTicketRecord = file.openTicketRecord;

    public void generateReport(ArrayList<String> allTickets, LocalDateTime startDate, LocalDateTime endDate){
        ArrayList<String> filteredTickets = new ArrayList<String>();
        String[] currentTicket;
        ArrayList<String> openTickets = new ArrayList<String>();
        ArrayList<String> closedResolvedTickets = new ArrayList<String>();
        ArrayList<String> closedUnresolvedTickets = new ArrayList<String>();
        ArrayList<String> archivedTickets = new ArrayList<String>();

        //SHOW
        //who
        //when submitted (creationDate)
        //severity
        //tecnician
        //time to close (Close date - creation date)

        for(int index=0; index < allTickets.size(); index++){
            currentTicket = allTickets.get(index).split(",", -1);
            LocalDateTime currentTicketDate = LocalDateTime.parse(currentTicket[6]);
            if(currentTicketDate.isAfter(startDate) && currentTicketDate.isBefore(endDate)){
                filteredTickets.add(allTickets.get(index));
                if(currentTicket[5].equals(Ticket.openStatus)){
                    openTickets.add(allTickets.get(index));
                } else if (currentTicket[5].equals(Ticket.closedResolvedStatus)){
                    closedResolvedTickets.add(allTickets.get(index));
                } else if (currentTicket[5].equals(Ticket.closedUnresolvedStatus)){
                    closedUnresolvedTickets.add(allTickets.get(index));
                } else if (currentTicket[5].equals(Ticket.archivedStatus)){
                    archivedTickets.add(allTickets.get(index));
                }
            }
        }
        // number of sybmitted tickets
        System.out.println("DEBUGGING Number of all tickets: " + allTickets.size());
        System.out.println("DEBUGGING Number of filtered tickets: " + filteredTickets.size());
        System.out.println("DEBUGGING Number of open tickets: " + openTickets.size());
        System.out.println("DEBUGGING Number of closed and resolved tickets: " + closedResolvedTickets.size());
        System.out.println("DEBUGGING Number of closed and unresolved tickets: " + closedUnresolvedTickets.size());
        System.out.println("DEBUGGING Number of archived tickets: " + archivedTickets.size());
        for(int i=0; i < allTickets.size(); i++){
            System.out.println("DEBUGGING ALLTICKETS: " + allTickets.get(i));
        }
        for(int i=0; i < filteredTickets.size(); i++){
            System.out.println("DEBUGGING FILTEREDTICKETS: " + filteredTickets.get(i));
        }
        for(int i=0; i < openTickets.size(); i++){
            System.out.println("DEBUGGING OPENTICKETS: " + openTickets.get(i));
        }
        for(int i=0; i < closedResolvedTickets.size(); i++){
            System.out.println("DEBUGGING CLOSEDRESOLVEDTICKETS: " + closedResolvedTickets.get(i));
        }
        for(int i=0; i < closedUnresolvedTickets.size(); i++){
            System.out.println("DEBUGGING CLOSEDUNRESOLVEDTICKETS: " + closedUnresolvedTickets.get(i));
        }
        for(int i=0; i < closedUnresolvedTickets.size(); i++){
            System.out.println("DEBUGGING ARCHIVEDTICKETS: " + archivedTickets.get(i));
        }
    }
}