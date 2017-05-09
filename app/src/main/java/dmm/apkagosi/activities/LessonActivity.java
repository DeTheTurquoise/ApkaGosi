package dmm.apkagosi.activities;

import android.os.Bundle;
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
        setPageCounter(screenInfo.getLessonPageLimit());

        TextView lessonNumber = (TextView) findViewById(R.id.lesson_number);
        TextView symbolSign = (TextView) findViewById(R.id.symbol);
        TextView symbolReading = (TextView) findViewById(R.id.reading);
        TextView symbolHelp = (TextView) findViewById(R.id.help_text);
        SeekBar lessonProgress = (SeekBar) findViewById(R.id.progress);

        lessonNumber.setText(displayTextOnTextView(screenInfo.getLessonNumber(),"ln"));
        symbolSign.setText(displayTextOnTextView(screenInfo.getLessonNumber(),"ss"));
        symbolReading.setText(displayTextOnTextView(screenInfo.getLessonNumber(),"sr"));
        symbolHelp.setText(displayTextOnTextView(screenInfo.getLessonNumber(), "sh"));
        lessonProgress.setProgress(getCurrentPage() - 1);

        setPreviousAndNextButton();
    }
}
