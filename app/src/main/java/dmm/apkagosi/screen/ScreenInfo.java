package dmm.apkagosi.screen;

/**
 * Created by ddabrowa on 2017-04-24.
 */

public class ScreenInfo {

    public ScreenInfo() {
    }

    public String getStoryPrefix() {
        return storyPrefix;
    }

    public void setStoryPrefix(String storyPrefix) {
        this.storyPrefix = storyPrefix;
    }

    public int getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(int pageLimit) {
        this.pageLimit = pageLimit;
    }

    private String storyPrefix;
    private int pageLimit;

    public String getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(String lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    private String lessonNumber;
}
