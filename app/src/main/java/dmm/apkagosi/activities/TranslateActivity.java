package dmm.apkagosi.activities;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
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

public class TranslateActivity extends GeneralActivity implements TranslationListAdapter.ListItemClickListener, LoaderManager.LoaderCallbacks<String> {
    private EditText textToTranslate;
    private TextView errorMessage;
    private ProgressBar waitingForConnection;
    private String japaneseTags[];
    private static final int NUMBER_OF_RESULT_TO_DISPLAY = 10;
    private int numberOfResultsToDisplay;
    private TranslationListAdapter translationListAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    private Toast tagsFromJisho;
    private String queryResponse = null;
    private URL jishoUrl;
    private static final String SEARCH_QUERY = "";
    private static final String SEARCH_RESULT = "";
    private View fakeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.translate_screen);
        prepareScreen();
        if (savedInstanceState != null){
                String tempUrl = savedInstanceState.getString(SEARCH_QUERY);
            jishoUrl = NetworkUtils.stringToUrl(tempUrl);
                queryResponse = savedInstanceState.getString(SEARCH_RESULT);
        }
        Log.i("Info","TranslateActivity created");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(SEARCH_RESULT,queryResponse);
        savedInstanceState.putString(SEARCH_QUERY,jishoUrl.toString());
    }

    private void prepareScreen(){
        textToTranslate = (EditText) findViewById(R.id.translate_text_to_translate);
        errorMessage = (TextView) findViewById(R.id.translate_error_message);
        waitingForConnection = (ProgressBar) findViewById(R.id.translate_wait_for_page_loading);
        recyclerView = (RecyclerView) findViewById(R.id.translation_list);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        translationListAdapter = new TranslationListAdapter(numberOfResultsToDisplay, this);
        textToTranslate.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    search(fakeView);
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * Method used to connect to the jisho
     * App performes it when user enters text
     */
    public void search(View view){
        String wordToSearch = textToTranslate.getText().toString();
        jishoUrl = NetworkUtils.buildUrl(wordToSearch);
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

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

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
            queryResponse = null;
            try {
                queryResponse = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return queryResponse;
        }

        @Override
        protected void onPostExecute(String queryResponse){
            waitingForConnection.setVisibility(View.INVISIBLE);
            if (queryResponse != null && !queryResponse.equals("")) {
                displaySearchResult();
                int numberOfResults = 0;
                try {
                    numberOfResults = JSONParser.getTranslationResultsNumber(queryResponse);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if( numberOfResults < NUMBER_OF_RESULT_TO_DISPLAY) {
                    numberOfResultsToDisplay = numberOfResults;
                }
                else {
                    numberOfResultsToDisplay = NUMBER_OF_RESULT_TO_DISPLAY;
                }
                String[] japaneseWord = JSONParser.getWords(queryResponse, numberOfResultsToDisplay);
                String[] japaneseReading = JSONParser.getReadings(queryResponse, numberOfResultsToDisplay);
                String[] japaneseDescription = JSONParser.getDefinitions(queryResponse, numberOfResultsToDisplay);
                japaneseTags = JSONParser.getTags(queryResponse,numberOfResultsToDisplay);
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
