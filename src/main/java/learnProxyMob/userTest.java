package learnProxyMob;

import java.io.IOException;

public class userTest {

    public static void main(String[] args) {

        User ivan = new User("Ivan", "Ivan@gmail.com", "5455464-874");
        UserAPI userAPI = new UserAPI();
        try {
            userAPI.populateJSON(ivan);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
