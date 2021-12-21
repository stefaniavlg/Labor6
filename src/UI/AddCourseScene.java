package UI;

import controller.AdminController;
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

import java.util.List;

public class AddCourseScene {
    /**
     * creates a Stage to add a course in
     * @param adminController Controller used for the methods
     * @return stage
     */
    public static Stage getScene(AdminController adminController){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add new Course");
        stage.setWidth(650);
        stage.setHeight(500);

        Group root = new Group();
        Scene scene = new Scene(root);

        Label lb_title = new Label("Add new Course");
        lb_title.setFont(new Font("Arial", 40));
        lb_title.setStyle("-fx-text-fill: #000000;");

        Label lb_courseName = new Label("Course name:");
        lb_courseName.setFont(new Font("Arial", 20));
        TextField tf_courseName = new TextField();

        Label lb_maxStuds = new Label("Max students:");
        lb_maxStuds.setFont(new Font("Arial", 20));
        TextField tf_maxStuds = new TextField();

        Label lb_credits = new Label("Credit value:");
        lb_credits.setFont(new Font("Arial", 20));
        TextField tf_credits = new TextField();

        Label lb_error=new Label("");
        lb_error.setFont(new Font("Arial", 20));
        lb_error.setStyle("-fx-text-fill: #ff0000;");
        lb_error.setMaxWidth(300);
        lb_error.setWrapText(true);

        //Check if course exists, if not, add it
        Button btn_submit = new Button("Submit");
        btn_submit.setFont(new Font("Arial", 20));
        btn_submit.setStyle("-fx-background-color: #2ba9fc; -fx-border-color: #eeeeee; -fx-border-width: 1px; -fx-text-fill: #ffffff;");
        btn_submit.setOnAction((ActionEvent e)->{
            String name = tf_courseName.getText();
            if(adminController.existsCourse(name)){
                lb_error.setText("Course already exists!");
            }
            else {
                int studs = Integer.parseInt(tf_maxStuds.getText());
                int credits = Integer.parseInt(tf_credits.getText());
                adminController.addCourse(name,studs,credits);
                stage.close();
            }
        });

        HBox layout_courseName = new HBox();
        layout_courseName.getChildren().addAll(lb_courseName,tf_courseName);
        layout_courseName.setAlignment(Pos.CENTER);

        HBox layout_maxStuds = new HBox();
        layout_maxStuds.getChildren().addAll(lb_maxStuds,tf_maxStuds);
        layout_maxStuds.setAlignment(Pos.CENTER);

        HBox layout_credits = new HBox();
        layout_credits.getChildren().addAll(lb_credits,tf_credits);
        layout_credits.setAlignment(Pos.CENTER);

        HBox layout_btnSubmit = new HBox();
        layout_btnSubmit.getChildren().addAll(btn_submit);
        layout_btnSubmit.setAlignment(Pos.CENTER);


        VBox layout_Start = new VBox();
        layout_Start.setPadding(new Insets(50,20,20,60));
        layout_Start.setAlignment(Pos.CENTER);
        layout_Start.setSpacing(30);
        layout_Start.getChildren().addAll(lb_title,layout_courseName,layout_maxStuds,layout_credits,layout_btnSubmit,lb_error);

        ((Group)scene.getRoot()).getChildren().addAll(layout_Start);
        stage.setScene(scene);

        return stage;
    }
}
