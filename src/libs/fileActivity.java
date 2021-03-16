package libs;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class fileActivity {
    public fileActivity() {}

    public void createFolder(String folder_name) {
        File file = new File(folder_name);
        file.mkdirs();
        // true if the directory was created, false otherwise
        if (file.mkdirs()) {
            System.out.println("Directory is created!");
        } else {
            System.out.println("Failed to create directory!");
        }
    }
}
