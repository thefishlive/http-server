package uk.co.thefishlive.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.co.thefishlive.http.config.ConfigManager;
import uk.co.thefishlive.http.config.server.ServerConfigFile;
import uk.co.thefishlive.http.handlers.*;

public class Server implements Runnable {

    private static final Logger logger = LogManager.getLogger();

    private int port;

    public Server(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            logger.info("Loading adapters");
                            int count = 0;

                            for (String adapter : ConfigManager.getConfig(ServerConfigFile.class).getAdapters()) {
                                Class<?> clazz = Server.class.getClassLoader().loadClass(adapter);
                                Object instance = clazz.newInstance();

                                if (!(instance instanceof ChannelHandler)) {
                                    throw new RuntimeException(String.format("Class %s is not a valid channel adapter", adapter));
                                }

                                logger.info("Loading adapter {}", adapter);
                                ch.pipeline().addLast((ChannelHandler) instance);
                                count++;
                            }

                            logger.info("Loaded {} adapters", count);
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(port).sync();
            logger.info("Listening on port {}", port);

            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
