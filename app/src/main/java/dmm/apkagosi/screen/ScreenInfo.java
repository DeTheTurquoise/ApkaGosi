package dmm.apkagosi.screen;

/**
 * Created by ddabrowa on 2017-04-24.
 */

public class ScreenInfo {

    public ScreenInfo() {
    }


    private String storyPrefix;
    private int storyPageLimit;

    private String lessonNumber;
    private int lessonPageLimit;


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
