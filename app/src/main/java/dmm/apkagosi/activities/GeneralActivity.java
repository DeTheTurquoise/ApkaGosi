package dmm.apkagosi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import dmm.apkagosi.R;
import dmm.apkagosi.screen.ScreenInfo;

/**
 * Created by ddabrowa on 2017-04-10.
 */

public class GeneralActivity extends AppCompatActivity{

    protected int pageCounter;
    protected int currentPage;
    protected String storyPrefix;
    protected String lessonPrefix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return selectItem(item);
    }

    protected void setRepeatableScreenValues(ScreenInfo info){
        pageCounter = info.getPageLimit();
        currentPage = 1;
    }

    protected void setNextPage() {
        if (currentPage < pageCounter) {
            currentPage = currentPage + 1;
        } else if (currentPage == pageCounter) {
            currentPage = 1;
        }
    }

    protected void setPreviousPage() {
        if (currentPage > 1) {
            currentPage = currentPage - 1;
        }
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
            startActivity(new Intent(this, MainActivity.class));
            return true;
        case R.id.menu_item_lesson:
            startActivity(new Intent(this, LessonActivity.class));
            return true;
        case R.id.menu_item_story:
            startActivity(new Intent(this, FluffActivity.class));
            return true;
        default:
            return super.onOptionsItemSelected(item);
    }
}
}
