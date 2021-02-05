//This was written by Sidharrth Nagappan and Ahmed Sanad
//1181102313 and 1181102208

import java.util.*;

//singleton- only one propertysearchfacade
public class PropertySearchFacade {
    //stores singleton instance
    private static PropertySearchFacade instance = new PropertySearchFacade();
    private ArrayList<Property> properties = new ArrayList<>();

    private PropertySearchFacade() {}

    //returns the only instance
    public static PropertySearchFacade getInstance() {
        return instance;
    }

    //returns properties in the system without a filter
    public ArrayList<Property> getProperties() {
        return properties;
    }

    //sets properties in one go
    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    //add single property
    public void addProperty(Property property) {
        properties.add(property);
    }

    //removes property
    public void deleteProperty(Property property) {
        properties.remove(property);
    }

    //sorts using custom comparator from lowest to highest and returns list
    public ArrayList<Property> getByPrice() {
        ArrayList<Property> sortedProperty = new ArrayList<>(properties);
        Collections.sort(sortedProperty, new Comparator<Property>() {
            @Override
            public int compare(final Property property1, final Property property2) {
                return Double.compare(property1.getPrice(), property2.getPrice());
            }
        });
        return sortedProperty;
    }

    //search logic based on a keyword
    //scans through listing names, description, address, facilities, etc.
    public ArrayList<Property> getByKeyword(String keyword) {
        ArrayList<Property> filteredProperty = new ArrayList<>();
        for (Property property: properties) {
            //different search criteria
            if (property.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                    property.getDescription().toLowerCase().contains(keyword.toLowerCase()) ||
                    property.getAddress().toLowerCase().contains(keyword.toLowerCase())
                ) {
                filteredProperty.add(property);
            }
            //checks if facility names appear in search
            for (String facility: property.getFacilities()) {
                if(facility.toLowerCase().contains(keyword.toLowerCase()) && !filteredProperty.contains(property)) {
                    filteredProperty.add(property);
                }
            }
        }
        return filteredProperty;
    }

    //returns properties for a certain type
    public ArrayList<Property> getByType(String type) {
        ArrayList<Property> filteredProperty = new ArrayList<>();
        for (Property property: properties) {
            if (property.getType().equalsIgnoreCase(type)) {
                filteredProperty.add(property);
            }
        }
        return filteredProperty;
    }

    //get only active properties
    public ArrayList<Property> getByActive() {
        ArrayList<Property> filteredProperty = new ArrayList<>();
        for (Property property: properties) {
            if (property.getAssignedStatus()) {
                filteredProperty.add(property);
            }
        }
        return filteredProperty;
    }

    //get only inactive properties
    public ArrayList<Property> getByInactive() {
        ArrayList<Property> filteredProperty = new ArrayList<>();
        for (Property property: properties) {
            if (!property.getAssignedStatus()) {
                filteredProperty.add(property);
            }
        }
        return filteredProperty;
    }
}