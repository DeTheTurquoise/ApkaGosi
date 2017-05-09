package dmm.apkagosi.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import dmm.apkagosi.R;

/**
 * Created by ddabrowa on 2017-02-26.
 */

public class FluffActivity extends GeneralActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fluff_screen);

        displayStoryScreen();
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
        setPageCounter(screenInfo.getStoryPageLimit());
        TextView storyText = (TextView) findViewById(R.id.fluff_text);
        ImageView storyImage = (ImageView) findViewById(R.id.fluff_image);

        storyText.setText(displayTextOnTextView(screenInfo.getStoryPrefix(),"t"));
        storyImage.setImageResource(displayImageOnImageView(screenInfo.getStoryPrefix(), "i"));

        setPreviousAndNextButton();
    }
}
