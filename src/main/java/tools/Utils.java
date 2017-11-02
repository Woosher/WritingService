package tools;

import org.json.JSONObject;

public class Utils {

    public static String convertToSearchObject(String source){
        JSONObject jsonObject = new JSONObject(source);
       // JSONObject jsonResponse = new JSONObject();
        jsonObject.remove("long_description");
        String refId = jsonObject.getString("_id");
        jsonObject.remove("_id");
        jsonObject.put("refId", refId);
        return jsonObject.toString();
    }
}
