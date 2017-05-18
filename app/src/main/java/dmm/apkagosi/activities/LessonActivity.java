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
        pageCounter = intent.getIntExtra(MainActivity.LESSON_LIMIT, 1);
        prefix = intent.getStringExtra(MainActivity.LESSON_PREFIX);
        lessonProgress.setMax(pageCounter - 1);

        Log.i("Param","Lesson limit = " + Integer.toString(pageCounter));
        Log.i("Param","Lesson prefix = " + prefix);
    }


    @Override
    protected void displayScreen() {
        lessonNumber.setText(displayTextOnTextView(prefix,"ln"));
        symbolSign.setText(displayTextOnTextView(prefix,"ss"));
        symbolReading.setText(displayTextOnTextView(prefix,"sr"));
        symbolHelp.setText(displayTextOnTextView(prefix, "sh"));
        lessonProgress.setProgress(currentPage- 1);
        setPreviousAndNextButton();
    }
}
