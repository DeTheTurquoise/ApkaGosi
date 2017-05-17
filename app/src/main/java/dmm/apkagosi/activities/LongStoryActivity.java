package dmm.apkagosi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import dmm.apkagosi.R;

/**
 * Created by ddabrowa on 2017-05-16.
 */

public class LongStoryActivity extends GeneralActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_screen);
        Log.i("Info","LongStoryActivity created");
        displayScreen();
    }

    @Override
    protected void displayScreen() {
        Intent intent = getIntent();
        TextView text = (TextView) findViewById(R.id.long_text);
        String textId = intent.getStringExtra(screenInfo.LONG_TEXT);

        Log.i("Param","Text prefix = " + textId);

        text.setText(displayTextOnTextView("",textId));
    }
}
