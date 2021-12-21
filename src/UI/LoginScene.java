package UI;

import controller.StudentController;
import controller.TeacherController;
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

public class LoginScene {

    /**
     * @param studentController studentController used by student menu
     * @return Stage for student menu
     */
    public static Stage getStage(StudentController studentController){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Login");
        stage.setWidth(400);
        stage.setHeight(400);

        Group root = new Group();
        Scene scene = new Scene(root);


        Label lb_title = new Label("Login");
        lb_title.setFont(new Font("Arial", 40));
        lb_title.setStyle("-fx-text-fill: #000000;");

        // labels and text fields for login
        Label lb_firstName = new Label("First name:");
        lb_firstName.setFont(new Font("Arial", 20));
        TextField tf_firstName = new TextField();

        Label lb_lastName = new Label("Last name:");
        lb_lastName.setFont(new Font("Arial", 20));
        TextField tf_lastName = new TextField();

        Label lb_error=new Label("");
        lb_error.setFont(new Font("Arial", 20));
        lb_error.setStyle("-fx-text-fill: #ff0000;");




        Button btn_submit = new Button("Submit");
        btn_submit.setFont(new Font("Arial", 20));
        btn_submit.setStyle("-fx-background-color: #2ba9fc; -fx-border-color: #eeeeee; -fx-border-width: 1px; -fx-text-fill: #ffffff;");
        btn_submit.setOnAction((ActionEvent e)->{

            String firstName = tf_firstName.getText();
            if(studentController.checkLogin(tf_firstName.getText(),tf_lastName.getText())){
                stage.close();
            }
            else{
                lb_error.setText("Failed to login please try again!");
            }
        });

        HBox layout_firstName = new HBox();
        layout_firstName.getChildren().addAll(lb_firstName,tf_firstName);
        layout_firstName.setAlignment(Pos.CENTER);
        HBox layout_lastName = new HBox();
        layout_lastName.getChildren().addAll(lb_lastName,tf_lastName);
        layout_lastName.setAlignment(Pos.CENTER);

        HBox layout_btnSubmit = new HBox();
        layout_btnSubmit.getChildren().addAll(btn_submit);
        layout_btnSubmit.setAlignment(Pos.CENTER);


        VBox layout_Start = new VBox();
        layout_Start.setPadding(new Insets(50,20,20,60));
        layout_Start.setAlignment(Pos.CENTER);
        layout_Start.setSpacing(30);
        layout_Start.getChildren().addAll(lb_title,layout_firstName,layout_lastName,layout_btnSubmit,lb_error);

        ((Group)scene.getRoot()).getChildren().addAll(layout_Start);
        stage.setScene(scene);
        return stage;
    }

    /**
     * @param teacherController teacherController used by teacher menu
     * @return Stage for student menu
     */
     public static Stage getStage(TeacherController teacherController){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Login");
        stage.setWidth(400);
        stage.setHeight(400);

        Group root = new Group();
        Scene scene = new Scene(root);

        Label lb_title = new Label("Login");
        lb_title.setFont(new Font("Arial", 40));
        lb_title.setStyle("-fx-text-fill: #000000;");

        Label lb_firstName = new Label("First name:");
        lb_firstName.setFont(new Font("Arial", 20));
        TextField tf_firstName = new TextField();

        Label lb_lastName = new Label("Last name:");
        lb_lastName.setFont(new Font("Arial", 20));
        TextField tf_lastName = new TextField();

        Label lb_error=new Label("");
        lb_error.setFont(new Font("Arial", 20));
        lb_error.setStyle("-fx-text-fill: #ff0000;");




        Button btn_submit = new Button("Submit");
        btn_submit.setFont(new Font("Arial", 20));
        btn_submit.setStyle("-fx-background-color: #2ba9fc; -fx-border-color: #eeeeee; -fx-border-width: 1px; -fx-text-fill: #ffffff;");
        btn_submit.setOnAction((ActionEvent e)->{
            String firstName = tf_firstName.getText();
            if(teacherController.checkLogin(tf_firstName.getText(),tf_lastName.getText())){
                stage.close();
            }
            else{
                lb_error.setText("Failed to login please try again!");
            }
        });

        HBox layout_firstName = new HBox();
        layout_firstName.getChildren().addAll(lb_firstName,tf_firstName);
        layout_firstName.setAlignment(Pos.CENTER);
        HBox layout_lastName = new HBox();
        layout_lastName.getChildren().addAll(lb_lastName,tf_lastName);
        layout_lastName.setAlignment(Pos.CENTER);

        HBox layout_btnSubmit = new HBox();
        layout_btnSubmit.getChildren().addAll(btn_submit);
        layout_btnSubmit.setAlignment(Pos.CENTER);


        VBox layout_Start = new VBox();
        layout_Start.setPadding(new Insets(50,20,20,60));
        layout_Start.setAlignment(Pos.CENTER);
        layout_Start.setSpacing(30);
        layout_Start.getChildren().addAll(lb_title,layout_firstName,layout_lastName,layout_btnSubmit,lb_error);

        ((Group)scene.getRoot()).getChildren().addAll(layout_Start);
        stage.setScene(scene);
        return stage;
    }
}
