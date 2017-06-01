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

    public static String getWord(String JSONResult, int translatedWordOrder, int translatingWordOrder){
        JSONObject translation = null;
        String word;
        try {
            translation = finalTranslatedWordObject(JSONResult,translatedWordOrder,translatingWordOrder);
            word = translation.getString("word");
        } catch (JSONException e) {
            e.printStackTrace();
            word = "";
        }
        return word;
    }

    public static String getReading(String JSONResult, int translatedWordOrder, int translatingWordOrder){
        JSONObject translation = null;
        String reading;
        try {
            translation = finalTranslatedWordObject(JSONResult,translatedWordOrder,translatingWordOrder);
            reading = translation.getString("reading");
        } catch (JSONException e) {
            e.printStackTrace();
            reading = "";
        }
        return reading;
    }

    public static String getDefinition(String JSONResult, int translatedWordOrder, int englishDefinitionOrder){
        JSONArray resultArray = null;
        String definitions = new String("");
        try {
            resultArray = getArray(JSONResult, "data");
            JSONObject arrayObject = resultArray.getJSONObject(translatedWordOrder);
            JSONArray sensesArray = arrayObject.getJSONArray("senses");
            JSONObject definitionObject = sensesArray.getJSONObject(englishDefinitionOrder);
            JSONArray englishDefinition = definitionObject.getJSONArray("english_definitions");
            for (int i=0; i<englishDefinition.length(); i++){
                definitions = definitions + englishDefinition.getString(i) + "\n";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return definitions;
    }

    public static String getFirstTranslation(String JSONResult) throws JSONException {
        String result = new String("");
        result = "Word\n" + getWord(JSONResult,0,0) + "\n\nReading\n" + getReading(JSONResult,0,0) + "\n\nEnglishDefinition\n" + getDefinition(JSONResult,0,0);
        return result;
    }

    public static int getTranslationResultsNumber(String JSONResult) throws JSONException {
        return getArray(JSONResult,"data").length();
    }

    public static String[] getWords(String JSONResult, int arrayLength){
        String[] words = new String[arrayLength];
        for (int i = 0; i < arrayLength; i++){
            words[i] = getWord(JSONResult,i,0);
        }
        return words;
    }
    public static String[] getReadings(String JSONResult, int arrayLength){
        String[] words = new String[arrayLength];
        for (int i = 0; i < arrayLength; i++){
            words[i] = getReading(JSONResult,i,0);
        }
        return words;
    }
    public static String[] getDefinitions(String JSONResult, int arrayLength){
        String[] words = new String[arrayLength];
        for (int i = 0; i < arrayLength; i++){
            words[i] = getDefinition(JSONResult,i,0);
        }
        return words;
    }
}
