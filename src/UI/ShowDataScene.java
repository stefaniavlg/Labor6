package UI;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Course;

public class ShowDataScene {

    /**
     * Returns a Stage showing data form ObservableList
     * @param title title of the scene
     * @param stringList list of string that will be shown
     * @return Stage
     */
    public static Stage getScene(String title,ObservableList<String> stringList){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Info " + title);
        stage.setWidth(500);
        stage.setHeight(500);

        Group root = new Group();
        Scene scene = new Scene(root);


        Label lb_title = new Label(title);
        lb_title.setFont(new Font("Arial", 40));
        lb_title.setStyle("-fx-text-fill: #000000;");
        lb_title.setMaxWidth(400);
        lb_title.setTextAlignment(TextAlignment.CENTER);
        lb_title.setWrapText(true);

        VBox layout_strings = new VBox();
        layout_strings.setSpacing(10);
        layout_strings.setAlignment(Pos.CENTER_LEFT);



        for(String str : stringList) {
            Label lb_string = new Label(str);
            lb_string.setFont(new Font("Arial", 20));
            lb_string.setStyle("-fx-text-fill: #000000;");
            lb_string.setMaxWidth(400);
            lb_string.setWrapText(true);

            layout_strings.getChildren().addAll(lb_string);

        }

        HBox layout_title = new HBox();
        layout_title.setAlignment(Pos.CENTER);
        layout_title.getChildren().addAll(lb_title);

        VBox layout_window = new VBox();
        layout_window.setPadding(new Insets(50,20,20,60));
        layout_window.setAlignment(Pos.CENTER);
        layout_window.setSpacing(30);
        layout_window.getChildren().addAll(layout_title,layout_strings);

        ((Group)scene.getRoot()).getChildren().addAll(layout_window);
        stage.setScene(scene);
        return stage;
    }

}
