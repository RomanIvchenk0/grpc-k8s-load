package com.github.romahat.load;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.util.Duration;
import io.dropwizard.validation.MinDuration;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AppConfig extends Configuration {
    @Valid
    @NotNull
    private GrpcServerParameters grpcServer = new GrpcServerParameters();

    @JsonProperty("grpcServer")
    public GrpcServerParameters getGrpcServerParameters() {
        return grpcServer;
    }

    @JsonProperty("grpcServer")
    public void setGrpcServerParameters(final GrpcServerParameters grpcServer) {
        this.grpcServer = grpcServer;
    }

    public static class GrpcServerParameters {
        @Min(0)
        @Max(65535)
        private int port = 50051;

        @MinDuration(1)
        private Duration shutdownPeriod = Duration.seconds(5);

        @Min(600)
        private long maxConnectionAgeSeconds;

        @Min(300)
        private long maxConnectionIdleSeconds = 60 * 5;

        @Min(300)
        private long keepAliveTime = 60 * 5;

        @Min(1024 * 1024)
        private int maxInboundMessageSize = 128 * 1024 * 1024;

        @JsonProperty
        public int getPort() {
            return port;
        }

        @JsonProperty
        public void setPort(int port) {
            this.port = port;
        }

        @JsonProperty
        public Duration getShutdownPeriod() {
            return shutdownPeriod;
        }

        @JsonProperty
        public void setShutdownPeriod(Duration shutdownPeriod) {
            this.shutdownPeriod = shutdownPeriod;
        }

        @JsonProperty
        public long getMaxConnectionAgeSeconds() {
            return maxConnectionAgeSeconds;
        }

        @JsonProperty
        public void setMaxConnectionAgeSeconds(long maxConnectionAgeSeconds) {
            this.maxConnectionAgeSeconds = maxConnectionAgeSeconds;
        }

        @JsonProperty
        public long getMaxConnectionIdleSeconds() {
            return maxConnectionIdleSeconds;
        }

        @JsonProperty
        public void setMaxConnectionIdleSeconds(long maxConnectionIdleSeconds) {
            this.maxConnectionIdleSeconds = maxConnectionIdleSeconds;
        }

        @JsonProperty
        public long getKeepAliveTime() {
            return keepAliveTime;
        }

        @JsonProperty
        public void setKeepAliveTime(long keepAliveTime) {
            this.keepAliveTime = keepAliveTime;
        }

        @JsonProperty
        public int getMaxInboundMessageSize() {
            return maxInboundMessageSize;
        }

        @JsonProperty
        public void setMaxInboundMessageSize(int maxInboundMessageSize) {
            this.maxInboundMessageSize = maxInboundMessageSize;
        }
    }
}
