package repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StudentRepo implements ICrudRepository<Student>{
    private List<Student> students = new ArrayList<>();

    private String fileName = "src/repository/Files/students.json";

    public StudentRepo(String fileName) {
        this.fileName = "src/repository/Files/"+fileName;
    }
    public StudentRepo(){}

    /**
     * Write students data to file as json
     * @throws IOException
     */
    public void writeToJson() throws IOException {
        Gson gson = new Gson();
        String jsonString = gson.toJson(students);
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(jsonString);
        writer.close();
    }
    /**
     * Read json file and transform json to List<Student>
     * @throws IOException
     */
    public void loadFromJson() throws IOException {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<Student>>(){}.getType();
        String jsonString = Files.readString(Path.of(fileName));
        students = gson.fromJson(jsonString, collectionType);
    }

    /**
     * Checks if list is empty
     * @return true if empty, false if not
     */
    public boolean isEmpty(){
        return students.isEmpty();
    }

    @Override
    public Student findOne(Long id) {

        for(Student stud:students){
            if (stud.getStudentId() == id)
                return stud;
        }
        return null;
    }


    @Override
    public Iterable<Student> findAll() {
        return students;
    }


    @Override
    public Student save(Student entity) {

        if(findOne(entity.getStudentId())!=null) {
            return findOne(entity.getStudentId());
        }
        students.add(entity);
        return null;
    }


    @Override
    public Student delete(Long id) {
        for (Student stud:students) {
            if(stud.getStudentId()==id){
                Student returnable=stud;
                students.remove((stud));
                return returnable;
            }
        }
        return null;
    }


    @Override
    public Student update(Student entity) {

        for(Student stud:students){
            if (stud.getStudentId() == entity.getStudentId()) {
                stud.setFirstName(entity.getFirstName());
                stud.setLastName(entity.getLastName());
                stud.setTotalCredits(entity.getTotalCredits());
                stud.setEnrolledCourses(entity.getEnrolledCourses());
                return null;
            }
        }
        return entity;
    }

    /**
     * Comparator for sorting by last name
     */
    public Comparator<Student> ByName = new Comparator<Student>() {
        @Override
        public int compare(Student o1, Student o2) {
            String Name1 = o1.getLastName().toUpperCase();
            String Name2 = o2.getLastName().toUpperCase();

            return Name1.compareTo(Name2);
        }
    };

}
