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

public class UpdateTeacherScene {
    /**
     * creates a Stage to update a teacher in
     * @param adminController Controller used for the methods
     * @return stage
     */
    public static Stage getScene(AdminController adminController){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Update Teacher");
        stage.setWidth(650);
        stage.setHeight(600);

        Group root = new Group();
        Scene scene = new Scene(root);

        Label lb_title = new Label("Update Teacher");
        lb_title.setFont(new Font("Arial", 40));
        lb_title.setStyle("-fx-text-fill: #000000;");

        Label lb_firstName = new Label("First name:");
        lb_firstName.setFont(new Font("Arial", 20));
        TextField tf_firstName = new TextField();

        Label lb_lastName = new Label("Last name:");
        lb_lastName.setFont(new Font("Arial", 20));
        TextField tf_lastName = new TextField();

        Label lb_change = new Label("Change:");
        lb_change.setFont(new Font("Arial", 20));
        TextField tf_change = new TextField();

        ToggleGroup radioBtnGroup = new ToggleGroup();
        VBox layout_radioBtn = new VBox();
        layout_radioBtn.setSpacing(10);
        layout_radioBtn.setAlignment(Pos.CENTER_LEFT);

        RadioButton radioFirstName = new RadioButton("First name");
        radioFirstName.setFont(new Font("Arial", 14));
        radioFirstName.setUserData("first");
        radioFirstName.setToggleGroup(radioBtnGroup);

        RadioButton radioLastName = new RadioButton("Last name");
        radioLastName.setFont(new Font("Arial", 14));
        radioLastName.setUserData("last");
        radioLastName.setToggleGroup(radioBtnGroup);

        RadioButton radioCourses = new RadioButton("Courses");
        radioCourses.setFont(new Font("Arial", 14));
        radioCourses.setUserData("courses");
        radioCourses.setToggleGroup(radioBtnGroup);

        layout_radioBtn.getChildren().addAll(radioFirstName, radioLastName, radioCourses);

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
            String first = tf_firstName.getText();
            String last = tf_lastName.getText();
            if(adminController.existsTeacher(first,last)) {
                if (option.equals("first")) {
                    String change = tf_change.getText();
                    if (adminController.existsTeacher(change, last)) {
                        lb_error.setText("Teacher with new first name already exists");
                    }
                    else {
                        adminController.updateTeacher(first, last, change, 1);
                        stage.close();
                    }
                }
                else if (option.equals("last")) {
                    String change = tf_change.getText();
                    if (adminController.existsTeacher(first, change)) {
                        lb_error.setText("Teacher with new last name already exists");
                    }
                    else {
                        adminController.updateTeacher(first, last, change, 2);
                        stage.close();
                    }
                }
                else if (option.equals("courses")) {
                    String change = tf_change.getText();
                    int updaterez = adminController.updateTeacher(first, last, change, 3);
                    if (updaterez == 0) { lb_error.setText(""); stage.close();}
                    else { lb_error.setText("Course name invalid"); }
                }
            }
            else{
                lb_error.setText("Teacher doesn't exist");
            }
        });

        HBox layout_firstName = new HBox();
        layout_firstName.getChildren().addAll(lb_firstName,tf_firstName);
        layout_firstName.setAlignment(Pos.CENTER);

        HBox layout_lastName = new HBox();
        layout_lastName.getChildren().addAll(lb_lastName,tf_lastName);
        layout_lastName.setAlignment(Pos.CENTER);

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
        layout_Start.getChildren().addAll(lb_title,layout_firstName,layout_lastName,layout_radioBtn,layout_change,layout_btnSubmit,lb_error);

        ((Group)scene.getRoot()).getChildren().addAll(layout_Start);
        stage.setScene(scene);

        return stage;
    }
}
