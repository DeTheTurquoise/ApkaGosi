package dmm.apkagosi.network;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

/**
 * Created by ddabrowa on 2017-05-17.
 */

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

  //  @Override
    protected void onPostExecute(String searchResults, TextView displayResults) {
        if (searchResults != null && !searchResults.equals("")) {
            displayResults.setText(searchResults);
        }
    }

}
