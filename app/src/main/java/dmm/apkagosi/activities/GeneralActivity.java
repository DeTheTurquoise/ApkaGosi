package dmm.apkagosi.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import dmm.apkagosi.R;
import dmm.apkagosi.utils.LogTags;

/**
 * Created by ddabrowa on 2017-04-10.
 */

public class GeneralActivity extends AppCompatActivity{

    protected LogTags logTags;
    protected int pageCounter = 1;
    protected int currentPage = 1;
    protected String prefix = "";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return selectItem(item);
    }


    /**
     * displayScreen() sets specific parameters for current instance of activity
     */
    protected void displayScreen(){}

    protected void nextPage(View view) {
        if (currentPage < pageCounter) {
            currentPage = currentPage + 1;
        }
        displayScreen();
    }

    protected void previousPage(View view) {
        if (currentPage > 1) {
            currentPage = currentPage - 1;
        }
        displayScreen();
    }

    protected String displayTextOnTextView(String prefix, String textType){
        String resourcePath = prefix + textType + Integer.toString(currentPage);
        return getString(getResources().getIdentifier(resourcePath, "string", getPackageName()));
    }


    protected int displayImageOnImageView(String prefix, String imageType){
        String resourcePath = prefix + imageType + Integer.toString(currentPage);
        return getResources().getIdentifier(resourcePath, "mipmap", getPackageName());
    }

    protected void setPreviousAndNextButton() {
        Button nextButton = (Button) findViewById(R.id.next);
        if (currentPage == pageCounter) {
            nextButton.setVisibility(View.INVISIBLE);
        }
        else{
            nextButton.setVisibility(View.VISIBLE);
        }

        Button previousButton = (Button) findViewById(R.id.previous);
        if (currentPage == 1) {
            previousButton.setVisibility(View.INVISIBLE);
        }
        else{
            previousButton.setVisibility(View.VISIBLE);
        }
    }

    public boolean selectItem(MenuItem item) {
    switch (item.getItemId()) {
        case R.id.menu_item_main:
            Intent profileActivityIntent = new Intent(this, ProfileActivity.class);
            Log.i(logTags.MENU,profileActivityIntent.getComponent().toString() + " selected");
            startActivity(profileActivityIntent);
            return true;
        case R.id.menu_item_lesson:
            Intent lessonActivityIntent = new Intent(this, LessonActivity.class);
            lessonActivityIntent.putExtra(MainActivity.LESSON_PREFIX,"l1");
            lessonActivityIntent.putExtra(MainActivity.LESSON_LIMIT,3);
            Log.i(logTags.MENU,lessonActivityIntent.getComponent().toString() + " selected");
            startActivity(lessonActivityIntent);
            return true;
        case R.id.menu_item_story:
            Intent storyActivityIntent = new Intent(this, StoryActivity.class);
            storyActivityIntent.putExtra(MainActivity.STORY_PREFIX,"kp");
            storyActivityIntent.putExtra(MainActivity.STORY_LIMIT,5);
            Log.i(logTags.MENU,storyActivityIntent.getComponent().toString() + " selected");
            startActivity(storyActivityIntent);
            return true;
        case R.id.menu_item_text:
            Intent textActivityIntent = new Intent(this, LongStoryActivity.class);
            textActivityIntent.putExtra(MainActivity.LONG_TEXT, "ct");
            Log.i(logTags.MENU,textActivityIntent.getComponent().toString() + " selected");
            startActivity(textActivityIntent);
            return true;
        case R.id.menu_item_translate:
            Intent translateActivityIntent = new Intent(this, TranslateActivity.class);
            Log.i(logTags.MENU,translateActivityIntent.getComponent().toString() + " selected");
            startActivity(translateActivityIntent);
            return true;
        case R.id.menu_item_extra:
            Intent extraActivityIntent = new Intent(this,ExtrasActivity.class);
            Log.i(logTags.MENU,extraActivityIntent.getComponent().toString() + " selected");
            startActivity(extraActivityIntent);
            return true;
        default:
            return super.onOptionsItemSelected(item);
    }
}
}
