package ee.ivkhkdev.nptv23javafx;

import ee.ivkhkdev.nptv23javafx.service.AppService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

@Component
public class Nptv23JavaFxController {
    private final AppService appService;
    @FXML private Label label;

    public Nptv23JavaFxController(AppService appService) {
        this.appService = appService;
    }
//    @FXML private Button button;

    @FXML
    private void buttonClick(){
        label.setText("Hello from Controller");
    }
    @FXML
    private void button2Click(){
        label.setText(appService.myService());
    }
}
