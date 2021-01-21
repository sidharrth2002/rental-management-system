import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//singleton- only one UserFactory
public class UserFactory {
    private static UserFactory instance = new UserFactory();
    public ArrayList<Tenant> tenants = new ArrayList<>();
    public ArrayList<Agent> agents = new ArrayList<>();
    public ArrayList<Owner> owners = new ArrayList<>();
    private static int numTenants, numAgents, numOwners;

    private UserFactory() {}

    public static UserFactory getInstance() {
        return instance;
    }

    public ArrayList<Tenant> getTenants() {
        return tenants;
    }

    public void setTenants(ArrayList<Tenant> tenants) {
        this.tenants = tenants;
    }

    public ArrayList<Agent> getAgents() {
        return agents;
    }

    public void setAgents(ArrayList<Agent> agents) {
        this.agents = agents;
    }

    public ArrayList<Owner> getOwners() {
        return owners;
    }

    public void setOwners(ArrayList<Owner> owners) {
        this.owners = owners;
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> combined = new ArrayList<>();
        combined.addAll(tenants);
        combined.addAll(agents);
        combined.addAll(owners);
        return combined;
    }

    //for use by the program
    public User makeUser(String userType, String name, String username, String password, String credential) {
        if(userType.equalsIgnoreCase("tenant")) {
            String userCode = "t" + ++numTenants;
            Tenant tenant = new Tenant(userCode, name, username, password, credential, false);
            tenants.add(tenant);
            return tenant;
        } else if(userType.equalsIgnoreCase("agent")) {
            String userCode = "a" + ++numAgents;
            Agent agent = new Agent(userCode, name, username, password, credential, false);
            agents.add(agent);
            return agent;
        } else if(userType.equalsIgnoreCase("owner")) {
            String userCode = "a" + ++numOwners;
            Owner owner = new Owner(userCode, name, username, password, credential, false);
            owners.add(owner);
            return owner;
        }
        return null;
    }

    //for use by file handling
    public User makeUser(String userId, String userType, String name, String username, String password, String credential, boolean approvalStatus) {
        if(userType.equalsIgnoreCase("tenant")) {
            Tenant tenant = new Tenant(userId, name, username, password, credential, approvalStatus);
            tenants.add(tenant);
            return tenant;
        } else if(userType.equalsIgnoreCase("agent")) {
            Agent agent = new Agent(userId, name, username, password, credential, approvalStatus);
            agents.add(agent);
            return agent;
        } else if(userType.equalsIgnoreCase("owner")) {
            Owner owner = new Owner(userId, name, username, password, credential, approvalStatus);
            owners.add(owner);
            return owner;
        }
        return null;
    }

