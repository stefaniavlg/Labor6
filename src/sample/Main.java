package sample;

import UI.GeneralMenuScene;
import controller.AdminController;
import controller.StudentController;
import controller.TeacherController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private StudentController studentController = new StudentController();
    private TeacherController teacherController = new TeacherController();
    private AdminController adminController = new AdminController();

    

    @Override
    public void start(Stage primaryStage) throws Exception{
        studentController.loadData();
        teacherController.loadData();
        adminController.loadData();

        primaryStage.setTitle("University administration");
        primaryStage.setWidth(580);
        primaryStage.setHeight(500);

        Scene menuScene = new GeneralMenuScene(studentController,teacherController,adminController).getScene();


        primaryStage.setScene(menuScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
