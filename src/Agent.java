public class Agent extends User {
    String licenseCode;

    public Agent(String userCode, String name, String username, String password, String licenseCode) {
        super(userCode, name, username, password);
        this.licenseCode = licenseCode;
    }
}
