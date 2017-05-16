package dmm.apkagosi.screen;

/**
 * Created by ddabrowa on 2017-04-24.
 */

public class ScreenInfo {

    public ScreenInfo() {
    }

    public static final String LESSON_PREFIX = "dmm.apkagosi.LESSON_PREFIX";
    public static final String LESSON_LIMIT = "dmm.apkagosi.LESSON_LIMIT";
    public static final String STORY_PREFIX = "dmm.apkagosi.STORY_PREFIX";
    public static final String STORY_LIMIT = "dmm.apkagosi.STORY_LIMIT";
    public static final String LONG_TEXT = "dmm.apkagosi.LONG_TEXT";


    private String storyPrefix ="";
    private int storyPageLimit = 1;

    private String lessonNumber = "";
    private int lessonPageLimit = 1;


    public String getStoryPrefix() {
        return storyPrefix;
    }

    public void setStoryPrefix(String storyPrefix) {
        this.storyPrefix = storyPrefix;
    }

    public int getStoryPageLimit() {
        return storyPageLimit;
    }

    public void setStoryPageLimit(int storyPageLimit) {
        this.storyPageLimit = storyPageLimit;
    }

    public String getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(String lessonNumber) {
        this.lessonNumber = lessonNumber;
    }


    public int getLessonPageLimit() {
        return lessonPageLimit;
    }

    public void setLessonPageLimit(int lessonPageLimit) {
        this.lessonPageLimit = lessonPageLimit;
    }
}
