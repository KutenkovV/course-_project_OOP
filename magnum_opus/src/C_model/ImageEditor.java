package C_model;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

// класс для сохранения и открытия изображений
public class ImageEditor {

    public void OpenImg(ImageView photo_load, Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения
        fileChooser.setTitle("Выбор изображения ");//Заголовок диалога
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File file = fileChooser.showOpenDialog(primaryStage.getOwner());//Указываем текущую сцену

        if (file != null) {

            Image image = new Image("file:" + file.getAbsolutePath());
            photo_load.setImage(image);
        }
    }

    public void saveImgAs(Node node, Stage primaryStage) {
        SnapshotParameters ssp = new SnapshotParameters();

        FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения
        fileChooser.setTitle("Сохранение резюме ");//Заголовок диалога
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        File file = fileChooser.showSaveDialog(primaryStage.getOwner());//Указываем текущую сцену
        if (file != null) {

            WritableImage image = node.snapshot(ssp, null);

            file = new File(file.getPath());
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            } catch (IOException ex) {
                System.out.println("error! " + ex.toString());
            }
        }
    }
}
