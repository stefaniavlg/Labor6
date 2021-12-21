package model;

import java.util.List;

/**
 * student model class
 */
public class Student extends Person{
    private long studentId;
    private int totalCredits;
    private List<Long> enrolledCourses;

    public Student(long studentId, String firstName, String lastName, int totalCredits, List<Long> enrolledCourses) {
        super(firstName, lastName);
        this.studentId = studentId;
        this.totalCredits = totalCredits;
        this.enrolledCourses = enrolledCourses;
    }
    public Student(){}

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public List<Long> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<Long> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    @Override
    public String toString() {
        return getFirstName() +
                " " + getLastName();
    }
}
