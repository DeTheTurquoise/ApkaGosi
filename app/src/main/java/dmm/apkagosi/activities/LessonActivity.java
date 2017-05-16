package dmm.apkagosi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

        setMaxProgressBarValue(getPageCounter());
        displayLessonScreen();
        Log.i("Info","LessonActivity created");
    }

    public void previousLessonPage(View view){
        setPreviousPage();
        displayLessonScreen();
    }

    public void nextLessonPage(View view){
        setNextPage();
        displayLessonScreen();
    }

    protected void setMaxProgressBarValue(int max){
        SeekBar lessonProgress = (SeekBar) findViewById(R.id.progress);
        lessonProgress.setMax(max - 1);
    }


    private void displayLessonScreen() {
        Intent intent = getIntent();
        setPageCounter(intent.getIntExtra(screenInfo.LESSON_LIMIT, 1));

        TextView lessonNumber = (TextView) findViewById(R.id.lesson_number);
        TextView symbolSign = (TextView) findViewById(R.id.symbol);
        TextView symbolReading = (TextView) findViewById(R.id.reading);
        TextView symbolHelp = (TextView) findViewById(R.id.help_text);
        SeekBar lessonProgress = (SeekBar) findViewById(R.id.progress);

        String lessonPrefix = intent.getStringExtra(screenInfo.LESSON_PREFIX);

        lessonNumber.setText(displayTextOnTextView(lessonPrefix,"ln"));
        symbolSign.setText(displayTextOnTextView(lessonPrefix,"ss"));
        symbolReading.setText(displayTextOnTextView(lessonPrefix,"sr"));
        symbolHelp.setText(displayTextOnTextView(lessonPrefix, "sh"));
        lessonProgress.setProgress(getCurrentPage() - 1);

        setPreviousAndNextButton();
    }
}
