package dmm.apkagosi.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import dmm.apkagosi.R;
import dmm.apkagosi.screen.ScreenInfo;

/**
 * Created by ddabrowa on 2017-02-26.
 */

public class FluffActivity extends GeneralActivity {

    ScreenInfo screenInfo = new ScreenInfo();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fluff_screen);

        setKubusStory();

        startStoryScreen(screenInfo);
        displayStoryScreen();
    }

    private void setKubusStory(){
        screenInfo.setPageLimit(5);
        screenInfo.setStoryPrefix("kp");
    }

    private void startStoryScreen(ScreenInfo info){
        storyPrefix = info.getStoryPrefix();
        setRepeatableScreenValues(info);
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
        TextView storyText = (TextView) findViewById(R.id.fluff_text);
        ImageView storyImage = (ImageView) findViewById(R.id.fluff_image);

        storyText.setText(displayTextOnTextView(storyPrefix,"t"));
        storyImage.setImageResource(displayImageOnImageView(storyPrefix, "i"));

        setPreviousAndNextButton();
    }
}
