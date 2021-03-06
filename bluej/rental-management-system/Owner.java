
//This was written by Sidharrth Nagappan and Piragash Maran
//1181102313 and 1181102208

import java.util.ArrayList;

public class Owner extends User {
    //custom ownership code only for verification purposes
    private String ownershipCode;

    public String getOwnershipCode() {
        return ownershipCode;
    }

    public void setOwnershipCode(String ownershipCode) {
        this.ownershipCode = ownershipCode;
    }

    //custom CSV string generated
    @Override
    public String toCSVString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\"" + getUserID() + "\"" + ",")
                .append("\"" + getName() + "\"" + ",")
                .append("\"" + getUsername() + "\"" + ",")
                .append("\"" + getPassword() + "\"" + ",")
                .append("\"" + getApprovalStatus() + "\"" + ",")
                .append("\"" + getOwnershipCode() + "\"" + ",")
                .append("\"" + getPhone() + "\"" + ",");
        sb.append("\"[");
        //loops through property list and gets their codes
        ArrayList<Property> propertyList = getPropertyList();
        for(int i = 0; i < propertyList.size(); i++) {
            sb.append(propertyList.get(i).getID());
            sb.append(",");
        }
        sb.append("]\"");
        return sb.toString();
    }

    public Owner(String userCode, String name, String username, String password, String ownershipCode, String phone, boolean approvalStatus) {
        super(userCode, name, username, password, phone, approvalStatus);
        this.ownershipCode = ownershipCode;
    }
}
