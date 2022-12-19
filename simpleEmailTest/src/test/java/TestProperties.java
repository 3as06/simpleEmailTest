import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
    protected static FileInputStream fileInputStream;
    public static Properties properties;
    static {
        try {
            fileInputStream = new FileInputStream("src/test/resources/test.properties");
            properties = new Properties();
            properties.load(fileInputStream);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
