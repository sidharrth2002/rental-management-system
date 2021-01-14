import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.scene.shape.Box;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VisitorDashboardController extends Controller implements Initializable {
    public TextField searchField;
    public VBox propertyTable;
    public VBox menuArea;

    public VisitorDashboardController() {
        System.out.println("Child controller");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //load property arraylists temporarily
    }

    public void searchProperty(KeyEvent e) {
        propertyTable.getChildren().clear();
        String searchText = searchField.getText();
        System.out.println("Search with the keyword. " + searchText);
        if(searchText.length() != 0) {
            ArrayList<Property> results = propertySearchFacade.getByKeyword(searchText);
            for (Property property : results) {
                HBox outerBox = new HBox();
                outerBox.setSpacing(10);
                outerBox.setAlignment(Pos.CENTER_LEFT);
                outerBox.setPadding(new Insets(10, 10, 10, 10));
                VBox tableEntry = new VBox();
                tableEntry.setPadding(new Insets(10, 10, 10, 10));
//                outerBox.getChildren().add(new Separator());
                tableEntry.getChildren().add(new Text(property.getName()));
                tableEntry.getChildren().add(new Text(property.getAddress()));
                tableEntry.getChildren().add(new Text(Double.toString(property.getPrice())));
                outerBox.getChildren().add(tableEntry);
                Button seeMore = new Button("View Details");
                seeMore.setOnAction(event -> {
                    //change to scene of property page
                });

                outerBox.getChildren().add(seeMore);
//                outerBox.getChildren().add(new Separator());
                propertyTable.getChildren().add(outerBox);
            }
        }
    }

    public void sortByPrice(ActionEvent e) {
        propertyTable.getChildren().clear();
        ArrayList<Property> results = propertySearchFacade.getByPrice();

        for (Property property : results) {
            HBox outerBox = new HBox();
            outerBox.setSpacing(10);
            outerBox.setAlignment(Pos.CENTER_LEFT);
            outerBox.setPadding(new Insets(10, 10, 10, 10));
            VBox tableEntry = new VBox();
            tableEntry.setPadding(new Insets(10, 10, 10, 10));
//            outerBox.getChildren().add(new Separator());
            tableEntry.getChildren().add(new Text(property.getName()));
            tableEntry.getChildren().add(new Text(property.getAddress()));
            tableEntry.getChildren().add(new Text(Double.toString(property.getPrice())));
            outerBox.getChildren().add(tableEntry);
            Button seeMore = new Button("View Details");
            seeMore.setOnAction(event -> {
                //change to scene of property page
            });

            outerBox.getChildren().add(seeMore);
//            outerBox.getChildren().add(new Separator());
            propertyTable.getChildren().add(outerBox);
        }
//        for (Property property: results) {
//            VBox tableEntry = new VBox();
//            tableEntry.setPadding(new Insets(10, 10, 10, 10));
//            tableEntry.getChildren().add(new Separator());
//            tableEntry.getChildren().add(new Box(10, 10, 10));
//            tableEntry.getChildren().add(new Text(property.getName()));
//            tableEntry.getChildren().add(new Text(property.getAddress()));
//            tableEntry.getChildren().add(new Text(Double.toString(property.getPrice())));
//            tableEntry.getChildren().add(new Box(10, 10, 10));
//            tableEntry.getChildren().add(new Separator());
//            propertyTable.getChildren().add(tableEntry);
//        }
    }

    public void sortByType(ActionEvent e) {

    }

}