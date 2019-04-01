import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.*;
import javafx.scene.image.*;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("gui/Home.fxml"));
        primaryStage.setTitle("DnD Simulator");

        Scene scene = new Scene(root, 600, 400);
        Image image = new Image("img/sword.png");
        scene.setCursor(new ImageCursor(image));
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
