package UI;

import observer.Observable;
import observer.Observer;
import controller.AdminController;
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

import java.util.List;

public class AdminScene implements Observer {
    /**
     * Returns a Stage with a menu for admin
     * @param adminController used for operations
     * @param observableList will be updated when something is changed in studentController
     * @return Stage
     */
    public Stage getStage(AdminController adminController, List<Observable> observableList){
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle("Admin Menu");
        stage.setWidth(400);
        stage.setHeight(830);

        Group root = new Group();
        Scene scene = new Scene(root);

        Label lb_title = new Label("Admin Menu");
        lb_title.setFont(new Font("Arial", 40));
        lb_title.setStyle("-fx-text-fill: #000000;");

        Button btn_addStudent = new Button("Add new student");
        btn_addStudent.setFont(new Font("Arial", 20));
        btn_addStudent.setStyle("-fx-background-color: #2ba9fc; -fx-border-color: #eeeeee; -fx-border-width: 1px; -fx-text-fill: #ffffff;");
        btn_addStudent.setMaxWidth(200);
        btn_addStudent.setMinWidth(200);
        btn_addStudent.setWrapText(true);
        btn_addStudent.setOnAction((ActionEvent e)->{
            stage.hide();
            AddStudentScene.getScene(adminController).showAndWait();
            notify(observableList);
            stage.show();
        });

        Button btn_updateStudent = new Button("Update student");
        btn_updateStudent.setFont(new Font("Arial", 20));
        btn_updateStudent.setStyle("-fx-background-color: #2ba9fc; -fx-border-color: #eeeeee; -fx-border-width: 1px; -fx-text-fill: #ffffff;");
        btn_updateStudent.setMaxWidth(200);
        btn_updateStudent.setMinWidth(200);
        btn_updateStudent.setWrapText(true);
        btn_updateStudent.setOnAction((ActionEvent e)->{
            stage.hide();
            UpdateStudentScene.getScene(adminController).showAndWait();
            notify(observableList);
            stage.show();
        });

        Button btn_deleteStudent = new Button("Delete student");
        btn_deleteStudent.setFont(new Font("Arial", 20));
        btn_deleteStudent.setStyle("-fx-background-color: #2ba9fc; -fx-border-color: #eeeeee; -fx-border-width: 1px; -fx-text-fill: #ffffff;");
        btn_deleteStudent.setMaxWidth(200);
        btn_deleteStudent.setMinWidth(200);
        btn_deleteStudent.setWrapText(true);
        btn_deleteStudent.setTextAlignment(TextAlignment.CENTER);
        btn_deleteStudent.setOnAction((ActionEvent e)->{
            stage.hide();
            DeleteStudentScene.getScene(adminController).showAndWait();
            notify(observableList);
            stage.show();
        });

        Button btn_addCourse = new Button("Add new course");
        btn_addCourse.setFont(new Font("Arial", 20));
        btn_addCourse.setStyle("-fx-background-color: #2ba9fc; -fx-border-color: #eeeeee; -fx-border-width: 1px; -fx-text-fill: #ffffff;");
        btn_addCourse.setMaxWidth(200);
        btn_addCourse.setMinWidth(200);
        btn_addCourse.setWrapText(true);
        btn_addCourse.setOnAction((ActionEvent e)->{
            stage.hide();
            AddCourseScene.getScene(adminController).showAndWait();
            notify(observableList);
            stage.show();
        });

        Button btn_updateCourse = new Button("Update course");
        btn_updateCourse.setFont(new Font("Arial", 20));
        btn_updateCourse.setStyle("-fx-background-color: #2ba9fc; -fx-border-color: #eeeeee; -fx-border-width: 1px; -fx-text-fill: #ffffff;");
        btn_updateCourse.setMaxWidth(200);
        btn_updateCourse.setMinWidth(200);
        btn_updateCourse.setWrapText(true);
        btn_updateCourse.setOnAction((ActionEvent e)->{
            stage.hide();
            UpdateCourseScene.getScene(adminController).showAndWait();
            notify(observableList);
            stage.show();
        });

        Button btn_deleteCourse = new Button("Delete course");
        btn_deleteCourse.setFont(new Font("Arial", 20));
        btn_deleteCourse.setStyle("-fx-background-color: #2ba9fc; -fx-border-color: #eeeeee; -fx-border-width: 1px; -fx-text-fill: #ffffff;");
        btn_deleteCourse.setMaxWidth(200);
        btn_deleteCourse.setMinWidth(200);
        btn_deleteCourse.setWrapText(true);
        btn_deleteCourse.setOnAction((ActionEvent e)->{
            stage.hide();
            DeleteCourseScene.getScene(adminController).showAndWait();
            notify(observableList);
            stage.show();
        });

        Button btn_addTeacher = new Button("Add new teacher");
        btn_addTeacher.setFont(new Font("Arial", 20));
        btn_addTeacher.setStyle("-fx-background-color: #2ba9fc; -fx-border-color: #eeeeee; -fx-border-width: 1px; -fx-text-fill: #ffffff;");
        btn_addTeacher.setMaxWidth(200);
        btn_addTeacher.setMinWidth(200);
        btn_addTeacher.setWrapText(true);
        btn_addTeacher.setOnAction((ActionEvent e)->{
            stage.hide();
            AddTeacherScene.getScene(adminController).showAndWait();
            notify(observableList);
            stage.show();
        });

        Button btn_updateTeacher = new Button("Update teacher");
        btn_updateTeacher.setFont(new Font("Arial", 20));
        btn_updateTeacher.setStyle("-fx-background-color: #2ba9fc; -fx-border-color: #eeeeee; -fx-border-width: 1px; -fx-text-fill: #ffffff;");
        btn_updateTeacher.setMaxWidth(200);
        btn_updateTeacher.setMinWidth(200);
        btn_updateTeacher.setWrapText(true);
        btn_updateTeacher.setOnAction((ActionEvent e)->{
            stage.hide();
            UpdateTeacherScene.getScene(adminController).showAndWait();
            notify(observableList);
            stage.show();
        });

        Button btn_deleteTeacher = new Button("Delete teacher");
        btn_deleteTeacher.setFont(new Font("Arial", 20));
        btn_deleteTeacher.setStyle("-fx-background-color: #2ba9fc; -fx-border-color: #eeeeee; -fx-border-width: 1px; -fx-text-fill: #ffffff;");
        btn_deleteTeacher.setMaxWidth(200);
        btn_deleteTeacher.setMinWidth(200);
        btn_deleteTeacher.setWrapText(true);
        btn_deleteTeacher.setTextAlignment(TextAlignment.CENTER);
        btn_deleteTeacher.setOnAction((ActionEvent e)->{
            stage.hide();
            DeleteTeacherScene.getScene(adminController).showAndWait();
            notify(observableList);
            stage.show();
        });

        Button btn_back = new Button("Back");
        btn_back.setFont(new Font("Arial", 20));
        btn_back.setStyle("-fx-background-color: #2ba9fc; -fx-border-color: #eeeeee; -fx-border-width: 1px; -fx-text-fill: #ffffff;");
        btn_back.setMaxWidth(200);
        btn_back.setMinWidth(200);
        btn_back.setWrapText(true);
        btn_back.setOnAction((ActionEvent e)-> stage.close());

        HBox layout_addStudent = new HBox();
        layout_addStudent.getChildren().addAll(btn_addStudent);
        layout_addStudent.setAlignment(Pos.CENTER);
        HBox layout_updateStudent = new HBox();
        layout_updateStudent.getChildren().addAll(btn_updateStudent);
        layout_updateStudent.setAlignment(Pos.CENTER);
        HBox layout_deleteStudent = new HBox();
        layout_deleteStudent.getChildren().addAll(btn_deleteStudent);
        layout_deleteStudent.setAlignment(Pos.CENTER);
        HBox layout_addCourse = new HBox();
        layout_addCourse.getChildren().addAll(btn_addCourse);
        layout_addCourse.setAlignment(Pos.CENTER);
        HBox layout_updateCourse = new HBox();
        layout_updateCourse.getChildren().addAll(btn_updateCourse);
        layout_updateCourse.setAlignment(Pos.CENTER);
        HBox layout_deleteCourse = new HBox();
        layout_deleteCourse.getChildren().addAll(btn_deleteCourse);
        layout_deleteCourse.setAlignment(Pos.CENTER);
        HBox layout_addTeacher = new HBox();
        layout_addTeacher.getChildren().addAll(btn_addTeacher);
        layout_addTeacher.setAlignment(Pos.CENTER);
        HBox layout_updateTeacher = new HBox();
        layout_updateTeacher.getChildren().addAll(btn_updateTeacher);
        layout_updateTeacher.setAlignment(Pos.CENTER);
        HBox layout_deleteTeacher = new HBox();
        layout_deleteTeacher.getChildren().addAll(btn_deleteTeacher);
        layout_deleteTeacher.setAlignment(Pos.CENTER);
        HBox layout_back = new HBox();
        layout_back.getChildren().addAll(btn_back);
        layout_back.setAlignment(Pos.CENTER);

        VBox layout_Start = new VBox();
        layout_Start.setPadding(new Insets(50,20,20,60));
        layout_Start.setAlignment(Pos.CENTER);
        layout_Start.setSpacing(30);
        layout_Start.getChildren().addAll(
                lb_title,layout_addStudent,layout_updateStudent,layout_deleteStudent,layout_addCourse,layout_updateCourse,layout_deleteCourse,layout_addTeacher,layout_updateTeacher,layout_deleteTeacher,layout_back);


        ((Group)scene.getRoot()).getChildren().addAll(layout_Start);
        stage.setScene(scene);
        return stage;
    }

    @Override
    public void notify(List<Observable> observableList) {
        observableList.forEach(Observable::update);
    }
}