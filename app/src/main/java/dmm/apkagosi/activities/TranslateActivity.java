package dmm.apkagosi.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

import dmm.apkagosi.R;
import dmm.apkagosi.network.JSONParser;
import dmm.apkagosi.network.NetworkUtils;

/**
 * Created by ddabrowa on 2017-05-17.
 */

public class TranslateActivity extends GeneralActivity {
    private EditText textToTranslate;
    private TextView translatedText;
    private TextView errorMessage;
    private ProgressBar waitingForConnection;
    private String japaneseWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.translate_screen);
        prepareScreen();
        Log.i("Info","TranslateActivity created");
    }

    private void prepareScreen(){
        textToTranslate = (EditText) findViewById(R.id.translate_text_to_translate);
        translatedText = (TextView) findViewById(R.id.translate_text_translated);
        errorMessage = (TextView) findViewById(R.id.translate_error_message);
        waitingForConnection = (ProgressBar) findViewById(R.id.translate_wait_for_page_loading);
    }

    /**
     * Method used by button to connect to the jisho
     * @param view - to use button method needs a parameter view, but it isn't used for search
     */
    public void search(View view){
        String searchQuery = textToTranslate.getText().toString();
        URL jishoUrl = NetworkUtils.buildUrl(searchQuery);
        Log.i(logTags.RESULT,"URL = " + jishoUrl.toString());

        new JishoSearch().execute(jishoUrl);
    }

    /**
     * Method used to display result of search - error and result messages are displayed alternatively
     */
    private void displaySearchResult(){
        errorMessage.setVisibility(View.INVISIBLE);
        translatedText.setVisibility(View.VISIBLE);
    }
    /**
     * Method used to display error - error and result messages are displayed alternatively
     */
    private void displayErrorMessage(){
        translatedText.setVisibility(View.INVISIBLE);
        errorMessage.setVisibility(View.VISIBLE);
    }

    public class JishoSearch extends AsyncTask<URL, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            waitingForConnection.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String searchResults = null;
            try {
                searchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return searchResults;
        }

        @Override
        protected void onPostExecute(String searchResults) {
            waitingForConnection.setVisibility(View.INVISIBLE);
            if (searchResults != null && !searchResults.equals("")) {
                displaySearchResult();
                try {
                    japaneseWord = JSONParser.getWord(searchResults);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                translatedText.setText(searchResults);
                Log.i("temp", japaneseWord);
            }
            else {
                displayErrorMessage();
            }
        }

    }
}
