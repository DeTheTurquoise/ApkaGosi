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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_screen);
        displayScreen();
        Log.i("Info","StoryActivity created");
    }

    @Override
    protected void displayScreen() {
        Intent intent = getIntent();
        int pageCounter = intent.getIntExtra(screenInfo.STORY_LIMIT,1);
        setPageCounter(pageCounter);
        String storyPrefix = intent.getStringExtra(screenInfo.STORY_PREFIX);

        Log.i("Param","Lesson limit = " + Integer.toString(pageCounter));
        Log.i("Param","Lesson prefix = " + storyPrefix);

        TextView storyText = (TextView) findViewById(R.id.fluff_text);
        ImageView storyImage = (ImageView) findViewById(R.id.fluff_image);

        storyText.setText(displayTextOnTextView(storyPrefix,"t"));
        storyImage.setImageResource(displayImageOnImageView(storyPrefix, "i"));

        setPreviousAndNextButton();
    }
}
