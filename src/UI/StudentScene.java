package UI;

import observer.Observable;
import observer.Observer;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

import static java.lang.Thread.sleep;

public class StudentScene implements Observer {
    /**
     * Returns a Stage with a menu for student
     * @param studentController used for operations
     * @param observableList will be updated when something is changed in studentController
     * @return Stage
     */
    public Stage getStage(StudentController studentController, List<Observable> observableList){
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle("Student Menu");
        stage.setWidth(400);
        stage.setHeight(630);

        Group root = new Group();
        Scene scene = new Scene(root);

        Label lb_title = new Label("Student Menu");
        lb_title.setFont(new Font("Arial", 40));
        lb_title.setStyle("-fx-text-fill: #000000;");


        Button btn_joinCourse = new Button("Join Course");
        btn_joinCourse.setFont(new Font("Arial", 20));
        btn_joinCourse.setStyle("-fx-background-color: #2ba9fc; -fx-border-color: #eeeeee; -fx-border-width: 1px; -fx-text-fill: #ffffff;");
        btn_joinCourse.setMaxWidth(200);
        btn_joinCourse.setMinWidth(200);
        btn_joinCourse.setWrapText(true);
        btn_joinCourse.setOnAction((ActionEvent e)->{
            stage.hide();
            JoinCourseScene.getStage(studentController).showAndWait();
            notify(observableList);
            stage.show();
        });


        Button btn_showCourses = new Button("Show Courses");
        btn_showCourses.setFont(new Font("Arial", 20));
        btn_showCourses.setStyle("-fx-background-color: #2ba9fc; -fx-border-color: #eeeeee; -fx-border-width: 1px; -fx-text-fill: #ffffff;");
        btn_showCourses.setMaxWidth(200);
        btn_showCourses.setMinWidth(200);
        btn_showCourses.setWrapText(true);
        btn_showCourses.setOnAction((ActionEvent e)->{
            stage.hide();
            ObservableList<String> obsList = FXCollections.observableArrayList();
            studentController.getAllCourses().forEach(course -> obsList.add(course.toString()));
            ShowDataScene.getScene("Courses",obsList).showAndWait();
            stage.show();
        });


        Button btn_availableCourses = new Button("Available Courses");
        btn_availableCourses.setFont(new Font("Arial", 20));
        btn_availableCourses.setStyle("-fx-background-color: #2ba9fc; -fx-border-color: #eeeeee; -fx-border-width: 1px; -fx-text-fill: #ffffff;");
        btn_availableCourses.setMaxWidth(200);
        btn_availableCourses.setMinWidth(200);
        btn_availableCourses.setWrapText(true);
        btn_availableCourses.setOnAction((ActionEvent e)->{
            stage.hide();
            ObservableList<String> obsList = FXCollections.observableArrayList();
            studentController.availableCourses().forEach(course -> obsList.add(course.toString()));
            ShowDataScene.getScene("Available Courses",obsList).showAndWait();
            stage.show();
        });


        Button btn_enrolledStudents = new Button("Enrolled Students");
        btn_enrolledStudents.setFont(new Font("Arial", 20));
        btn_enrolledStudents.setStyle("-fx-background-color: #2ba9fc; -fx-border-color: #eeeeee; -fx-border-width: 1px; -fx-text-fill: #ffffff;");
        btn_enrolledStudents.setMaxWidth(200);
        btn_enrolledStudents.setMinWidth(200);
        btn_enrolledStudents.setWrapText(true);
        btn_enrolledStudents.setOnAction((ActionEvent e)->{
            stage.hide();
            ShowStudentsByCourseScene.getScene(studentController).showAndWait();
            stage.show();
        });


        Button btn_studentsByLastName = new Button("Show all students by last name");
        btn_studentsByLastName.setFont(new Font("Arial", 20));
        btn_studentsByLastName.setStyle("-fx-background-color: #2ba9fc; -fx-border-color: #eeeeee; -fx-border-width: 1px; -fx-text-fill: #ffffff;");
        btn_studentsByLastName.setMaxWidth(200);
        btn_studentsByLastName.setMinWidth(200);
        btn_studentsByLastName.setTextAlignment(TextAlignment.CENTER);
        btn_studentsByLastName.setWrapText(true);
        btn_studentsByLastName.setOnAction((ActionEvent e)->{
            stage.hide();
            ObservableList<String> obsList = FXCollections.observableArrayList();
            studentController.sortedByName().forEach(student -> obsList.add(student.toString()));
            ShowDataScene.getScene("Students sorted by last name",obsList).showAndWait();
            stage.show();
        });

        Button btn_back = new Button("Back");
        btn_back.setFont(new Font("Arial", 20));
        btn_back.setStyle("-fx-background-color: #2ba9fc; -fx-border-color: #eeeeee; -fx-border-width: 1px; -fx-text-fill: #ffffff;");
        btn_back.setMaxWidth(200);
        btn_back.setMinWidth(200);
        btn_back.setWrapText(true);
        btn_back.setOnAction((ActionEvent e)->{
            stage.close();
        });

        HBox layout_joinCourse = new HBox();
        layout_joinCourse.getChildren().addAll(btn_joinCourse);
        layout_joinCourse.setAlignment(Pos.CENTER);
        HBox layout_showCourses = new HBox();
        layout_showCourses.getChildren().addAll(btn_showCourses);
        layout_showCourses.setAlignment(Pos.CENTER);
        HBox layout_availableCourses = new HBox();
        layout_availableCourses.getChildren().addAll(btn_availableCourses);
        layout_availableCourses.setAlignment(Pos.CENTER);
        HBox layout_enrolledStudents = new HBox();
        layout_enrolledStudents.getChildren().addAll(btn_enrolledStudents);
        layout_enrolledStudents.setAlignment(Pos.CENTER);
        HBox layout_studentsByLastName = new HBox();
        layout_studentsByLastName.getChildren().addAll(btn_studentsByLastName);
        layout_studentsByLastName.setAlignment(Pos.CENTER);
        HBox layout_back = new HBox();
        layout_back.getChildren().addAll(btn_back);
        layout_back.setAlignment(Pos.CENTER);

        VBox layout_Start = new VBox();
        layout_Start.setPadding(new Insets(50,20,20,60));
        layout_Start.setAlignment(Pos.CENTER);
        layout_Start.setSpacing(30);
        layout_Start.getChildren().addAll(lb_title,layout_joinCourse,layout_showCourses,layout_availableCourses,layout_enrolledStudents,layout_studentsByLastName,layout_back);


        ((Group)scene.getRoot()).getChildren().addAll(layout_Start);
        stage.setScene(scene);
        return stage;
    }

    @Override
    public void notify(List<Observable> observableList) {
        observableList.forEach(Observable::update);
    }
}
