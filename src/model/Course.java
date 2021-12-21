package model;

import java.util.List;

/**
 * Course model class
 */
public class Course {
    private long courseId;
    private String name;
    private Long teacher;
    private int maxEnrollment;
    private List<Long> studentsEnrolled;
    private int credits;

    public Course(long courseId, String name, Long teacher, int maxEnrollment, List<Long> studentsEnrolled, int credits) {
        this.courseId = courseId;
        this.name = name;
        this.teacher = teacher;
        this.maxEnrollment = maxEnrollment;
        this.studentsEnrolled = studentsEnrolled;
        this.credits = credits;
    }

    public Course() {}

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTeacher() {
        return teacher;
    }

    public void setTeacher(Long teacher) {
        this.teacher = teacher;
    }

    public int getMaxEnrollment() {
        return maxEnrollment;
    }

    public void setMaxEnrollment(int maxEnrollment) {
        this.maxEnrollment = maxEnrollment;
    }

    public List<Long> getStudentsEnrolled() {
        return studentsEnrolled;
    }

    public void setStudentsEnrolled(List<Long> studentsEnrolled) {
        this.studentsEnrolled = studentsEnrolled;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return  name +
                " - available places: " +
                (maxEnrollment - studentsEnrolled.size());

    }
}
