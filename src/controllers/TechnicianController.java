package src.controllers;
import src.handlers.FileHandler;
public class TechnicianController {
    private FileHandler file = new FileHandler();
    public TechnicianController(){
        System.out.println("Constructing TechnicianController");
                
        // Test the file handler
        System.out.println(file.Read("Technician"));
    }
}