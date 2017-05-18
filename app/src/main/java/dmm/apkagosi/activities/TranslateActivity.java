package dmm.apkagosi.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.net.URL;

import dmm.apkagosi.R;
import dmm.apkagosi.network.JishoSearch;
import dmm.apkagosi.network.NetworkUtils;

/**
 * Created by ddabrowa on 2017-05-17.
 */

public class TranslateActivity extends GeneralActivity {
//    private EditText textToTranslate = (EditText) findViewById(R.id.text_to_translate);
//    private TextView translatedText = (TextView) findViewById(R.id.text_translated);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.translate_screen);
        Log.i("Info","TranslateActivity created");
    }

    public void search(View view){
        EditText textToTranslate = (EditText) findViewById(R.id.text_to_translate);
        String searchQuery = textToTranslate.getText().toString();
        URL jishoUrl = NetworkUtils.buildUrl(searchQuery);

        new JishoSearch().execute(jishoUrl);
    }
}
