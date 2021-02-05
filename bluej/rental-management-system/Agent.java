//This was written by Piragash Maran
//1181101448
import java.util.ArrayList;

public class Agent extends User {
    private String licenseCode;

    public String getLicenseCode() {
        return licenseCode;
    }

    public void setLicenseCode(String licenseCode) {
        this.licenseCode = licenseCode;
    }

    //custom toCSVString function for the agent
    //to write to file
    @Override
    public String toCSVString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\"" + getUserID() + "\"" + ",")
                .append("\"" + getName() + "\"" + ",")
                .append("\"" + getUsername() + "\"" + ",")
                .append("\"" + getPassword() + "\"" + ",")
                .append("\"" + getApprovalStatus() + "\"" + ",")
                .append("\"" + getLicenseCode() + "\"" + ",")
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

    public Agent(String userCode, String name, String username, String password, String licenseCode, String phone, boolean approvalStatus) {
        super(userCode, name, username, password, phone, approvalStatus);
        this.licenseCode = licenseCode;
    }
}
