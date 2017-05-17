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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_screen);


        displayScreen();
        Log.i("Info","LessonActivity created");
    }

    protected void setMaxProgressBarValue(int max){
        SeekBar lessonProgress = (SeekBar) findViewById(R.id.progress);
        lessonProgress.setMax(max - 1);
    }


    @Override
    protected void displayScreen() {
        Intent intent = getIntent();
        int lessonCounter = intent.getIntExtra(screenInfo.LESSON_LIMIT, 1);
        String lessonPrefix = intent.getStringExtra(screenInfo.LESSON_PREFIX);

        Log.i("Param","Lesson limit = " + Integer.toString(lessonCounter));
        Log.i("Param","Lesson prefix = " + lessonPrefix);

        setPageCounter(lessonCounter);
        setMaxProgressBarValue(getPageCounter());

        TextView lessonNumber = (TextView) findViewById(R.id.lesson_number);
        TextView symbolSign = (TextView) findViewById(R.id.symbol);
        TextView symbolReading = (TextView) findViewById(R.id.reading);
        TextView symbolHelp = (TextView) findViewById(R.id.help_text);
        SeekBar lessonProgress = (SeekBar) findViewById(R.id.progress);

        lessonNumber.setText(displayTextOnTextView(lessonPrefix,"ln"));
        symbolSign.setText(displayTextOnTextView(lessonPrefix,"ss"));
        symbolReading.setText(displayTextOnTextView(lessonPrefix,"sr"));
        symbolHelp.setText(displayTextOnTextView(lessonPrefix, "sh"));
        lessonProgress.setProgress(getCurrentPage() - 1);

        setPreviousAndNextButton();
    }
}
