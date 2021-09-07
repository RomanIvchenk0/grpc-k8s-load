package com.github.romahat.load;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.dropwizard.lifecycle.Managed;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LoadService implements Managed {
    private final LoadClient loadClient;
    private Integer iterationCount;
    private Integer iterationLength;
    private final ScheduledThreadPoolExecutor scheduler;
    private ScheduledFuture<?> scheduledFuture;

    public LoadService(AppConfig config) {
        this(config.getUrl(), config.getPort(), config.getIterationCount(), config.getIterationLength());
    }

    public LoadService(String url, int port, Integer iterationCount, Integer iterationLength) {
        this.loadClient = new LoadClient(url, port);
        this.iterationCount = iterationCount;
        this.iterationLength = iterationLength;
        this.scheduler = new ScheduledThreadPoolExecutor(5,
                new ThreadFactoryBuilder().setNameFormat("app-query-scheduler-%d").build());
    }

    @Override
    public void start() throws Exception {
        this.scheduledFuture = scheduler.scheduleWithFixedDelay(new LoadTask(loadClient,
                        this.iterationCount,
                        this.iterationLength)
                , 1, 1, TimeUnit.SECONDS);
    }

    public void setIterationLength(Integer iterationLength) {
        this.scheduledFuture.cancel(true);
        this.iterationLength = iterationLength;
        this.scheduledFuture = scheduler.scheduleWithFixedDelay(new LoadTask(loadClient,
                        this.iterationCount,
                        this.iterationLength)
                , 1, 1, TimeUnit.SECONDS);
    }

    public void setIterationCount(Integer iterationCount) {
        this.scheduledFuture.cancel(true);
        this.iterationCount = iterationCount;
        this.scheduledFuture = scheduler.scheduleWithFixedDelay(new LoadTask(loadClient,
                        this.iterationCount,
                        this.iterationLength)
                , 1, 1, TimeUnit.SECONDS);
    }

    public Status status() {
        return Status.StatusBuilder.aStatus()
                .withIterationCount(iterationCount)
                .withIterationLength(iterationLength)
                .withQueueSize(scheduler.getQueue().size())
                .build();
    }

    @Override
    public void stop() throws Exception {
        scheduler.shutdown();
    }
}
