import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("gui/Home.fxml"));
        primaryStage.setTitle("DnD Simulator");

        Scene scene = new Scene(root, 600, 400);
        Image image = new Image("media/img/sword.png");
        scene.setCursor(new ImageCursor(image));
        primaryStage.setScene(scene);
        playSound("intro.wav");
        primaryStage.show();

    }

    public void playSound(String sound){
        String relativeSoundPath = System.getProperty("user.dir");
        String fullSoundPath;
        fullSoundPath = relativeSoundPath + "\\media\\audio\\" + sound;
        File soundPath = new File(fullSoundPath);
        AudioClip clip = new AudioClip(soundPath.toURI().toString());
        clip.setCycleCount(5);
        clip.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
