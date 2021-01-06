public class Tenant extends User {
    String ICNumber;

    public Tenant(String userCode, String name, String username, String password, String ICNumber) {
        super(userCode, name, username, password);
        this.ICNumber = ICNumber;
    }
}
