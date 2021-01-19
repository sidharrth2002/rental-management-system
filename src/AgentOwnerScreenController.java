import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class AgentOwnerScreenController extends Controller implements Initializable {
    public VBox root;
    public Text welcomeMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String name =  ((User)stage.getUserData()).getName();
        welcomeMessage.setText("Welcome " + name);
    }
}
