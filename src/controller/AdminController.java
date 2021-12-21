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

public class AdminController implements Observable {
    private CourseRepo courseRepo =  new CourseRepo("courses.json");
    private StudentRepo studentRepo = new StudentRepo("students.json");
    private TeacherRepo teacherRepo = new TeacherRepo("teachers.json");

    public void loadData() throws FileDoesNotExistException {
        try {
            courseRepo.loadFromJson();
            studentRepo.loadFromJson();
            teacherRepo.loadFromJson();
        } catch (IOException e) {
            throw new FileDoesNotExistException("Can't find storage file!");
        }
    }

    public Iterable<Student> getAllStudents(){
        return studentRepo.findAll();
    }
    public Iterable<Teacher> getAllTeachers(){
        return teacherRepo.findAll();
    }
    public Iterable<Course> getAllCourses(){
        return courseRepo.findAll();
    }


    public boolean existsStudent(String firstName, String lastName){
        /**
         * Verifies the existence of the provided student
         * @param1 first name of the Student
         * @param2 last name of the Student
         * @return true if the student exists, false otherwise
         */
        for(Student s:studentRepo.findAll()){
            if(s.getFirstName().equals(firstName) && s.getLastName().equals(lastName)){
                return true;
            }
        }
        return false;
    }

    public boolean existsTeacher(String firstName, String lastName){
        /**
         * Verifies the existence of the provided teacher
         * @param1 first name of the teacher
         * @param2 last name of the teacher
         * @return true if the teacher exists, false otherwise
         */
        for(Teacher t:teacherRepo.findAll()){
            if(t.getFirstName().equals(firstName) && t.getLastName().equals(lastName)){
                return true;
            }
        }
        return false;
    }

    public boolean existsCourse(String courseName){
        /**
         * Verifies the existence of the provided course
         * @param1 name of the course
         * @return true if the course exists, false otherwise
         */
        for(Course c:courseRepo.findAll()){
            if(c.getName().equals(courseName)){
                return true;
            }
        }
        return false;
    }

