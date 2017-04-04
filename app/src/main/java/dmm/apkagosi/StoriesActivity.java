package dmm.apkagosi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by ddabrowa on 2017-02-18.
 */

public class StoriesActivity extends AppCompatActivity {
    public final static String STORY_PREFIX = "dmm.apkagosi.StoriesActivity.STORY_PREFIX";
    public final static String PAGE_COUNTER = "dmm.apkagosi.StoriesActivity.PAGE_COUNTER";
 //   public final static String CURRENT_PAGE = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stories);
    }

    public void kubusPuchatek (View view){
        Intent intent = new Intent(this, Fluff.class);
        startActivity(intent);
        String prefix = "kp";
        intent.putExtra(STORY_PREFIX, prefix);
        int counter = 5;
        intent.putExtra(PAGE_COUNTER, counter);
//        int current = 1;
//        intent.putExtra(CURRENT_PAGE, current);
        startActivity(intent);
    }
}
