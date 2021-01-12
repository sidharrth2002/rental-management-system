import java.io.IOException;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

public class Controller {
    public static void saveTenantsToFile() throws IOException {
        StringBuilder sb = new StringBuilder();
        ArrayList<Tenant> tenants = UserFactory.tenants;
        for (int i = 0; i < tenants.size(); i++) {
            sb.append(tenants.get(i).toCSVString());
            sb.append("\n");
        }
        Files.write(Paths.get("./data/tenants.csv"), sb.toString().getBytes());
    }

    public static void saveOwnersToFile() throws IOException {
        StringBuilder sb = new StringBuilder();
        ArrayList<Owner> owners = UserFactory.owners;
        for (int i = 0; i < owners.size(); i++) {
            sb.append(owners.get(i).toCSVString());
            sb.append("\n");
        }
        Files.write(Paths.get("./data/owners.csv"), sb.toString().getBytes());
    }

    public static void saveAgentsToFile() throws IOException {
        StringBuilder sb = new StringBuilder();
        ArrayList<Agent> agents = UserFactory.agents;
        for (int i = 0; i < agents.size(); i++) {
            sb.append(agents.get(i).toCSVString());
            sb.append("\n");
        }
        Files.write(Paths.get("./data/agents.csv"), sb.toString().getBytes());
    }

    public static void savePropertyToFile() throws IOException {
        StringBuilder sb = new StringBuilder();
        ArrayList<Property> propertyList = PropertySearchFacade.getProperties();
        for (int i = 0; i < propertyList.size(); i++) {
            sb.append(propertyList.get(i).toCSVString());
            sb.append("\n");
        }
        Files.write(Paths.get("./data/agents.csv"), sb.toString().getBytes());
    }

    //add Property Method
    //load data inside (reads all the file store, loads the users, property)
    //load data into files (save at the end)
    //login
        //if wrong login, set Error Message of Main class, and Main.showLoginScreen()
        //check details (cross check with CSV)
        //Main.name = ''
        //Main.role = ''
        //Main.showDashboard

    //register
        //create user
        //Main.name = ''
        //Main.role = ''


    //search
    //searchProperty
//    Main.propertyToDisplay = PropertySearchFacade.getByActive();
}
