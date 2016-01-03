package uk.co.thefishlive.http.data.payload;

import io.netty.buffer.ByteBuf;
import uk.co.thefishlive.http.crypt.HashUtil;

import java.nio.charset.Charset;

public class StringPayload implements HttpPayload {

    private String payload;

    public StringPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public void write(ByteBuf data) {
        data.writeBytes(payload.getBytes());
    }

    @Override
    public String getETag() {
        return HashUtil.sha256(payload.getBytes(Charset.forName("UTF-8")));
    }
}
