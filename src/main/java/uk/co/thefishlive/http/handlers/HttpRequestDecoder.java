package uk.co.thefishlive.http.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import uk.co.thefishlive.http.data.headers.HttpHeaders;
import uk.co.thefishlive.http.data.payload.EmptyPayload;
import uk.co.thefishlive.http.data.request.HttpRequest;
import uk.co.thefishlive.http.data.request.HttpRequestLine;

import java.util.*;

public class HttpRequestDecoder extends MessageToMessageDecoder<List<String>> {
    @Override
    protected void decode(ChannelHandlerContext ctx, List<String> msg, List<Object> out) throws Exception {
        if (msg.size() == 0) {
            return;
        }

        HttpRequestLine requestLine = new HttpRequestLine(msg.get(0));
        HttpHeaders headers = new HttpHeaders();

        for (int i = 1; i < msg.size(); i++) {
            try {
                String header = msg.get(i);

                if (header.equals("\n")) {
                    break;
                }

                headers.addHeader(msg.get(i));
            } catch (IllegalArgumentException ex) {
                // Swallow exception
            }
        }

        HttpRequest request = new HttpRequest(requestLine, headers, new EmptyPayload());

        out.add(request);
    }
}
