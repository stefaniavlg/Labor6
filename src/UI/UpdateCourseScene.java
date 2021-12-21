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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class UpdateCourseScene {
    /**
     * creates a Stage to update a course in
     * @param adminController Controller used for the methods
     * @return stage
     */
    public static Stage getScene(AdminController adminController){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Update Course");
        stage.setWidth(650);
        stage.setHeight(600);

        Group root = new Group();
        Scene scene = new Scene(root);

        Label lb_title = new Label("Update Course");
        lb_title.setFont(new Font("Arial", 40));
        lb_title.setStyle("-fx-text-fill: #000000;");

        Label lb_Name = new Label("Course name:");
        lb_Name.setFont(new Font("Arial", 20));
        TextField tf_Name = new TextField();

        Label lb_change = new Label("Change:");
        lb_change.setFont(new Font("Arial", 20));
        TextField tf_change = new TextField();

        ToggleGroup radioBtnGroup = new ToggleGroup();
        VBox layout_radioBtn = new VBox();
        layout_radioBtn.setSpacing(10);
        layout_radioBtn.setAlignment(Pos.CENTER_LEFT);

        RadioButton radioName = new RadioButton("Course name");
        radioName.setFont(new Font("Arial", 14));
        radioName.setUserData("name");
        radioName.setToggleGroup(radioBtnGroup);

        RadioButton radioTeacher = new RadioButton("Teacher");
        radioTeacher.setFont(new Font("Arial", 14));
        radioTeacher.setUserData("teacher");
        radioTeacher.setToggleGroup(radioBtnGroup);

        RadioButton radioEnrolls = new RadioButton("Max enrollment");
        radioEnrolls.setFont(new Font("Arial", 14));
        radioEnrolls.setUserData("maxenr");
        radioEnrolls.setToggleGroup(radioBtnGroup);

        RadioButton radioCredits = new RadioButton("Credits");
        radioCredits.setFont(new Font("Arial", 14));
        radioCredits.setUserData("creds");
        radioCredits.setToggleGroup(radioBtnGroup);

        layout_radioBtn.getChildren().addAll(radioName, radioTeacher, radioEnrolls, radioCredits);

        Label lb_error=new Label("");
        lb_error.setFont(new Font("Arial", 20));
        lb_error.setStyle("-fx-text-fill: #ff0000;");
        lb_error.setMaxWidth(300);
        lb_error.setWrapText(true);


        Button btn_submit = new Button("Submit");
        btn_submit.setFont(new Font("Arial", 20));
        btn_submit.setStyle("-fx-background-color: #2ba9fc; -fx-border-color: #eeeeee; -fx-border-width: 1px; -fx-text-fill: #ffffff;");
        btn_submit.setOnAction((ActionEvent e)->{
            String option = (String)radioBtnGroup.getSelectedToggle().getUserData();
            String name = tf_Name.getText();
            if(adminController.existsCourse(name)) {
                if (option.equals("name")) {
                    String change = tf_change.getText();
                    if (adminController.existsCourse(change)) {
                        lb_error.setText("Course with new name already exists");
                    } else {
                        adminController.updateCourse(name,change,1);
                        stage.close();
                    }
                }
                else if (option.equals("teacher")) {
                    String change = tf_change.getText();
                    adminController.updateCourse(name, change, 2);
                    stage.close();
                }
                else if (option.equals("maxenr")) {
                    String change = tf_change.getText();
                    adminController.updateCourse(name, change, 3);
                    stage.close();
                }
                else if (option.equals("creds")) {
                    String change = tf_change.getText();
                    adminController.updateCourse(name, change, 4);
                    stage.close();
                }
            }
            else{
                lb_error.setText("Course doesn't exist");
            }
        });

        HBox layout_Name = new HBox();
        layout_Name.getChildren().addAll(lb_Name,tf_Name);
        layout_Name.setAlignment(Pos.CENTER);

        HBox layout_change = new HBox();
        layout_change.getChildren().addAll(lb_change,tf_change);
        layout_change.setAlignment(Pos.CENTER);

        HBox layout_btnSubmit = new HBox();
        layout_btnSubmit.getChildren().addAll(btn_submit);
        layout_btnSubmit.setAlignment(Pos.CENTER);

        VBox layout_Start = new VBox();
        layout_Start.setPadding(new Insets(50,20,20,60));
        layout_Start.setAlignment(Pos.CENTER);
        layout_Start.setSpacing(30);
        layout_Start.getChildren().addAll(lb_title,layout_Name,layout_radioBtn,layout_change,layout_btnSubmit,lb_error);

        ((Group)scene.getRoot()).getChildren().addAll(layout_Start);
        stage.setScene(scene);

        return stage;
    }
}
