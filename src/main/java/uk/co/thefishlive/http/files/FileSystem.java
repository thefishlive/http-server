package uk.co.thefishlive.http.files;

import uk.co.thefishlive.http.config.ConfigManager;
import uk.co.thefishlive.http.config.directories.DirectoriesConfigFile;
import uk.co.thefishlive.http.data.HttpStatusCode;
import uk.co.thefishlive.http.data.payload.FilePayload;
import uk.co.thefishlive.http.exception.HttpException;

import java.io.File;
import java.io.IOException;

public class FileSystem {

    private static final FileSystem fileSystem = new FileSystem();

    public static FileSystem getDefaultFileSystem() {
        return fileSystem;
    }

    private File rootDir;

    public FileSystem() {
        DirectoriesConfigFile config = ConfigManager.getConfig(DirectoriesConfigFile.class);
        String rootId = config.getRoot();
        rootDir = new File(config.getDirectory(rootId).getDirectory());
    }

    public FilePayload findFile(String request) throws IOException, HttpException {
        if (request.endsWith("/")) {
            request += "index.html";
        }

        File file = new File(rootDir, request);

        if (!file.exists()) {
            throw new HttpException(HttpStatusCode.NOT_FOUND);
        }

        return new FilePayload(file);
    }
}
