import java.util.ArrayList;
import java.util.Date;

public class PropertySearchFacade {
    private ArrayList<Property> properties;

    public ArrayList<Property> getByPrice() {
        //use comparator
        return null;
    }


    public ArrayList<Property> getByType(String type) {
        ArrayList<Property> filteredProperty = new ArrayList<>();
        for (Property property: properties) {
            if (property.getType().equalsIgnoreCase(type)) {
                filteredProperty.add(property);
            }
        }
        return filteredProperty;
    }

    //Ahmed
    public ArrayList<Property> getByDate(Date from, Date to) {
        return null;
    }

    public ArrayList<Property> getByActive() {
        ArrayList<Property> filteredProperty = new ArrayList<>();
        for (Property property: properties) {
            if (property.getStatus().equalsIgnoreCase("active")) {
                filteredProperty.add(property);
            }
        }
        return filteredProperty;
    }

    public ArrayList<Property> getByInactive() {
        ArrayList<Property> filteredProperty = new ArrayList<>();
        for (Property property: properties) {
            if (property.getStatus().equalsIgnoreCase("inactive")) {
                filteredProperty.add(property);
            }
        }
        return filteredProperty;
    }

    public ArrayList<Property> getByAssigned() {
        ArrayList<Property> filteredProperty = new ArrayList<>();
        for (Property property: properties) {
            if (property.getAssigned()) {
                filteredProperty.add(property);
            }
        }
        return filteredProperty;
    }

    public ArrayList<Property> getByUnassigned() {
        ArrayList<Property> filteredProperty = new ArrayList<>();
        for (Property property: properties) {
            if (!property.getAssigned()) {
                filteredProperty.add(property);
            }
        }
        return filteredProperty;
    }
}
