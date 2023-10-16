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
        for(int index=0; index < allTickets.size(); index++){
            currentTicket = allTickets.get(index).split(",", -1);
            LocalDateTime currentTicketDate = LocalDateTime.parse(currentTicket[6]);
            if(currentTicketDate.isAfter(startDate) && currentTicketDate.isBefore(endDate)){
                filteredTickets.add(allTickets.get(index));
            }
        }
        // number of sybmitted tickets
        System.out.println("DEBUGGING Number of all tickets: " + allTickets.size());
        System.out.println("DEBUGGING Number of filtered tickets: " + filteredTickets.size());
        for(int i=0; i < allTickets.size(); i++){
            System.out.println("DEBUGGING ALLTICKETS: " + allTickets.get(i));
        }
        for(int i=0; i < filteredTickets.size(); i++){
            System.out.println("DEBUGGING FILTEREDTICKETS: " + filteredTickets.get(i));
        }
    }
}