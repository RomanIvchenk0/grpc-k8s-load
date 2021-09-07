package com.github.romahat.load;

import com.codahale.metrics.SharedMetricRegistries;
import io.grpc.Attributes;
import io.grpc.ServerTransportFilter;

import java.util.concurrent.atomic.AtomicInteger;

public class ActiveConnectionCounter extends ServerTransportFilter {
    private final AtomicInteger total;

    public ActiveConnectionCounter() {
        total = new AtomicInteger();
        SharedMetricRegistries.getDefault().gauge("activeConnectionsCounter", () -> total::get);
    }

    @Override
    public Attributes transportReady(Attributes transportAttrs) {
        total.incrementAndGet();
        return super.transportReady(transportAttrs);
    }

    @Override
    public void transportTerminated(Attributes transportAttrs) {
        total.decrementAndGet();
    }
}
