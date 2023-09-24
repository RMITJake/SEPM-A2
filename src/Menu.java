public class Menu {
    public Menu(){
        System.out.println("Constructing Menu");

        // Create the FileHandler
        FileHandler file = new FileHandler();
        System.out.println(file.Read("Login"));
    }
}