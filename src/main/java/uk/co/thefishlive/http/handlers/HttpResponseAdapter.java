package uk.co.thefishlive.http.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import uk.co.thefishlive.http.data.response.HttpResponse;

public class HttpResponseAdapter extends SimpleChannelInboundHandler<HttpResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpResponse msg) throws Exception {
        System.out.println(msg);

        ByteBuf data = ctx.alloc().buffer();
        msg.write(data);
        final ChannelFuture f = ctx.writeAndFlush(data);

        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                assert f == future;
                ctx.close();
            }
        });
    }
}
