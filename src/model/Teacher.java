package model;

import java.util.List;

/**
 * teacher model class
 */
public class Teacher extends Person{
    private long id;
    private List<Long> courses;


    public Teacher(long id,String firstName, String lastName, List<Long> courses) {
        super(firstName, lastName);
        this.id = id;
        this.courses = courses;
    }
    public Teacher(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Long> getCourses() {
        return courses;
    }

    public void setCourses(List<Long> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Teacher: " +
                "Id: " + id +
                ", First name: " + getFirstName()+
                ", Last name: " + getLastName() +
                ", courses: " + courses;
    }
}

