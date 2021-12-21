package repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Teacher;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepo implements ICrudRepository<Teacher>{

    private List<Teacher> teachers = new ArrayList<>();

    private String fileName = "src/repository/Files/teachers.json";

    public TeacherRepo(String fileName) {
        this.fileName = "src/repository/Files/"+fileName;
    }
    public TeacherRepo(){}

    /**
     * Write teachers data to file as json
     * @throws IOException
     */
    public void writeToJson() throws IOException {
        Gson gson = new Gson();
        String jsonString = gson.toJson(teachers);
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(jsonString);
        writer.close();
    }

    /**
     * Read json file and transform json to List<Teacher>
     * @throws IOException
     */
    public void loadFromJson() throws IOException {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<Teacher>>(){}.getType();
        String jsonString = Files.readString(Path.of(fileName));
        teachers = gson.fromJson(jsonString, collectionType);
    }

    /**
     * Checks if list is empty
     * @return true if empty, false if not
     */
    public boolean isEmpty(){
        return teachers.isEmpty();
    }


    @Override
    public Teacher findOne(Long id) {
        for(Teacher t:teachers){
            if(t.getId()==id)
                return t;
        }
        return null;
    }


    @Override
    public Iterable<Teacher> findAll() {
        return teachers;
    }


    @Override
    public Teacher save(Teacher entity) {

        if(findOne(entity.getId())!=null)
            return findOne(entity.getId());
        teachers.add(entity);
        return null;
    }


    @Override
    public Teacher delete(Long id) {
        for(Teacher t:teachers){
            if(t.getId()==id){
                Teacher returnable = t;
                teachers.remove(t);
                return returnable;
            }
        }
        return null;
    }


    @Override
    public Teacher update(Teacher entity) {

        for(Teacher t:teachers){
            if(t.getId()==entity.getId()){
                t.setCourses(entity.getCourses());
                t.setFirstName(entity.getFirstName());
                t.setLastName(entity.getLastName());
            }
            return null;
        }
        return entity;
    }
}
