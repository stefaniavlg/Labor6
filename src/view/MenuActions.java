package view;

import exception.BadChoiceException;
import exception.FileDoesNotExistException;
import controller.AdminController;
import controller.StudentController;
import controller.TeacherController;
import model.Course;
import model.Student;

import java.util.List;
import java.util.Scanner;

import static view.Menus.*;

public class MenuActions {
    private StudentController studentController =new StudentController();
    private TeacherController teacherController =new TeacherController();
    private AdminController adminController =new AdminController();

    /**
     * Generates login menu
     * @param type (string)for who is login(Student/Teacher)
     * @return (boolean)was successfully logged in or not

     */
    public boolean GenerateLogin(String type){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please Login:");
        System.out.println("Enter First Name");
        String firstName = sc.next();
        System.out.println("Enter Last Name");
        String lastName = sc.next();

        if(studentController.getClass().toString().equals(type))
            return studentController.checkLogin(firstName,lastName);
        if(teacherController.getClass().toString().equals(type))
            return teacherController.checkLogin(firstName,lastName);
        return false;
    }

    /**
     * Get user input(int) and return it
     * @return (int) user choice
     * @throws BadChoiceException when user enter something that is not an number
     */
    public static int userChoice() throws BadChoiceException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your option: ");
        try{
            return Integer.parseInt(sc.next());
        }
        catch (NumberFormatException e) {
            throw new BadChoiceException("This is not an number!");
        }
    }

    /**
     * Calling Menu generator and showing output for controller actions done by student
     */
    public void StudentMenu(){
        while (true){
            try {
                studentController.loadData();
            } catch (FileDoesNotExistException e) {
                e.printStackTrace();
            }
            GenerateStudentMenu();
            int choice = 0;
            try {
                choice = userChoice();
            } catch (BadChoiceException e) {
                System.out.println("Your input is not an number!");
            }
            if(choice == 1){

                StringBuilder sb = new StringBuilder();
                sb.append(studentController.coursesNumber());
                sb.append(" courses: \n");
                for(Course course:studentController.getAllCourses()) {
                    sb.append(course.getCourseId());
                    sb.append(". ");
                    sb.append(course.getName());
                    sb.append(" - available places: ");
                    sb.append(course.getMaxEnrollment()-course.getStudentsEnrolled().size());
                    sb.append('\n');
                }
                System.out.println(sb.toString());

                long courseId;
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter id of course: ");
                try{
                    courseId = Long.parseLong(sc.next());
                    int joinStatus = studentController.joinCourse(courseId);
                    if(joinStatus==0)
                        System.out.println("You successfully joined course!");
                    if(joinStatus==-1)
                        System.out.println("You are already enrolled to this course");
                    if(joinStatus==-2)
                        System.out.println("You have passed 30 credits if join this course");
                    if(joinStatus==-3)
                        System.out.println("This course has no free places");
                }
                catch (NumberFormatException e) {
                    System.out.println("Not an id!");
                }

            }
            else if(choice == 2){
                StringBuilder sb = new StringBuilder();
                sb.append(studentController.coursesNumber());
                sb.append(" courses: \n");
                for(Course course:studentController.getAllCourses()) {
                    sb.append(course.getCourseId());
                    sb.append(". ");
                    sb.append(course.getName());
                    sb.append(" - available places: ");
                    sb.append(course.getMaxEnrollment()-course.getStudentsEnrolled().size());
                    sb.append('\n');
                }
                System.out.println(sb.toString());
            }
            else if(choice == 3){
                List<Course> availableCourses = studentController.availableCourses();
                StringBuilder sb = new StringBuilder();
                sb.append(availableCourses.size());
                sb.append(" are available right now: \n");
                for(Course course:availableCourses) {
                    sb.append(course.getCourseId());
                    sb.append(". ");
                    sb.append(course.getName());
                    sb.append('\n');
                }
                System.out.println(sb.toString());
            }
            else if(choice == 4){
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter course name: ");
                String courseName = sc.next();
                List<Student> students = studentController.enrolledStudents(courseName);
                if(students != null){
                    StringBuilder sb = new StringBuilder();
                    sb.append("Students enrolled for "+ courseName + " are: \n");
                    for(Student student:students){
                        sb.append(student.getFirstName());
                        sb.append(" ");
                        sb.append(student.getLastName() + "\n");
                    }
                    System.out.println(sb.toString());
                }
                else
                    System.out.println("Specified course does not exist!");


            }
            else if (choice == 5){
                //sort students by last name
                List<Student> sorted = studentController.sortedByName();
                StringBuilder sb = new StringBuilder();
                sb.append("Students:\n");
                for(Student s:sorted){
                    sb.append(s.getFirstName());
                    sb.append(" ");
                    sb.append(s.getLastName() + "\n");
                }
                System.out.println(sb.toString());
            }
            else if(choice == 0)
                return;
        }
    }

    public void TeacherMenu(){
        while (true){
            GenerateTeacherMenu();
            try {
                teacherController.loadData();
            } catch (FileDoesNotExistException e) {
                e.printStackTrace();
            }
            int choice = 0;
            try {
                choice = userChoice();
            } catch (BadChoiceException e) {
                System.out.println("Your input is not an number!");
            }
            if(choice == 1){

                StringBuilder sb = new StringBuilder();
                sb.append(teacherController.coursesNumber());
                sb.append(" courses: \n");
                for(Course course:teacherController.getAllCourses()) {
                    sb.append(course.getCourseId());
                    sb.append(". ");
                    sb.append(course.getName());
                    sb.append(" - available places: ");
                    sb.append(course.getMaxEnrollment()-course.getStudentsEnrolled().size());
                    sb.append('\n');
                }
                System.out.println(sb.toString());
            }
            else if(choice == 2){

                StringBuilder sb = new StringBuilder();
                sb.append(teacherController.ownCoursesNumber());
                sb.append(" courses: \n");
                for(Course course:teacherController.getOwnCourses()) {
                    sb.append(course.getCourseId());
                    sb.append(". ");
                    sb.append(course.getName());
                    sb.append(" - available places: ");
                    sb.append(course.getMaxEnrollment()-course.getStudentsEnrolled().size());
                    sb.append('\n');
                }
                System.out.println(sb.toString());
            }
            else if(choice == 0)
                return;
        }
    }

    public void AdminMenu(){
        while (true){
            GenerateAdminMenu();
            try {
                adminController.loadData();
            } catch (FileDoesNotExistException e) {
                e.printStackTrace();
            }
            int choice = 0;
            try {
                choice = userChoice();
            } catch (BadChoiceException e) {
                System.out.println("Your input is not an number!");
            }
            if(choice == 1){
                //add new stud
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter student's first name: ");
                String firstName = sc.next();
                System.out.println("Enter student's last name: ");
                String lastName = sc.next();
                if(adminController.existsStudent(firstName,lastName)){
                    System.out.println("Student already exists");
                }
                else{
                    adminController.addStudent(firstName,lastName);
                }

            }
            else if (choice == 2){
                //update stud
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter student's first name: ");
                String firstName = sc.next();
                System.out.println("Enter student's last name: ");
                String lastName = sc.next();
                if(adminController.existsStudent(firstName,lastName)){
                    System.out.println("1. Change first name: ");
                    System.out.println("2. Change last name: ");
                    System.out.println("3. Change enrolled courses: ");
                    System.out.println("0. Back");
                    int change = 0;
                    try {
                        change = userChoice();
                    } catch (BadChoiceException e) {
                        e.printStackTrace();
                    }
                    if(change == 1){
                        //change first name
                        System.out.println("Enter student's new first name: ");
                        String newFirst = sc.next();
                        if(adminController.existsStudent(newFirst,lastName)){
                            System.out.println("A student with this name already exists");
                        }
                        else{
                            adminController.updateStudent(firstName,lastName,newFirst,1);
                        }
                    }
                    if(change == 2){
                        //change last name
                        System.out.println("Enter student's new last name: ");
                        String newLast = sc.next();
                        if(adminController.existsStudent(firstName,newLast)){
                            System.out.println("A student with this name already exists");
                        }
                        else{
                            adminController.updateStudent(firstName,lastName,newLast,2);
                        }
                    }
                    if(change == 3){
                        //change courses
                        System.out.println("Enter student's course name you would like to add or delete: ");
                        String course = sc.next();
                        if(adminController.existsCourse(course)){
                            int updateRez = adminController.updateStudent(firstName,lastName,course,3);
                            if(updateRez == 0){
                                System.out.println("Student successfully updated!");
                            }
                            else{
                                System.out.println("Course has already reached max enrollment, update impossible");
                            }
                        }
                        else{
                            System.out.println("Course does not exist");
                        }
                    }
                }
                else{
                    System.out.println("Student doesn't exist");
                }
            }
            else if (choice == 3){

                Scanner sc = new Scanner(System.in);
                System.out.println("Enter student's first name: ");
                String firstName = sc.next();
                System.out.println("Enter student's last name: ");
                String lastName = sc.next();
                if(adminController.existsStudent(firstName,lastName)){
                    adminController.deleteStudent(firstName,lastName);
                }
                else{
                    System.out.println("Student doesn't exist");
                }
            }
            else if (choice == 4){

                Scanner sc = new Scanner(System.in);
                System.out.println("Enter course name: ");
                String courseName = sc.next();
                System.out.println("Enter max students: ");
                int maxEnrolled = Integer.parseInt(sc.next());
                System.out.println("Enter credit value: ");
                int credits = Integer.parseInt(sc.next());
                if(adminController.existsCourse(courseName)){
                    System.out.println("Course already exists");
                }
                else{
                    adminController.addCourse(courseName,maxEnrolled,credits);
                }
            }
            else if (choice == 5){

                Scanner sc = new Scanner(System.in);
                System.out.println("Enter course name: ");
                String courseName = sc.next();
                if(adminController.existsCourse(courseName)){
                    System.out.println("1. Change course name");
                    System.out.println("2. Change course teacher");
                    System.out.println("3. Change course max enrollment");
                    System.out.println("4. Change course credits");
                    System.out.println("0. Back");
                    int change = 0;
                    try {
                        change = userChoice();
                    } catch (BadChoiceException e) {
                        e.printStackTrace();
                    }
                    if(change == 1){

                        System.out.println("Enter new name: ");
                        String newName = sc.next();
                        adminController.updateCourse(courseName,newName,1);
                    }
                    if(change == 2){

                        System.out.println("Enter new teacher's last name: ");
                        String newTeacher = sc.next();
                        adminController.updateCourse(courseName,newTeacher,2);
                    }
                    if(change == 3){

                        System.out.println("Enter new max enrollments: ");
                        String newEnrolls = sc.next();
                        adminController.updateCourse(courseName,newEnrolls,3);
                    }
                    if(change == 4){

                        System.out.println("Enter new credit amount: ");
                        String newCreds = sc.next();
                        adminController.updateCourse(courseName,newCreds,4);
                    }
                }
                else{
                    System.out.println("Course doesn't exist");
                }
            }
            else if (choice == 6){

                Scanner sc = new Scanner(System.in);
                System.out.println("Enter course's name: ");
                String courseName = sc.next();
                if(adminController.existsCourse(courseName)){
                    adminController.deleteCourse(courseName);
                }
                else{
                    System.out.println("Course doesn't exist");
                }
            }
            else if (choice == 7){

                Scanner sc = new Scanner(System.in);
                System.out.println("Enter teacher's first name: ");
                String firstName = sc.next();
                System.out.println("Enter teacher's last name: ");
                String lastName = sc.next();
                if(adminController.existsTeacher(firstName,lastName)){
                    System.out.println("Teacher already exists");
                }
                else{
                    adminController.addTeacher(firstName,lastName);
                }
            }
            else if (choice == 8){

                Scanner sc = new Scanner(System.in);
                System.out.println("Enter teacher's first name: ");
                String firstName = sc.next();
                System.out.println("Enter teacher's last name: ");
                String lastName = sc.next();
                if(adminController.existsTeacher(firstName,lastName)){
                    System.out.println("1. Change teacher's first name: ");
                    System.out.println("2. Change teacher's last name: ");
                    System.out.println("3. Change teacher's courses: ");
                    System.out.println("0. Back");
                    int change = 0;
                    try {
                        change = userChoice();
                    } catch (BadChoiceException e) {
                        e.printStackTrace();
                    }
                    if(change == 1){

                        System.out.println("Enter teacher's new first name: ");
                        String update = sc.next();
                        adminController.updateTeacher(firstName,lastName,update,change);
                    }
                    if(change == 2){

                        System.out.println("Enter teacher's new last name: ");
                        String update = sc.next();
                        adminController.updateTeacher(firstName,lastName,update,change);
                    }
                    if(change == 3){

                        System.out.println("Enter new course's name: ");
                        String update = sc.next();
                        int updateRez = adminController.updateTeacher(firstName,lastName,update,change);
                        if(updateRez == -1){
                            System.out.println("Invalid course name");
                        }
                    }
                }
                else{
                    System.out.println("Teacher doesn't exist");
                }
            }
            else if (choice == 9){

                Scanner sc = new Scanner(System.in);
                System.out.println("Enter teacher's first name: ");
                String firstName = sc.next();
                System.out.println("Enter teacher's last name: ");
                String lastName = sc.next();
                if(adminController.existsTeacher(firstName,lastName)){
                    adminController.deleteTeacher(firstName,lastName);
                }
                else{
                    System.out.println("Teacher doesn't exist");
                }
            }
            else if (choice == 10){

                List<Course> filtered = adminController.filterByEnrolled();
                StringBuilder sb = new StringBuilder();
                sb.append("Courses:\n");
                for(Course c:filtered){
                    sb.append(c.getName());
                    sb.append(", " + c.getStudentsEnrolled() + "\n");
                }
                System.out.println(sb.toString());
            }
            else if (choice == 0){
                return;
            }
        }
    }

    public void startUpMenu(){

        try {
            studentController.loadData();
            teacherController.loadData();
            adminController.loadData();
        } catch (FileDoesNotExistException e) {
            e.printStackTrace();
        }

        while (true){
            GenerateStartUpMenu();
            int choice = 0;
            try {
                choice = userChoice();
            } catch (BadChoiceException e) {
                System.out.println("Your input is not an number!");
            }
            if(choice == 1){
                if(GenerateLogin(StudentController.class.toString())){
                    System.out.println("Successfully logged in!");
                    StudentMenu();
                }
                else
                    System.out.println("Bad first name or last name! Please try again");
            }
            else if (choice == 2){
                if(GenerateLogin(TeacherController.class.toString())){
                    System.out.println("Successfully logged in!");
                    TeacherMenu();
                }
                else
                    System.out.println("Bad first name or last name! Please try again");

            }
            else if( choice == 3){
                    AdminMenu();
            }
            else if( choice == 0){
                return;
            }
        }
    }
}
