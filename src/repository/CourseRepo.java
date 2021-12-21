package repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Course;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CourseRepo implements ICrudRepository<Course>{
    private List<Course> courses = new ArrayList<>();

    private String fileName = "src/repository/Files/courses.json";

    public CourseRepo(String fileName) {
        this.fileName = "src/repository/Files/"+fileName;
    }
    public CourseRepo(){}
    /**
     * Write courses data to file as json
     * @throws IOException
     */
    public void writeToJson() throws IOException {
        Gson gson = new Gson();
        String jsonString = gson.toJson(courses);
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(jsonString);
        writer.close();
    }
    /**
     * Read json file and transform json to List<Course>
     * @throws IOException
     */
    public void loadFromJson() throws IOException {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<Course>>(){}.getType();
        String jsonString = Files.readString(Path.of(fileName));
        courses = gson.fromJson(jsonString, collectionType);
    }

    /**
     * Checks if list is empty
     * @return true if empty, false if not
     */
    public boolean isEmpty(){
        return courses.isEmpty();
    }

    @Override
    public Course findOne(Long id) {
        for(Course curs:courses){
            if(curs.getCourseId()==id)
                return curs;
        }
        return null;
    }


    @Override
    public Iterable<Course> findAll() {
        return courses;
    }


    @Override
    public Course save(Course entity) {
        if(findOne(entity.getCourseId())!=null) {
            return findOne(entity.getCourseId());
        }
        courses.add(entity);
        return null;
    }


    @Override
    public Course delete(Long id) {
        for (Course curs:courses) {
            if(curs.getCourseId()==id){
                Course returnable=curs;
                courses.remove((curs));
                return returnable;
            }
        }
        return null;
    }


    @Override
    public Course update(Course entity) {

        for(Course curs:courses){
            if(curs.getCourseId() == entity.getCourseId()){
                curs.setCredits(entity.getCredits());
                curs.setMaxEnrollment(entity.getMaxEnrollment());
                curs.setName(entity.getName());
                curs.setStudentsEnrolled(entity.getStudentsEnrolled());
                curs.setTeacher(entity.getTeacher());
                return null;
            }
        }
        return entity;
    }


    public int getCourseNumber(){
        return courses.size();
    }
}
