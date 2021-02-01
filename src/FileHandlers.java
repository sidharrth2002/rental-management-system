import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

//the singleton class that deals with all file handling related matters
public class FileHandlers {
    //one singleton instance
    private static FileHandlers instance = new FileHandlers();

    //regex to parse CSV String
    //since fields themselves may contain commas
    private final String CSVRegex = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
    private final String propertyCSVRegex = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)\\s*";
    //the location changes a lot, so if in one place, easier to change later
    private final String filePath = "./src/data/";

    //aggregates the user factory and property search facade to call their methods
    private UserFactory userFactory = UserFactory.getInstance();
    private PropertySearchFacade propertySearchFacade = PropertySearchFacade.getInstance();

    private FileHandlers() {}

    public static FileHandlers getInstance() {
        return instance;
    }

    //method reads all users from the file
    public void readUsersFromFile() throws IOException {
        ArrayList<Property> properties = PropertySearchFacade.getInstance().getProperties();
        //read owners
        List<String> ownerLines = Files.readAllLines(Paths.get(filePath + "owners.csv"));
        //set the number of the owners in the system
        for(int i = 0; i < ownerLines.size(); i++) {
            //extracts field by field from CSV string, parses and loads them
            String[] dataInFile = ownerLines.get(i).split(CSVRegex);
            //remove quotation marks
            String userID = dataInFile[0].substring(1, dataInFile[0].length() - 1);
            String name = dataInFile[1].substring(1, dataInFile[1].length() - 1);
            String username = dataInFile[2].substring(1, dataInFile[2].length() - 1);
            String password = dataInFile[3].substring(1, dataInFile[3].length() - 1);
            boolean approvalStatus = Boolean.parseBoolean(dataInFile[4].substring(1, dataInFile[4].length() - 1));
            String ownershipCode = dataInFile[5].substring(1, dataInFile[5].length() - 1);
            String phone = dataInFile[6].substring(1, dataInFile[6].length() - 1);
            String propertyList = dataInFile[7].substring(2, dataInFile[7].length() - 2);
            String[] propertyCodes = propertyList.split(",");
            //call makeUser to load them- overloaded to directly take in the ID and not autogenerate
            User user = userFactory.makeUser(userID, "owner", name, username, password, ownershipCode, approvalStatus, phone);
            user.setPropertyCodes(Arrays.asList(propertyCodes));
            if(i == ownerLines.size() - 1) {
                System.out.println(Integer.parseInt(userID.substring(1)));
                UserFactory.setNumOwners(Integer.parseInt(userID.substring(1)));
            }
        }

        //reads agents
        List<String> agentLines = Files.readAllLines(Paths.get(filePath + "agents.csv"));
//        UserFactory.setNumAgents(agentLines.size());
        for(int i = 0; i < agentLines.size(); i++) {
            String[] dataInFile = agentLines.get(i).split(CSVRegex);
            //remove quotation marks
            String userID = dataInFile[0].substring(1, dataInFile[0].length() - 1);
            String name = dataInFile[1].substring(1, dataInFile[1].length() - 1);
            String username = dataInFile[2].substring(1, dataInFile[2].length() - 1);
            String password = dataInFile[3].substring(1, dataInFile[3].length() - 1);
            boolean approvalStatus = Boolean.parseBoolean(dataInFile[4].substring(1, dataInFile[4].length() - 1));
            String agentCode = dataInFile[5].substring(1, dataInFile[5].length() - 1);
            String phone = dataInFile[6].substring(1, dataInFile[6].length() - 1);
            String propertyList = dataInFile[7].substring(2, dataInFile[7].length() - 2);
            String[] propertyCodes = propertyList.split(",");
            //search property lists for matching codes and make an arraylist of those properties//call makeUser to load them
            User user = userFactory.makeUser(userID, "agent", name, username, password, agentCode, approvalStatus, phone);
            //load property to this user object
            user.setPropertyCodes(Arrays.asList(propertyCodes));
            if(i == agentLines.size() - 1) {
                System.out.println(Integer.parseInt(userID.substring(1)));
                UserFactory.setNumAgents(Integer.parseInt(userID.substring(1)));
            }
        }

        List<String> tenantLines = Files.readAllLines(Paths.get(filePath + "tenants.csv"));
//        UserFactory.setNumTenants(tenantLines.size());
        for(int i = 0; i < tenantLines.size(); i++) {
            String[] dataInFile = tenantLines.get(i).split(CSVRegex);
            //remove quotation marks
            String userID = dataInFile[0].substring(1, dataInFile[0].length() - 1);
            String name = dataInFile[1].substring(1, dataInFile[1].length() - 1);
            String username = dataInFile[2].substring(1, dataInFile[2].length() - 1);
            String password = dataInFile[3].substring(1, dataInFile[3].length() - 1);
            boolean approvalStatus = Boolean.parseBoolean(dataInFile[4].substring(1, dataInFile[4].length() - 1));
            String ICNumber = dataInFile[5].substring(1, dataInFile[5].length() - 1);
            String phone = dataInFile[6].substring(1, dataInFile[6].length() - 1);
            String propertyList = dataInFile[7].substring(2, dataInFile[7].length() - 2);
            String[] propertyCodes = propertyList.split(",");
            //call makeUser to load them
            User user = userFactory.makeUser(userID, "tenant", name, username, password, ICNumber, approvalStatus, phone);
            user.setPropertyCodes(Arrays.asList(propertyCodes));
            if(i == tenantLines.size() - 1) {
                System.out.println(Integer.parseInt(userID.substring(1)));
                UserFactory.setNumTenants(Integer.parseInt(userID.substring(1)));
            }
        }

        List<String> adminLines = Files.readAllLines(Paths.get(filePath + "admins.csv"));
//        UserFactory.setNumAdmins(adminLines.size());
        for(int i = 0; i < adminLines.size(); i++) {
            String[] dataInFile = adminLines.get(i).split(CSVRegex);
            //remove quotation marks
            String userID = dataInFile[0].substring(1, dataInFile[0].length() - 1);
            String name = dataInFile[1].substring(1, dataInFile[1].length() - 1);
            String username = dataInFile[2].substring(1, dataInFile[2].length() - 1);
            String password = dataInFile[3].substring(1, dataInFile[3].length() - 1);
            boolean approvalStatus = Boolean.parseBoolean(dataInFile[4].substring(1, dataInFile[4].length() - 1));
            //call makeUser to load them
            //pass empty string in place of credential and phone because admin does not need credentials
            User user = userFactory.makeUser(userID, "admin", name, username, password, "", approvalStatus, "");
            if(i == adminLines.size() - 1) {
                System.out.println(Integer.parseInt(userID.substring(2)));
                UserFactory.setNumAdmins(Integer.parseInt(userID.substring(2)));
            }
        }
    }

    //get the properties from file by reading, parsing fields and creating property objects
    public void getPropertyFromFile() throws IOException, ParseException {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        List<String> lines = Files.readAllLines(Paths.get(filePath + "properties.csv"));
        for(int i = 0; i < lines.size(); i++) {
            String[] dataInFile = lines.get(i).split(propertyCSVRegex);
            String ID = dataInFile[0].substring(1, dataInFile[0].length() - 1);
            String name = dataInFile[1].substring(1, dataInFile[1].length() - 1);
            String address = dataInFile[2].substring(1, dataInFile[2].length() - 1);
            String project = dataInFile[3].substring(1, dataInFile[3].length() - 1);
            String description = dataInFile[4].substring(1, dataInFile[4].length() - 1);
            String type = dataInFile[5].substring(1, dataInFile[5].length()-1);
            String photo = dataInFile[6].substring(1, dataInFile[6].length() - 1);
            double price = Double.parseDouble(dataInFile[7].substring(1, dataInFile[7].length() - 1));
            Date initialMarketRate = format.parse(dataInFile[8].substring(1, dataInFile[8].length() - 1));
            boolean status = Boolean.parseBoolean(dataInFile[9].substring(1, dataInFile[9].length() - 1));
            String user = dataInFile[10].substring(1, dataInFile[10].length() - 1);
            String facilities = dataInFile[11].substring(2, dataInFile[11].length()-2);
            String[] facilitiesList = facilities.split(",");
            ArrayList<String> loadFacilities = new ArrayList<>();
            for (String facility: facilitiesList) {
                loadFacilities.add(facility);
            }
            //call builder object to make property
            Property property = new Property.Builder()
                    //uses withID because want to manually pass in the ID without letting it autogenerate
                    .withID(ID)
                    .withName(name)
                    .withAddress(address)
                    .withProject(project)
                    .withDescription(description)
                    .withType(type)
                    .withPhoto(photo)
                    .withPrice(price)
                    .withInitialMarketDate(initialMarketRate)
                    .withAssignedStatus(status)
                    .withFacilities(loadFacilities)
                    .build();
            //since property aggregates users, based on the code, get the object and store it in property class
            if(user.charAt(0) == 'o') {
                Owner chosenOwner = null;
                for(int j = 0; j < userFactory.getOwners().size(); j++) {
                    Owner owner = userFactory.getOwners().get(j);
                    if(owner.getUserID().equals(user)) {
                        chosenOwner = owner;
                        break;
                    }
                }
                property.setOwner(chosenOwner);
            } else if (user.charAt(0) == 'a') {
                Agent chosenAgent = null;
                for(int j = 0; j < userFactory.getAgents().size(); j++) {
                    Agent agent = userFactory.getAgents().get(j);
                    if(agent.getUserID().equals(user)) {
                        chosenAgent = agent;
                        break;
                    }
                }
                property.setAgent(chosenAgent);
            }
            propertySearchFacade.addProperty(property);
            //this sets the current count of properties, so that the next property has it's ID correctly generated
            if(i == lines.size() - 1) {
                Property.setCount(Integer.parseInt(ID.substring(1)) + 1);
            }
        }
    }

    //once property and users are both loaded,
    //assign property to each user based on aggregating codes
    public void loadPropertyToUsers() {
        for(int i = 0; i < userFactory.getUsers().size(); i++) {
            User user = userFactory.getUsers().get(i);
            //get the property codes managed by the user
            for(int j = 0; j < user.getPropertyCodes().size(); j++) {
                String code = user.getPropertyCodes().get(j);
                //get the properties based on the codes
                for (int k = 0; k < PropertySearchFacade.getInstance().getProperties().size(); k++) {
                    Property property = PropertySearchFacade.getInstance().getProperties().get(k);
                    if(code.equals(property.getID())) {
                        user.addProperty(property);
                        break;
                    }
                }
            }
        }
    }

    //save the tenants to file by calling the CSV string method each time
    public void saveTenantsToFile() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < userFactory.getTenants().size(); i++) {
            sb.append(userFactory.getTenants().get(i).toCSVString());
            sb.append("\n");
        }
        Files.write(Paths.get(filePath + "tenants.csv"), sb.toString().getBytes());
    }

    //save owners to file by calling CSV string method
    public void saveOwnersToFile() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < userFactory.getOwners().size(); i++) {
            sb.append(userFactory.getOwners().get(i).toCSVString());
            sb.append("\n");
        }
        Files.write(Paths.get(filePath + "owners.csv"), sb.toString().getBytes());
    }

    //save agents to file by calling CSV string method
    public void saveAgentsToFile() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < userFactory.getAgents().size(); i++) {
            sb.append(userFactory.getAgents().get(i).toCSVString());
            sb.append("\n");
        }
        Files.write(Paths.get(filePath + "agents.csv"), sb.toString().getBytes());
    }

    //save admins to file by calling CSV string method
    public void saveAdminsToFile() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < userFactory.getAdmins().size(); i++) {
            sb.append(userFactory.getAdmins().get(i).toCSVString());
            sb.append("\n");
        }
        Files.write(Paths.get(filePath + "admins.csv"), sb.toString().getBytes());
    }

    //save properties to file by calling CSV string method
    public void savePropertyToFile() throws IOException {
        StringBuilder sb = new StringBuilder();
        ArrayList<Property> propertyList = propertySearchFacade.getProperties();
        for (int i = 0; i < propertyList.size(); i++) {
            System.out.println(propertyList.get(i).toCSVString());
            sb.append(propertyList.get(i).toCSVString());
            sb.append("\n");
        }
        Files.write(Paths.get(filePath + "properties.csv"), sb.toString().getBytes());
    }
}
