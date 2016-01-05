package uk.co.thefishlive.http.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class ConfigManager {

    private static final Map<Class<? extends ConfigFile>, ConfigFile> configs = new HashMap<>();
    private static File basedir = new File("config");

    private static final Gson GSON = new GsonBuilder().create();

    @SuppressWarnings("unchecked")
    public static <T extends ConfigFile> T getConfig(Class<? extends T> clazz) {
        T t = (T) configs.get(clazz);

        if (t == null) {
            throw new IllegalArgumentException(String.format("The class %s is not registered as a config file", clazz.getName()));
        }

        return t;
    }

    public static void registerConfig(String file, Class<? extends ConfigFile> clazz) {
        try {
            ConfigFile config = GSON.fromJson(new FileReader(new File(basedir, String.format("%s.json", file))), clazz);
            configs.put(clazz, config);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error loading config file", e);
        }
    }

    public static int getCurrentVersion(Class<? extends ConfigFile> clazz) throws ReflectiveOperationException {
        return (int) clazz.getDeclaredField("CURRENT_VERSION").get(null);
    }
}
