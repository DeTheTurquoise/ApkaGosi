package dmm.apkagosi.network;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by ddabrowa on 2017-05-17.
 */

public class NetworkUtils {


    final static String JISHO_BASE_URL = "https://http://jisho.org/search/";

    /**
     * Builds the URL used to search in jisho translator
     *
     * @param textToTranslate The word in english to translate
     * @return The URL to use to query the jisho
     */
    public static URL buildUrl(String textToTranslate) {
        Uri builtUri = Uri.parse(JISHO_BASE_URL).buildUpon().appendQueryParameter("", textToTranslate).build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
