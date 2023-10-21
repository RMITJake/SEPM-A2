package src.controllers;

import src.models.Ticket;
import src.handlers.FileHandler;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReportController {
    private FileHandler file = new FileHandler();
    private TicketController ticket = new TicketController();
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
        System.out.println("");
        System.out.println("REPORT FOR " + startDate + " TO " + endDate);
        System.out.println("Number of all tickets submitted: " + allTickets.size());
        System.out.println("Number of filtered tickets: " + filteredTickets.size());
        System.out.println("Number of open tickets: " + openTickets.size());
        System.out.println("Number of closed and resolved tickets: " + closedResolvedTickets.size());
        System.out.println("Number of closed and unresolved tickets: " + closedUnresolvedTickets.size());
        System.out.println("Number of archived tickets: " + archivedTickets.size());
        System.out.println("");
        // for(int i=0; i < allTickets.size(); i++){
        //     System.out.println("DEBUGGING ALLTICKETS: " + allTickets.get(i));
        // }
        // for(int i=0; i < filteredTickets.size(); i++){
        //     System.out.println("DEBUGGING FILTEREDTICKETS: " + filteredTickets.get(i));
        // }
        System.out.println("OPEN TICKETS");
        for(int i=0; i < openTickets.size(); i++){
            ticket.displayTicketString(openTickets.get(i));
        }
        System.out.println("");
        System.out.println("CLOSED RESOLVED TICKETS");
        for(int i=0; i < closedResolvedTickets.size(); i++){
            ticket.displayTicketString(closedResolvedTickets.get(i));
        }
        System.out.println("");
        System.out.println("CLOSED UNRESOLVED TICKETS");
        for(int i=0; i < closedUnresolvedTickets.size(); i++){
            ticket.displayTicketString(closedUnresolvedTickets.get(i));
        }
        System.out.println("");
        System.out.println("ARCHIVED TICKETS");
        for(int i=0; i < closedUnresolvedTickets.size(); i++){
            ticket.displayTicketString(archivedTickets.get(i));
        }
        System.out.println("");
        System.out.println("END REPORT");
        System.out.println("");
    }
}