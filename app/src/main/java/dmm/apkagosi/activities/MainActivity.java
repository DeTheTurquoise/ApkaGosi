package dmm.apkagosi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends GeneralActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        Intent profileIntent = new Intent(this, ProfileActivity.class);
        setStoryScreen(profileIntent);
        setLessonScreen(profileIntent);
        Log.i("Info","MainActivity created");
        startActivity(profileIntent);
//        Intent profileIntent = new Intent(this, ProfileActivity.class);
//        startActivity(profileIntent);
    }

    private void setStoryScreen(Intent intent){
        intent.putExtra(screenInfo.LESSON_PREFIX,"l1");
        intent.putExtra(screenInfo.LESSON_LIMIT,3);
    }

    private void setLessonScreen(Intent intent){
        intent.putExtra(screenInfo.LESSON_PREFIX,"l1");
        intent.putExtra(screenInfo.LESSON_LIMIT,3);
    }

}
