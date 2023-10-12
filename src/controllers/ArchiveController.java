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
	
	
}
