package learnProxyMob;


import org.json.simple.JSONObject;

import java.io.*;

public class UserAPI {

    @SuppressWarnings("unchecked")
    public void populateJSON(User user) throws IOException {
        JSONObject obj = new JSONObject();
        obj.put("Name", user.userName);
        obj.put("Email", user.userEmail);
        obj.put("Phone", user.userPhone);

        System.out.println(obj.toJSONString());


        FileWriter file = new FileWriter("/Users/smiroshn/Desktop/user.json");
        try {

            file.write(obj.toJSONString());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            file.flush();
            file.close();
        }

    }


    public void readJSON(File file) {
//        JSONParser parser = new JSONParser("/Users/smiroshn/Desktop/user.json");

    }


}
