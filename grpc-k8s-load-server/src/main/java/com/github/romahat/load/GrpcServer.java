package com.github.romahat.load;

import io.dropwizard.lifecycle.Managed;
import io.dropwizard.util.Duration;
import io.grpc.Server;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class GrpcServer implements Managed {
    private final static Logger LOGGER = LoggerFactory.getLogger(GrpcServer.class);
    
    private final Duration shutdownPeriod;
    private final Server server;

    public GrpcServer(AppConfig config) {
        this.server = NettyServerBuilder.forPort(config.getGrpcServerParameters().getPort())
                .permitKeepAliveWithoutCalls(true)
                .permitKeepAliveTime(20L, TimeUnit.SECONDS)
                .maxConnectionAge(config.getGrpcServerParameters().getMaxConnectionAgeSeconds(), TimeUnit.SECONDS)
                .maxConnectionIdle(config.getGrpcServerParameters().getMaxConnectionIdleSeconds(), TimeUnit.SECONDS)
                .keepAliveTime(config.getGrpcServerParameters().getKeepAliveTime(), TimeUnit.SECONDS)
                .maxInboundMessageSize(config.getGrpcServerParameters().getMaxInboundMessageSize())
                .addService(new LoadResource())
                //.addTransportFilter(new ActiveConnectionCounter())
                .build();
        this.shutdownPeriod = config.getGrpcServerParameters().getShutdownPeriod();
    }

    @Override
    public void start() throws Exception {
        LOGGER.info("Starting gRPC server");
        server.start();
        LOGGER.info("gRPC server started on port {}", server.getPort());
    }

    @Override
    public void stop() throws Exception {
        LOGGER.info("Stopping gRPC server on port {}", server.getPort());
        boolean isTerminatedCleanly =
                server.shutdown().awaitTermination(shutdownPeriod.getQuantity(), shutdownPeriod.getUnit());
        if (isTerminatedCleanly) {
            LOGGER.info("gRPC server stopped and terminated cleanly.");
        } else {
            LOGGER.info("gRPC server did not terminate cleanly after " + shutdownPeriod);
            LOGGER.info("Shutting down gRPC server forcefully.");
            server.shutdownNow();
        }
    }
}
