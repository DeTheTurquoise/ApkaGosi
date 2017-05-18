package dmm.apkagosi.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

import dmm.apkagosi.R;
import dmm.apkagosi.network.NetworkUtils;

/**
 * Created by ddabrowa on 2017-05-17.
 */

public class TranslateActivity extends GeneralActivity {
    private EditText textToTranslate;
    private TextView translatedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.translate_screen);
        prepareScreen();
        Log.i("Info","TranslateActivity created");
    }

    private void prepareScreen(){
        textToTranslate = (EditText) findViewById(R.id.text_to_translate);
        translatedText = (TextView) findViewById(R.id.text_translated);
    }

    public void search(View view){
        String searchQuery = textToTranslate.getText().toString();
        URL jishoUrl = NetworkUtils.buildUrl(searchQuery);
        Log.i(logTags.RESULT,"URL = " + jishoUrl.toString());

        new JishoSearch().execute(jishoUrl);
    }

    public class JishoSearch extends AsyncTask<URL, Void, String> {

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
            if (searchResults != null && !searchResults.equals("")) {
                translatedText.setText(searchResults);
            }
        }

    }
}
