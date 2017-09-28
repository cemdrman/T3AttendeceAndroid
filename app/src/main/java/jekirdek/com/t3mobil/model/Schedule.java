package jekirdek.com.t3mobil.model;

/**
 * Created by cem on 6.07.2017.
 */
public class Schedule {

    private int id;
    private int lessonId;
    private int deneYapId;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public int getDeneYapId() {
        return deneYapId;
    }

    public void setDeneYapId(int deneYapId) {
        this.deneYapId = deneYapId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", lessonId=" + lessonId +
                ", deneYapId=" + deneYapId +
                ", date='" + date + '\'' +
                '}';
    }
}
