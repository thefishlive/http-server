package uk.co.thefishlive.http.data.payload;

import io.netty.buffer.ByteBuf;
import uk.co.thefishlive.http.config.ConfigManager;
import uk.co.thefishlive.http.config.content.ContentTypesConfigFile;
import uk.co.thefishlive.http.crypt.HashUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FilePayload implements HttpPayload {

    private File file;
    private byte[] contents;
    private String etag;
    private String contentType;

    public FilePayload(File file) throws IOException {
        this.file = file;
        this.contents = Files.readAllBytes(file.toPath());
    }

    @Override
    public void write(ByteBuf data) {
        data.writeBytes(contents);
    }

    @Override
    public String getETag() {
        if (etag == null) {
            etag = HashUtil.sha256(contents);
        }
        return etag;
    }

    @Override
    public String getContentType() {
        if (contentType == null) {
            contentType = ConfigManager.getConfig(ContentTypesConfigFile.class).getContentType(file);
        }
        return contentType;
    }

    @Override
    public String toString() {
        return "FilePayload{" +
                "file=" + file.getName() +
                "}";
    }
}
