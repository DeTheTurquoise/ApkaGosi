package dmm.apkagosi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import dmm.apkagosi.R;

/**
 * Created by ddabrowa on 2017-02-26.
 */

public class StoryActivity extends GeneralActivity {

    private TextView storyText;
    private ImageView storyImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_screen);
        prepareScreen();
        displayScreen();
        Log.i("Info","StoryActivity created");
    }

    /**
     * prepareScreen() sets general parameters to activity
     */
    private void prepareScreen(){
        Intent intent = getIntent();
        pageCounter = intent.getIntExtra(MainActivity.STORY_LIMIT,1);
        prefix = intent.getStringExtra(MainActivity.STORY_PREFIX);

        storyText = (TextView) findViewById(R.id.fluff_text);
        storyImage = (ImageView) findViewById(R.id.fluff_image);

        Log.i(logTags.PARAMETERS,"Story pages = " + Integer.toString(pageCounter));
        Log.i(logTags.PARAMETERS,"Story prefix = " + prefix);
    }

    @Override
    protected void displayScreen() {
        storyText.setText(displayTextOnTextView(prefix,"t"));
        storyImage.setImageResource(displayImageOnImageView(prefix, "i"));
        setPreviousAndNextButton();
    }
}
