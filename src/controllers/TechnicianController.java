package src.controllers;
import src.handlers.FileHandler;
import src.models.Technician;
import java.util.ArrayList;
public class TechnicianController {
    private FileHandler file = new FileHandler();

    public Technician getTechnicianById(int accountId){
        // accountTable arraylist to store the file contents
        ArrayList<String> technicianTable = file.read(FileHandler.technicianRecord);
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

        currentTechnician.setId(0);
        currentTechnician.setAccountId(0);
        currentTechnician.setLevel(0);
        return currentTechnician;
    }
}