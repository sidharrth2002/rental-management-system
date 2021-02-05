//This was written by Piragash Maran
//1181101448

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class RoleChooserController extends Controller {
    public VBox root;

    public void goToLogin(ActionEvent actionEvent) throws IOException {
        Button button = (Button) actionEvent.getSource();
        Stage stage = (Stage) root.getScene().getWindow();
        //identify type of user since this controller is used by multiple pages
        if(button.getId().equals("admin")) {
            LoginController.typeOfUser = "admin";
        } else if(button.getId().equals("owner")) {
            LoginController.typeOfUser = "owner";
        } else if(button.getId().equals("agent")) {
            LoginController.typeOfUser = "agent";
        } else if(button.getId().equals("tenant")) {
            LoginController.typeOfUser = "tenant";
        }
        //login page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        loader.setController(new LoginController());
        Scene scene = new Scene(loader.load(), 700, 600);
        stage.setScene(scene);
    }

    //go to search page
    public void goToVisitorDashboard(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
        loader.setController(new VisitorDashboardController());
        Scene scene = new Scene(loader.load(), 700, 600);
        stage.setScene(scene);
    }
}
