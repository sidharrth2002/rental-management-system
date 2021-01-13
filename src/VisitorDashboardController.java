import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;

public class VisitorDashboardController extends Controller implements Initializable {
    public TextField searchField;
    public TableView propertyTable;
    public VBox menuArea;

    public void searchProperty(ActionEvent e) {
        String searchText = searchField.getText();
        System.out.println("Search with the keyword. " + searchText);
    }


}
