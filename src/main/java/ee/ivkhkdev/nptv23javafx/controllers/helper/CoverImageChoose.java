package ee.ivkhkdev.nptv23javafx.controllers.helper;

import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.springframework.stereotype.Component;

import java.io.File;
@Component
public class CoverImageChoose {
    public File getSelectedCoverFile(Window window){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите обложку книги");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Изображения", "*.jpg", "*.jpeg", "*.png")
        );
        String userHome = System.getProperty("user.home");
        File picturesDir = new File(userHome, "Pictures");

        if (picturesDir.exists()) {
            fileChooser.setInitialDirectory(picturesDir);
        }
        return fileChooser.showOpenDialog(window);
    }
}
