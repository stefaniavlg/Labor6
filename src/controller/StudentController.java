package controller;

import observer.Observable;
import exception.FileDoesNotExistException;
import model.Course;
import model.Student;
import repository.CourseRepo;
import repository.StudentRepo;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class StudentController implements Observable {
    private CourseRepo courseRepo =  new CourseRepo("courses.json");
    private StudentRepo studentRepo = new StudentRepo("students.json");
    private long loggedInStudentId = -1;

    public void loadData() throws FileDoesNotExistException {
        try {
            courseRepo.loadFromJson();
            studentRepo.loadFromJson();
        } catch (IOException e) {
            throw new FileDoesNotExistException("Can't find storage file!");
        }
    }

    /**
     * Search in students repo for a student with specific firstName and lastName
     * @param firstName
     * @param lastName
     * @return (bool) - true if logged in false if not
     */
    public boolean checkLogin(String firstName, String lastName){
        for(Student s:studentRepo.findAll()){
            if(s.getFirstName().equalsIgnoreCase(firstName) &&
                    s.getLastName().equalsIgnoreCase(lastName)){
                loggedInStudentId = s.getStudentId();
                return true;
            }
        }
        return false;
    }

    public Iterable<Course> getAllCourses(){
        return courseRepo.findAll();
    }



    /**
     * Search for courses with available places
     * @return List<Course> list of available courses
     */
    public List<Course> availableCourses(){
        List<Course> available = new ArrayList<>();
        for(Course course:courseRepo.findAll()){

            if(course.getMaxEnrollment()>course.getStudentsEnrolled().size())
                available.add(course);
        }
        return available;
    }



    /**
     * Sets file for saving courseRepo data if it is empty
     * @param fileName
     * @return true if change has been made and false if not
     */
    public boolean setCourseRepoFile(String fileName){
        if(courseRepo.isEmpty()){
            courseRepo = new CourseRepo(fileName);
            return true;
        }
        return false;
    }

    /**
     * Sets file for saving studentRepo data if it is empty
     * @param fileName
     * @return true if change has been made and false if not
     */
    public boolean setStudentRepoFile(String fileName){
        if(studentRepo.isEmpty()){
            studentRepo = new StudentRepo(fileName);
            return true;
        }
        return false;
    }


    /**
     * Returs students enrolled for a specific course
     * @param courseName
     * @return (List<Student>)
     */
    public List<Student> enrolledStudents(String courseName){
        for(Course course:courseRepo.findAll()){
            if(courseName.equals(course.getName()))
            {
                List<Long> idsList = course.getStudentsEnrolled();
                List<Student> studentList = new ArrayList<>();
                for(Long id:idsList){
                    studentList.add(studentRepo.findOne(id));
                }
                return studentList;
            }
        }
        return null;
    }

    /**
     * Checks if a student is already enrolled to a specified course
     * @param courseId
     * @return (boolean) true if student is already enrolled and false if not
     */
    private boolean isEnrolled(long courseId){
        for(Long entry:studentRepo.findOne(loggedInStudentId).getEnrolledCourses()){
            if(entry==courseId)
                return true;
        }
        return false;
    }

    /**
     * Checks if a course is available
     * @param courseId
     * @return (boolean) true if course is available and false if not
     */
    private  boolean isCourseAvailable(long courseId){
        List<Course> filteredList = availableCourses().stream().filter(course -> course.getCourseId() == courseId).collect(Collectors.toList());
        if(filteredList.size() == 1)
            return true;
        else
            return false;
    }

    /**
     * Checking if a student can join a specified course and if it's possible he join
     * @param courseId
     * @return  -1 if student is already enrolled to this course
     *          -2 if student pass over 30 credits with this course
     *          -3 if course is not available
     *          0  if student was successfully enrolled
     */
    public int joinCourse(long courseId){
        if(isEnrolled(courseId))
            return -1;
        if(!isCourseAvailable(courseId))
            return -3;


        Student loggedInStundent = studentRepo.findOne(loggedInStudentId);
        Course selectedCourse = courseRepo.findOne(courseId);

        if(selectedCourse.getMaxEnrollment()>selectedCourse.getStudentsEnrolled().size()){

            if(loggedInStundent.getTotalCredits()+selectedCourse.getCredits()<=30){
                List<Long> newStundentCourseslist = loggedInStundent.getEnrolledCourses();
                newStundentCourseslist.add(selectedCourse.getCourseId());
                studentRepo.update(new Student(
                        loggedInStudentId,
                        loggedInStundent.getFirstName(),
                        loggedInStundent.getLastName(),
                        loggedInStundent.getTotalCredits()+selectedCourse.getCredits(),
                        newStundentCourseslist
                ));
                List<Long> newCourseStudentsList = selectedCourse.getStudentsEnrolled();
                newCourseStudentsList.add(loggedInStundent.getStudentId());
                selectedCourse.setStudentsEnrolled(newCourseStudentsList);
                courseRepo.update(selectedCourse);

                try {
                    courseRepo.writeToJson();
                    studentRepo.writeToJson();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else
                return -2;
        }
        return 0;
    }

    public List<Student> sortedByName(){
        /**
         * Creates a list of students which will be returned sorted alphabetically
         * @returns sorted list of students
         */
        List<Student> studentList = new ArrayList<>();
        for(Student s:studentRepo.findAll()) {
            studentList.add(s);
        }
        Collections.sort(studentList, studentRepo.ByName);
        return studentList;
    }

    public Integer coursesNumber(){
        return courseRepo.getCourseNumber();
    }

    public void setCourseRepo(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    public void setStudentRepo(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public void setLoggedInStudentId(long loggedInStudentId) {
        this.loggedInStudentId = loggedInStudentId;
    }

    /**
     * Update data from controllers when is notified by observer
     */
    @Override
    public void update() {
        try {
            loadData();
        } catch (FileDoesNotExistException e) {
            e.printStackTrace();
        }
    }

    public boolean isSomeoneLoggedIn(){
        return loggedInStudentId!=-1;
    }

    /**
     * Resets logged in teacher
     */
    public void resetLogIn(){
        loggedInStudentId = -1;
    }
}
