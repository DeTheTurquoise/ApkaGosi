package dmm.apkagosi.network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ddabrowa on 2017-05-22.
 */

public class JSONParser {
    private static JSONArray getArray(String JSONResult, String arrayField) throws JSONException {
        JSONObject searchResult = new JSONObject(JSONResult);
        return searchResult.getJSONArray(arrayField);
    }

    private static JSONObject finalTranslatedWordObject(String JSONResult, int translatedWordOrder, int translatingWordOrder) throws JSONException {
        JSONArray resultArray = getArray(JSONResult, "data");
        JSONObject arrayObject = resultArray.getJSONObject(translatedWordOrder);
        JSONArray japanese = arrayObject.getJSONArray("japanese");
        return japanese.getJSONObject(translatingWordOrder);
    }

    public static String getWord(String JSONResult, int translatedWordOrder, int translatingWordOrder) throws JSONException {
        JSONObject translation = finalTranslatedWordObject(JSONResult,translatedWordOrder,translatingWordOrder);
        return translation.getString("word");
    }

    public static String getReading(String JSONResult, int translatedWordOrder, int translatingWordOrder) throws JSONException {
        JSONObject translation = finalTranslatedWordObject(JSONResult,translatedWordOrder,translatingWordOrder);
        return translation.getString("reading");
    }

    public static String getDefinition(String JSONResult, int translatedWordOrder, int englishDefinitionOrder) throws JSONException {
        JSONArray resultArray = getArray(JSONResult, "data");
        JSONObject arrayObject = resultArray.getJSONObject(translatedWordOrder);
        JSONArray sensesArray = arrayObject.getJSONArray("senses");
        JSONObject definitionObject = sensesArray.getJSONObject(englishDefinitionOrder);
        JSONArray englishDefinition = definitionObject.getJSONArray("english_definitions");
        String definitions = new String("");
        for (int i=0; i<englishDefinition.length(); i++){
            definitions = definitions + englishDefinition.getString(i) + "\n";
        }
        return definitions;
    }

    public static String getFirstTranslation(String JSONResult) throws JSONException {
        String result = new String("");
        result = "Word\n" + getWord(JSONResult,0,0) + "\n\nReading\n" + getReading(JSONResult,0,0) + "\n\nEnglishDefinition\n" + getDefinition(JSONResult,0,0);
        return result;
    }



}
