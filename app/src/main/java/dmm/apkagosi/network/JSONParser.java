package dmm.apkagosi.network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ddabrowa on 2017-05-22.
 */

public class JSONParser {
    private static int localArrayLength;
    private static String resultString;

    private static void resetInitialValues(){
        localArrayLength = 0;
        resultString = "";
    }

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

    private static String getThirdArrayStringFromJSONParser(String JSONResult, int translatedWordOrder, int translatingWordOrder, String generalArray, String internalArray, String thirdArray){
        resetInitialValues();
        try {
            JSONObject definitionObject = finalTranslatedWordObject(JSONResult,translatedWordOrder,translatingWordOrder,generalArray, internalArray);
            JSONArray englishDefinition = definitionObject.getJSONArray(thirdArray);
            localArrayLength = englishDefinition.length();
            if (localArrayLength > 0){
                resultString = englishDefinition.getString(0);
                for (int i = 1; i< localArrayLength; i++){
                    resultString = resultString +  "\n" + englishDefinition.getString(i);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultString;
    }

    private static String getThirdStringFromJSONParser(String JSONResult, int translatedWordOrder, int translatingWordOrder, String generalArray, String internalArray, String thirdLevelString){
        resetInitialValues();
        try {
            JSONObject translation = finalTranslatedWordObject(JSONResult,translatedWordOrder,translatingWordOrder,generalArray,internalArray);
            resultString = translation.getString(thirdLevelString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  resultString;
    }

    public static String getWord(String JSONResult, int translatedWordOrder, int translatingWordOrder){
        return getThirdStringFromJSONParser(JSONResult,translatedWordOrder,translatingWordOrder,"data","japanese","word");
    }

    public static String getReading(String JSONResult, int translatedWordOrder, int translatingWordOrder){
        return getThirdStringFromJSONParser(JSONResult,translatedWordOrder,translatingWordOrder,"data","japanese","reading");
    }

    public static String getDefinition(String JSONResult, int translatedWordOrder, int englishDefinitionOrder){
        return getThirdArrayStringFromJSONParser(JSONResult,translatedWordOrder,englishDefinitionOrder,"data","senses","english_definitions");
    }

    public static String getTag(String JSONResult, int translatedWordOrder, int englishDefinitionOrder){
        return getThirdArrayStringFromJSONParser(JSONResult,translatedWordOrder,englishDefinitionOrder,"data","senses","tags");
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
