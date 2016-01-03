package uk.co.thefishlive.http.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class HttpRequestDecoder extends MessageToMessageDecoder<List<String>> {
    @Override
    protected void decode(ChannelHandlerContext ctx, List<String> msg, List<Object> out) throws Exception {

    }
}
