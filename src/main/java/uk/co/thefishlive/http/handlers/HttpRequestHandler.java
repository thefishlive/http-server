package uk.co.thefishlive.http.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import uk.co.thefishlive.http.data.HttpStatusCode;
import uk.co.thefishlive.http.data.HttpVersion;
import uk.co.thefishlive.http.data.headers.HttpHeaders;
import uk.co.thefishlive.http.data.payload.EmptyPayload;
import uk.co.thefishlive.http.data.payload.ErrorPayload;
import uk.co.thefishlive.http.data.payload.FilePayload;
import uk.co.thefishlive.http.data.payload.HttpPayload;
import uk.co.thefishlive.http.data.request.HttpRequest;
import uk.co.thefishlive.http.data.response.HttpResponse;
import uk.co.thefishlive.http.data.response.HttpStatusLine;
import uk.co.thefishlive.http.exception.HttpException;
import uk.co.thefishlive.http.files.FileSystem;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class HttpRequestHandler extends MessageToMessageDecoder<HttpRequest> {
    @Override
    protected void decode(ChannelHandlerContext ctx, HttpRequest msg, List<Object> out) throws Exception {
        System.out.println(msg);

        HttpStatusCode status = HttpStatusCode.OK;
        HttpPayload payload;

        try {
            payload = FileSystem.getDefaultFileSystem().findFile(msg.getStartLine().getTarget());
        } catch (HttpException ex) {
            status = ex.getStatus();
            payload = new ErrorPayload(status);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.addHeader("Server", "http-server");
        headers.addHeader("Content-Type", payload.getContentType());
        headers.addHeader("ETag", payload.getETag());

        if (payload.getETag().equals(msg.getHeaderValue("If-None-Match"))) {
            status = HttpStatusCode.NOT_MODIFIED;
            payload = new EmptyPayload();
        }

        HttpStatusLine statusLine = new HttpStatusLine(HttpVersion.HTTP_1_1, status);

        HttpResponse response = new HttpResponse(statusLine, headers, payload);
        out.add(response);
    }
}
