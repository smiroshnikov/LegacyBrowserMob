package learnProxyMob;

public class User {

    String userName;
    String userEmail;
    String userPhone;

    public User(String userName, String userEmail, String userPhone) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
    }

    public User() {
        this.userName = "default";
        this.userEmail = "default";
        this.userPhone = "default";
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPhone='" + userPhone + '\'' +
                '}';
    }
}

