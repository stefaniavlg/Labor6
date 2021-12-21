package controller;

import observer.Observable;
import exception.FileDoesNotExistException;
import model.Course;
import model.Student;
import model.Teacher;
import repository.CourseRepo;
import repository.StudentRepo;
import repository.TeacherRepo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TeacherController implements Observable {
    private CourseRepo courseRepo =  new CourseRepo("courses.json");
    private StudentRepo studentRepo = new StudentRepo("students.json");
    private TeacherRepo teacherRepo = new TeacherRepo("teachers.json");
    private long loggedInTeacherId = -1;

    public void loadData() throws FileDoesNotExistException {
        try {
            courseRepo.loadFromJson();
            studentRepo.loadFromJson();
            teacherRepo.loadFromJson();
        } catch (IOException e) {
            throw new FileDoesNotExistException("Can't find storage file!");
        }
    }

    /**
     * Search in teachers repo for a teacher with specific firstName and lastName
     * @param firstName-
     * @param lastName  -
     * @return (bool) - true if logged in false if not
     */
    public boolean checkLogin(String firstName, String lastName){
        for(Teacher t:teacherRepo.findAll()){
            if(t.getFirstName().equalsIgnoreCase(firstName) &&
                    t.getLastName().equalsIgnoreCase(lastName)){
                loggedInTeacherId = t.getId();
                return true;
            }
        }
        return false;
    }

    public List<Course> getTeacherCourses(long id){
        /**
         * Returns a list of courses a specific teacher teaches
         * @return a list of courses
         */
        List<Long> courseIds =  teacherRepo.findOne(id).getCourses();
        List<Course> coursesList = new ArrayList<>();
        for(Long entryId:courseIds){
            coursesList.add(courseRepo.findOne(entryId));
        }
        return coursesList;
    }



    /**
     * Sets file for saving courseRepo data if it is empty
     * @param fileName -
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
     * @param fileName -
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
     * Sets file for saving teacherRepo data if it is empty
     * @param fileName -
     * @return true if change has been made and false if not
     */
    public boolean setTeacherRepoFile(String fileName){
        if(teacherRepo.isEmpty()){
            teacherRepo = new TeacherRepo(fileName);
            return true;
        }
        return false;
    }

    public int coursesNumber(){
        return courseRepo.getCourseNumber();
    }

    public Iterable<Course> getAllCourses(){
        return courseRepo.findAll();
    }

    public int ownCoursesNumber(){
        /**
         * Finds the number of courses a teacher currently teaches
         * @return the number of courses
         */
        List<Long> courseIds =  teacherRepo.findOne(loggedInTeacherId).getCourses();
        int countOwn = 0;
        for(Long entryId:courseIds){
            countOwn += 1;
        }
        return countOwn;
    }

    public List<Course> getOwnCourses(){
        /**
         * Finds the courses the logged-in teacher teaches
         * @return a list of course id's
         */
        List<Long> courseIds =  teacherRepo.findOne(loggedInTeacherId).getCourses();
        List<Course> coursesList = new ArrayList<>();
        for(Long entryId:courseIds){
            coursesList.add(courseRepo.findOne(entryId));
        }
        return coursesList;
    }
    /**
     * If course id owned by logged in teacher delete course and returns true and returns false if not
     * @return bool
     */
    public boolean deleteOwnCourse(long id){

        if(teacherRepo.findOne(loggedInTeacherId).getCourses().contains(id)){

            List<Long> courseIdList = teacherRepo
                    .findOne(loggedInTeacherId)
                    .getCourses()
                    .stream()
                    .filter(courseId->courseId!=id)
                    .collect(Collectors.toList());
            teacherRepo.findOne(loggedInTeacherId).setCourses(courseIdList);


            for(Student student:studentRepo.findAll()){
                List<Long> newCourseList =  student.getEnrolledCourses()
                        .stream()
                        .filter(courseId->courseId!=id)
                        .collect(Collectors.toList());
                student.setEnrolledCourses(newCourseList);
            }


            courseRepo.delete(id);


            try {
                courseRepo.writeToJson();
                teacherRepo.writeToJson();
                studentRepo.writeToJson();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
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
        return loggedInTeacherId!=-1;
    }

    /**
     * Resets logged in teacher
     */
    public void resetLogIn(){
        loggedInTeacherId = -1;
    }

    /**
     * Returns a list with course names and names of enrolled students
     * @return List<String>
     */
    public List<String> getAllCoursesAndStudentsAsString(){
        List<String> stringList = new ArrayList<>();
        for(Course course:courseRepo.findAll()){
            List<Student> students = new ArrayList();
            course.getStudentsEnrolled().forEach(studentId->students.add(studentRepo.findOne(studentId)));
            StringBuilder sb = new StringBuilder();
            sb.append(course.getName()+": ");
            students.forEach(student -> sb.append(student.getFirstName()+" "+ student.getLastName()+", "));
            stringList.add(sb.toString());
        }
        return stringList;
    }
}
