package uk.co.thefishlive.http.data.payload;

import io.netty.buffer.ByteBuf;
import uk.co.thefishlive.http.crypt.HashUtil;

import java.nio.charset.Charset;

public class StringPayload implements HttpPayload {

    private final String contentType;
    private final String payload;

    public StringPayload(String payload) {
        this(payload, "text/plain");
    }

    public StringPayload(String payload, String contentType) {
        this.payload = payload;
        this.contentType = contentType;
    }

    @Override
    public void write(ByteBuf data) {
        data.writeBytes(payload.getBytes());
    }

    @Override
    public String getETag() {
        return HashUtil.sha256(payload.getBytes(Charset.forName("UTF-8")));
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public String toString() {
        return "StringPayload{" +
                "payload='" + payload + '\'' +
                '}';
    }
}
