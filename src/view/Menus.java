package view;

public class Menus {
    public static void GenerateStartUpMenu(){
        System.out.println("1. Login Student");
        System.out.println("2. Login Teacher");
        System.out.println("3. Admin Mode");
        System.out.println("0. Exit");
    }

    public static void GenerateAdminMenu(){
        System.out.println("1. Add new student");
        System.out.println("2. Update student info");
        System.out.println("3. Delete student");
        System.out.println("4. Add new course");
        System.out.println("5. Update course info");
        System.out.println("6. Delete course");
        System.out.println("7. Add new teacher");
        System.out.println("8. Update teacher info");
        System.out.println("9. Delete teacher");
        System.out.println("10. Filter courses with enrolled students");

        System.out.println("0. Back");
    }
    public static void GenerateStudentMenu(){
        System.out.println("1. Join course");
        System.out.println("2. Show courses");
        System.out.println("3. Available courses");
        System.out.println("4. Enrolled students");
        System.out.println("5. Show all students by last name");

        System.out.println("0. Back");
    }
    public static void GenerateTeacherMenu(){
        System.out.println("1. Show courses");
        System.out.println("2. Show my courses");
        System.out.println("3. Delete course");

        System.out.println("0. Back");
    }

}
