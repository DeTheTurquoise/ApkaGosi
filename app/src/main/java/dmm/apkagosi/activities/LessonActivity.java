package dmm.apkagosi.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import dmm.apkagosi.R;
import dmm.apkagosi.screen.ScreenInfo;

/**
 * Created by ddabrowa on 2017-04-05.
 */

public class LessonActivity extends GeneralActivity {

    ScreenInfo lessonInfo = new ScreenInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_screen);

        setLesson();

        startLessonScreen(lessonInfo);
        setMaxProgressBarValue(pageCounter);
        displayLessonScreen();
    }


    private void setLesson(){
        lessonInfo.setPageLimit(3);
        lessonInfo.setLessonNumber("l1");
    }

    protected void startLessonScreen(ScreenInfo info){
        lessonPrefix = info.getLessonNumber();
        setRepeatableScreenValues(info);
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

        TextView lessonNumber = (TextView) findViewById(R.id.lesson_number);
        TextView symbolSign = (TextView) findViewById(R.id.symbol);
        TextView symbolReading = (TextView) findViewById(R.id.reading);
        TextView symbolHelp = (TextView) findViewById(R.id.help_text);
        SeekBar lessonProgress = (SeekBar) findViewById(R.id.progress);

        lessonNumber.setText(displayTextOnTextView(lessonPrefix,"ln"));
        symbolSign.setText(displayTextOnTextView(lessonPrefix,"ss"));
        symbolReading.setText(displayTextOnTextView(lessonPrefix,"sr"));
        symbolHelp.setText(displayTextOnTextView(lessonPrefix, "sh"));
        lessonProgress.setProgress(currentPage - 1);

        setPreviousAndNextButton();
    }
}
