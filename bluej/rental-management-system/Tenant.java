//This was written by Sidharrth Nagappan and Piragash Maran
//1181102313 and 1181101448

import java.util.ArrayList;

public class Tenant extends User {
    private String ICNumber;

    //return user's IC number
    public String getICNumber() {
        return ICNumber;
    }

    //set the IC number of user
    public void setICNumber(String ICNumber) {
        this.ICNumber = ICNumber;
    }

    //tenant's constructor called by the user factory
    public Tenant(String userCode, String name, String username, String password, String ICNumber, String phone, boolean approvalStatus) {
        super(userCode, name, username, password, phone, approvalStatus);
        this.ICNumber = ICNumber;
    }

    //custom CSV string that prepares data to write to file
    public String toCSVString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\"" + getUserID() + "\"" + ",")
                .append("\"" + getName() + "\"" + ",")
                .append("\"" + getUsername() + "\"" + ",")
                .append("\"" + getPassword() + "\"" + ",")
                .append("\"" + getApprovalStatus() + "\"" + ",")
                .append("\"" + getICNumber() + "\"" + ",")
                .append("\"" + getPhone() + "\"" + ",");
        sb.append("\"[");
        ArrayList<Property> propertyList = getPropertyList();
        for(int i = 0; i < propertyList.size(); i++) {
            sb.append(propertyList.get(i).getID());
            sb.append(",");
        }
        sb.append("]\"");
        return sb.toString();
    }
}