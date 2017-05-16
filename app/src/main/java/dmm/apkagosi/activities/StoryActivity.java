package dmm.apkagosi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
        displayStoryScreen();
        Log.i("Info","StoryActivity created");
    }


    public void previousStoryPage(View view) {
        setPreviousPage();
        displayStoryScreen();
    }

    public void nextStoryPage(View view) {
        setNextPage();
        displayStoryScreen();
    }

    private void displayStoryScreen() {
        Intent intent = getIntent();
        setPageCounter(intent.getIntExtra(screenInfo.STORY_LIMIT,1));
        TextView storyText = (TextView) findViewById(R.id.fluff_text);
        ImageView storyImage = (ImageView) findViewById(R.id.fluff_image);

        String storyPrefix = intent.getStringExtra(screenInfo.STORY_PREFIX);
        storyText.setText(displayTextOnTextView(storyPrefix,"t"));
        storyImage.setImageResource(displayImageOnImageView(storyPrefix, "i"));

        setPreviousAndNextButton();
    }
}
