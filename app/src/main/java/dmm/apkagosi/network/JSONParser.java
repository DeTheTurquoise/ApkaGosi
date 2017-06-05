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

    private static JSONObject finalTranslatedWordObject(String JSONResult, int translatedWordOrder, int translatingWordOrder, String generalArray, String internalArray) throws JSONException {
        JSONArray resultArray = getArray(JSONResult, generalArray);
        JSONObject arrayObject = resultArray.getJSONObject(translatedWordOrder);
        JSONArray japanese = arrayObject.getJSONArray(internalArray);
        return japanese.getJSONObject(translatingWordOrder);
    }

    public static String getWord(String JSONResult, int translatedWordOrder, int translatingWordOrder){
        JSONObject translation = null;
        String word;
        try {
            translation = finalTranslatedWordObject(JSONResult,translatedWordOrder,translatingWordOrder, "data", "japanese");
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
            translation = finalTranslatedWordObject(JSONResult,translatedWordOrder,translatingWordOrder,"data","japanese");
            reading = translation.getString("reading");
        } catch (JSONException e) {
            e.printStackTrace();
            reading = "";
        }
        return reading;
    }

    public static String getDefinition(String JSONResult, int translatedWordOrder, int englishDefinitionOrder){
        String definitions = new String("");
        try {
            JSONObject definitionObject = finalTranslatedWordObject(JSONResult,translatedWordOrder,englishDefinitionOrder,"data","senses");
            JSONArray englishDefinition = definitionObject.getJSONArray("english_definitions");
            for (int i=0; i<englishDefinition.length(); i++){
                definitions = definitions + englishDefinition.getString(i) + "\n";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return definitions;
    }

    public static String getTag(String JSONResult, int translatedWordOrder, int englishDefinitionOrder){
        String tags = new String("");
        try {
            JSONObject tagObject = finalTranslatedWordObject(JSONResult,translatedWordOrder,englishDefinitionOrder,"data","senses");
            JSONArray englishTag = tagObject.getJSONArray("tags");
            for (int i=0; i<englishTag.length(); i++){
                tags = tags + englishTag.getString(i) + "\n";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tags;
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

    public static String[] getTags(String JSONResult, int arrayLength){
        String[] words = new String[arrayLength];
        for (int i = 0; i < arrayLength; i++){
            words[i] = getTag(JSONResult,i,0);
        }
        return words;
    }
}
