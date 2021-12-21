package UI;

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Course;

public class DeleteTeacherCourseScene {
    /**
     * Returns stage for deleting a course owned by a teacher
     * @param teacherController used to delete a course
     * @return Stage
     */
    public static Stage getStage(TeacherController teacherController){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Delete my course Course");
        stage.setWidth(400);
        stage.setHeight(400);

        Group root = new Group();
        Scene scene = new Scene(root);

        Label lb_title = new Label("Your courses:");
        lb_title.setFont(new Font("Arial", 18));
        lb_title.setStyle("-fx-text-fill: #000000;");

        ObservableList<String> stringCourses = FXCollections.observableArrayList();


        ToggleGroup radioBtnGroup = new ToggleGroup();
        VBox layout_radioBtn = new VBox();
        layout_radioBtn.setSpacing(10);
        layout_radioBtn.setAlignment(Pos.CENTER_LEFT);



        for(Course course:teacherController.getOwnCourses()) {

            String string = course.toString();

            RadioButton radioButton = new RadioButton(string);
            radioButton.setFont(new Font("Arial", 14));
            radioButton.setUserData(course.getCourseId());
            radioButton.setToggleGroup(radioBtnGroup);
            layout_radioBtn.getChildren().addAll(radioButton);

        }


        Button btn_submit = new Button("Submit");
        btn_submit.setFont(new Font("Arial", 20));
        btn_submit.setStyle("-fx-background-color: #2ba9fc; -fx-border-color: #eeeeee; -fx-border-width: 1px; -fx-text-fill: #ffffff;");
        btn_submit.setOnAction((ActionEvent e)->{
            long option = (long)radioBtnGroup.getSelectedToggle().getUserData();
            boolean deleteStatus = teacherController.deleteOwnCourse(option);
            stage.close();
            if(deleteStatus)
                NotificationScene.getInfoMessage("You successfully deleted your course").showAndWait();
            else
                NotificationScene.getErrorMessage("You don't own this course").showAndWait();
        });

        HBox layout_btnSubmit = new HBox();
        layout_btnSubmit.getChildren().addAll(btn_submit);
        layout_btnSubmit.setAlignment(Pos.CENTER);


        VBox layout_Start = new VBox();
        layout_Start.setPadding(new Insets(50,20,20,60));
        layout_Start.setAlignment(Pos.CENTER);
        layout_Start.setSpacing(30);
        layout_Start.getChildren().addAll(lb_title,layout_radioBtn,layout_btnSubmit);

        ((Group)scene.getRoot()).getChildren().addAll(layout_Start);
        stage.setScene(scene);
        return stage;
    }
}
