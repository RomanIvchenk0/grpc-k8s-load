package com.github.romahat.load;

public enum Endpoint {
    QA("grpc-k8s-load.qa", 50051),
    STAGING("grpc-k8s-load.staging", 50051),
    PROD("grpc-k8s-load.prod", 50051),
    LOCAL("localhost", 50051);

    private final String url;
    private final int port;

    Endpoint(String url, int port) {
        this.url = url;
        this.port = port;
    }

    public String getUrl() {
        return url;
    }

    public int getPort() {
        return port;
    }
}
