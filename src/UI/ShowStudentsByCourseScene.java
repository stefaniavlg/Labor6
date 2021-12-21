package UI;

import observer.Observable;
import controller.StudentController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Student;

import java.util.List;

public class ShowStudentsByCourseScene {
    /**
     * Returns a Stage that shows students enrolled for specific course
     * @param studentController used to get data
     * @return Stage
     */
    public static Stage getScene(StudentController studentController){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Students enrolled for course");
        stage.setWidth(650);
        stage.setHeight(400);

        Group root = new Group();
        Scene scene = new Scene(root);

        Label lb_title = new Label("Students enrolled for course");
        lb_title.setFont(new Font("Arial", 40));
        lb_title.setStyle("-fx-text-fill: #000000;");

        Label lb_courseName = new Label("Course name:");
        lb_courseName.setFont(new Font("Arial", 20));
        TextField tf_courseName = new TextField();

        Label lb_error=new Label("");
        lb_error.setFont(new Font("Arial", 20));
        lb_error.setStyle("-fx-text-fill: #ff0000;");
        lb_error.setMaxWidth(300);
        lb_error.setWrapText(true);


        VBox layout_students =new VBox();
        layout_students.setAlignment(Pos.CENTER_LEFT);


        Button btn_search = new Button("Search");
        btn_search.setFont(new Font("Arial", 20));
        btn_search.setStyle("-fx-background-color: #2ba9fc; -fx-border-color: #eeeeee; -fx-border-width: 1px; -fx-text-fill: #ffffff;");
        btn_search.setOnAction((ActionEvent e)->{
            ObservableList<String> studentsList = FXCollections.observableArrayList();
            // get list of students enrolled to course specified by user
            List<Student> students = studentController.enrolledStudents(tf_courseName.getText());
            // if course exists is adding student name to a list of string
            if(students != null){
                students.forEach(student -> studentsList.add(student.toString()));
                lb_error.setText("");
            }
            else {
                lb_error.setText("Searching course does not exists!");
            }

            for(String name:studentsList){
                Label lb_name = new Label(name);
                lb_name.setFont(new Font("Arial", 20));
                lb_name.setStyle("-fx-text-fill: #000000;");
                layout_students.getChildren().addAll(lb_name);
            }
        });

        HBox layout_courseName = new HBox();
        layout_courseName.getChildren().addAll(lb_courseName,tf_courseName);
        layout_courseName.setAlignment(Pos.CENTER);

        HBox layout_btnSearch = new HBox();
        layout_btnSearch.getChildren().addAll(btn_search);
        layout_btnSearch.setAlignment(Pos.CENTER);


        VBox layout_Start = new VBox();
        layout_Start.setPadding(new Insets(50,20,20,60));
        layout_Start.setAlignment(Pos.CENTER);
        layout_Start.setSpacing(30);
        layout_Start.getChildren().addAll(lb_title,layout_courseName,layout_btnSearch,layout_students,lb_error);

        ((Group)scene.getRoot()).getChildren().addAll(layout_Start);
        stage.setScene(scene);
        return stage;
    }
}