    public void readUsersFromFile() throws IOException {
        ArrayList<Property> properties = PropertySearchFacade.getInstance().getProperties();
        //read owners
        List<String> ownerLines = Files.readAllLines(Paths.get("./src/data/owners.csv"));
        for(int i = 0; i < ownerLines.size(); i++) {
            String[] dataInFile = ownerLines.get(i).split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            //remove quotation marks
            String userID = dataInFile[0].substring(1, dataInFile[0].length() - 1);
            System.out.println("yo " + userID);
            String name = dataInFile[1].substring(1, dataInFile[1].length() - 1);
            System.out.println("yo " + name);
            String username = dataInFile[2].substring(1, dataInFile[2].length() - 1);
            System.out.println("yo " + username);

            String password = dataInFile[3].substring(1, dataInFile[3].length() - 1);
            System.out.println("yo " + password);

            boolean approvalStatus = Boolean.parseBoolean(dataInFile[4].substring(1, dataInFile[4].length() - 1));
            String ownershipCode = dataInFile[5].substring(1, dataInFile[5].length() - 1);
            System.out.println("Ownership Code: " + ownershipCode);
            String propertyList = dataInFile[6].substring(2, dataInFile[6].length() - 2);
            String[] propertyCodes = propertyList.split(",");
//            System.out.println(propertyCodes);
            //search property lists for matching codes and make an arraylist of those properties
            //call makeUser to load them
            User user = instance.makeUser(userID, "owner", name, username, password, ownershipCode, approvalStatus);
            user.setPropertyCodes(Arrays.asList(propertyCodes));
            //load property to this user object
        }

        List<String> agentLines = Files.readAllLines(Paths.get("./src/data/agents.csv"));
        for(int i = 0; i < agentLines.size(); i++) {
            String[] dataInFile = agentLines.get(i).split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            //remove quotation marks
            String userID = dataInFile[0].substring(2, dataInFile[0].length() - 1);
            String name = dataInFile[1].substring(2, dataInFile[1].length() - 1);
            String username = dataInFile[2].substring(2, dataInFile[2].length() - 1);
            String password = dataInFile[3].substring(2, dataInFile[3].length() - 1);
            boolean approvalStatus = Boolean.parseBoolean(dataInFile[4].substring(2, dataInFile[4].length() - 1));
            String agentCode = dataInFile[5].substring(2, dataInFile[5].length() - 1);
            String propertyList = dataInFile[6].substring(2, dataInFile[6].length() - 1);
            String[] propertyCodes = propertyList.split(",");
            //search property lists for matching codes and make an arraylist of those properties//call makeUser to load them
            User user = instance.makeUser(userID, "agent", name, username, password, agentCode, approvalStatus);
            //            user.setPropertyList();
            //load property to this user object

        }

        List<String> tenantLines = Files.readAllLines(Paths.get("./src/data/tenants.csv"));
        for(int i = 0; i < tenantLines.size(); i++) {
            String[] dataInFile = tenantLines.get(i).split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            //remove quotation marks
            String userID = dataInFile[0].substring(2, dataInFile[0].length() - 1);
            String name = dataInFile[1].substring(2, dataInFile[1].length() - 1);
            String username = dataInFile[2].substring(2, dataInFile[2].length() - 1);
            String password = dataInFile[3].substring(2, dataInFile[3].length() - 1);
            boolean approvalStatus = Boolean.parseBoolean(dataInFile[4].substring(2, dataInFile[4].length() - 1));
            String ICNumber = dataInFile[5].substring(2, dataInFile[5].length() - 1);
            String propertyList = dataInFile[6].substring(2, dataInFile[6].length() - 1);
            String[] propertyCodes = propertyList.split(",");
            //search property lists for matching codes and make an arraylist of those properties
            //call makeUser to load them
            User user = instance.makeUser(userID, "tenant", name, username, password, ICNumber, approvalStatus);
            //load property to this user object
        }
    }

    public void loadPropertyToUsers() {
        for(int i = 0; i < getUsers().size(); i++) {
            User user = getUsers().get(i);
            System.out.println(user.getUserID());
            for(int j = 0; j < user.getPropertyCodes().size(); j++) {
                String code = user.getPropertyCodes().get(j);
                for (int k = 0; k < PropertySearchFacade.getInstance().getProperties().size(); k++) {
                    Property property = PropertySearchFacade.getInstance().getProperties().get(k);
                    if(code.equals(property.getID())) {
                        System.out.println("equals");
                        System.out.println(property);
                        user.addProperty(property);
                        System.out.println("User's properties are: " + user.getPropertyList());
                        break;
                    }
                }
            }
            System.out.println(user.getPropertyList());
        }
    }
}




































//public class UserFactory {
//    //for cross checking during login
//    private ArrayList<User> usersInSystem = new ArrayList<>();
//    private int numAgentsInSystem;
//    private int numOwnersInSystem;
//
//    public ArrayList<User> getUsersInSystem() {
//        return usersInSystem;
//    }
//
//    public int getNumAgentsInSystem() {
//        return numAgentsInSystem;
//    }
//
//    public void setNumAgentsInSystem(int numAgentsInSystem) {
//        this.numAgentsInSystem = numAgentsInSystem;
//    }
//
//    public int getNumOwnersInSystem() {
//        return numOwnersInSystem;
//    }
//
//    public void setNumOwnersInSystem(int numOwnersInSystem) {
//        this.numOwnersInSystem = numOwnersInSystem;
//    }
//
//    public User createUser(String name, String role, String username, String password) {
//            switch(role) {
//                case "Agent":
//                    //pass in parameters for agent
//                    User agent = new User();
//                    usersInSystem.add(agent);
//                    return agent;
//
//                case "Owner":
//                    //pass in parameters for owner
//                    User owner = new User();
//                    usersInSystem.add(owner);
//                    return owner;
//                default:
//                    return null;
//            }
//    }
//}