import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class User {
    private String name;
    private String userID;
    private String username;
    private String password;
    private boolean approvalStatus;
    private ArrayList<Property> propertyList;

    public abstract String toCSVString();

    public User() {}

    public User(String userCode, String name, String username, String password) {
        this.userID = userCode;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Property> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(ArrayList<Property> propertyList) {
        this.propertyList = propertyList;
    }

    public boolean getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(boolean approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public void readUsersFromFile() throws IOException {
        //read owners
        List<String> ownerLines = Files.readAllLines(Paths.get("./data/owners.csv"));
        for(int i = 0; i < ownerLines.size(); i++) {
            String[] dataInFile = ownerLines.get(i).split(",");
            //remove quotation marks
            String userID = dataInFile[0].substring(1, dataInFile[0].length() - 1);
            String name = dataInFile[1].substring(1, dataInFile[1].length() - 1);
            String username = dataInFile[2].substring(1, dataInFile[2].length() - 1);
            String password = dataInFile[3].substring(1, dataInFile[3].length() - 1);
            boolean approvalStatus = Boolean.parseBoolean(dataInFile[4].substring(1, dataInFile[4].length() - 1));
            String ownershipCode = dataInFile[5].substring(1, dataInFile[5].length() - 1);
            String propertyList = dataInFile[6].substring(1, dataInFile[6].length() - 1);
            String[] propertyCodes = propertyList.split(",");
            //search property lists for matching codes and make an arraylist of those properties
            //call makeUser to load them
        }

        List<String> agentLines = Files.readAllLines(Paths.get("./data/agents.csv"));
        for(int i = 0; i < agentLines.size(); i++) {
            String[] dataInFile = agentLines.get(i).split(",");
            //remove quotation marks
            String userID = dataInFile[0].substring(1, dataInFile[0].length() - 1);
            String name = dataInFile[1].substring(1, dataInFile[1].length() - 1);
            String username = dataInFile[2].substring(1, dataInFile[2].length() - 1);
            String password = dataInFile[3].substring(1, dataInFile[3].length() - 1);
            boolean approvalStatus = Boolean.parseBoolean(dataInFile[4].substring(1, dataInFile[4].length() - 1));
            String agentCode = dataInFile[5].substring(1, dataInFile[5].length() - 1);
            String propertyList = dataInFile[6].substring(1, dataInFile[6].length() - 1);
            String[] propertyCodes = propertyList.split(",");
            //search property lists for matching codes and make an arraylist of those properties
            //call makeUser to load them
        }

        List<String> tenantLines = Files.readAllLines(Paths.get("./data/agents.csv"));
        for(int i = 0; i < tenantLines.size(); i++) {
            String[] dataInFile = tenantLines.get(i).split(",");
            //remove quotation marks
            String userID = dataInFile[0].substring(1, dataInFile[0].length() - 1);
            String name = dataInFile[1].substring(1, dataInFile[1].length() - 1);
            String username = dataInFile[2].substring(1, dataInFile[2].length() - 1);
            String password = dataInFile[3].substring(1, dataInFile[3].length() - 1);
            boolean approvalStatus = Boolean.parseBoolean(dataInFile[4].substring(1, dataInFile[4].length() - 1));
            String ICNumber = dataInFile[5].substring(1, dataInFile[5].length() - 1);
            String propertyList = dataInFile[6].substring(1, dataInFile[6].length() - 1);
            String[] propertyCodes = propertyList.split(",");
            //search property lists for matching codes and make an arraylist of those properties
            //call makeUser to load them
        }
    }
}
