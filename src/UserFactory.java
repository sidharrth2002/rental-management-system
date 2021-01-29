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

    public ArrayList<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(ArrayList<Admin> admins) {
        this.admins = admins;
    }

    public ArrayList<Admin> admins = new ArrayList<>();
    private static int numTenants;

    public static int getNumTenants() {
        return numTenants;
    }

    public static void setNumTenants(int numTenants) {
        UserFactory.numTenants = numTenants;
    }

    public static int getNumAgents() {
        return numAgents;
    }

    public static void setNumAgents(int numAgents) {
        UserFactory.numAgents = numAgents;
    }

    public static int getNumOwners() {
        return numOwners;
    }

    public static void setNumOwners(int numOwners) {
        UserFactory.numOwners = numOwners;
    }

    public static int getNumAdmins() {
        return numAdmins;
    }

    public static void setNumAdmins(int numAdmins) {
        UserFactory.numAdmins = numAdmins;
    }

    public void removeOwner(Owner owner) {
        owners.remove(owner);
        ArrayList<Property> theirproperties = owner.getPropertyList();
        for(Property property: theirproperties) {
            PropertySearchFacade.getInstance().deleteProperty(property);
        }
    }

    public void removeAgent(Agent agent) {
        agents.remove(agent);
        ArrayList<Property> theirproperties = agent.getPropertyList();
        for(Property property: theirproperties) {
            PropertySearchFacade.getInstance().deleteProperty(property);
        }
    }

    public void removeTenant(Tenant tenant) {
        tenants.remove(tenant);
    }

    private static int numAgents;
    private static int numOwners;
    private static int numAdmins;

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
        combined.addAll(admins);
        return combined;
    }

    //for use by the program
    public User makeUser(String userType, String name, String username, String password, String credential, String phone) {
        if(userType.equalsIgnoreCase("tenant")) {
            String userCode = "t" + ++numTenants;
            Tenant tenant = new Tenant(userCode, name, username, password, credential, phone, false);
            tenants.add(tenant);
            return tenant;
        } else if(userType.equalsIgnoreCase("agent")) {
            String userCode = "a" + ++numAgents;
            Agent agent = new Agent(userCode, name, username, password, credential, phone, false);
            agents.add(agent);
            return agent;
        } else if(userType.equalsIgnoreCase("owner")) {
            String userCode = "o" + ++numOwners;
            Owner owner = new Owner(userCode, name, username, password, credential, phone, false);
            owners.add(owner);
            return owner;
        } else if(userType.equalsIgnoreCase("admin")) {
            String userCode = "ad" + ++numAdmins;
            Admin admin = new Admin(userCode, name, username, password, true);
            admins.add(admin);
            return admin;
        }
        return null;
    }

    //for use by file handling
    public User makeUser(String userId, String userType, String name, String username, String password, String credential, boolean approvalStatus, String phone) {
        if(userType.equalsIgnoreCase("tenant")) {
            Tenant tenant = new Tenant(userId, name, username, password, credential, phone, approvalStatus);
            tenants.add(tenant);
            return tenant;
        } else if(userType.equalsIgnoreCase("agent")) {
            Agent agent = new Agent(userId, name, username, password, credential, phone, approvalStatus);
            agents.add(agent);
            return agent;
        } else if(userType.equalsIgnoreCase("owner")) {
            Owner owner = new Owner(userId, name, username, password, credential, phone, approvalStatus);
            owners.add(owner);
            return owner;
        } else if(userType.equalsIgnoreCase("admin")) {
            Admin admin = new Admin(userId, name, username, password, approvalStatus);
            admins.add(admin);
            return admin;
        }
        return null;
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