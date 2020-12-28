import java.util.ArrayList;

public class UserFactory {
    //for cross checking during login
    private ArrayList<User> usersInSystem = new ArrayList<>();
    private int numAgentsInSystem;
    private int numOwnersInSystem;

    public ArrayList<User> getUsersInSystem() {
        return usersInSystem;
    }

    public int getNumAgentsInSystem() {
        return numAgentsInSystem;
    }

    public void setNumAgentsInSystem(int numAgentsInSystem) {
        this.numAgentsInSystem = numAgentsInSystem;
    }

    public int getNumOwnersInSystem() {
        return numOwnersInSystem;
    }

    public void setNumOwnersInSystem(int numOwnersInSystem) {
        this.numOwnersInSystem = numOwnersInSystem;
    }

    public User createUser(String name, String role, String username, String password) {
            switch(role) {
                case "Agent":
                    //pass in parameters for agent
                    User agent = new User();
                    usersInSystem.add(agent);
                    return agent;

                case "Owner":
                    //pass in parameters for owner
                    User owner = new User();
                    usersInSystem.add(owner);
                    return owner;
                default:
                    return null;
            }
    }
}