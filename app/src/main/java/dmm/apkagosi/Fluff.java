package dmm.apkagosi;

import android.content.Intent;
import android.content.res.Resources;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ddabrowa on 2017-02-26.
 */

public class Fluff extends AppCompatActivity {
    int pageCounter;
    int currentPage;
    String storyPrefix;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fluff_screen);


        Intent intent = getIntent();
        storyPrefix = intent.getStringExtra(StoriesActivity.STORY_PREFIX);
        pageCounter = intent.getIntExtra(StoriesActivity.PAGE_COUNTER,1);
        currentPage = 1;

        displayScreen();
    }

    public void nextPage(View view){
        if (currentPage < pageCounter){
            currentPage = currentPage + 1;
        }
        else if (currentPage == pageCounter){
            currentPage = 1;
        }
        displayScreen();
    }

    public void previousPage(View view){
        if (currentPage > 1){
            currentPage = currentPage - 1;
        }
        else if (currentPage == 1){
            currentPage = pageCounter;
        }
        displayScreen();
    }

    private void displayScreen(){
        String resourcePath;
        String textToDisplay;
        int resourceId;

        TextView textView = (TextView) findViewById(R.id.fluff_text);
        ImageView imageView = (ImageView) findViewById(R.id.fluff_image);

        resourcePath = storyPrefix + "t" + Integer.toString(currentPage);
        resourceId = getResources().getIdentifier(resourcePath, "string", getPackageName());
        textToDisplay = getString(resourceId);
        textView.setText(textToDisplay);

        resourcePath = storyPrefix + "i" + Integer.toString(currentPage);
        resourceId = getResources().getIdentifier(resourcePath, "mipmap", getPackageName());
        imageView.setImageResource(resourceId);

    }

}
