package uk.co.thefishlive.http;

import uk.co.thefishlive.http.config.ConfigManager;
import uk.co.thefishlive.http.config.content.ContentTypesConfigFile;
import uk.co.thefishlive.http.config.directories.DirectoriesConfigFile;
import uk.co.thefishlive.http.config.server.ServerConfigFile;

public class Main {

    public static void main(String[] args) {
        ConfigManager.registerConfig("server", ServerConfigFile.class);
        ConfigManager.registerConfig("directories", DirectoriesConfigFile.class);
        ConfigManager.registerConfig("content_types", ContentTypesConfigFile.class);

        int port = ConfigManager.getConfig(ServerConfigFile.class).getServer().getPort();
        new Server(port).run();
    }
}
