package dmm.apkagosi.network;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ddabrowa on 2017-05-22.
 */

public class JSONParser {
    public static String getWord(String JSONResult) throws JSONException {
        JSONObject searchResult = new JSONObject(JSONResult);
        JSONObject data = searchResult.getJSONObject("data");
        JSONObject japanese = data.getJSONObject("japanese");
        return japanese.getString("word");
    }
}
