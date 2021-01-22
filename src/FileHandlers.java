import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileHandlers {
    private static FileHandlers instance = new FileHandlers();

    UserFactory userFactory = UserFactory.getInstance();
    PropertySearchFacade propertySearchFacade = PropertySearchFacade.getInstance();

    private FileHandlers() {}

    public static FileHandlers getInstance() {
        return instance;
    }

    public void readUsersFromFile() throws IOException {
        ArrayList<Property> properties = PropertySearchFacade.getInstance().getProperties();
        //read owners
        List<String> ownerLines = Files.readAllLines(Paths.get("./src/data/owners.csv"));
        for(int i = 0; i < ownerLines.size(); i++) {
            String[] dataInFile = ownerLines.get(i).split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            //remove quotation marks
            String userID = dataInFile[0].substring(1, dataInFile[0].length() - 1);
            String name = dataInFile[1].substring(1, dataInFile[1].length() - 1);
            String username = dataInFile[2].substring(1, dataInFile[2].length() - 1);
            String password = dataInFile[3].substring(1, dataInFile[3].length() - 1);
            boolean approvalStatus = Boolean.parseBoolean(dataInFile[4].substring(1, dataInFile[4].length() - 1));
            String ownershipCode = dataInFile[5].substring(1, dataInFile[5].length() - 1);
            String propertyList = dataInFile[6].substring(2, dataInFile[6].length() - 2);
            String[] propertyCodes = propertyList.split(",");
            //call makeUser to load them
            User user = userFactory.makeUser(userID, "owner", name, username, password, ownershipCode, approvalStatus);
            user.setPropertyCodes(Arrays.asList(propertyCodes));
        }

        List<String> agentLines = Files.readAllLines(Paths.get("./src/data/agents.csv"));
        for(int i = 0; i < agentLines.size(); i++) {
            String[] dataInFile = agentLines.get(i).split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            //remove quotation marks
            String userID = dataInFile[0].substring(1, dataInFile[0].length() - 1);
            String name = dataInFile[1].substring(1, dataInFile[1].length() - 1);
            String username = dataInFile[2].substring(1, dataInFile[2].length() - 1);
            String password = dataInFile[3].substring(1, dataInFile[3].length() - 1);
            boolean approvalStatus = Boolean.parseBoolean(dataInFile[4].substring(1, dataInFile[4].length() - 1));
            String agentCode = dataInFile[5].substring(1, dataInFile[5].length() - 1);
            String propertyList = dataInFile[6].substring(2, dataInFile[6].length() - 2);
            String[] propertyCodes = propertyList.split(",");
            //search property lists for matching codes and make an arraylist of those properties//call makeUser to load them
            User user = userFactory.makeUser(userID, "agent", name, username, password, agentCode, approvalStatus);
            //load property to this user object
            user.setPropertyCodes(Arrays.asList(propertyCodes));
        }

        List<String> tenantLines = Files.readAllLines(Paths.get("./src/data/tenants.csv"));
        for(int i = 0; i < tenantLines.size(); i++) {
            String[] dataInFile = tenantLines.get(i).split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            //remove quotation marks
            String userID = dataInFile[0].substring(1, dataInFile[0].length() - 1);
            String name = dataInFile[1].substring(1, dataInFile[1].length() - 1);
            String username = dataInFile[2].substring(1, dataInFile[2].length() - 1);
            String password = dataInFile[3].substring(1, dataInFile[3].length() - 1);
            boolean approvalStatus = Boolean.parseBoolean(dataInFile[4].substring(1, dataInFile[4].length() - 1));
            String ICNumber = dataInFile[5].substring(1, dataInFile[5].length() - 1);
            String propertyList = dataInFile[6].substring(2, dataInFile[6].length() - 2);
            String[] propertyCodes = propertyList.split(",");
            //call makeUser to load them
            User user = userFactory.makeUser(userID, "tenant", name, username, password, ICNumber, approvalStatus);
            user.setPropertyCodes(Arrays.asList(propertyCodes));
        }
    }

    public void loadPropertyToUsers() {
        for(int i = 0; i < userFactory.getUsers().size(); i++) {
            User user = userFactory.getUsers().get(i);
            for(int j = 0; j < user.getPropertyCodes().size(); j++) {
                String code = user.getPropertyCodes().get(j);
                for (int k = 0; k < PropertySearchFacade.getInstance().getProperties().size(); k++) {
                    Property property = PropertySearchFacade.getInstance().getProperties().get(k);
                    if(code.equals(property.getID())) {
                        System.out.println("User who manages is " + user.getName() + " and he manages " + property.getID());
                        user.addProperty(property);
                        break;
                    }
                }
            }
        }
    }

    public void saveTenantsToFile() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < userFactory.getTenants().size(); i++) {
            sb.append(userFactory.getTenants().get(i).toCSVString());
            sb.append("\n");
        }
        Files.write(Paths.get("./data/tenants.csv"), sb.toString().getBytes());
    }

    public void saveOwnersToFile() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < userFactory.getOwners().size(); i++) {
            sb.append(userFactory.getOwners().get(i).toCSVString());
            sb.append("\n");
        }
        Files.write(Paths.get("data/owners.csv"), sb.toString().getBytes());
    }

    public void saveAgentsToFile() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < userFactory.getAgents().size(); i++) {
            sb.append(userFactory.getAgents().get(i).toCSVString());
            sb.append("\n");
        }
        Files.write(Paths.get("./data/agents.csv"), sb.toString().getBytes());
    }

    public void savePropertyToFile() throws IOException {
        StringBuilder sb = new StringBuilder();
        ArrayList<Property> propertyList = propertySearchFacade.getProperties();
        for (int i = 0; i < propertyList.size(); i++) {
            sb.append(propertyList.get(i).toCSVString());
            sb.append("\n");
        }
        Files.write(Paths.get("./data/properties.csv"), sb.toString().getBytes());
    }

    public void getPropertyFromFile() throws IOException, ParseException {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        List<String> lines = Files.readAllLines(Paths.get("./src/data/properties.csv"));
        for(int i = 0; i < lines.size(); i++) {
            String[] dataInFile = lines.get(i).split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)\\s*");
            String ID = dataInFile[0].substring(1, dataInFile[0].length() - 1);
//            System.out.println(ID);
            String name = dataInFile[1].substring(1, dataInFile[1].length() - 1);
//            System.out.println(name);

            String address = dataInFile[2].substring(1, dataInFile[2].length() - 1);
//            System.out.println(address);

            String project = dataInFile[3].substring(1, dataInFile[3].length() - 1);
//            System.out.println(project);

            String description = dataInFile[4].substring(1, dataInFile[4].length() - 1);
//            System.out.println(description);

            String type = dataInFile[5].substring(1, dataInFile[5].length()-1);
//            System.out.println(type);

            String photo = dataInFile[6].substring(1, dataInFile[6].length() - 1);
//            System.out.println(photo);

            double price = Double.parseDouble(dataInFile[7].substring(1, dataInFile[7].length() - 1));
//            System.out.println(price);

            Date initialMarketRate = format.parse(dataInFile[8].substring(1, dataInFile[8].length() - 1));
//            System.out.println(initialMarketRate);

            boolean status = Boolean.parseBoolean(dataInFile[9].substring(1, dataInFile[9].length() - 1));
//            System.out.println(status);

            String user = dataInFile[10].substring(1, dataInFile[10].length() - 1);
//            System.out.println(user);

            String facilities = dataInFile[11].substring(2, dataInFile[11].length()-2);
            String[] facilitiesList = facilities.split(",");
            ArrayList<String> loadFacilities = new ArrayList<>();
            for (String facility: facilitiesList) {
                loadFacilities.add(facility);
            }
            Property property = new Property.Builder()
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

            System.out.println(property.toCSVString()); // test output the read property, should match directly with raw csv data
            propertySearchFacade.addProperty(property);
        }
    }
}
