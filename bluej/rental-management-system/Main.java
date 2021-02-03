import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    //singleton instance of filehandlers
    FileHandlers fileHandlers = FileHandlers.getInstance();

    @Override
    public void start(Stage primaryStage) throws Exception {
        //load all the data here- now just adding dummy data
        fileHandlers.readUsersFromFile();
        fileHandlers.getPropertyFromFile();
        fileHandlers.loadPropertyToUsers();

        //load role chooser page
        Controller.stage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("roleChooser.fxml"));
        loader.setController(new RoleChooserController());
        Scene scene = new Scene(loader.load(), 700, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Rental Management System");
        primaryStage.show();

        //custom close function that saves to file first
        //makes sure data saved for next visit
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
        //manually close
        Controller.stage.close();
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}