package uk.co.thefishlive.http.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.ArrayList;
import java.util.List;

public class StringDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        StringBuilder builder = new StringBuilder();
        List<String> data = new ArrayList<>();

        while (in.isReadable()) {
            char c = (char) in.readByte();

            if (c == '\n') {
                data.add(builder.toString());
                builder = new StringBuilder();
            }

            builder.append(c);
        }

        out.add(data);
    }
}