    public void addStudent(String firstName, String lastName){
        /**
         * adds a student to the file
         * @param1 student first name
         * @param2 student last name
         */
        long studId = -1;
        for(Student s:studentRepo.findAll()){
            if(s.getStudentId() > studId)
                studId = s.getStudentId();
        }
        studId += 1;
        Student s = new Student(studId,firstName,lastName,0,new ArrayList<Long>());
        studentRepo.save(s);

        try {
            studentRepo.writeToJson();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addTeacher(String firstName, String lastName){
        /**
         * adds a teacher to the file
         * @param1 student first name
         * @param2 student last name
         */
        long teachId = -1;
        for(Teacher t:teacherRepo.findAll()){
            if(t.getId() > teachId)
                teachId = t.getId();
        }
        teachId += 1;
        Teacher t = new Teacher(teachId, firstName, lastName, new ArrayList<Long>());
        teacherRepo.save(t);

        try {
            teacherRepo.writeToJson();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addCourse(String courseName, int maxEnrollment, int credits){
        /**
         * adds a course to the file
         * @param1 course name
         */
        long courseId = -1;
        for(Course c:courseRepo.findAll()){
            if(c.getCourseId() > courseId)
                courseId = c.getCourseId();
        }
        courseId += 1;
        Course c = new Course(courseId,courseName,(long)0,maxEnrollment,new ArrayList<Long>(),credits);
        courseRepo.save(c);

        try {
            courseRepo.writeToJson();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int updateStudent(String firstName, String lastName, String update, int change){
        /**
         * Updates a student's information
         * @param1 first name of the student
         * @param2 last name of the student
         * @param3 the new name or course needing to be changed
         * @param4 operation needing to be done
         * @return 0 if the update was successful, -1 if the student can't join the course because of max Enrollments being reached
         */
        if(change == 1){

            for(Student s:studentRepo.findAll()) {
                if (s.getFirstName().equals(firstName) && s.getLastName().equals(lastName)){
                    s.setFirstName(update);
                    break;
                }
            }
        }
        if(change == 2){

            for(Student s:studentRepo.findAll()) {
                if (s.getFirstName().equals(firstName) && s.getLastName().equals(lastName)){
                    s.setLastName(update);
                    break;
                }
            }
        }
        if(change == 3){

            List<Long> courses= new ArrayList<Long>();
            long courseId = -1;
            long id = -1;
            int credits = 0;
            int maxEnrolled = 0;
            for(Student s:studentRepo.findAll()) {
                if (s.getFirstName().equals(firstName) && s.getLastName().equals(lastName)){
                    courses = s.getEnrolledCourses();
                    id = s.getStudentId();
                }
            }
            for(Course c:courseRepo.findAll()){
                if(c.getName().equals(update)){
                    courseId = c.getCourseId();
                    credits = c.getCredits();
                    maxEnrolled = c.getMaxEnrollment();
                    break;
                }
            }
            List<Long> students = new ArrayList<Long>();
            if(courses.contains(courseId)){

                courses.remove(courseId);

                for(Student s:studentRepo.findAll()) {
                    if (s.getFirstName().equals(firstName) && s.getLastName().equals(lastName)){
                        s.setEnrolledCourses(courses);

                        for(Course c:courseRepo.findAll()) {
                            if (c.getName().equals(update)) {
                                students = c.getStudentsEnrolled();
                                students.remove(id);
                                c.setStudentsEnrolled(students);
                                break;
                            }
                        }
                        s.setTotalCredits(s.getTotalCredits() - credits);
                        break;
                    }
                }
            }
            else{

                courses.add(courseId);
                if(courses.size() > maxEnrolled){
                    return -1;
                }
                for(Student s:studentRepo.findAll()) {
                    if (s.getFirstName().equals(firstName) && s.getLastName().equals(lastName)){
                        s.setEnrolledCourses(courses);

                        for(Course c:courseRepo.findAll()) {
                            if (c.getName().equals(update)) {
                                students = c.getStudentsEnrolled();
                                students.add(id);
                                c.setStudentsEnrolled(students);
                                break;
                            }
                        }
                        s.setTotalCredits(s.getTotalCredits() + credits);
                        break;
                    }
                }
            }
        }

        try {
            courseRepo.writeToJson();
            studentRepo.writeToJson();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public void updateCourse(String courseName, String update, int change){
        /**
         * Updates a specific course's information
         * @param1 course name
         * @param2 course name/teacher/maxEnrollment/creditNumber
         * @param3 operation needing to be done
         */
        if(change == 1){
            for(Course c:courseRepo.findAll()){
                if(c.getName().equals(courseName)){
                    c.setName(update);
                }
            }
        }

        if(change == 2){
            for(Course c:courseRepo.findAll()){
                if(c.getName().equals(courseName)) {
                    Long id = 0L;

                    for(Teacher t:teacherRepo.findAll()){
                        if(t.getCourses().contains(c.getCourseId())){
                            List<Long> courses = t.getCourses();
                            courses.remove(c.getCourseId());
                            t.setCourses(courses);
                        }
                    }

                    for(Teacher t:teacherRepo.findAll()){
                        if(t.getLastName().equals(update)){
                            List<Long> courses = t.getCourses();
                            courses.add(c.getCourseId());
                            t.setCourses(courses);
                            id = t.getId();
                        }
                    }

                    c.setTeacher(id);
                }
            }
        }

        if(change == 3){
            for(Course c:courseRepo.findAll()){
                if(c.getName().equals(courseName)){
                    int maxEn = Integer.parseInt(update);
                    c.setMaxEnrollment(maxEn);
                }
            }
        }

        if(change == 4){
            for(Course c:courseRepo.findAll()){
                if(c.getName().equals(courseName)){
                    int credits = Integer.parseInt(update) - c.getCredits();
                    for(Student s:studentRepo.findAll()){
                        if(s.getEnrolledCourses().contains(c.getCourseId())){
                            s.setTotalCredits(s.getTotalCredits() + credits);

                        }
                    }
                    c.setCredits(Integer.parseInt(update));
                }
            }
        }

        try {
            courseRepo.writeToJson();
            studentRepo.writeToJson();
            teacherRepo.writeToJson();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int updateTeacher(String firstName, String lastName, String update, int change){
        /**
         * Updates a certain teacher's information
         * @param1 first name of the teacher
         * @param2 last name of the teacher
         * @param3 new first name/last name/course needing to be added/deleted
         * @param4 operation needing to be done
         * @return 0 if the update was successful, -1 if the provided course name was invalid
         */
        if(change == 1){
            for(Teacher t:teacherRepo.findAll()){
                if(t.getFirstName().equals(firstName) && t.getLastName().equals(lastName)){
                    t.setFirstName(update);
                }
            }
        }
        if(change == 2){
            for(Teacher t:teacherRepo.findAll()){
                if(t.getFirstName().equals(firstName) && t.getLastName().equals(lastName)){
                    t.setLastName(update);
                }
            }
        }
        if(change == 3){
            long id = 0L;
            int credits = 0;
            for(Course c:courseRepo.findAll()){
                if(c.getName().equals(update)){
                    id = c.getCourseId();
                    credits = c.getCredits();
                    break;
                }
                return -1;
            }
            for(Teacher t:teacherRepo.findAll()){
                if(t.getFirstName().equals(firstName) && t.getLastName().equals(lastName)){
                    if(t.getCourses().contains(id)){

                        for(Student s:studentRepo.findAll()){
                            if(s.getEnrolledCourses().contains(id)){
                                List<Long> courses = s.getEnrolledCourses();
                                courses.remove(id);
                                s.setEnrolledCourses(courses);

                                s.setTotalCredits(s.getTotalCredits() - credits);
                            }
                        }

                        List<Long> courses = t.getCourses();
                        courses.remove(id);
                        t.setCourses(courses);

                        courseRepo.delete(id);
                    }
                    else{

                        for(Course c:courseRepo.findAll()){
                            if(c.getCourseId() == id){
                                c.setTeacher(t.getId());
                                List<Long> courses = t.getCourses();
                                courses.add(c.getCourseId());
                                t.setCourses(courses);
                            }
                        }
                    }
                }
            }
        }

        try {
            courseRepo.writeToJson();
            studentRepo.writeToJson();
            teacherRepo.writeToJson();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public void deleteStudent(String firstName, String lastName){
        /**
         * deletes a student from all of the records
         * @param1 first name
         * @param2 last name
         */
        for(Student s:studentRepo.findAll()){
            if(s.getFirstName().equals(firstName) && s.getLastName().equals(lastName)){

                for(Course c:courseRepo.findAll()){
                    if(c.getStudentsEnrolled().contains(s.getStudentId())){
                        c.getStudentsEnrolled().remove(s.getStudentId());
                    }
                }

                studentRepo.delete(s.getStudentId());
                break;
            }
        }

        try {
            courseRepo.writeToJson();
            studentRepo.writeToJson();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteTeacher(String firstName, String lastName){
        /**
         * deletes a teacher from all of the records
         * @param1 first name
         * @param2 last name
         */
        for(Teacher t:teacherRepo.findAll()){
            if(t.getFirstName().equals(firstName) && t.getLastName().equals(lastName)){
                List<Long> courses = t.getCourses();

                for(Long el:courses){
                    int credits = 0;

                    for(Student s:studentRepo.findAll()){
                        if(s.getEnrolledCourses().contains(el)){
                            for(Course c:courseRepo.findAll()){
                                if(c.getCourseId() == el){
                                    credits = c.getCredits();
                                }
                            }
                            s.setTotalCredits(s.getTotalCredits() - credits);
                            List<Long> c = s.getEnrolledCourses();
                            c.remove(el);
                            s.setEnrolledCourses(c);
                        }
                    }

                    for(Course c:courseRepo.findAll()){
                        if(c.getCourseId() == el){
                            courseRepo.delete(c.getCourseId());
                        }
                    }
                }

                teacherRepo.delete(t.getId());
                break;
            }
        }

        try {
            courseRepo.writeToJson();
            studentRepo.writeToJson();
            teacherRepo.writeToJson();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteCourse(String courseName){
        /**
         * deletes a course from all of the records
         * @param1 course name
         */
        for(Course c:courseRepo.findAll()){
            if(c.getName().equals(courseName)){
                int credits = c.getCredits();

                for(Student s:studentRepo.findAll()){
                    if(s.getEnrolledCourses().contains(c.getCourseId())){
                        List<Long> courses = s.getEnrolledCourses();
                        courses.remove(c.getCourseId());
                        s.setEnrolledCourses(courses);

                        s.setTotalCredits(s.getTotalCredits() - credits);
                    }
                }

                for(Teacher t:teacherRepo.findAll()){
                    if(t.getCourses().contains(c.getCourseId())){
                        List<Long> courses = t.getCourses();
                        courses.remove(c.getCourseId());
                        t.setCourses(courses);
                    }
                }
                courseRepo.delete(c.getCourseId());
                break;
            }
        }

        try {
            courseRepo.writeToJson();
            studentRepo.writeToJson();
            teacherRepo.writeToJson();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Course> filterByEnrolled(){
        /**
         * Creates and returns a list of courses with enrolled students
         * @return list with courses that have at least 1 enrolled student
         */
        List<Course> filtered = new ArrayList<>();
        for(Course c:courseRepo.findAll()){
            if(!c.getStudentsEnrolled().isEmpty()){
                filtered.add(c);
            }
        }
        return filtered;
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
}
