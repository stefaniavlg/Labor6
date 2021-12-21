package UI;

import controller.AdminController;
import controller.StudentController;
import controller.TeacherController;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Arrays;

public class GeneralMenuScene {
    private StudentController studentController;
    private TeacherController teacherController;
    private AdminController adminController;

    public GeneralMenuScene(StudentController studentController, TeacherController teacherController, AdminController adminController) {
        this.studentController = studentController;
        this.teacherController = teacherController;
        this.adminController = adminController;
    }

    public Scene getScene(){
        Group root = new Group();
        Scene scene = new Scene(root);

        Label lb_title = new Label("University administration");
        lb_title.setFont(new Font("Arial", 40));
        lb_title.setStyle("-fx-text-fill: #000000;");



        Button btn_studentMenu = new Button("Student Menu");
        btn_studentMenu.setFont(new Font("Arial", 20));
        btn_studentMenu.setStyle("-fx-background-color: #2ba9fc; -fx-border-color: #eeeeee; -fx-border-width: 1px; -fx-text-fill: #ffffff;");
        btn_studentMenu.setMaxWidth(180);
        btn_studentMenu.setMinWidth(180);
        btn_studentMenu.setOnAction((ActionEvent e)->{
            studentController.resetLogIn();
            LoginScene.getStage(studentController).showAndWait();
            if(studentController.isSomeoneLoggedIn()){
               new StudentScene().getStage(studentController, Arrays.asList(teacherController,adminController)).showAndWait();
            }
        });


        Button btn_teacherMenu = new Button("Teacher Menu");
        btn_teacherMenu.setFont(new Font("Arial", 20));
        btn_teacherMenu.setStyle("-fx-background-color: #2ba9fc; -fx-border-color: #eeeeee; -fx-border-width: 1px; -fx-text-fill: #ffffff;");
        btn_teacherMenu.setMinWidth(180);
        btn_teacherMenu.setMaxWidth(180);
        btn_teacherMenu.setOnAction((ActionEvent e)->{
            teacherController.resetLogIn();
            LoginScene.getStage(teacherController).showAndWait();
            if(teacherController.isSomeoneLoggedIn()){
                new TeacherScene().getStage(teacherController,Arrays.asList(studentController,adminController)).showAndWait();
            }
        });



        Button btn_adminMenu = new Button("Admin Menu");
        btn_adminMenu.setFont(new Font("Arial", 20));
        btn_adminMenu.setStyle("-fx-background-color: #2ba9fc; -fx-border-color: #eeeeee; -fx-border-width: 1px; -fx-text-fill: #ffffff;");
        btn_adminMenu.setMinWidth(180);
        btn_adminMenu.setMaxWidth(180);
        btn_adminMenu.setOnAction((ActionEvent e)->{
            new AdminScene().getStage(adminController,Arrays.asList(studentController,teacherController)).showAndWait();
        });

        HBox layout_btnStudent = new HBox();
        layout_btnStudent.getChildren().addAll(btn_studentMenu);
        layout_btnStudent.setAlignment(Pos.CENTER);
        HBox layout_btnTeacher = new HBox();
        layout_btnTeacher.getChildren().addAll(btn_teacherMenu);
        layout_btnTeacher.setAlignment(Pos.CENTER);
        HBox layout_btnAdmin = new HBox();
        layout_btnAdmin.getChildren().addAll(btn_adminMenu);
        layout_btnAdmin.setAlignment(Pos.CENTER);


        VBox layout_Start = new VBox();
        layout_Start.setPadding(new Insets(50,20,20,60));
        layout_Start.setAlignment(Pos.CENTER);
        layout_Start.setSpacing(30);
        layout_Start.getChildren().addAll(lb_title,layout_btnStudent,layout_btnTeacher,layout_btnAdmin);

        ((Group)scene.getRoot()).getChildren().addAll(layout_Start);
        return scene;
    }

}
