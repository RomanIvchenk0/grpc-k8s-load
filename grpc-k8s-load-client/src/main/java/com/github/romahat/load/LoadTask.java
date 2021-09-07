package com.github.romahat.load;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoadTask implements Runnable {
    private final static Logger LOGGER = LoggerFactory.getLogger(LoadTask.class);
    private final LoadClient loadClient;
    private final Integer iterationCount;
    private final Integer iterationLength;

    public LoadTask(LoadClient loadClient, Integer iterationCount, Integer iterationLength) {
        this.loadClient = loadClient;
        this.iterationCount = iterationCount;
        this.iterationLength = iterationLength;
    }

    @Override
    public void run() {
        try {
            LOGGER.info("Running load task, iterCount: {}, iterLength: {}", iterationCount, iterationLength);
            String result = loadClient.load(iterationCount, iterationLength);
            LOGGER.info("{}", result);
        } catch (Exception e) {
            LOGGER.error("Error running loadTask", e);
        }
    }
}
