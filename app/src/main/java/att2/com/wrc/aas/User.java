package att2.com.wrc.aas;

/**
 * User Model for storing and retrieving user data, Created by Aurghya, 31-03-2018
 */
public class User {
    //TODO: Implement teacher ID
    private String name;
    private String email;
    private String lastClass;
    private boolean verified;
    private String accessLevel;

    public User() {
    }

    /**
     * Constructor for User
     * @param name name of the user
     * @param email email of the user, same as authentication
     * @param lastClass last class taken by the user
     * @param verified verification status of user
     * @param accessLevel access level of the user
     */
    User(String name, String email, String lastClass, boolean verified, String accessLevel) {
        this.name = name;
        this.email = email;
        this.lastClass = lastClass;
        this.verified = verified;
        this.accessLevel = accessLevel;
    }

    /**
     * @return returns the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * @return returns the email of the user, redundant?
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return returns the string with the last class taken by user, maybe update to something more specific?
     */
    public String getLastClass() {
        return lastClass;
    }

    /**
     * @return returns true if the user is verified by one of the admins
     */
    public boolean isVerified() {
        return verified;
    }

    /**
     * @return returns the access level of the user
     */
    public String getAccessLevel() {
        return accessLevel;
    }

    /**
     * @param name Name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param email email of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param lastClass Last class taen by the user, redundant?
     */
    public void setLastClass(String lastClass) {
        this.lastClass = lastClass;
    }

    /**
     * @param verified sets the verified status of the user. redundant?
     */
    public void setVerified(boolean verified) {
        this.verified = verified;
    }

}
