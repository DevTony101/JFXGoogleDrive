package jfxgoogledrive;

import java.net.URL;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utilities.*;

public class MainApp extends Application {

    // Define your offsets here
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage) throws Exception {
        URL path = getClass().getResource(Constants.FXML_HOME);
        if (path != null) {
            Parent root = FXMLLoader.load(path);
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle(Constants.APP_TITLE);
            stage.initStyle(StageStyle.TRANSPARENT);

            // Grab your root here
            root.setOnMousePressed((MouseEvent event) -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            // Move around here
            root.setOnMouseDragged((MouseEvent event) -> {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            });

            stage.show();
        } else {
            System.err.println("Error Initialazing FXML Home");
            System.exit(-1);
        }
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
