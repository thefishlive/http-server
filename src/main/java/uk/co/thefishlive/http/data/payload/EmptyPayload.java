package uk.co.thefishlive.http.data.payload;

import io.netty.buffer.ByteBuf;
import uk.co.thefishlive.http.crypt.HashUtil;

public class EmptyPayload implements HttpPayload {
    @Override
    public void write(ByteBuf data) {
    }

    @Override
    public String getETag() {
        return HashUtil.sha256(new byte[0]);
    }

    @Override
    public String getContentType() {
        return "text/plain";
    }

    @Override
    public String toString() {
        return "EmptyPayload{}";
    }
}
