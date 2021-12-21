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

public class DeleteCourseScene {
    /**
     * creates a Stage to delete a course in
     * @param adminController Controller used for the methods
     * @return stage
     */
    public static Stage getScene(AdminController adminController){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Delete Course");
        stage.setWidth(650);
        stage.setHeight(500);

        Group root = new Group();
        Scene scene = new Scene(root);

        Label lb_title = new Label("Delete Course");
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

        //Check if course exists, if not, add it
        Button btn_submit = new Button("Submit");
        btn_submit.setFont(new Font("Arial", 20));
        btn_submit.setStyle("-fx-background-color: #2ba9fc; -fx-border-color: #eeeeee; -fx-border-width: 1px; -fx-text-fill: #ffffff;");
        btn_submit.setOnAction((ActionEvent e)->{
            String name = tf_courseName.getText();
            if(adminController.existsCourse(name)){
                adminController.deleteCourse(name);
                stage.close();
            }
            else {
                lb_error.setText("Course doesn't exist!");
            }
        });

        HBox layout_courseName = new HBox();
        layout_courseName.getChildren().addAll(lb_courseName,tf_courseName);
        layout_courseName.setAlignment(Pos.CENTER);

        HBox layout_btnSubmit = new HBox();
        layout_btnSubmit.getChildren().addAll(btn_submit);
        layout_btnSubmit.setAlignment(Pos.CENTER);


        VBox layout_Start = new VBox();
        layout_Start.setPadding(new Insets(50,20,20,60));
        layout_Start.setAlignment(Pos.CENTER);
        layout_Start.setSpacing(30);
        layout_Start.getChildren().addAll(lb_title,layout_courseName,layout_btnSubmit,lb_error);

        ((Group)scene.getRoot()).getChildren().addAll(layout_Start);
        stage.setScene(scene);

        return stage;
    }
}
