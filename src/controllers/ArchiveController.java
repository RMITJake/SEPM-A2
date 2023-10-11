package src.controllers;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import src.handlers.FileHandler;

public class ArchiveController {
	private FileHandler fileHandler = new FileHandler();
	public TicketController ticketController = new TicketController();
	public final String openTicketRecord = fileHandler.openTicketRecord;
	public ArrayList<String[]> allTickets;
	public Iterator<String[]> iterator;
	
	public void getClosedTickets() {
	    allTickets = ticketController.getAllTickets();
	    iterator = allTickets.iterator();
	    while (iterator.hasNext()) {
	        String[] ticketArray = iterator.next();
	        if ("closed".equals(ticketArray[5])) {
	            compareTime(ticketArray[6]);
	        }
	    }
	    fileHandler.write(fileHandler.openTicketRecord, allTickets);
	}
	public void compareTime(String dateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");
        LocalDateTime timestamp = LocalDateTime.parse(dateTime, formatter);
        if (timestamp.isBefore(LocalDateTime.now().minusDays(1))) {
            iterator.remove();
            int rowNumber = 1;  // Row counter for better readability
            for (String[] ticketArray : allTickets) {
                System.out.println("Row " + rowNumber + ": " + Arrays.toString(ticketArray));
                rowNumber++;
            }
            fileHandler.write(fileHandler.openTicketRecord, allTickets);
        }
	}

}
