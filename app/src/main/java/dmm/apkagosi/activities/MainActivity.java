package dmm.apkagosi.activities;

import android.os.Bundle;

import dmm.apkagosi.R;

public class MainActivity extends GeneralActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStoryScreen();
        setLessonScreen();
//        Intent intent = new Intent(this, ProfileActivity.class);
//        startActivity(intent);
    }

    private void setStoryScreen(){
        screenInfo.setStoryPrefix("kb");
        screenInfo.setStoryPageLimit(5);
    }

    private void setLessonScreen(){
        screenInfo.setLessonNumber("l1");
        screenInfo.setLessonPageLimit(3);
    }

}
