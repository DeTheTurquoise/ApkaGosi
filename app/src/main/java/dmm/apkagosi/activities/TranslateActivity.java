package dmm.apkagosi.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

import dmm.apkagosi.R;
import dmm.apkagosi.network.JSONParser;
import dmm.apkagosi.network.NetworkUtils;
import dmm.apkagosi.recyclerView.TranslationListAdapter;

/**
 * Created by ddabrowa on 2017-05-17.
 */

public class TranslateActivity extends GeneralActivity implements TranslationListAdapter.ListItemClickListener{
    private EditText textToTranslate;
    private TextView errorMessage;
    private ProgressBar waitingForConnection;
    private String japaneseWord[];
    private String japaneseReading[];
    private String japaneseDescription[];
    private String japaneseTags[];
    private static final int NUMBER_OF_RESULT_TO_DISPLAY = 10;
    private int numberOfResultsToDisplay;
    private TranslationListAdapter translationListAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    private Toast tagsFromJisho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.translate_screen);
        prepareScreen();
        textToTranslate.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                search();
            }
        });
        Log.i("Info","TranslateActivity created");
    }

    private void prepareScreen(){
        textToTranslate = (EditText) findViewById(R.id.translate_text_to_translate);
        errorMessage = (TextView) findViewById(R.id.translate_error_message);
        waitingForConnection = (ProgressBar) findViewById(R.id.translate_wait_for_page_loading);
        recyclerView = (RecyclerView) findViewById(R.id.translation_list);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        translationListAdapter = new TranslationListAdapter(numberOfResultsToDisplay, this);
    }

    /**
     * Method used to connect to the jisho
     * App performes it when user enters text
     */
    public void search(){
        String searchQuery = textToTranslate.getText().toString();
        URL jishoUrl = NetworkUtils.buildUrl(searchQuery);
        Log.i(logTags.RESULT, "URL = " + jishoUrl.toString());
        new JishoSearch().execute(jishoUrl);
    }

    /**
     * Method used to display result of search - error and result messages are displayed alternatively
     */
    private void displaySearchResult(){
        errorMessage.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }
    /**
     * Method used to display error - error and result messages are displayed alternatively
     */
    private void displayErrorMessage(){
        recyclerView.setVisibility(View.INVISIBLE);
        errorMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        if (tagsFromJisho != null) {
            tagsFromJisho.cancel();
        }
        String messageTag = japaneseTags[clickedItemIndex];
        if (messageTag != ""){
            tagsFromJisho = Toast.makeText(this, messageTag, Toast.LENGTH_LONG);
            tagsFromJisho.show();
        }
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
        protected void onPostExecute(String searchResults){
            waitingForConnection.setVisibility(View.INVISIBLE);
            if (searchResults != null && !searchResults.equals("")) {
                displaySearchResult();
                int numberOfResults = 0;
                try {
                    numberOfResults = JSONParser.getTranslationResultsNumber(searchResults);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if( numberOfResults < NUMBER_OF_RESULT_TO_DISPLAY) {
                    numberOfResultsToDisplay = numberOfResults;
                }
                else {
                    numberOfResultsToDisplay = NUMBER_OF_RESULT_TO_DISPLAY;
                }
                japaneseWord = JSONParser.getWords(searchResults, numberOfResultsToDisplay);
                japaneseReading = JSONParser.getReadings(searchResults,numberOfResultsToDisplay);
                japaneseDescription = JSONParser.getDefinitions(searchResults,numberOfResultsToDisplay);
                japaneseTags = JSONParser.getTags(searchResults,numberOfResultsToDisplay);
                recyclerView.clearOnScrollListeners();
                translationListAdapter.setJishoWord(japaneseWord, japaneseReading, japaneseDescription, numberOfResultsToDisplay);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(translationListAdapter);

            }
            else {
                errorMessage.setText(R.string.trans_no_connection);
                displayErrorMessage();
            }
        }

    }
}
