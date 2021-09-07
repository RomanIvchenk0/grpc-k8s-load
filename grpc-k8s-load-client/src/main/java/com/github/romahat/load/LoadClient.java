package com.github.romahat.load;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

public class LoadClient implements AutoCloseable {
    private final ManagedChannel channel;
    private final LoadGrpc.LoadBlockingStub stub;

    public LoadClient(Endpoint endpoint) {
        this(endpoint.getUrl(), endpoint.getPort());
    }

    public LoadClient(String url, int port) {
        this.channel = ManagedChannelBuilder.forAddress(url, port)
                .keepAliveTime(30, TimeUnit.SECONDS)
                .usePlaintext()
                .maxInboundMessageSize(400 * 1024 * 1024)
                .build();
        this.stub = LoadGrpc.newBlockingStub(channel);
    }

    public String load(int iterCount, int iterLength) {
        Parameters params = Parameters.newBuilder()
                .setIterationCount(iterCount)
                .setIterationLength(iterLength)
                .build();
        Reply reply = this.stub.load(params);
        return reply.getMessage();
    }

    @Override
    public void close() throws Exception {
        channel.shutdownNow();
        int retryCount = 0;
        while (!channel.isTerminated() && retryCount < 5) {
            channel.awaitTermination(1, TimeUnit.SECONDS);
            retryCount++;
        }
        if (!channel.isTerminated()) throw new IllegalStateException("Channel not closed!");
    }

    public static void main(String[] args) {
        LoadClient loadClient = new LoadClient(Endpoint.LOCAL);
        String load = loadClient.load(1000, 500);
        System.out.println(load);
    }
}
