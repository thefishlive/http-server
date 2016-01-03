package uk.co.thefishlive.http.data.payload;

import io.netty.buffer.ByteBuf;

public interface HttpPayload {

    void write(ByteBuf data);

    String getETag();

}
