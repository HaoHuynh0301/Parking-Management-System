package libs;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

public class fileActivity {
    public fileActivity() {}

    public void createFolder_date(String folder_usesr, String folder_name) {
        String path = "D:\\Carparking_2\\src\\data\\" + folder_usesr + "\\" + folder_name + "\\";
        //Creating a File object
        File file = new File(path);
        //Creating the directory
        boolean bool = file.mkdir();
        if(bool){
            System.out.println("Directory created successfully");
        }else{
            System.out.println("Sorry couldn’t create specified directory");
        }
    }

    public void createFolder_user(String folder_name) {
        String path = "D:\\Carparking_2\\src\\data\\" + folder_name + "\\";
        //Creating a File object
        File file = new File(path);
        //Creating the directory
        boolean bool = file.mkdir();
        if(bool){
            System.out.println("Directory created successfully");
        }else{
            System.out.println("Sorry couldn’t create specified directory");
        }
    }

    public void deleteDirectory(String dir) throws IOException {
        Path path = new Path(dir)
        file.delete();
        System.out.println("Files deleted........");
    }

    public static void deleteDirectoryJava8Extract(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            System.err.printf("Unable to delete this path : %s%n%s", path, e);
        }
    }

}
