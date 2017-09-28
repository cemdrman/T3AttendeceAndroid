package jekirdek.com.t3mobil.model;

/**
 * Created by cem on 6.07.2017.
 */
public class Lesson {

    private int id;
    private String lessonName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", lessonName='" + lessonName + '\'' +
                '}';
    }
}
