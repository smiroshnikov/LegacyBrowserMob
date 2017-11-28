package proxy;

public class UserApi {

    String userName;
    String userEmail;
    String userPhone;

    public UserApi(String userName, String userEmail, String userPhone) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
    }

    public UserApi() {
        this.userName = "default";
        this.userEmail = "default";
        this.userPhone = "default";
    }

    @Override
    public String toString() {
        return "UserApi{" +
                "userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPhone='" + userPhone + '\'' +
                '}';
    }
}

