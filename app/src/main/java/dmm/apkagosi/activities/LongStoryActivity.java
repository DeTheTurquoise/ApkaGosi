package dmm.apkagosi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import dmm.apkagosi.R;

/**
 * Created by ddabrowa on 2017-05-16.
 */

public class LongStoryActivity extends GeneralActivity {

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_screen);
        Log.i("Info","LongStoryActivity created");
        prepareScreen();
        displayScreen();
    }

    private void prepareScreen(){
        Intent intent = getIntent();
        text = (TextView) findViewById(R.id.long_text);
        prefix = intent.getStringExtra(screenInfo.LONG_TEXT);
        Log.i("Param","Text prefix = " + prefix);
    }

    @Override
    protected void displayScreen() {
        text.setText(displayTextOnTextView("",prefix));
    }
}
