package jfxgoogledrive.controllers;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jfxgoogledrive.drive.DriveController;
import com.google.api.services.drive.model.File;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import utilities.Constants;

public class HomeController implements Initializable {

    @FXML
    private Label lblEmail;

    @FXML
    private VBox vbFiles;

    @FXML
    private void closeWindow(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void hideWindow(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void uploadFile(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File to Google Drive");
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        java.io.File archivo = fileChooser.showOpenDialog(stage);
        System.out.println(archivo.getName());
        try {
            DriveController.uploadFile(archivo);
        } catch (IOException | GeneralSecurityException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            List<File> files = DriveController.connect();
            lblEmail.setText(DriveController.USER_EMAIL);
            Node[] nodes = new Node[files.size()];
            for (int i = 0; i < nodes.length; i++) {
                //load specific item
                FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.FXML_FILE_ITEM));
                FileItemController controller = new FileItemController();
                loader.setController(controller);
                nodes[i] = loader.load();
                vbFiles.getChildren().add(nodes[i]);
                controller.setFile(files.get(i));
            }
        } catch (IOException | GeneralSecurityException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
