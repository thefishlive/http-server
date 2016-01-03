package uk.co.thefishlive.http.data.response;

import io.netty.buffer.ByteBuf;
import uk.co.thefishlive.http.data.headers.HttpHeader;
import uk.co.thefishlive.http.data.headers.HttpHeaders;
import uk.co.thefishlive.http.data.HttpMessage;
import uk.co.thefishlive.http.data.payload.HttpPayload;

public class HttpResponse extends HttpMessage<HttpStatusLine> {

    public HttpResponse(HttpStatusLine startLine, HttpHeaders headers, HttpPayload payload) {
        super(startLine, headers, payload);
    }

    public void write(ByteBuf data) {

        data.writeBytes(startLine.toString().getBytes()).writeByte('\n');

        for (HttpHeader header : headers.getHeaders()) {
            data.writeBytes(header.toString().getBytes()).writeByte('\n');
        }

        data.writeByte('\n');
        payload.write(data);
    }
}
