public class Owner extends User {
    private String ownershipCode;

    public Owner(String userCode, String name, String username, String password, String ownershipCode) {
        super(userCode, name, username, password);
        this.ownershipCode = ownershipCode;
    }
}
