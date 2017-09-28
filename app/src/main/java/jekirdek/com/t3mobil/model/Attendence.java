package jekirdek.com.t3mobil.model;

/**
 * Created by cem
 */
public class Attendence {

    private String id;
    private String scheduleId;
    private String instructorId;
    private String studentId;
    private String presence;
    private String studentNameSurname;
    private String lessonDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPresence() {
        return presence;
    }

    public void setPresence(String presence) {
        this.presence = presence;
    }

    public String getStudentNameSurname() {
        return studentNameSurname;
    }

    public void setStudentNameSurname(String studentNameSurname) {
        this.studentNameSurname = studentNameSurname;
    }

    public String getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(String lessonDate) {
        this.lessonDate = lessonDate;
    }

    @Override
    public String toString() {
        return "Attendence{" +
                "id=" + id +
                ", scheduleId=" + scheduleId +
                ", instructorId=" + instructorId +
                ", studentId=" + studentId +
                ", presence=" + presence +
                ", studentNameSurname='" + studentNameSurname + '\'' +
                ", lessonDate='" + lessonDate + '\'' +
                '}';
    }
}
