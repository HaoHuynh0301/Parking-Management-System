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

    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete(); // The directory is empty now and can be deleted.
    }
}
