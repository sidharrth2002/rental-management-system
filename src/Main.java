import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {
    Scene roleChooser;
    Scene loginScreen;
    String role;

    @Override
    public void start(Stage primaryStage) throws Exception{
        HBox roleChooserPane = new HBox();
        HBox loginPane = new HBox();
        roleChooser = new Scene(roleChooserPane, 900, 700);
        loginScreen = new Scene(loginPane, 900, 700);

        roleChooserPane.setPadding(new Insets(15, 12, 15, 12));
        roleChooserPane.setSpacing(10);
        Button owner = new Button("Owner");
        Button tenant = new Button("Tenant");
        Button agent = new Button("agent");
        owner.setOnAction(e -> {
            role = "owner";
            showLoginScreen();
            primaryStage.setScene(loginScreen);
        });

        loginPane.getChildren().add(new Text("You are an " + role));

        roleChooserPane.getChildren().addAll(owner, tenant, agent);

        primaryStage.setScene(roleChooser);
//        switch(sceneToShow) {
//            case "roleChooser":
//                showRoleChooser(primaryStage);
//                break;
//            case "loginScreen":
//                System.out.println("login screen now");
////                showLoginScreen(primaryStage);
//                break;
//            case "homepage":
////                showHomepage(primaryStage);
//                break;
//        }
//        primaryStage.setScene(new Scene(new VBox(), 900, 700));
        primaryStage.show();
    }

    public void showRoleChooser(Stage primaryStage) {

    }
//    public void showLoginScene

    public static void main(String[] args) {
        launch(args);
    }
}
