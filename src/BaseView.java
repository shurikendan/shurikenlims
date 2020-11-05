import java.io.File;
import java.io.IOException;

public class BaseView {
    public static void main(String[] args) {
        try {
            Runtime.getRuntime().exec("C:\\Users\\Daniel\\IdeaProjects\\shurikenlims\\sqlite\\SQLiteStudio" +
                    "\\SQLiteStudio.exe", null, new File("C:\\Users\\Daniel\\IdeaProjects\\shurikenlims\\sqlite" +
                    "\\SQLiteStudio"));
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
