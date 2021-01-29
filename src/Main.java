import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    FileHandlers fileHandlers = FileHandlers.getInstance();

    @Override
    public void start(Stage primaryStage) throws Exception {
        //load all the data here- now just adding dummy data
        fileHandlers.readUsersFromFile();
        fileHandlers.getPropertyFromFile();
        fileHandlers.loadPropertyToUsers();

        //temp to test
        Parent root = FXMLLoader.load(getClass().getResource("roleChooser.fxml"));
        Scene scene = new Scene(root, 700, 600);
        Controller.stage = primaryStage;
        primaryStage.setTitle("Rental Management System");
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            try {
                closeProgram();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }

    //safe exit
    private void closeProgram() throws IOException {
        //save to owners, tenants and agents
        //save to properties
        fileHandlers.saveOwnersToFile();
        fileHandlers.saveAgentsToFile();
        fileHandlers.saveTenantsToFile();
        fileHandlers.saveAdminsToFile();
        fileHandlers.savePropertyToFile();
        Controller.stage.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}