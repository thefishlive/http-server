package uk.co.thefishlive.http.config.server;

import uk.co.thefishlive.http.config.ConfigFile;

import java.util.List;
import java.util.Map;

public class ServerConfigFile extends ConfigFile {

    public static final int CURRENT_VERSION = 1;

    private ServerInfo server;
    private Map<String, LoggerInfo> logging;
    private List<String> adapters;

    public static class ServerInfo {
        private int port;

        public int getPort() {
            return port;
        }
    }

    public static class LoggerInfo {
        private boolean enabled;
        private String file;

        public boolean isEnabled() {
            return enabled;
        }

        public String getFile() {
            return file;
        }
    }

    public ServerInfo getServer() {
        return server;
    }

    public Map<String, LoggerInfo> getLogging() {
        return logging;
    }

    public List<String> getAdapters() {
        return adapters;
    }
}
