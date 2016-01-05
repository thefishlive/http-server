package uk.co.thefishlive.http.config.directories;

import uk.co.thefishlive.http.config.ConfigFile;

import java.util.List;
import java.util.Map;

public class DirectoriesConfigFile extends ConfigFile {

    private String root;
    private Map<String, Directory> directories;

    public static class Directory {
        private String directory;
        private AccessControl access;

        public String getDirectory() {
            return directory;
        }

        public AccessControl getAccess() {
            return access;
        }

        public static class AccessControl {
            private String level;

            public String getLevel() {
                return level;
            }
        }
    }

    public String getRoot() {
        return root;
    }

    public Map<String, Directory> getDirectories() {
        return directories;
    }

    public Directory getDirectory(String id) {
        return this.directories.get(id);
    }
}
