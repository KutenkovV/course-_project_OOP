package A_sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../B_resume_editor/creatingResume.fxml"));
        primaryStage.setTitle("Конструктор резюме");
        primaryStage.setScene(new Scene(root, 879, 965));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
