package uk.co.thefishlive.http.config.content;

import com.google.gson.annotations.SerializedName;
import uk.co.thefishlive.http.config.ConfigFile;

import java.io.File;
import java.util.Map;

public class ContentTypesConfigFile extends ConfigFile {

    @SerializedName("default")
    private String defaultType;

    private Map<String, String> types;

    public String getDefaultType() {
        return defaultType;
    }

    public Map<String, String> getTypes() {
        return types;
    }

    public String getContentType(File file) {
        String ext = file.getName().substring(file.getName().lastIndexOf('.') + 1);
        String type = types.get(ext);

        if (type == null) {
            return defaultType;
        }

        return type;
    }
}
