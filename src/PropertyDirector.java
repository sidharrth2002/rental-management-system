import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PropertyDirector {
    private Property.Builder propertyBuilder;

    // engineer constructor, a concrete builder object is passed here
    public PropertyDirector(Property.Builder propertyBuilder) {
        this.propertyBuilder = propertyBuilder;
    }

    // returns the final property object
    public Property makeProperty() {
        return propertyBuilder.build();
    }

    public void makePropertiesFromFile() throws IOException {
        //read owners
        List<String> propertyLines = Files.readAllLines(Paths.get("./src/data/properties.csv"));
        for (int i = 0; i < propertyLines.size(); i++) {
            String[] dataInFile = propertyLines.get(i).split(",");
            //remove quotation marks
            String ID = dataInFile[0].substring(1, dataInFile[0].length() - 1);
            String name = dataInFile[1].substring(1, dataInFile[1].length() - 1);
            String address = dataInFile[2].substring(1, dataInFile[2].length() - 1);
            String project = dataInFile[3].substring(1, dataInFile[3].length() - 1);
            String description = dataInFile[4].substring(1, dataInFile[4].length() - 1);
            String type = dataInFile[5].substring(1, dataInFile[5].length() - 1);
            String photo = dataInFile[6].substring(1, dataInFile[6].length() - 1);
            double price = Double.parseDouble(dataInFile[7].substring(1, dataInFile[7].length() - 1));
            char rating = dataInFile[8].substring(1, dataInFile[8].length() - 1).charAt(0);
            // dataInFile[9]: date!!!
            boolean activeStatus = Boolean.parseBoolean(dataInFile[10].substring(1, dataInFile[10].length() - 1));
            // dataInFile[11]: if start with 'o' it's an owner, else if start with 'a' it's an agent
            // if dataInIle[12] exists, then tenant

            String ownershipCode = dataInFile[5].substring(1, dataInFile[5].length() - 1);
            String propertyList = dataInFile[6].substring(1, dataInFile[6].length() - 1);
            String[] propertyCodes = propertyList.split(",");
            //search property lists for matching codes and make an arraylist of those properties
            //call makeUser to load them
//            User user = instance.makeUser(userID, "owner", name, username, password, ownershipCode, approvalStatus);
            //load property to this user object
        }
    }
}
