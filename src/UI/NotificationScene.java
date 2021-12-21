package UI;

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

public class NotificationScene {
    /**
     * Show a popUp window with specified string
     * @param string that will be displayed
     * @return Stage
     */
    public static Stage getInfoMessage(String string){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Info");
        stage.setWidth(600);
        stage.setHeight(200);

        Group root = new Group();
        Scene scene = new Scene(root);

        Label lb_title = new Label(string);
        lb_title.setFont(new Font("Arial", 20));
        lb_title.setStyle("-fx-text-fill: #000000;");

        HBox layout_Message = new HBox();
        layout_Message.setAlignment(Pos.CENTER);
        layout_Message.getChildren().addAll(lb_title);

        VBox layout_Start = new VBox();
        layout_Start.setPadding(new Insets(50,20,20,60));
        layout_Start.setAlignment(Pos.CENTER);
        layout_Start.setSpacing(30);
        layout_Start.getChildren().addAll(layout_Message);

        ((Group)scene.getRoot()).getChildren().addAll(layout_Start);
        stage.setScene(scene);
        return stage;
    }

    /**
     * Show a popUp window with specified string as an error message
     * @param string that will be displayed
     * @return Stage
     */
    public static Stage getErrorMessage(String string){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Error");
        stage.setWidth(600);
        stage.setHeight(200);

        Group root = new Group();
        Scene scene = new Scene(root);

        Label lb_title = new Label(string);
        lb_title.setFont(new Font("Arial", 20));
        lb_title.setStyle("-fx-text-fill: #c4290a;");
        lb_title.setWrapText(true);

        HBox layout_Message = new HBox();
        layout_Message.setAlignment(Pos.CENTER);
        layout_Message.getChildren().addAll(lb_title);

        VBox layout_Start = new VBox();
        layout_Start.setPadding(new Insets(50,20,20,60));
        layout_Start.setAlignment(Pos.CENTER);
        layout_Start.setSpacing(30);
        layout_Start.getChildren().addAll(layout_Message);

        ((Group)scene.getRoot()).getChildren().addAll(layout_Start);
        stage.setScene(scene);
        return stage;
    }
}
