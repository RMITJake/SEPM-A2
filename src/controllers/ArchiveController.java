package src.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import src.handlers.FileHandler;
import src.models.Ticket;

public class ArchiveController {
	private FileHandler fileHandler = new FileHandler();
	public TicketController ticketController = new TicketController();
	public final String openTicketRecord = fileHandler.openTicketRecord;
	public ArrayList<String[]> allTickets;
	public Iterator<String[]> iterator;
	public ArrayList<Ticket> tickets;

	public List<Ticket> getAllTickets() {
		 tickets = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("records/OpenTicket.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Ticket ticket = new Ticket();
                ticket.setId(Integer.parseInt(data[0]));
                ticket.setTechnicianAssignedId(Integer.parseInt(data[1]));
                ticket.setRequesterId(Integer.parseInt(data[2]));
                ticket.setDescription(data[3]);
                ticket.setSeverity(data[4]);
                ticket.setStatus(data[5]);
                ticket.setCreationDate(LocalDateTime.parse(data[6]));
                if (!data[7].equals("null")) {
                    ticket.setResolvedDate(LocalDateTime.parse(data[7]));
                }
                tickets.add(ticket);            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        archiveOldTickets();
        return tickets;
    }
	public void archiveOldTickets() {
      for (Ticket ticket : tickets) {
          System.out.println(ticket.getStatus());
          if (ticket.getResolvedDate() != null) { 	
              if (LocalDateTime.now().isAfter(ticket.getResolvedDate().plusDays(1)) 
                  && ((ticket.getStatus().equals("closed and resolved") 
                  || ticket.getStatus().equals("closed and unresolved")))) {
                  ticket.setStatus("archived");
                  ticketController.updateTicketRecord(ticket);                   
              }
          }
      }
      for (Ticket ticket : tickets) {
          System.out.println(ticket.getProperties());
      }
  }
	
	
	
	
	
}
