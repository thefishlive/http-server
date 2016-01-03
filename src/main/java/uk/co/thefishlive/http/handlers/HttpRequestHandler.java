package uk.co.thefishlive.http.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import uk.co.thefishlive.http.data.HttpStatusCode;
import uk.co.thefishlive.http.data.HttpVersion;
import uk.co.thefishlive.http.data.headers.HttpHeaders;
import uk.co.thefishlive.http.data.payload.EmptyPayload;
import uk.co.thefishlive.http.data.payload.HttpPayload;
import uk.co.thefishlive.http.data.payload.StringPayload;
import uk.co.thefishlive.http.data.request.HttpRequest;
import uk.co.thefishlive.http.data.response.HttpResponse;
import uk.co.thefishlive.http.data.response.HttpStatusLine;

import java.util.List;

public class HttpRequestHandler extends MessageToMessageDecoder<HttpRequest> {
    @Override
    protected void decode(ChannelHandlerContext ctx, HttpRequest msg, List<Object> out) throws Exception {
        System.out.println(msg);

        HttpPayload payload = new StringPayload("Hello World");

        HttpHeaders headers = new HttpHeaders();
        headers.addHeader("Server", "http-server");
        headers.addHeader("Content-Type", "text/plain");
        headers.addHeader("ETag", payload.getETag());

        HttpStatusCode status = HttpStatusCode.OK;

        if (payload.getETag().equals(msg.getHeaderValue("If-None-Match"))) {
            status = HttpStatusCode.NOT_MODIFIED;
            payload = new EmptyPayload();
        }

        HttpStatusLine statusLine = new HttpStatusLine(HttpVersion.HTTP_1_1, status);

        HttpResponse response = new HttpResponse(statusLine, headers, payload);
        out.add(response);
    }
}
