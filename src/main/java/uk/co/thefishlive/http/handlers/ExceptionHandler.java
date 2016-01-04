package uk.co.thefishlive.http.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import uk.co.thefishlive.http.data.HttpStatusCode;
import uk.co.thefishlive.http.data.HttpVersion;
import uk.co.thefishlive.http.data.headers.HttpHeaders;
import uk.co.thefishlive.http.data.payload.HttpPayload;
import uk.co.thefishlive.http.data.payload.StringPayload;
import uk.co.thefishlive.http.data.response.HttpResponse;
import uk.co.thefishlive.http.data.response.HttpStatusLine;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // Output error to the user
        StringWriter error = new StringWriter();
        error.append("<!DOCTYPE html>");
        error.append("<html>").append("\n");
        error.append("<h2>An internal server error has occurred</h2>").append("\n");
        error.append("Stack trace:\n");

        PrintWriter pw = new PrintWriter(error);
        cause.printStackTrace(pw);
        error.write("</html>");

        HttpPayload payload = new StringPayload(error.toString().replace("\n", "<br />\n").replace("\t", "&emsp;&emsp;"));

        HttpHeaders headers = new HttpHeaders();
        headers.addHeader("Server", "http-server");
        headers.addHeader("Content-Type", "text/html");
        headers.addHeader("ETag", payload.getETag());

        HttpStatusLine statusLine = new HttpStatusLine(HttpVersion.HTTP_1_1, HttpStatusCode.INTERNAL_SERVER_ERROR);

        HttpResponse response = new HttpResponse(statusLine, headers, payload);
        ByteBuf data = ctx.alloc().buffer();
        response.write(data);
        final ChannelFuture f = ctx.writeAndFlush(data);

        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                assert f == future;
                ctx.close();
            }
        });

        // Output error to log
        cause.printStackTrace();
    }
}
