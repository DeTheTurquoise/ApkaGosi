package dmm.apkagosi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import dmm.apkagosi.R;

/**
 * Created by ddabrowa on 2017-04-05.
 */

public class LessonActivity extends GeneralActivity {

    private SeekBar lessonProgress;
    private TextView lessonNumber;
    private TextView symbolSign;
    private TextView symbolReading;
    private TextView symbolHelp;
    private String lessonPrefix;
    int lessonCounter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_screen);
        setLessonInfo();
        displayScreen();
        Log.i("Info","LessonActivity created");
    }

    private void setLessonInfo(){
        lessonProgress = (SeekBar) findViewById(R.id.progress);
        lessonNumber = (TextView) findViewById(R.id.lesson_number);
        symbolSign = (TextView) findViewById(R.id.symbol);
        symbolReading = (TextView) findViewById(R.id.reading);
        symbolHelp = (TextView) findViewById(R.id.help_text);

        Intent intent = getIntent();
        lessonCounter = intent.getIntExtra(screenInfo.LESSON_LIMIT, 1);
        lessonPrefix = intent.getStringExtra(screenInfo.LESSON_PREFIX);
        setPageCounter(lessonCounter);
        lessonProgress.setMax(lessonCounter - 1);

        Log.i("Param","Lesson limit = " + Integer.toString(lessonCounter));
        Log.i("Param","Lesson prefix = " + lessonPrefix);
    }


    @Override
    protected void displayScreen() {
        lessonNumber.setText(displayTextOnTextView(lessonPrefix,"ln"));
        symbolSign.setText(displayTextOnTextView(lessonPrefix,"ss"));
        symbolReading.setText(displayTextOnTextView(lessonPrefix,"sr"));
        symbolHelp.setText(displayTextOnTextView(lessonPrefix, "sh"));
        lessonProgress.setProgress(getCurrentPage()- 1);
        setPreviousAndNextButton();
    }
}
