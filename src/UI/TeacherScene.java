package UI;

import observer.Observable;
import observer.Observer;
import controller.StudentController;
import controller.TeacherController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Course;

import java.util.List;
import java.util.stream.Collectors;

public class TeacherScene implements Observer {
    /**
     * Returns a Stage with a menu for student
     * @param teacherController used for operations
     * @param observableList will be updated when something is changed in studentController
     * @return Stage
     */
    public Stage getStage(TeacherController teacherController, List<Observable> observableList){
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle("Teacher Menu");
        stage.setWidth(400);
        stage.setHeight(630);

        Group root = new Group();
        Scene scene = new Scene(root);

        Label lb_title = new Label("Teacher Menu");
        lb_title.setFont(new Font("Arial", 40));
        lb_title.setStyle("-fx-text-fill: #000000;");


        Button btn_showCourses = new Button("Show Courses");
        btn_showCourses.setFont(new Font("Arial", 20));
        btn_showCourses.setStyle("-fx-background-color: #2ba9fc; -fx-border-color: #eeeeee; -fx-border-width: 1px; -fx-text-fill: #ffffff;");
        btn_showCourses.setMaxWidth(200);
        btn_showCourses.setMinWidth(200);
        btn_showCourses.setWrapText(true);
        btn_showCourses.setOnAction((ActionEvent e)->{
            stage.hide();
            ObservableList<String> obs = FXCollections.observableArrayList(teacherController.getAllCoursesAndStudentsAsString());
            ShowDataScene.getScene("Courses and students",obs).showAndWait();
            stage.show();
        });

        Button btn_showMyCourses = new Button("Show My Courses");
        btn_showMyCourses.setFont(new Font("Arial", 20));
        btn_showMyCourses.setStyle("-fx-background-color: #2ba9fc; -fx-border-color: #eeeeee; -fx-border-width: 1px; -fx-text-fill: #ffffff;");
        btn_showMyCourses.setMaxWidth(200);
        btn_showMyCourses.setMinWidth(200);
        btn_showMyCourses.setWrapText(true);
        btn_showMyCourses.setOnAction((ActionEvent e)->{
            stage.hide();
            List<String> stringList = teacherController.getOwnCourses().stream().map(Course::toString).collect(Collectors.toList());
            ObservableList<String> obsList = FXCollections.observableArrayList(stringList);
            ShowDataScene.getScene("My courses",obsList).showAndWait();
            stage.show();
        });

        Button btn_deleteCourse = new Button("Delete My Courses");
        btn_deleteCourse.setFont(new Font("Arial", 20));
        btn_deleteCourse.setStyle("-fx-background-color: #2ba9fc; -fx-border-color: #eeeeee; -fx-border-width: 1px; -fx-text-fill: #ffffff;");
        btn_deleteCourse.setMaxWidth(200);
        btn_deleteCourse.setMinWidth(200);
        btn_deleteCourse.setWrapText(true);
        btn_deleteCourse.setTextAlignment(TextAlignment.CENTER);
        btn_deleteCourse.setOnAction((ActionEvent e)->{
            stage.hide();
            DeleteTeacherCourseScene.getStage(teacherController).showAndWait();
            notify(observableList);
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

        HBox layout_showCourses = new HBox();
        layout_showCourses.getChildren().addAll(btn_showCourses);
        layout_showCourses.setAlignment(Pos.CENTER);
        HBox layout_showMyCourses = new HBox();
        layout_showMyCourses.getChildren().addAll(btn_showMyCourses);
        layout_showMyCourses.setAlignment(Pos.CENTER);
        HBox layout_deleteCourse = new HBox();
        layout_deleteCourse.getChildren().addAll(btn_deleteCourse);
        layout_deleteCourse.setAlignment(Pos.CENTER);
        HBox layout_back = new HBox();
        layout_back.getChildren().addAll(btn_back);
        layout_back.setAlignment(Pos.CENTER);

        VBox layout_Start = new VBox();
        layout_Start.setPadding(new Insets(50,20,20,60));
        layout_Start.setAlignment(Pos.CENTER);
        layout_Start.setSpacing(30);
        layout_Start.getChildren().addAll(lb_title,layout_showCourses,layout_showMyCourses,layout_deleteCourse,layout_back);


        ((Group)scene.getRoot()).getChildren().addAll(layout_Start);
        stage.setScene(scene);
        return stage;
    }

    @Override
    public void notify(List<Observable> observableList) {
        observableList.forEach(Observable::update);
    }
}
