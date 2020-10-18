package example;

import example.TaskPerformer;
import example.Utils.CustomAlert;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;

public class Controller {

    public Button openRouteButton;
    public TextField pipeText;
    public Button openDatabaseButton;
    public TextField databaseText;
    public Button submit;
    public TextField routeText;
    public Button openPipeButton;


    @FXML
    public void initialize() {
        openRouteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                FileChooser fc = new FileChooser();
                fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("csv Files", "*.csv"));
                File f = fc.showOpenDialog(null);
                if (f != null) {
                    routeText.setText(f.getAbsolutePath());
                }
            }
        });

        openPipeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                FileChooser fc = new FileChooser();
                fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("csv Files", "*.csv"));
                File f = fc.showOpenDialog(null);
                if (f != null) {
                    pipeText.setText(f.getAbsolutePath());
                }
            }
        });

        openDatabaseButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                FileChooser fc = new FileChooser();
                fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("DB Files", "*.db"));
                File f = fc.showOpenDialog(null);

                //exclude mv.db part for db connection
                if (f != null) {
                    databaseText.setText(f.getAbsolutePath().replaceAll(".mv.db", ""));
                }
            }
        });

        submit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                File pipeFile = new File(pipeText.getText());

//                include mv.db to check if file exists
                File dbFile = new File(databaseText.getText() + ".mv.db");
                File routeFile = new File(routeText.getText());

                if (pipeFile.exists() && !pipeFile.isDirectory()
                        && dbFile.exists() && !dbFile.isDirectory()
                        && routeFile.exists() && !routeFile.isDirectory()) {

                    System.out.println("File path is OK");
                    try {
                        TaskPerformer taskPerformer = new TaskPerformer(pipeText.getText(), routeText.getText(), databaseText.getText());
                        taskPerformer.generateFiles();
                        CustomAlert.showAlert("Success","Successfully written to file and DB",true);
                    } catch (Exception e) {
                        e.printStackTrace();
                        CustomAlert.showAlert("Error","check your DB files",false);
                    }
                } else {
                    CustomAlert.showAlert("File Error","check your DB files",false);
                }
            }
        });
    }

}
