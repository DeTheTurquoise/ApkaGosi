package dmm.apkagosi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity{

    public static final String LESSON_PREFIX = "dmm.apkagosi.LESSON_PREFIX";
    public static final String LESSON_LIMIT = "dmm.apkagosi.LESSON_LIMIT";
    public static final String STORY_PREFIX = "dmm.apkagosi.STORY_PREFIX";
    public static final String STORY_LIMIT = "dmm.apkagosi.STORY_LIMIT";
    public static final String LONG_TEXT = "dmm.apkagosi.LONG_TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent profileIntent = new Intent(this, ProfileActivity.class);
        Log.i("Info","MainActivity created");
        startActivity(profileIntent);
    }


}
