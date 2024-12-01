import java.util.HashMap;
import java.util.Map;

public class Database {
    private static Database instance;
    private Map<String, User> data;

    Database() {
        data = new HashMap<>();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void save(String key, User value) {
            data.put(key,value);
    }

    public User get(String key) {
        return data.get(key);
    }
}
