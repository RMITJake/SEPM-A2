package src.controllers;
import src.handlers.FileHandler;
import src.models.Technician;
import java.util.ArrayList;
public class TechnicianController {
    private FileHandler file = new FileHandler();
    public TechnicianController(){
        System.out.println("Constructing TechnicianController");
                
        // Test the file handler
        System.out.println(file.Read("Technician"));
    }

    public Technician getTechnician(int accountId){
        // accountTable arraylist to store the file contents
        ArrayList<String> technicianTable = file.Read("Technician");
        // Account object to temporarily store account details
        Technician currentTechnician = new Technician();
        for(int index=0; index < technicianTable.size(); index++){
            // split the string into chunks for operation
            String[] indexDetails = technicianTable.get(index).split(",", -1);
            // if account id matches the id in the line set the account properties
            if(accountId == Integer.parseInt(indexDetails[1])){
                currentTechnician.setId(Integer.parseInt(indexDetails[0]));
                currentTechnician.setAccountId(Integer.parseInt(indexDetails[1]));
                currentTechnician.setLevel(Integer.parseInt(indexDetails[2]));

                return currentTechnician;
            }
        }
        return null;
    }
}